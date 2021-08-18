package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.beans.ProviderService;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetProviderServicesByProviderCodeResponseType extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;

  protected List<ProviderService> providerServices;

  public List<ProviderService> getProviderServices() {
    return providerServices;
  }

  public void setProviderServices(List<ProviderService> providerServices) {
    this.providerServices = providerServices;
  }
}
