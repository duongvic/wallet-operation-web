package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class UpdateTrueServiceTransactionRuleMatrixRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected Long trueServiceId;
  protected Long transactionRuleId;

  public Long getTrueServiceId() {
    return trueServiceId;
  }

  public void setTrueServiceId(Long trueServiceId) {
    this.trueServiceId = trueServiceId;
  }

  public Long getTransactionRuleId() {
    return transactionRuleId;
  }

  public void setTransactionRuleId(Long transactionRuleId) {
    this.transactionRuleId = transactionRuleId;
  }
}
