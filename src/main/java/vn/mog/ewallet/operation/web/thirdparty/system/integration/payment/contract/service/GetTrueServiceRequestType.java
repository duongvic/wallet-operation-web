package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetTrueServiceRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected Long serviceId;
  protected String serviceCode;

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

}
