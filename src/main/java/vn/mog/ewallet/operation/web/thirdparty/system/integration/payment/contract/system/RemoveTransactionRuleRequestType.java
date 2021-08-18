package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class RemoveTransactionRuleRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected Long txnRuleId;

  public Long getTxnRuleId() {
    return txnRuleId;
  }

  public void setTxnRuleId(Long txnRuleId) {
    this.txnRuleId = txnRuleId;
  }
}
