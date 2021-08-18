package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.beans.Provider;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetProviderResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;
  protected Provider provider;

  public Provider getProvider() {
    return provider;
  }

  public void setProvider(Provider provider) {
    this.provider = provider;
  }
}
