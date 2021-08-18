package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.beans;

public class TrueServiceOperation {

  private String serviceType;

  private String serviceCode;
  private Character serviceActive;

  private String providerServiceCode;
  private String providerServiceName;
  private Character providerServiceActive;

  private String providerCode;
  private Character providerActive;
  
  private Character serviceMatchingActive;

  public String getServiceCode() {
    return serviceCode;
  }

  public void setServiceCode(String serviceCode) {
    this.serviceCode = serviceCode;
  }

  public Character getServiceActive() {
    return serviceActive;
  }

  public void setServiceActive(Character serviceActive) {
    this.serviceActive = serviceActive;
  }

  public String getProviderServiceCode() {
    return providerServiceCode;
  }

  public void setProviderServiceCode(String providerServiceCode) {
    this.providerServiceCode = providerServiceCode;
  }

  public String getProviderServiceName() {
    return providerServiceName;
  }

  public void setProviderServiceName(String providerServiceName) {
    this.providerServiceName = providerServiceName;
  }

  public String getServiceType() {
    return serviceType;
  }

  public void setServiceType(String serviceType) {
    this.serviceType = serviceType;
  }

  public Character getProviderServiceActive() {
    return providerServiceActive;
  }

  public void setProviderServiceActive(Character providerServiceActive) {
    this.providerServiceActive = providerServiceActive;
  }

  public String getProviderCode() {
    return providerCode;
  }

  public void setProviderCode(String providerCode) {
    this.providerCode = providerCode;
  }

  public Character getProviderActive() {
    return providerActive;
  }

  public void setProviderActive(Character providerActive) {
    this.providerActive = providerActive;
  }
  
  public Character getServiceMatchingActive() {
    return serviceMatchingActive;
  }
  
  public void setServiceMatchingActive(Character serviceMatchingActive) {
    this.serviceMatchingActive = serviceMatchingActive;
  }
}
