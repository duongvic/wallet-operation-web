package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer;

public class GetOTPConfirmRequest extends GetOTPConfirmRequestType {

  private static final long serialVersionUID = 1L;

  public GetOTPConfirmRequest(Long orderId) {
    this.orderId = orderId;
  }

  public GetOTPConfirmRequest() {

  }
}
