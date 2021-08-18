package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class OrderFlowApproveRequestType extends MobiliserRequestType implements Serializable {

  private static final long serialVersionUID = 1L;

  protected Long orderId;
  protected String remark;

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
