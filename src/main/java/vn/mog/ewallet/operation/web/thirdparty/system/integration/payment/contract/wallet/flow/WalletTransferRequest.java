package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow;

public class WalletTransferRequest extends WalletTransferRequestType {

  private static final long serialVersionUID = 1L;

  public WalletTransferRequest(Long orderId, String otp) {
    this.orderId = orderId;
    this.otp = otp;
  }

  public WalletTransferRequest() {

  }
}
