package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow;

public class OrderFlowSubmitProcessRequest extends OrderFlowSubmitProcessRequestType {

  private static final long serialVersionUID = 1L;

  public OrderFlowSubmitProcessRequest(Long orderId, String remark) {
    this.orderId = orderId;
    this.remark = remark;
  }

}
