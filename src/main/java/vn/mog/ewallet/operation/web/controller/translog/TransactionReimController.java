package vn.mog.ewallet.operation.web.controller.translog;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.WalletTransferOrderFlowStage;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.TransactionReimRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.TransactionReimResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.TransactionCancel;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.FindFullCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Customer;
import vn.mog.framework.common.utils.NumberUtil;


/**
 * Created by duongnh on 30/11/2017.
 */
@Controller
@RequestMapping(value = "/service/reim-history")
public class TransactionReimController extends AbstractController {

  public static final String TRANS_REIM_CONTROLLER = "/service/reim-history";
  public static final String TRANS_REIM_LIST = TRANS_REIM_CONTROLLER + "/list";
  public static final String TRANS_REIM_DETAIL = TRANS_REIM_CONTROLLER + "/detail";
  public static final String TRANS_REIM_LIST_TXN_REQUEST = TRANS_REIM_CONTROLLER + "/listReimTxnRequest";

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "')")
  public String search(HttpServletRequest request, ModelMap map) throws FrontEndException {
    String quickSearch = StringUtils.trimToEmpty(request.getParameter("search"));
    String paramDateRange = StringUtils.trimToEmpty(request.getParameter("range"));
    String[] paramPayerId = request.getParameterValues("sourceAccount");
    String[] paramPayeeId = request.getParameterValues("targetAccount");
    // String[] status = request.getParameterValues("procces");

    TransactionReimRequest fwtRequest = new TransactionReimRequest();
    Date[] dates = parseDateRange(paramDateRange, true);
    fwtRequest.setFromDate(dates[0]);
    fwtRequest.setEndDate(dates[1]);

    int offset = 0;
    int limit = 20;
    if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
      offset = Integer.parseInt(request.getParameter("d-49489-p"));
      if (offset > 0) {
        offset = (offset - 1) * limit;
      }
    }

    if (paramPayerId != null && paramPayerId.length > 0 && StringUtils.isNotEmpty(paramPayerId[0])) {
      fwtRequest.setPayerIds(NumberUtil.convertListToLong(paramPayerId));
    }

    if (paramPayeeId != null && paramPayeeId.length > 0 && StringUtils.isNotEmpty(paramPayeeId[0])) {
      fwtRequest.setPayeeIds(NumberUtil.convertListToLong(paramPayeeId));
    }

    // if (status != null && status.length > 0 &&
    // StringUtils.isNotEmpty(status[0])) {
    // fwtRequest.setStages(NumberUtil.convertListToInt(status));
    // }

    fwtRequest.setSearchText(quickSearch);
    fwtRequest.setLimit(limit);
    fwtRequest.setOffset(offset);

    TransactionReimResponse fwtResponse = walletEndpoint.findReim(fwtRequest);

    List<TransactionCancel> tmp = new ArrayList<>();
    tmp.add(new TransactionCancel(1L, new Date(), "service0", "merchant0", "requester0", 1L, 1L, 1L, 1L, 1L, 0, "approver0", 0));
    tmp.add(new TransactionCancel(1L, new Date(), "service1", "merchant1", "requester1", 1L, 1L, 1L, 1L, 1L, 1, "approver1", 1));
    tmp.add(new TransactionCancel(1L, new Date(), "service2", "merchant2", "requester2", 1L, 1L, 1L, 1L, 1L, 2, "approver2", 2));

    // static
    map.put("statuses", TransactionCancel.Status.list());
    // map.put("processes", TransactionCancel.Process.list());
    map.put("transCancels", tmp);
    map.put("pagesize", limit);
    map.put("offset", offset);
    map.put("total", tmp.size());
    List<Customer> sourceMerchants = getCustomers(CustomerType.ID_POOL, CustomerType.ID_ZOTA);
    List<Customer> targetMerchants = getCustomers(CustomerType.ID_SOF, CustomerType.ID_ZOTA);
    map.put("sourceMerchants", sourceMerchants);
    map.put("targetMerchants", targetMerchants);

    return "/transaction_reim/transaction-reim";
  }

  @RequestMapping(value = "/listReimTxnRequest", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "')")
  public String searchReimTXNRequest(HttpServletRequest request, ModelMap map) throws FrontEndException {

    String quickSearch = StringUtils.trimToEmpty(request.getParameter("search"));
    String paramDateRange = StringUtils.trimToEmpty(request.getParameter("range"));
    String[] paramPayerId = request.getParameterValues("sourceAccount");
    String[] paramPayeeId = request.getParameterValues("targetAccount");
    String[] processStatus = request.getParameterValues("process");

    TransactionReimRequest fwtRequest = new TransactionReimRequest();
    Date[] dates = parseDateRange(paramDateRange, true);
    fwtRequest.setFromDate(dates[0]);
    fwtRequest.setEndDate(dates[1]);

    int offset = 0;
    int limit = 20;
    if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
      offset = Integer.parseInt(request.getParameter("d-49489-p"));
      if (offset > 0) {
        offset = (offset - 1) * limit;
      }
    }

    if (paramPayerId != null && paramPayerId.length > 0 && StringUtils.isNotEmpty(paramPayerId[0])) {
      fwtRequest.setPayerIds(NumberUtil.convertListToLong(paramPayerId));
    }

    if (paramPayeeId != null && paramPayeeId.length > 0 && StringUtils.isNotEmpty(paramPayeeId[0])) {
      fwtRequest.setPayeeIds(NumberUtil.convertListToLong(paramPayeeId));
    }

    // if (processStatus != null && processStatus.length > 0 &&
    // StringUtils.isNotEmpty(processStatus[0])) {
    // fwtRequest.setStages(NumberUtil.convertListToInt(processStatus));
    // }

    fwtRequest.setSearchText(quickSearch);
    fwtRequest.setLimit(limit);
    fwtRequest.setOffset(offset);

    TransactionReimResponse fwtResponse = walletEndpoint.findReim(fwtRequest);

    List<TransactionCancel> tmp = new ArrayList<>();
    tmp.add(new TransactionCancel(1L, new Date(), "service0", "merchant0", "requester0", 1L, 1L, 1L, 1L, 1L, 0, "approver0", 0));
    tmp.add(new TransactionCancel(1L, new Date(), "service1", "merchant1", "requester1", 1L, 1L, 1L, 1L, 1L, 1, "approver1", 1));
    tmp.add(new TransactionCancel(1L, new Date(), "service2", "merchant2", "requester2", 1L, 1L, 1L, 1L, 1L, 2, "approver2", 2));

    // static
    // map.put("statuses", TransactionCancel.Status.list());
    // map.put("processes", TransactionCancel.Process.list());
    map.put("transCancels", tmp);
    map.put("pagesize", limit);
    map.put("offset", offset);
    map.put("total", tmp.size());
    map.put("walletTransferStages", WalletTransferOrderFlowStage.WALLET_TRANSFER_STAGES);
    List<Customer> sourceMerchants = getCustomers(CustomerType.ID_POOL, CustomerType.ID_ZOTA);
    List<Customer> targetMerchants = getCustomers(CustomerType.ID_SOF, CustomerType.ID_ZOTA);
    map.put("sourceMerchants", sourceMerchants);
    map.put("targetMerchants", targetMerchants);

    return "/transaction_reim/transaction-reim-txn-request";
  }

  @RequestMapping(value = "/request", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "')")
  public String requestCancel(HttpServletRequest request, ModelMap map) throws FrontEndException {

    List<Customer> sourceMerchants = getCustomers(CustomerType.ID_POOL, CustomerType.ID_ZOTA);
    List<Customer> targetMerchants = getCustomers(CustomerType.ID_SOF, CustomerType.ID_ZOTA);
    map.put("sourceMerchants", sourceMerchants);
    map.put("targetMerchants", targetMerchants);

    return "/transaction_reim/transaction-reim-request";
  }

  @RequestMapping(value = "/request/verify", method = RequestMethod.POST)
  @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "')")
  public String requestCancelSend(HttpServletRequest request, ModelMap map) throws FrontEndException {
    return "/transaction_reim/reim_verify";
  }

  @RequestMapping(value = "/request/otp", method = RequestMethod.POST)
  @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "')")
  public String requestCancelSendOTP(HttpServletRequest request, ModelMap map) throws FrontEndException {
    return "/transaction_reim/reim_otp";
  }

  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "')")
  public String detail(HttpServletRequest request, ModelMap map) throws FrontEndException {
    String quickSearch = StringUtils.trimToEmpty(request.getParameter("search"));
    String paramDateRange = StringUtils.trimToEmpty(request.getParameter("range"));
    String[] paramPayerId = request.getParameterValues("sourceAccount");
    String[] paramPayeeId = request.getParameterValues("targetAccount");
    String[] processStatus = request.getParameterValues("process");

    TransactionReimRequest fwtRequest = new TransactionReimRequest();
    Date[] dates = parseDateRange(paramDateRange, true);
    fwtRequest.setFromDate(dates[0]);
    fwtRequest.setEndDate(dates[1]);

    int offset = 0;
    int limit = 20;
    if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
      offset = Integer.parseInt(request.getParameter("d-49489-p"));
      if (offset > 0) {
        offset = (offset - 1) * limit;
      }
    }

    if (paramPayerId != null && paramPayerId.length > 0 && StringUtils.isNotEmpty(paramPayerId[0])) {
      fwtRequest.setPayerIds(NumberUtil.convertListToLong(paramPayerId));
    }

    if (paramPayeeId != null && paramPayeeId.length > 0 && StringUtils.isNotEmpty(paramPayeeId[0])) {
      fwtRequest.setPayeeIds(NumberUtil.convertListToLong(paramPayeeId));
    }

    if (processStatus != null && processStatus.length > 0 && StringUtils.isNotEmpty(processStatus[0])) {
      fwtRequest.setStages(NumberUtil.convertListToInt(processStatus));
    }

    fwtRequest.setSearchText(quickSearch);
    fwtRequest.setLimit(limit);
    fwtRequest.setOffset(offset);

    TransactionReimResponse fwtResponse = walletEndpoint.findReim(fwtRequest);

    List<TransactionCancel> tmp = new ArrayList<>();
    tmp.add(new TransactionCancel(1L, new Date(), "service0", "merchant0", "requester0", 1L, 1L, 1L, 1L, 1L, 0, "approver0", 0));
    tmp.add(new TransactionCancel(1L, new Date(), "service1", "merchant1", "requester1", 1L, 1L, 1L, 1L, 1L, 1, "approver1", 1));
    tmp.add(new TransactionCancel(1L, new Date(), "service2", "merchant2", "requester2", 1L, 1L, 1L, 1L, 1L, 2, "approver2", 2));

    // static
    map.put("reimDetail", tmp);
    // map.put("statuses", TransactionCancel.Status.list());
    // map.put("processes", TransactionCancel.Process.list());
    map.put("pagesize", limit);
    map.put("offset", offset);
    map.put("total", tmp.size());

    List<Customer> sourceMerchants = getCustomers(CustomerType.ID_POOL, CustomerType.ID_ZOTA);
    List<Customer> targetMerchants = getCustomers(CustomerType.ID_SOF, CustomerType.ID_ZOTA);

    map.put("sourceMerchants", sourceMerchants);
    map.put("targetMerchants", targetMerchants);

    return "/transaction_reim/transaction-reim-detail";
  }

  private List<Customer> getCustomers(Integer... customerTypeIds) {
    try {
      FindFullCustomerRequest customerRequest = new FindFullCustomerRequest();
      customerRequest.setCustomerTypes(Arrays.asList(customerTypeIds));
      return customerEndpoint.findCustomers(customerRequest).getCustomers();
    } catch (Exception e) {
      return Collections.emptyList();
    }
  }
}
