package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service;

import java.util.Collections;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.TransactionRule;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetTrueServiceTransactionRuleMatrixResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  protected List<TransactionRule> transactionRule;
  protected Long transactionRuleMapped;

  public List<TransactionRule> getTransactionRule() {
    if (transactionRule == null) {
      transactionRule = Collections.emptyList();
    }
    return transactionRule;
  }

  public void setTransactionRule(List<TransactionRule> transactionRule) {
    this.transactionRule = transactionRule;
  }

  public Long getTransactionRuleMapped() {
    return transactionRuleMapped;
  }

  public void setTransactionRuleMapped(Long transactionRuleMapped) {
    this.transactionRuleMapped = transactionRuleMapped;
  }
}
