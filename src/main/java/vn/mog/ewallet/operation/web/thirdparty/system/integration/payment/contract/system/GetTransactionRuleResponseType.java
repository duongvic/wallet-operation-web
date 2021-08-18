package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.TransactionRule;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetTransactionRuleResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;
  protected TransactionRule transactionRule;

  public TransactionRule getTransactionRule() {
    return transactionRule;
  }

  public void setTransactionRule(TransactionRule transactionRule) {
    this.transactionRule = transactionRule;
  }
}
