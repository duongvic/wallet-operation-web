package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class TransactionRevertFlowSubmitRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  private Long orderId;
  private String remark;

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
