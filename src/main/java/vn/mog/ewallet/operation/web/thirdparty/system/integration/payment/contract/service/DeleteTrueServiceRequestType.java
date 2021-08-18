package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class DeleteTrueServiceRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected Long serviceId;

  public Long getServiceId() {
    return serviceId;
  }

  public void setServiceId(Long serviceId) {
    this.serviceId = serviceId;
  }

}
