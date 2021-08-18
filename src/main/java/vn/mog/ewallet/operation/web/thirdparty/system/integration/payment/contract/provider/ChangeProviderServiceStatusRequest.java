package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider;

public class ChangeProviderServiceStatusRequest extends ChangeProviderServiceStatusRequestType {

  private static final long serialVersionUID = 1L;

  public ChangeProviderServiceStatusRequest(Long providerServiceId, Boolean active) {
    this.providerServiceId = providerServiceId;
    this.active = active;
  }

  public ChangeProviderServiceStatusRequest() {

  }
}
