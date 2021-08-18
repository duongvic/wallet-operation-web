package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class ChangeBlacklistReasonRequestType extends MobiliserRequestType {

  private int blacklistReasonType;
  private Long customerId;
  private String remark;

  public int getBlacklistReasonType() {
    return blacklistReasonType;
  }

  public void setBlacklistReasonType(int blacklistReasonType) {
    this.blacklistReasonType = blacklistReasonType;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
