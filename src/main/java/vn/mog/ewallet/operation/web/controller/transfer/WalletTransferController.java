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
public class WalletTransferController extends AbstractTransferController {

  public static final String TRANSFER_WALLET_HISTORY_LIST = "/wallet/transfer-wallet/history/list";
  public static final String TRANSFER_WALLET_REQUEST_LIST = "/wallet/transfer-wallet/processing/list";

  @GetMapping(value = "/wallet/transfer-wallet/history/list")
  @PreAuthorize(value = "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.STAFF + "')")
  public String walletTransferHistory(HttpServletRequest request, ModelMap model) throws FrontEndException {
    searchTransferTrans(request, model, Collections.singletonList(ServiceType.WALLET_TRANSFER.name()), null);
    model.put("nav", "wallet_transaction");
    model.put("transferType", ServiceType.WALLET_TRANSFER.name());
    return "/transfer/transfer_wallet/transfer_wallet_history";
  }

  @GetMapping(value = "/wallet/transfer-wallet/history/detail")
  @PreAuthorize(value = "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.STAFF + "')")
  public String walletTransferHistoryDetail(HttpServletRequest request, ModelMap model) throws FrontEndException {
    movementHistoryDetail(request, model);
    model.put("nav", "wallet_transaction");
    model.put("transferType", ServiceType.WALLET_TRANSFER.name());
    return "/transfer/transfer_wallet/transfer_wallet_history_detail";
  }

  @GetMapping(value = "/wallet/transfer-wallet/processing/list")
  @PreAuthorize(value = "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.STAFF + "')")
  public String processTransferWallet(HttpServletRequest request, ModelMap map) throws FrontEndException {
    searchDataTransferRequest(request, map, ServiceType.WALLET_TRANSFER.name());
    map.put("transferType", ServiceType.WALLET_TRANSFER.name());
    return "/transfer/transfer_wallet/list";
  }

  @GetMapping(value = "/wallet/transfer-wallet/processing/{orderId}")
  @PreAuthorize(value = "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.STAFF + "')")
  public String processTransferWalletDetail(@PathVariable Long orderId, ModelMap model) throws FrontEndException {
    getDetailTransferRequest(orderId, model);
    model.put("transferType", ServiceType.WALLET_TRANSFER.name());
    return "/transfer/transfer_wallet/detail";
  }

  @GetMapping(value = "/wallet/transfer-wallet/request-transfer")
  @PreAuthorize(value = "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.SALESUPPORT + "')")
  public String requestTransferTransferWallet(HttpServletRequest request, ModelMap model) throws FrontEndException {
    requestTransfer(request, model);
    model.put("transferType", ServiceType.WALLET_TRANSFER.name());
    return "/transfer/transfer_wallet/request";
  }

  @PostMapping(value = "/wallet/transfer-wallet/request-transfer")
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + SALESUPPORT + "')")
  public String requestTransferWalletTransferPost(
      @ModelAttribute("transferDataForm") final TransferDataForm transferData,
      HttpServletRequest request,
      ModelMap model)
      throws FrontEndException, IOException {
    model.put("transferType", ServiceType.WALLET_TRANSFER.name());
    return requestTransferPost(transferData, request, model);
  }

  @GetMapping(value = "/wallet/transfer-wallet/order-initiate")
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + FINANCE + "')")
  public String orderInitiateTransferWallet(HttpServletRequest request, ModelMap model) throws FrontEndException {
    model.put("transferType", ServiceType.WALLET_TRANSFER.name());
    return orderInitiate(request, model, ServiceType.WALLET_TRANSFER.name());

  }

  @PostMapping(value = "/wallet/transfer-wallet/order-initiate")
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + RoleConstants.FINANCE + "')")
  public String orderInitiatePostTransferWallet(HttpServletRequest request, ModelMap model)
      throws FrontEndException {
    model.put("transferType", ServiceType.WALLET_TRANSFER.name());
    return orderInitiatePost(request, model, ServiceType.WALLET_TRANSFER.name());
  }

  @GetMapping(value = "/wallet/transfer-wallet/order-review")
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + FINANCE_LEADER + "')")
  public String orderReviewTransferWallet(HttpServletRequest request, ModelMap model) throws FrontEndException {
    model.put("transferType", ServiceType.WALLET_TRANSFER.name());
    return orderReview(request, model, ServiceType.WALLET_TRANSFER.name());
  }

  @PostMapping(value = "/wallet/transfer-wallet/order-review")
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + FINANCE_LEADER + "')")
  public String orderReviewTransferWalletPost(HttpServletRequest request, ModelMap model) throws FrontEndException {
    model.put("transferType", ServiceType.WALLET_TRANSFER.name());
    return orderReviewPost(request, model, ServiceType.WALLET_TRANSFER.name());
  }

  @GetMapping(value = "/wallet/transfer-wallet/order-approve")
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + FA_MANAGER + "','" + FINANCE_LEADER + "')")
  public String orderApproveTransferWallet(HttpServletRequest request, ModelMap model) throws FrontEndException {
    model.put("transferType", ServiceType.WALLET_TRANSFER.name());
    return orderApprove(request, model, ServiceType.WALLET_TRANSFER.name());
  }

  @PostMapping(value = "/wallet/transfer-wallet/order-approve")
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + FA_MANAGER + "','" + FINANCE_LEADER + "')")
  public String orderApprovePostTransferWallet(HttpServletRequest request, ModelMap model) throws FrontEndException {
    model.put("transferType", ServiceType.WALLET_TRANSFER.name());
    return orderApprovePost(request, model, ServiceType.WALLET_TRANSFER.name());
  }

  @PostMapping(value = "/wallet/transfer-wallet/order-otp")
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','" + FA_MANAGER + "','" + FINANCE_LEADER + "')")
  public String orderOTPPostTransferWallet(HttpServletRequest request, ModelMap model) throws FrontEndException {
    model.put("transferType", ServiceType.WALLET_TRANSFER.name());
    return orderOTPPost(request, model, ServiceType.WALLET_TRANSFER.name());
  }

  @GetMapping(value = "/wallet/transfer-wallet/findCustomerByTypeWallet")
  @PreAuthorize(value = "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.FINANCE + "')")
  @ResponseBody
  public ResponseEntity<?> findCustomerByTypeWalletTransferWallet(HttpServletRequest request) throws FrontEndException {
    return findCustomerByTypeWallet(request);
  }

  @PostMapping(value = "/wallet/transfer-wallet/getOtp")
  @ResponseBody
  @PreAuthorize(value = "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','FINANCE','FINANCESUPPORT_LEADER')")
  public GetOTPConfirmResponse getOTPConfirmTransferWallet(HttpServletRequest request) throws FrontEndException {
    return getOTPConfirm(request);
  }

  @GetMapping(value = "/wallet/transfer-wallet/files/{file_name}")
  public void getFileTransferWallet(@PathVariable("file_name") String fileName, HttpServletResponse response) throws IOException {
    getFile(fileName, response);
  }
}
