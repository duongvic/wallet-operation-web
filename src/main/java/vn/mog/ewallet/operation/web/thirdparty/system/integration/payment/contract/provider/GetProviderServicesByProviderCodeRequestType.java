package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetProviderServicesByProviderCodeRequestType extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;

  protected String providerCode;

  public String getProviderCode() {
    return providerCode;
  }

  public void setProviderCode(String providerCode) {
    this.providerCode = providerCode;
  }
}
