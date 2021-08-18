package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow;

public class OrderFlowRejectRequest extends OrderFlowRejectRequestType {

  private static final long serialVersionUID = 1L;

  public OrderFlowRejectRequest(Long orderId) {
    this.orderId = orderId;
  }

  public OrderFlowRejectRequest(Long orderId, String remark) {
    this.orderId = orderId;
    this.remark = remark;
  }
}
