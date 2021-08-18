package vn.mog.ewallet.operation.web.controller.service.beans;

import java.util.List;


public class ServiceOperation {

  private String serviceCode;
  private String serviceName;
  private String serviceStatus;

  private List<ProviderOperation> providerOperations;

  public ServiceOperation() {
  }

  public ServiceOperation(String serviceCode, String serviceName, String serviceStatus, List<ProviderOperation> providerOperations) {
    this.serviceCode = serviceCode;
    this.serviceName = serviceName;
    this.serviceStatus = serviceStatus;
    this.providerOperations = providerOperations;
  }

  public String getServiceCode() {
    return serviceCode;
  }

  public void setServiceCode(String serviceCode) {
    this.serviceCode = serviceCode;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getServiceStatus() {
    return serviceStatus;
  }

  public void setServiceStatus(String serviceStatus) {
    this.serviceStatus = serviceStatus;
  }

  public List<ProviderOperation> getProviderOperations() {
    return providerOperations;
  }

  public void setProviderOperations(List<ProviderOperation> providerOperations) {
    this.providerOperations = providerOperations;
  }
}
