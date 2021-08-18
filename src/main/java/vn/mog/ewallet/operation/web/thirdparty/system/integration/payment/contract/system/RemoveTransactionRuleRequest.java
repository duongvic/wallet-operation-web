package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system;

public class RemoveTransactionRuleRequest extends RemoveTransactionRuleRequestType {

  private static final long serialVersionUID = 1L;

  public RemoveTransactionRuleRequest() {
  }

  public RemoveTransactionRuleRequest(Long txnRuleId) {
    this.txnRuleId = txnRuleId;
  }
}
