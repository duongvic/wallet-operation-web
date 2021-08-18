package vn.mog.ewallet.operation.web.controller.fundin;


import static vn.mog.ewallet.operation.web.constant.RoleConstants.AGGREATOR;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.BILLER;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.CUSTOMERCARE;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.CUSTOMERCARE_MANAGER;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.RECONCILIATION;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.RECONCILIATION_LEADER;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.SALESUPPORT;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.SALESUPPORT_MANAGER;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.SALE_ASM;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.SALE_DIRECTOR;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.SALE_EXCUTIVE;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.SALE_RSM;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.SALE_SUPERVISOR;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.CUSTOMER;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.MERCHANT;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.TELCO;

import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.contract.form.FundInOutForm;
import vn.mog.ewallet.operation.web.contract.form.SearchDataForm;
import vn.mog.ewallet.operation.web.controller.translog.AbstractTransactionController;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.GetTransactionRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.GetTransactionResponse;
import vn.mog.framework.common.utils.NumberUtil;


@Controller
@RequestMapping(value = "/wallet/fund-in-history")
public class FundInController extends AbstractTransactionController {

  public static final String FUND_IN_HISTORY_CONTROLLER = "/wallet/fund-in-history";
  public static final String FUND_IN_HISTORY_LIST = FUND_IN_HISTORY_CONTROLLER + "/list";
  public static final String FUND_IN_HISTORY_DETAIL = FUND_IN_HISTORY_CONTROLLER + "/detail";
  public static final String FUND_IN_HISTORY_DEFAULT_FILTER = "txnStatusIds=10";

  private static final Logger LOG = LogManager.getLogger(FundInController.class);

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + MERCHANT + "','" + CUSTOMER + "','"
          + TELCO + "','" + AGGREATOR + "','" + BILLER + "','" + RoleConstants.FINANCE_LEADER
          + "','"
          + RoleConstants.FINANCE + "','"
          + RoleConstants.FA_MANAGER + "','" + SALESUPPORT + "','" + SALESUPPORT_MANAGER
          + "','" + SALE_DIRECTOR + "','" + SALE_EXCUTIVE + "','" + SALE_SUPERVISOR + "','"
          + SALE_ASM
          + "','" + SALE_RSM + "','" + RECONCILIATION + "','" + RECONCILIATION_LEADER + "','"
          + SALESUPPORT_MANAGER + "' , '" + SALESUPPORT + "' ,'"+CUSTOMERCARE_MANAGER+"','"+CUSTOMERCARE+"')")
  public String search(HttpServletRequest request, ModelMap map,
      @ModelAttribute FundInOutForm fundInOutForm) {

    searchFundinTrans(request, map, fundInOutForm);

    return "/fundin/fund_in";
  }

  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + MERCHANT + "','" + CUSTOMER + "','"
          + TELCO + "','" + AGGREATOR + "','" + BILLER + "','" + RoleConstants.FINANCE_LEADER
          + "','"
          + RoleConstants.FINANCE + "','"
          + RoleConstants.FA_MANAGER + "','" + SALESUPPORT + "','" + SALESUPPORT_MANAGER
          + "','" + SALE_DIRECTOR + "','" + SALE_EXCUTIVE + "','" + SALE_SUPERVISOR + "','"
          + SALE_ASM
          + "','" + SALE_RSM + "','" + RECONCILIATION + "','" + RECONCILIATION_LEADER + "','"
          + SALESUPPORT_MANAGER + "' , '" + SALESUPPORT + "','"+CUSTOMERCARE_MANAGER+"','"+CUSTOMERCARE+"')")
  public String detail(HttpServletRequest request, ModelMap map) {

    Long txnId = NumberUtil.convertToLong(request.getParameter("txnId"));
    String searchText = StringUtils.trimToEmpty(request.getParameter("search"));
    String[] txnStatusIds = request.getParameterValues("txnStatusIds");
    String searchType = StringUtils.trimToEmpty(request.getParameter("type"));
    String[] customerIds = request.getParameterValues("customerIds");
    String dateRange = StringUtils.trimToEmpty(request.getParameter("range"));
    String page = StringUtils.trimToEmpty(request.getParameter("d-149386-p"));

    GetTransactionRequest gtxnRequest = new GetTransactionRequest(txnId);
    GetTransactionResponse gtxnResponse = transactionEndpoint.getTransaction(gtxnRequest);

    map.put("transaction", gtxnResponse.getTransaction());
    map.put("transactionEvents", gtxnResponse.getTransactionEvents());
    map.put("search", searchText);
    map.put("customerIds", customerIds);
    map.put("txnStatusIds", txnStatusIds);
    map.put("type", searchType);
    map.put("range", dateRange);
    map.put("numberPage", page);

    return "/fundin/fund_in_detail";
  }

  @RequestMapping(value = "/export", method = RequestMethod.GET)
  @PreAuthorize("isAuthenticated()")
  public void export(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute SearchDataForm searchDataForm) throws Exception {
    searchDataForm.setServiceTypeIds(Collections.singletonList(ServiceType.FUND_IN.name()));
    super.export(request, response, searchDataForm);
  }
}
