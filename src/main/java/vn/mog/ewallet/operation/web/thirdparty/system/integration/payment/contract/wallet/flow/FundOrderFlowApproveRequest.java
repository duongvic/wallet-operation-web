package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow;

public class FundOrderFlowApproveRequest extends FundOrderFlowApproveRequestType {

  private static final long serialVersionUID = 1L;

  public FundOrderFlowApproveRequest(Long fundOrderId) {
    this.fundOrderId = fundOrderId;
  }

  public FundOrderFlowApproveRequest(Long fundOrderId, String remark) {
    this.fundOrderId = fundOrderId;
    this.remark = remark;
  }
}
