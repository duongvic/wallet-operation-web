package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.Transaction;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class TransactionHoldApproveToSuccessResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;
  private Transaction transaction;

  public Transaction getTransaction() {
    return transaction;
  }

  public void setTransaction(Transaction transaction) {
    this.transaction = transaction;
  }
}
