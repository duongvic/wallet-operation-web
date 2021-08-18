package vn.mog.ewallet.operation.web.controller.statement;


import java.util.Collections;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.contract.form.CustomerDataForm;
import vn.mog.ewallet.operation.web.contract.form.SearchDataForm;
import vn.mog.ewallet.operation.web.controller.account.AbstractAccountController;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.FindUmgrCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.FindUmgrCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.*;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.ExportExcelFileCustomerStatementRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.ExportExcelFileCustomerStatementResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.GetStatementOfCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.GetStatementOfCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.StatementAttachment;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.TxnStatus;
import vn.mog.ewallet.operation.web.utils.WebUtil;
import vn.mog.framework.common.utils.NumberUtil;

@Controller
@RequestMapping(value = "")
public class StatementController extends AbstractAccountController {

  public static final String STATEMENT_LIST = "/wallet/user/statement/list";
  public static final String STATEMENT_USER_LIST = "/wallet/user/statement/user-trans/list";

  private static final Logger LOG = LogManager.getLogger(StatementController.class);

  @GetMapping(value = "/wallet/user/statement/list")
  @PreAuthorize(value = "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.STAFF
      + "')")
  public String statementList(HttpServletRequest request, ModelMap model,
      @ModelAttribute CustomerDataForm customerDataForm) throws Exception {

    FindUmgrCustomerRequest customerReq = new FindUmgrCustomerRequest();
    customerReq.setQuickSearch(customerDataForm.getTextSearch());
    customerReq.setCustomerTypes(customerDataForm.getCustomerTypes());
    customerReq.setWalletTypeIds(Collections.singletonList(WalletType.WALLET));
    customerReq.setUserTypeIds(Collections.singletonList(UserType.USER));
    customerReq.setBlacklistReasons(customerDataForm.getBlackLists());

    FindUmgrCustomerResponse customerRes = umgrRolesPrivilegesEndpoint
        .findUmgrCustomers(customerReq);
    int offset = 0;
    int limit = 20;

    if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
      offset = Integer.parseInt(request.getParameter("d-49489-p"));
      if (offset > 0) {
        offset = (offset - 1) * limit;
      }
    }

    model.put("list", customerRes.getCustomers());
    model.put("pagesize", limit);
    model.put("offset", offset);
    model.put("total", (int) customerRes.getTotal());

    model.put("textSearch", customerDataForm.getTextSearch());

    //get blackList
    getBlackListReason(model);

    return "/statement/list_user";
  }

  @GetMapping(value = "/wallet/user/statement/user-trans/list/list")
  @PreAuthorize(value = "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.STAFF
      + "')")
  public String serchTransactionUser1(ModelMap model, HttpServletRequest request,
      @ModelAttribute SearchDataForm searchDataForm) throws Exception {
    String customerId = request.getParameter("customerId");
    return serchTransactionUser(Long.parseLong(customerId), model, request, searchDataForm);
  }

  @GetMapping(value = "/wallet/user/statement/user-trans/list/{customerId}")
  @PreAuthorize(value = "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.STAFF
      + "')")
  public String serchTransactionUser(@PathVariable Long customerId, ModelMap model,
      HttpServletRequest request,
      @ModelAttribute SearchDataForm searchDataForm) throws Exception {

    Date[] dates = parseDateRange(searchDataForm.getRange(), false);

    int offset = 0;
    int limit = 20;

    if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
      offset = Integer.parseInt(request.getParameter("d-49489-p"));
      if (offset > 0) {
        offset = (offset - 1) * limit;
      }
    }

    GetStatementOfCustomerRequest userTransReq = new GetStatementOfCustomerRequest();
    userTransReq.setFromDate(dates[0]);
    userTransReq.setEndDate(dates[1]);
    userTransReq.setCustomerIds(Collections.singletonList(customerId));
    userTransReq.setSourceOfFundTypeIds(searchDataForm.getSourceOfFundTypeIds());
    userTransReq.setStatusIds(searchDataForm.getTxnStatusIds());
    userTransReq.setPayeeIds(searchDataForm.getPayeeIds());
    userTransReq.setPayerIds(searchDataForm.getPayerIds());
    userTransReq.setServiceTypeIds(searchDataForm.getServiceTypeIds());
    userTransReq.setIsActorPayer(searchDataForm.isActorPayer());
    userTransReq.setOffset(offset);
    userTransReq.setLimit(limit);

    GetStatementOfCustomerResponse userTransRes = transactionEndpoint.statementUser(userTransReq);

    if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
      offset = Integer.parseInt(request.getParameter("d-49489-p"));
      if (offset > 0) {
        offset = (offset - 1) * limit;
      }
    }

    Long total = 0L;
    long realAmount = 0L;
    long commision = 0L;
    long capitalValue = 0L;

    if (userTransRes != null) {
      total = userTransRes.getTotalTxn();
      realAmount = userTransRes.getTotalNetAmount();
      commision = userTransRes.getTotalCommision();
      capitalValue = userTransRes.getTotalCapitalValue();
    }

    model.put("transactions", userTransRes.getTransactions());
    model.put("total", total.intValue());
    model.put("offset", offset);
    model.put("pagesize", limit);
    model.put("totalCapitalValue", capitalValue);
    model.put("realAmount", realAmount);
    model.put("commision", commision);
    model.put("customerId", customerId);

    // list merchant
    model.put("serviceTypes", ServiceType.FULL_SERVICE_TYPES);
    model.put("txnStatuses", TxnStatus.TRANSACTION_STATUSES);
    model.put("providerTypes", ProviderCode.PAYMENT_PROVIDER_CODES);
    model.put("sourceOfFundTypes", SourceOfFundType.SOF_ID_TYPES);

    return "/statement/transaction_export";
  }

  @RequestMapping(value = "/wallet/user/statement/user-trans/list/export", method = RequestMethod.GET)
  @PreAuthorize("isAuthenticated()")
  public void export(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute SearchDataForm searchDataForm) throws Exception {
    try {
      Date[] dates = parseDateRange(searchDataForm.getRange(), true);

      ExportExcelFileCustomerStatementRequest exportStaRes = new ExportExcelFileCustomerStatementRequest();
      exportStaRes.setFromDate(dates[0]);
      exportStaRes.setEndDate(dates[1]);
      exportStaRes.setCustomerIds(
          Collections.singletonList(NumberUtil.convertToLong(request.getParameter("customerId"))));
      exportStaRes.setSourceOfFundTypeIds(searchDataForm.getSourceOfFundTypeIds());
      exportStaRes.setProviderCodes(searchDataForm.getProvider());
      exportStaRes.setServiceTypeIds(searchDataForm.getServiceTypeIds());
      exportStaRes.setIsActorPayer(searchDataForm.isActorPayer());

      ExportExcelFileCustomerStatementResponse etlResponse = transactionEndpoint
          .exportStatementUser(exportStaRes);
      StatementAttachment attachment = new StatementAttachment();
      if (etlResponse != null && etlResponse.getStatus().getCode() == 0) {
        attachment = etlResponse.getAttachment();
        WebUtil.exportFile(response, attachment);
      }

    } catch (Exception ex) {
      LOG.error(ex.getMessage());
    }
  }
}
