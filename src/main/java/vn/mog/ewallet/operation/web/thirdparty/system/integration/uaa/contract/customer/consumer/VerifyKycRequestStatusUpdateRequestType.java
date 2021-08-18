package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class VerifyKycRequestStatusUpdateRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  private String action;
  private Long requestId;
  private String remark;

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Long getRequestId() {
    return requestId;
  }

  public void setRequestId(Long requestId) {
    this.requestId = requestId;
  }

}
