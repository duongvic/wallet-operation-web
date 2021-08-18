package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider;

public class GetProviderRequest extends GetProviderRequestType {

  private static final long serialVersionUID = 1L;

  public GetProviderRequest(String providerCode) {
    this.providerCode = providerCode;
  }

  public GetProviderRequest() {

  }
}
