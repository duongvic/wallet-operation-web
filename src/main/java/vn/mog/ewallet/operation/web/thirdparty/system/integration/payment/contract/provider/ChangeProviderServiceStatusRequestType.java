package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class ChangeProviderServiceStatusRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  protected Long providerServiceId;
  protected Boolean active;
  protected String remark;

  public Long getProviderServiceId() {
    return providerServiceId;
  }

  public void setProviderServiceId(Long providerServiceId) {
    this.providerServiceId = providerServiceId;
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
