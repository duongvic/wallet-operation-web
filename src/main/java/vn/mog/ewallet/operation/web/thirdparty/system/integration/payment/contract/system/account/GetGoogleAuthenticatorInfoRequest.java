package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

public class GetGoogleAuthenticatorInfoRequest extends GetGoogleAuthenticatorInfoRequestType {

  private Long customerId;

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }
}
