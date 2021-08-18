package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system;

import java.util.ArrayList;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.TransactionRule;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindTransactionRuleResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  protected List<TransactionRule> transactionRules;
  protected Long total;

  public List<TransactionRule> getTransactionRules() {
    if (transactionRules == null) {
      transactionRules = new ArrayList<>();
    }
    return transactionRules;
  }

  public void setTransactionRules(List<TransactionRule> transactionRules) {
    this.transactionRules = transactionRules;
  }

  public Long getTotal() {
    if (total == null) {
      total = 0L;
    }
    return total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }
}
