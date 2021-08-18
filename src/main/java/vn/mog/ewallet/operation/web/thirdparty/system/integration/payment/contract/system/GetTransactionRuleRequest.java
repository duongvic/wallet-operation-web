package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system;

public class GetTransactionRuleRequest extends GetTransactionRuleRequestType {

  private static final long serialVersionUID = 1L;

  public GetTransactionRuleRequest(Long txnRuleId) {
    this.id = txnRuleId;
  }

  public GetTransactionRuleRequest() {

  }
}
