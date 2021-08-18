package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider;

public class ChangeProviderStatusRequest extends ChangeProviderStatusRequestType {

  private static final long serialVersionUID = 1L;

  public ChangeProviderStatusRequest(Long providerId, Boolean active) {
    this.providerId = providerId;
    this.active = active;
  }

  public ChangeProviderStatusRequest() {

  }
}
