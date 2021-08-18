package vn.mog.ewallet.operation.web.controller.p2p;


import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.mog.ewallet.operation.web.contract.form.SearchDataForm;
import vn.mog.ewallet.operation.web.controller.translog.AbstractTransactionController;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;

@Controller
@RequestMapping(value = "")
public class P2pTransferController extends AbstractTransactionController {

  public static final String P2P_TRANSFER_URL = "/wallet/p2p-transfer/list";
  public static final String P2P_TRANSFER_DETAIL_URL = "/wallet/p2p-transfer/detail";

  @RequestMapping(value = "/wallet/p2p-transfer/list", method = RequestMethod.GET)
  @PreAuthorize("isAuthenticated()")
  public String searchAll(HttpServletRequest request, HttpServletResponse response, ModelMap map,
      @ModelAttribute SearchDataForm searchDataForm)
      throws Exception {

    searchDataForm.setServiceTypeIds(Collections.singletonList(ServiceType.P2P_TRANSFER.name()));
    searchCommonTrans(request, response, map, searchDataForm);

    return "/p2p/list";
  }

  @RequestMapping(value = "/wallet/p2p-transfer/detail", method = RequestMethod.GET)
  @PreAuthorize("isAuthenticated()")
  public String detailP2p(HttpServletRequest request, HttpServletResponse response, ModelMap map,
      @ModelAttribute SearchDataForm searchDataForm)
      throws Exception {

    detailCommonTrans(request, map);

    return "/p2p/detail";
  }

}
