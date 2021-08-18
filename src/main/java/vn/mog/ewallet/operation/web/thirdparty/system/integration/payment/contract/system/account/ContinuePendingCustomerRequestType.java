package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import vn.mog.framework.contract.base.TraceableRequestType;

public class ContinuePendingCustomerRequestType extends TraceableRequestType {

  private static final long serialVersionUID = 1L;

  protected String taskId;
  protected boolean approve;

  public String getTaskId() {
    return this.taskId;
  }

  public void setTaskId(String value) {
    this.taskId = value;
  }

  public boolean isApprove() {
    return this.approve;
  }

  public void setApprove(boolean value) {
    this.approve = value;
  }
}
