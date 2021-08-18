package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class ChangeProviderStatusRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  protected Long providerId;
  protected Boolean active;
  protected String remark;

  public Long getProviderId() {
    return providerId;
  }

  public void setProviderId(Long providerId) {
    this.providerId = providerId;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
