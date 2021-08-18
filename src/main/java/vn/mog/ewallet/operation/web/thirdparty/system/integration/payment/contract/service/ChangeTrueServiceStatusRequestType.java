package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class ChangeTrueServiceStatusRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  protected Long serviceId;
  protected Character active;
  protected String remark;

  public Long getServiceId() {
    return serviceId;
  }

  public void setServiceId(Long serviceId) {
    this.serviceId = serviceId;
  }

  public Character getActive() {
    return active;
  }

  public void setActive(Character active) {
    this.active = active;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
