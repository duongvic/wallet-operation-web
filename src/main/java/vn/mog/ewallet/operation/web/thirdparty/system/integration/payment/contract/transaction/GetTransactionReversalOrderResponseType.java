package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction;


import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.TransactionReversalOrder;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetTransactionReversalOrderResponseType extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;
  
  private TransactionReversalOrder transactionReversalOrder;

  public TransactionReversalOrder getTransactionReversalOrder() {
    return transactionReversalOrder;
  }

  public void setTransactionReversalOrder(TransactionReversalOrder transactionReversalOrder) {
    this.transactionReversalOrder = transactionReversalOrder;
  }
}
