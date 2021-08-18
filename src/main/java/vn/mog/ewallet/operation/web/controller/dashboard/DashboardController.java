package vn.mog.ewallet.operation.web.controller.dashboard;


import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.contract.form.DashboardDataForm;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.controller.translog.TransactionLogController;


@Controller
@RequestMapping(value = "/service/dashboard")
public class DashboardController extends AbstractController {

  public static final String DASHBOARD_CONTROLLER = "/service/dashboard";
  public static final String DASHBOARD_LIST = DASHBOARD_CONTROLLER + "/index";
  private static final Logger LOG = LogManager.getLogger(DashboardController.class);

  @RequestMapping(value = "/index", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.FA_MANAGER + "','" + RoleConstants.FINANCE + "','" + RoleConstants.SALE_DIRECTOR + "','" + RoleConstants.MERCHANT + "','"
          + RoleConstants.CUSTOMER + "','"
          + RoleConstants.CUSTOMERCARE_MANAGER + "','" + RoleConstants.SALE_ASM + "','" + RoleConstants.TECH_SUPPORT + "','" + RoleConstants.STAFF + "','" + RoleConstants.SALESUPPORT + "','"
          + RoleConstants.SALESUPPORT_LEADER + "','"
          + RoleConstants.SALESUPPORT_MANAGER + "')")
  public String search(HttpServletRequest request, @ModelAttribute DashboardDataForm dashboardDataForm, ModelMap map) {

    // Tmp code
    return "redirect:" + TransactionLogController.TRANSACTION_LIST_ALL/*
        + "?" + TransactionLogController.TRANSACTION_DEFAULT_FILTER*/;

    /*
     * String paramServiceType =
     * StringUtils.trimToEmpty(dashboardDataForm.getType()); String
     * paramServiceCode =
     * StringUtils.trimToEmpty(dashboardDataForm.getService()); String
     * paramDateRange = StringUtils.trimToEmpty(dashboardDataForm.getRange());
     *
     * String[] paramCustomerIds =
     * request.getParameterValues("customerIds");
     *
     * Date today = new Date(); Date fromDate = Utils.getStartOfDay(today);
     * Date endDate = Utils.getEndOfDay(today);
     *
     * if (StringUtils.isNotBlank(paramDateRange)) { Date[] dates =
     * parseDateRange(paramDateRange); fromDate = dates[0]; endDate =
     * dates[1]; endDate = DateUtils.addDays(endDate, 1); }
     *
     * int offset = 0; int limit = 0;
     *
     * FindTransactionsRequest ffRequest = new FindTransactionsRequest();
     *
     * if (paramCustomerIds != null && paramCustomerIds.length > 0 &&
     * StringUtils.isNotEmpty(paramCustomerIds[0])) {
     * ffRequest.setCustomerIds(NumberUtil.convertListToLong(
     * paramCustomerIds)); }
     *
     * List<String> serviceCodes = new ArrayList<>(); if
     * (PAYMENT_TYPES.containsKey(paramServiceCode)) {
     * serviceCodes.add(paramServiceCode); } else {
     * serviceCodes.addAll(PAYMENT_TYPES.keySet()); }
     *
     * ffRequest.setStatusIds(Collections.singletonList(TxnStatus.CLOSED));
     * ffRequest.setServiceTypeIds(Collections.singletonList(
     * paramServiceType)); ffRequest.setServiceCodes(serviceCodes);
     * ffRequest.setFromDate(fromDate); ffRequest.setEndDate(endDate);
     * ffRequest.setOffset(offset); ffRequest.setLimit(limit);
     *
     * FindTransactionsResponse ffResponse =
     * transactionEndpoint.findTransactions(ffRequest);
     *
     * Collection<Transaction> transactions = null; Long total = 0L; Long
     * all = 0L; Long realAmount = 0L; Long commision = 0L; Long fee = 0L;
     *
     * if (ffResponse != null) { transactions =
     * ffResponse.getTransactions(); total = ffResponse.getTotalTxn() !=
     * null ? ffResponse.getTotalTxn() : 0; all = ffResponse.getAll() !=
     * null ? ffResponse.getAll() : 0; realAmount =
     * ffResponse.getTotalNetAmount() != null ?
     * ffResponse.getTotalNetAmount() : 0; commision =
     * ffResponse.getTotalCommision() != null ?
     * ffResponse.getTotalCommision() : 0; fee = ffResponse.getTotalFee() !=
     * null ? ffResponse.getTotalFee() : 0; }
     *
     * Double rate = null; if (all != null && all != 0) { rate = (total *
     * 1.0) / (all * 1.0); }
     *
     * map.put("transactions", transactions); map.put("total", total);
     * map.put("rate", rate); map.put("realAmount", realAmount);
     * map.put("commision", commision); map.put("fee", fee);
     *
     * map.put("customers", cacheDataUtil.getCustomersByBiz());
     * map.put("serviceTypes", PAYMENT_TYPES); map.put("status",
     * TRANSACTION_STATUSES);
     *
     * return "/dashboard/index";
     */
  }
}
