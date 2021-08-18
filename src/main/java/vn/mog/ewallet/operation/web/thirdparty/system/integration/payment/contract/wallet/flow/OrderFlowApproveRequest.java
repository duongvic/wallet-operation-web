package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow;

import java.io.Serializable;

public class OrderFlowApproveRequest extends OrderFlowApproveRequestType implements Serializable {

  private static final long serialVersionUID = 1L;

  public OrderFlowApproveRequest(Long orderId, String remark) {
    this.orderId = orderId;
    this.remark = remark;
  }
}
