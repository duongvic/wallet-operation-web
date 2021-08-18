package vn.mog.ewallet.operation.web.controller.service.beans;


public class ProviderServiceOperation {

  private String serviceType;

  private String providerServiceCode;
  private String providerServiceName;
  private String providerServiceActive;
  
  private String serviceMatchingActive;

  public String getServiceType() {
    return serviceType;
  }

  public void setServiceType(String serviceType) {
    this.serviceType = serviceType;
  }

  public String getProviderServiceActive() {
    return providerServiceActive;
  }

  public void setProviderServiceActive(String providerServiceActive) {
    this.providerServiceActive = providerServiceActive;
  }

  public String getProviderServiceName() {
    return providerServiceName;
  }

  public void setProviderServiceName(String providerServiceName) {
    this.providerServiceName = providerServiceName;
  }

  public String getProviderServiceCode() {
    return providerServiceCode;
  }

  public void setProviderServiceCode(String providerServiceCode) {
    this.providerServiceCode = providerServiceCode;
  }
  
  public String getServiceMatchingActive() {
    return serviceMatchingActive;
  }
  
  public void setServiceMatchingActive(String serviceMatchingActive) {
    this.serviceMatchingActive = serviceMatchingActive;
  }
}
