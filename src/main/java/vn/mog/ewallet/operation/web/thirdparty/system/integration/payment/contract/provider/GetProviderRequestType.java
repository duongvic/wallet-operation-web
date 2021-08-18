package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetProviderRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  protected Long providerId;

  protected String providerCode;

  public Long getProviderId() {
    return providerId;
  }

  public void setProviderId(Long providerId) {
    this.providerId = providerId;
  }

  public String getProviderCode() {
    return providerCode;
  }

  public void setProviderCode(String providerCode) {
    this.providerCode = providerCode;
  }
}
