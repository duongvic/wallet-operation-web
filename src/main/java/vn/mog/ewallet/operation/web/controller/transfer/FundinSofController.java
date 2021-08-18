package vn.mog.ewallet.operation.web.controller.transfer;


import static vn.mog.ewallet.operation.web.constant.RoleConstants.ADMIN_OPERATION;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.FA_MANAGER;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.FINANCE;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.FINANCE_LEADER;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.SALESUPPORT;

import java.io.IOException;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.contract.form.TransferDataForm;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetOTPConfirmResponse;

@Controller
public class FundinSofController extends AbstractTransferController {

  public static final String FUND_SOF_TRANSFER_HISTORY_LIST = "/wallet/fundin-sof/history";
  public static final String FUND_SOF_REQUEST_LIST = "/wallet/fundin-sof/processing";
  public static final String FUND_SOF_HISTORY_DETAIL = FUND_SOF_REQUEST_LIST + "/detail";

  @GetMapping(value = "/wallet/fundin-sof/history")
  @PreAuthorize(value = "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.STAFF + "')")
  public String fundinSofHistory(HttpServletRequest request, ModelMap model) throws FrontEndException {
    searchTransferTrans(request, model, Collections.singletonList(ServiceType.FUND_TRANSFER.name()), null);
    model.put("nav", "fund_transaction");
    model.put("transferType", ServiceType.FUND_TRANSFER.name());
    return "/transfer/fundin_sof/fundin_sof_history";
  }

  @GetMapping(value = "/wallet/fundin-sof/history/detail")
  @PreAuthorize(value = "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.STAFF + "')")
  public String fundinSofHistoryDetail(HttpServletRequest request, ModelMap model) throws FrontEndException {
    movementHistoryDetail(request, model);
    model.put("nav", "fund_transaction");
    model.put("transferType", ServiceType.FUND_TRANSFER.name());
    return "/transfer/fundin_sof/fundin_sof_history_detail";
  }


  @GetMapping(value = "/wallet/fundin-sof/processing")
  @PreAuthorize(value = "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.STAFF + "')")
  public String processFundinSOF(HttpServletRequest request, ModelMap model) throws FrontEndException {
    searchDataTransferRequest(request, model, ServiceType.FUND_TRANSFER.name());
    model.put("transferType", ServiceType.FUND_TRANSFER.name());
    return "/transfer/fundin_sof/list";
  }


  @GetMapping(value = "/wallet/fundin-sof/processing/{orderId}")
  @PreAuthorize(value = "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.STAFF + "')")
  public String processFundinSofDetail(@PathVariable Long orderId, ModelMap model) throws FrontEndException {
    getDetailTransferRequest(orderId, model);
    model.put("transferType", ServiceType.FUND_TRANSFER.name());
    return "/transfer/fundin_sof/detail";
  }

  @GetMapping(value = "/wallet/fundin-sof/request-transfer")
  @PreAuthorize(value = "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.SALESUPPORT + "')")
  public String requestTransferFundinSof(HttpServletRequest request, ModelMap model) {
    requestTransfer(request, model);
    model.put("transferType", ServiceType.FUND_TRANSFER.name());
    return "/transfer/fundin_sof/request";
  }

  @PostMapping(value = "/wallet/fundin-sof/request-transfer")
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + SALESUPPORT + "')")
  public String requestTransferFundinSofPost(
      @ModelAttribute("transferDataForm") final TransferDataForm transferData,
      HttpServletRequest request,
      ModelMap model)
      throws FrontEndException, IOException {
    model.put("transferType", ServiceType.FUND_TRANSFER.name());
    return requestTransferPost(transferData, request, model);
  }

  @GetMapping(value = "/wallet/fundin-sof/order-initiate")
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + FINANCE + "')")
  public String orderInitiateFundinSof(HttpServletRequest request, ModelMap model) throws FrontEndException {
    model.put("transferType", ServiceType.FUND_TRANSFER.name());
    return orderInitiate(request, model, ServiceType.FUND_TRANSFER.name());
  }

  @PostMapping(value = "/wallet/fundin-sof/order-initiate")
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + FINANCE + "')")
  public String orderInitiateFundinSofPost(HttpServletRequest request, ModelMap model)
      throws FrontEndException {
    model.put("transferType", ServiceType.FUND_TRANSFER.name());
    return orderInitiatePost(request, model, ServiceType.FUND_TRANSFER.name());
  }

  @GetMapping(value = "/wallet/fundin-sof/order-review")
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + FINANCE_LEADER + "')")
  public String orderReviewFundinSof(HttpServletRequest request, ModelMap model) throws FrontEndException {
    model.put("transferType", ServiceType.FUND_TRANSFER.name());
    return orderReview(request, model, ServiceType.FUND_TRANSFER.name());
  }

  @PostMapping(value = "/wallet/fundin-sof/order-review")
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + FINANCE_LEADER + "')")
  public String orderReviewFundinSofPost(HttpServletRequest request, ModelMap model) throws FrontEndException {
    model.put("transferType", ServiceType.FUND_TRANSFER.name());
    return orderReviewPost(request, model, ServiceType.FUND_TRANSFER.name());
  }

  @GetMapping(value = "/wallet/fundin-sof/order-approve")
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + FA_MANAGER + "','" + FINANCE_LEADER + "')")
  public String orderApproveFundinSof(HttpServletRequest request, ModelMap model) throws FrontEndException {
    model.put("transferType", ServiceType.FUND_TRANSFER.name());
    return orderApprove(request, model, ServiceType.FUND_TRANSFER.name());
  }

  @PostMapping(value = "/wallet/fundin-sof/order-approve")
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + FA_MANAGER + "','" + FINANCE_LEADER + "')")
  public String orderApprovePostFundinSof(HttpServletRequest request, ModelMap model) throws FrontEndException {
    model.put("transferType", ServiceType.FUND_TRANSFER.name());
    return orderApprovePost(request, model, ServiceType.FUND_TRANSFER.name());
  }

  @PostMapping(value = "/wallet/fundin-sof/order-otp")
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + FA_MANAGER + "','" + FINANCE_LEADER + "')")
  public String orderOTPPostFundinSof(HttpServletRequest request, ModelMap model) throws FrontEndException {
    model.put("transferType", ServiceType.FUND_TRANSFER.name());
    return orderOTPPost(request, model, ServiceType.FUND_TRANSFER.name());
  }

  @GetMapping(value = "/wallet/fundin-sof/findCustomerByTypeWallet")
  @PreAuthorize(value = "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.FINANCE + "')")
  @ResponseBody
  public ResponseEntity<?> findCustomerByTypeWalletFundinSof(HttpServletRequest request) throws FrontEndException {
    return findCustomerByTypeWallet(request);
  }

  @PostMapping(value = "/wallet/fundin-sof/getOtp")
  @ResponseBody
  @PreAuthorize(value = "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','FINANCE','FINANCESUPPORT_LEADER')")
  public GetOTPConfirmResponse getOTPConfirmFundinSof(HttpServletRequest request) throws FrontEndException {
    return getOTPConfirm(request);
  }

  @GetMapping(value = "/wallet/fundin-sof/files/{file_name}")
  public void getFileFundinSof(@PathVariable("file_name") String fileName, HttpServletResponse response) throws IOException {
    getFile(fileName, response);
  }


}
