package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service;

public class GetTrueServiceTransactionRuleMatrixRequest extends GetTrueServiceTransactionRuleMatrixRequestType {

  private static final long serialVersionUID = 1L;

  public GetTrueServiceTransactionRuleMatrixRequest(Long trueServiceId) {
    this.trueServiceId = trueServiceId;
  }
}
