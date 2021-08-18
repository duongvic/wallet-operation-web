package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service;

import java.util.Collections;
import java.util.Map;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.beans.TrueService;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.beans.TrueServiceOperation;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetTrueServiceOperationResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  protected Map<String, Character> providers;
  protected Map<String, TrueService> services;
  protected Map<String, TrueServiceOperation> serviceOperations;

  public Map<String, Character> getProviders() {
    return providers == null ? Collections.emptyMap() : providers;
  }

  public void setProviders(Map<String, Character> providers) {
    this.providers = providers;
  }

  public Map<String, TrueService> getServices() {
    return services == null ? Collections.emptyMap() : services;
  }

  public void setServices(Map<String, TrueService> services) {
    this.services = services;
  }

  public Map<String, TrueServiceOperation> getServiceOperations() {
    return serviceOperations == null ? Collections.emptyMap() : serviceOperations;
  }

  public void setServiceOperations(Map<String, TrueServiceOperation> serviceOperations) {
    this.serviceOperations = serviceOperations;
  }
}
