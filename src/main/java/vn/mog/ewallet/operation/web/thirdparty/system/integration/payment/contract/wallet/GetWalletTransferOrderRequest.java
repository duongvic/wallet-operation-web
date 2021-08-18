package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

public class GetWalletTransferOrderRequest extends GetWalletTransferOrderRequestType {

  private static final long serialVersionUID = 1L;

  public GetWalletTransferOrderRequest(Long orderId) {
    this.orderId = orderId;
  }

  public GetWalletTransferOrderRequest() {

  }
}
