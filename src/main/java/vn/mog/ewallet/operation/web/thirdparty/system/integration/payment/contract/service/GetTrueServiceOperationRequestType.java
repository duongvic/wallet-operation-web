package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service;

import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetTrueServiceOperationRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  protected String serviceName;
  protected List<Long> serviceIds;
  protected List<String> serviceTypes;
  protected List<String> providerCodes;

  public List<Long> getServiceIds() {
    return serviceIds;
  }

  public void setServiceIds(List<Long> serviceIds) {
    this.serviceIds = serviceIds;
  }

  public List<String> getServiceTypes() {
    return serviceTypes;
  }

  public void setServiceTypes(List<String> serviceTypes) {
    this.serviceTypes = serviceTypes;
  }

  public List<String> getProviderCodes() {
    return providerCodes;
  }

  public void setProviderCodes(List<String> providerCodes) {
    this.providerCodes = providerCodes;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }
}
