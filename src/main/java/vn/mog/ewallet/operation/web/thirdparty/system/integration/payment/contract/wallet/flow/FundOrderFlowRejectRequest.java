package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow;

public class FundOrderFlowRejectRequest extends FundOrderFlowRejectRequestType {

  private static final long serialVersionUID = 1L;

  public FundOrderFlowRejectRequest(Long fundOrderId, String remark) {
    this.fundOrderId = fundOrderId;
    this.remark = remark;
  }
}
