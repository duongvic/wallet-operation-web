package vn.mog.ewallet.operation.web.controller.reconciliation;

import static vn.mog.ewallet.operation.web.constant.RoleConstants.CUSTOMERCARE_MANAGER;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.FA_MANAGER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.ReconcileTransactionExportRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.ReconcileTransactionExportResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.ReconcileTransactionFindRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation.ReconcileTransactionFindResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.LogFile;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.Transaction;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.impl.WalletEndpoint;
import vn.mog.ewallet.operation.web.utils.WebUtil;

@Controller
@RequestMapping(value = "/wallet/balance-deduction-reconcilation-report/v1")
public class ReconciliationV1Controller extends AbstractController {

  public static final String RECON_CONTROLLER = "/wallet/balance-deduction-reconcilation-report/v1";
  public static final String RECON_LIST_ALL = RECON_CONTROLLER + "/list-all";
  public static final String RECON_LIST = RECON_CONTROLLER + "/list";
  public static final String RECON_EXPORT = RECON_CONTROLLER + "/export";
  public static final String RECON_DETAIL = RECON_CONTROLLER + "/detail";
  private static final Logger LOG = LogManager.getLogger(ReconciliationV2Controller.class);

  @Autowired
  WalletEndpoint walletEndpoint;

  @RequestMapping(value = "/list-all", method = RequestMethod.GET)
  @PreAuthorize("isAuthenticated()")
  public String searchAll(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws Exception {
    return searchReconciliation(request, response, map);
  }

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + "','"
          + RoleConstants.RECONCILIATION_LEADER
          + "','"
          + RoleConstants.RECONCILIATION
          + "' ,'"
          + CUSTOMERCARE_MANAGER
          + "' , '"
          + FA_MANAGER
          + "')")
  public String searchReconciliation(
      HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws FrontEndException {
    try {
      String paramTextSearch = request.getParameter("quickSearch");
      String paramDateRange = request.getParameter("range");
      String[] paramIdOwnerCustomer = request.getParameterValues("idOwnerCustomerTypes");
      Date[] dates = parseDateRange(paramDateRange, false);
      Date fromDate = dates[0];
      Date endDate = dates[1];
      int offset = 0;
      int limit = 20;

      ReconcileTransactionFindRequest reconRequest = new ReconcileTransactionFindRequest();
      reconRequest.setTextSearch(paramTextSearch);
      reconRequest.setFromDate(fromDate);
      reconRequest.setEndDate(endDate);

      List<String> idOwnerCustomerTypes = null;
      if (paramIdOwnerCustomer != null && paramIdOwnerCustomer.length > 0) {
        idOwnerCustomerTypes = new ArrayList<>();
        idOwnerCustomerTypes.addAll(Arrays.asList(paramIdOwnerCustomer));
        idOwnerCustomerTypes.remove(null);
        idOwnerCustomerTypes.remove(StringUtils.EMPTY);
      }

      reconRequest.setIdOwnerCustomerTypes(idOwnerCustomerTypes);
      reconRequest.setLimit(limit);
      reconRequest.setOffset(offset);

      ReconcileTransactionFindResponse findResponse = walletEndpoint.findReconci(reconRequest);

      Collection<Transaction> transactions = null;
      Long total = 0L;
      Long firstPreBalance = 0L;
      Long lastPostBalance = 0L;
      Long totalCashIn = 0L;
      Long totalCashOut = 0L;
      Long endBalance = 0L;

      if (findResponse != null && findResponse.getStatus().getCode() == 0) {
        transactions = findResponse.getTransactions();
        total = findResponse.getTotalTxn() == null ? 0 : findResponse.getTotalTxn();

        firstPreBalance =
            findResponse.getFirstPreBalance() == null ? 0 : findResponse.getFirstPreBalance();
        lastPostBalance =
            findResponse.getLastPostBalance() == null ? 0 : findResponse.getLastPostBalance();
        totalCashIn = findResponse.getTotalCashIn() == null ? 0 : findResponse.getTotalCashIn();
        totalCashOut = findResponse.getTotalCashOut() == null ? 0 : findResponse.getTotalCashOut();
        endBalance = findResponse.getEndBalance() == null ? 0 : findResponse.getEndBalance();
      }
      map.put("quickSearch", paramTextSearch);
      map.put("range", paramDateRange);
      map.put("transactions", transactions);
      map.put("pagesize", limit);
      map.put("offset", offset);
      map.put("total", total.intValue());
      map.put("firstPreBalance", firstPreBalance);
      map.put("lastPostBalance", lastPostBalance);
      map.put("totalCashIn", totalCashIn);
      map.put("totalCashOut", totalCashOut);
      map.put("endBalance", endBalance);
    } catch (Exception ex) {
      LOG.error(ex.getMessage());
    }

    return "/reconciliation/reconciliation-logs";
  }

  @RequestMapping(value = "/export", method = RequestMethod.GET)
  @PreAuthorize("isAuthenticated()")
  public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
    try {
      String paramTextSearch = StringUtils.trimToEmpty(request.getParameter("quickSearch"));
      String paramDateRange = request.getParameter("range");
      String[] paramIdOwnerCustomer = request.getParameterValues("idOwnerCustomerTypes");
      Date[] dates = parseDateRange(paramDateRange, true);
      Date fromDate = dates[0];
      Date endDate = dates[1];

      ReconcileTransactionExportRequest reconExportRequest =
          new ReconcileTransactionExportRequest();
      List<String> idOwnerCustomerTypes = null;
      if (paramIdOwnerCustomer != null && paramIdOwnerCustomer.length > 0) {
        idOwnerCustomerTypes = new ArrayList<>();
        idOwnerCustomerTypes.addAll(Arrays.asList(paramIdOwnerCustomer));
        idOwnerCustomerTypes.remove(null);
        idOwnerCustomerTypes.remove(StringUtils.EMPTY);
      }

      reconExportRequest.setIdOwnerCustomerTypes(idOwnerCustomerTypes);
      reconExportRequest.setTextSearch(paramTextSearch);
      reconExportRequest.setFromDate(fromDate);
      reconExportRequest.setEndDate(endDate);
      reconExportRequest.setOffset(null);
      reconExportRequest.setLimit(null);

//      ReconcileTransactionExportResponse reconExportResponse =
//          walletEndpoint.exportReconci(reconExportRequest);
      ReconcileTransactionExportResponse reconExportResponse =
          walletEndpoint.findReconciCustomerSummaryExport(reconExportRequest);
      if (reconExportResponse != null && reconExportResponse.getStatus().getCode() == 0) {
        LogFile logFile = reconExportResponse.getLogFile();
        WebUtil.exportFile(response, logFile);
      } else {
        WebUtil.exportFile(response, null);
      }

    } catch (HttpClientErrorException | HttpServerErrorException e) {
      String str = e.getResponseBodyAsString();
      LOG.error(str, e);
    } catch (Exception ex) {
      LOG.error(ex.getMessage());
    }
  }

  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + "','"
          + RoleConstants.RECONCILIATION_LEADER
          + "','"
          + RoleConstants.RECONCILIATION
          + "' ,'"
          + CUSTOMERCARE_MANAGER
          + "' , '"
          + FA_MANAGER
          + "')")
  public String detailReconciliation(
      HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws FrontEndException {
    try {
      String paramTextSearch = request.getParameter("quickSearch");
      String paramDateRange = request.getParameter("range");
      String[] paramIdOwnerCustomer = request.getParameterValues("idOwnerCustomerTypes");

      Date[] dates = parseDateRange(paramDateRange, true);
      Date fromDate = dates[0];
      Date endDate = dates[1];

      int offset = 0;
      int limit = 20;

      if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
        offset = Integer.parseInt(request.getParameter("d-49489-p"));
        if (offset > 0) {
          offset = (offset - 1) * limit;
        }
      }

      ReconcileTransactionFindRequest reconRequest = new ReconcileTransactionFindRequest();

      reconRequest.setTextSearch(paramTextSearch);
      reconRequest.setFromDate(fromDate);
      reconRequest.setEndDate(endDate);

      List<String> idOwnerCustomerTypes = null;
      if (paramIdOwnerCustomer != null && paramIdOwnerCustomer.length > 0) {
        idOwnerCustomerTypes = new ArrayList<>();
        idOwnerCustomerTypes.addAll(Arrays.asList(paramIdOwnerCustomer));
        idOwnerCustomerTypes.remove(null);
        idOwnerCustomerTypes.remove(StringUtils.EMPTY);
      }

      reconRequest.setIdOwnerCustomerTypes(idOwnerCustomerTypes);
      reconRequest.setLimit(limit);
      reconRequest.setOffset(offset);

      ReconcileTransactionFindResponse findResponse = walletEndpoint.findReconci(reconRequest);

      Collection<Transaction> transactions = null;
      Long total = 0L;
      Long firstPreBalance = 0L;
      Long lastPostBalance = 0L;
      Long totalCashIn = 0L;
      Long totalCashOut = 0L;
      Long endBalance = 0L;

      if (findResponse != null && findResponse.getStatus().getCode() == 0) {
        transactions = findResponse.getTransactions();
        total = findResponse.getTotalTxn() == null ? 0 : findResponse.getTotalTxn();

        firstPreBalance =
            findResponse.getFirstPreBalance() == null ? 0 : findResponse.getFirstPreBalance();
        lastPostBalance =
            findResponse.getLastPostBalance() == null ? 0 : findResponse.getLastPostBalance();
        totalCashIn = findResponse.getTotalCashIn() == null ? 0 : findResponse.getTotalCashIn();
        totalCashOut = findResponse.getTotalCashOut() == null ? 0 : findResponse.getTotalCashOut();
        endBalance = findResponse.getEndBalance() == null ? 0 : findResponse.getEndBalance();
      }

      map.put("quickSearch", paramTextSearch);
      map.put("range", paramDateRange);
      map.put("transactions", transactions);
      map.put("pagesize", limit);
      map.put("offset", offset);
      map.put("total", total.intValue());

      map.put("firstPreBalance", firstPreBalance);
      map.put("lastPostBalance", lastPostBalance);
      map.put("totalCashIn", totalCashIn);
      map.put("totalCashOut", totalCashOut);
      map.put("endBalance", endBalance);
    } catch (Exception ex) {
      LOG.error(ex.getMessage());
    }

    return "/reconciliation/reconciliation-logs";
  }
}
