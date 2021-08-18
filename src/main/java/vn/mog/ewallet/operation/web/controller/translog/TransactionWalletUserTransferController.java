package vn.mog.ewallet.operation.web.controller.translog;

import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.contract.form.SearchDataForm;
import vn.mog.ewallet.operation.web.controller.transfer.AbstractTransferController;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;

@Controller
@RequestMapping(value = "")
public class TransactionWalletUserTransferController extends AbstractTransferController {


  @RequestMapping(value = "/wallet/transaction/user/sof-transfer/list", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION
      + "','SALESUPPORT_MANAGER' , 'SALESUPPORT','FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE','CUSTOMERCARE_MANAGER','CUSTOMERCARE')")
  public String sofTransfer(HttpServletRequest request, ModelMap model,
      @ModelAttribute SearchDataForm searchDataForm)
      throws FrontEndException {

    model.put("transferType", ServiceType.FUND_TRANSFER.name());
    searchTransferTrans(request, model, Collections.singletonList(ServiceType.FUND_TRANSFER.name()),
        null);

    return "/transaction_wallet/sof_transfer";
  }

  @RequestMapping(value = "/wallet/transaction/user/p2p/list", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION
      + "', 'SALESUPPORT_MANAGER' , 'SALESUPPORT','FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE','CUSTOMERCARE_MANAGER','CUSTOMERCARE')")
  public String p2pTransfer(HttpServletRequest request, ModelMap model,
      @ModelAttribute SearchDataForm searchDataForm)
      throws FrontEndException {

    model.put("transferType", ServiceType.P2P_TRANSFER.name());
    searchTransferTrans(request, model, Collections.singletonList(ServiceType.P2P_TRANSFER.name()),
        null);

    return "/transaction_wallet/p2p_transfer";
  }


  @RequestMapping(value = "/wallet/transaction/user/internal-wallet/list", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION
      + "','SALESUPPORT_MANAGER' , 'SALESUPPORT','FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE','CUSTOMERCARE_MANAGER','CUSTOMERCARE')")
  public String internalWalletTransfer(HttpServletRequest request, ModelMap model,
      @ModelAttribute SearchDataForm searchDataForm)
      throws FrontEndException {

    model.put("transferType", ServiceType.WALLET_TRANSFER.name());
    searchTransferTrans(request, model,
        Collections.singletonList(ServiceType.WALLET_TRANSFER.name()), null);

    return "/transaction_wallet/internal_wallet_transfer";
  }
}
