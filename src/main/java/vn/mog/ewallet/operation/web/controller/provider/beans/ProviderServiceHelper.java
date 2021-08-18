package vn.mog.ewallet.operation.web.controller.provider.beans;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.beans.ProviderService;

public class ProviderServiceHelper {

  private String providerCode;
  private List<ProviderService> providerServices;
  private String group;

  public String getProviderCode() {
    return providerCode;
  }

  public void setProviderCode(String providerCode) {
    this.providerCode = providerCode;
  }

  public List<ProviderService> getProviderServices() {
    return providerServices;
  }

  public void setProviderServices(List<ProviderService> providerServices) {
    this.providerServices = providerServices;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }
}
