package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class UpdateTrueServiceRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected Long serviceId;

  protected String serviceCode;

  protected Long parentServiceId;

  protected String serviceName;

  protected String serviceShortName;
  
  protected String customerTypeSupported;
  protected String serviceDesc;

  protected String servicePrices;

  protected String serviceType;
  protected Character isActorPayee;
  protected Character parentFeeStructureAllowed;

  public Long getServiceId() {
    return serviceId;
  }

  public void setServiceId(Long serviceId) {
    this.serviceId = serviceId;
  }

  public String getServiceCode() {
    return serviceCode;
  }

  public void setServiceCode(String serviceCode) {
    this.serviceCode = serviceCode;
  }

  public Long getParentServiceId() {
    return parentServiceId;
  }

  public void setParentServiceId(Long parentServiceId) {
    this.parentServiceId = parentServiceId;
  }

  public String getServiceName() {
    return serviceName;
  }

  public String getServiceShortName() {
    return serviceShortName;
  }

  public void setServiceShortName(String serviceShortName) {
    this.serviceShortName = serviceShortName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getServiceDesc() {
    return serviceDesc;
  }

  public void setServiceDesc(String serviceDesc) {
    this.serviceDesc = serviceDesc;
  }

  public String getServicePrices() {
    return servicePrices;
  }

  public void setServicePrices(String servicePrices) {
    this.servicePrices = servicePrices;
  }

  public String getServiceType() {
    return serviceType;
  }

  public void setServiceType(String serviceType) {
    this.serviceType = serviceType;
  }

  public Character getIsActorPayee() {
    return isActorPayee;
  }

  public void setIsActorPayee(Character isActorPayee) {
    this.isActorPayee = isActorPayee;
  }

  public Character getParentFeeStructureAllowed() {
    return parentFeeStructureAllowed;
  }

  public void setParentFeeStructureAllowed(Character parentFeeStructureAllowed) {
    this.parentFeeStructureAllowed = parentFeeStructureAllowed;
  }

  public String getCustomerTypeSupported() {
    return customerTypeSupported;
  }

  public void setCustomerTypeSupported(String customerTypeSupported) {
    this.customerTypeSupported = customerTypeSupported;
  }
  
}
