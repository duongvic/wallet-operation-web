package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow;

public class FundOrderFlowSubmitProcessRequest extends FundOrderFlowSubmitProcessRequestType {

  private static final long serialVersionUID = 1L;

  public FundOrderFlowSubmitProcessRequest(Long fundOrderId) {
    this.fundOrderId = fundOrderId;
  }
}
