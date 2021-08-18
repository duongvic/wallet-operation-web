package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.TransactionRule;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class UpdateTransactionRuleRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected TransactionRule txnRule;
  protected boolean includeTxnRuleStep;

  public TransactionRule getTxnRule() {
    return txnRule;
  }

  public void setTxnRule(TransactionRule txnRule) {
    this.txnRule = txnRule;
  }

  public boolean isIncludeTxnRuleStep() {
    return includeTxnRuleStep;
  }

  public void setIncludeTxnRuleStep(boolean includeTxnRuleStep) {
    this.includeTxnRuleStep = includeTxnRuleStep;
  }
}
