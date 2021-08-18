package vn.mog.ewallet.operation.web.controller.fundout;

import static org.apache.commons.httpclient.auth.CredentialsProvider.PROVIDER;

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
@RequestMapping(value = "/wallet/fund-out-history")
public class FundOutController extends AbstractTransactionController {

  public static final String FUND_OUT_HISTORY_CONTROLLER = "/wallet/fund-out-history";
  public static final String FUND_OUT_HISTORY_LIST = FUND_OUT_HISTORY_CONTROLLER + "/list";
  public static final String FUND_OUT_HISTORY_DETAIL = FUND_OUT_HISTORY_CONTROLLER + "/detail";
  public static final String FUND_OUT_HISTORY_DEFAULT_FILTER = "txnStatusIds=10";

  private static final Logger LOG = LogManager.getLogger(FundOutController.class);

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.MERCHANT + "','"
          + RoleConstants.CUSTOMER + "','" + PROVIDER + "','" + RoleConstants.FA_MANAGER + "','"
          + RoleConstants.FINANCE + "','"
          + RoleConstants.SALE_ASM + "','" + RoleConstants.SALE_EXCUTIVE + "','"
          + RoleConstants.SALE_DIRECTOR + "','" + RoleConstants.RECONCILIATION + "','"
          + RoleConstants.RECONCILIATION_LEADER + "' , '"+RoleConstants.CUSTOMERCARE_MANAGER+"' , '"+RoleConstants.CUSTOMERCARE+"')")
  public String search(HttpServletRequest request, ModelMap map,
      @ModelAttribute FundInOutForm fundInOutForm) {

    searchFundoutTrans(request, map, fundInOutForm);
    return "/fundout/fund_out";
  }

  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.MERCHANT + "','"
          + RoleConstants.CUSTOMER + "','" + PROVIDER + "','" + RoleConstants.FA_MANAGER + "','"
          + RoleConstants.FINANCE + "','"
          + RoleConstants.SALE_ASM + "','" + RoleConstants.SALE_EXCUTIVE + "','"
          + RoleConstants.SALE_DIRECTOR + "','" + RoleConstants.RECONCILIATION + "','"
          + RoleConstants.RECONCILIATION_LEADER + "','"+RoleConstants.CUSTOMERCARE_MANAGER+"' , '"+RoleConstants.CUSTOMERCARE+"')")
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

    return "/fundout/fund_out_detail";
  }

  @RequestMapping(value = "/export", method = RequestMethod.GET)
  @PreAuthorize("isAuthenticated()")
  public void export(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute SearchDataForm searchDataForm) throws Exception {
    searchDataForm.setServiceTypeIds(Collections.singletonList(ServiceType.FUND_OUT.name()));
    super.export(request, response, searchDataForm);
  }

}
