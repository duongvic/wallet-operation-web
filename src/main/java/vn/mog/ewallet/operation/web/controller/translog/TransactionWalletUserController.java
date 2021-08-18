package vn.mog.ewallet.operation.web.controller.translog;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.contract.form.FundInOutForm;
import vn.mog.ewallet.operation.web.contract.form.SearchDataForm;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;

@Controller
@RequestMapping(value = "/wallet/transaction/user")
public class TransactionWalletUserController extends AbstractTransactionController {

  public static final String TRANSACTION_WALLET_CONTROLLER = "/wallet/transaction/user";
  public static final String TRANSACTION_WALLET_USER_LIST =
      TRANSACTION_WALLET_CONTROLLER + "/all/list";
  public static final String TRANSACTION_WALLET_FUND_IN_LIST =
      TRANSACTION_WALLET_CONTROLLER + "/fund-in/list";
  public static final String TRANSACTION_WALLET_FUND_OUT_LIST =
      TRANSACTION_WALLET_CONTROLLER + "/fund-out/list";
  public static final String TRANSACTION_WALLET_INTERNAL_WALLET_LIST =
      TRANSACTION_WALLET_CONTROLLER + "/internal-wallet/list";
  public static final String TRANSACTION_WALLET_P2P_WALLET_LIST =
      TRANSACTION_WALLET_CONTROLLER + "/p2p/list";
  public static final String TRANSACTION_WALLET_SOF_TRANSFER_LIST =
      TRANSACTION_WALLET_CONTROLLER + "/sof-transfer/list";
  public static final String TRANSACTION_WALLET_DEFAULT_FILTER = "txnStatusIds=10";


  @RequestMapping(value = "/all/list", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION
      + "','SALE_DIRECTOR','SALE_EXCUTIVE','SALE_SUPERVISOR','SALE_ASM','SALE_RSM','SALESUPPORT_MANAGER','SALESUPPORT',"
      + "'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE','CUSTOMERCARE_MANAGER','CUSTOMERCARE', 'RECONCILIATION', 'RECONCILIATION_LEADER' )")
  public String list(HttpServletRequest request, HttpServletResponse response, ModelMap map,
      @ModelAttribute SearchDataForm searchDataForm)
      throws FrontEndException {
    if (searchDataForm.getServiceTypeIds() == null || searchDataForm.getServiceTypeIds().isEmpty()) {
      searchDataForm.setServiceTypeIds(new ArrayList<>(ServiceType.WALLET_SERVICE_TYPES.keySet()));
    }
    searchCommonTrans(request, response, map, searchDataForm);
    map.put("serviceTypes", ServiceType.WALLET_SERVICE_TYPES);
    return "/transaction_wallet/all_list";
  }

  @RequestMapping(value = "/fund-in/list", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION
      + "','SALE_DIRECTOR','SALE_EXCUTIVE','SALE_SUPERVISOR','SALE_ASM','SALE_RSM','SALESUPPORT_MANAGER','SALESUPPORT',"
      + "'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE','CUSTOMERCARE_MANAGER','CUSTOMERCARE')")
  public String fundIn(HttpServletRequest request, HttpServletResponse response, ModelMap map,
      @ModelAttribute FundInOutForm fundInOutForm)
      throws FrontEndException {

    searchFundinTrans(request, map, fundInOutForm);

    return "/transaction_wallet/fund_in";
  }

  @RequestMapping(value = "/fund-out/list", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION
      + "','SALE_DIRECTOR','SALE_EXCUTIVE','SALE_SUPERVISOR','SALE_ASM','SALE_RSM','SALESUPPORT_MANAGER' , 'SALESUPPORT',"
      + "'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE','CUSTOMERCARE_MANAGER','CUSTOMERCARE')")
  public String fundOut(HttpServletRequest request, HttpServletResponse response, ModelMap map,
      @ModelAttribute FundInOutForm fundInOutForm)
      throws FrontEndException {

    searchFundoutTrans(request, map, fundInOutForm);

    return "/transaction_wallet/fund_out";
  }

  @RequestMapping(value = "/export", method = RequestMethod.GET)
  @PreAuthorize("isAuthenticated()")
  public void export(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute SearchDataForm searchDataForm) throws Exception {
    List<String> serviceTypes = searchDataForm.getServiceTypeIds();
    if (serviceTypes == null || serviceTypes.isEmpty()) {
      searchDataForm.setServiceTypeIds(new ArrayList<>(ServiceType.WALLET_SERVICE_TYPES.keySet()));
    }
    super.export(request, response, searchDataForm);
  }

  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.STAFF + "','" + RoleConstants.MERCHANT + "','"
      + RoleConstants.CUSTOMER + "')")
  public String get(HttpServletRequest request, ModelMap map)
      throws Exception {

    detailCommonTrans(request, map);
    return "/transaction_wallet/transaction-logs-detail";
  }
}
