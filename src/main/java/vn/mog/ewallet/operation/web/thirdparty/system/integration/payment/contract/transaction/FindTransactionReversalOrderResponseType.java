package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction;

import java.util.Collection;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.TransactionReversalOrder;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindTransactionReversalOrderResponseType extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;
  protected Collection<TransactionReversalOrder> transactionReversalOrders;
  protected Long totalTxn;
  protected Long all;
  protected Long totalRequestAmount;
  protected Long totalCapitalValue;
  protected Long totalNetAmount;


  public Collection<TransactionReversalOrder> getTransactionReversalOrders() {
    return transactionReversalOrders;
  }

  public void setTransactionReversalOrders(
      Collection<TransactionReversalOrder> transactionReversalOrders) {
    this.transactionReversalOrders = transactionReversalOrders;
  }

  public Long getTotalTxn() {
    return totalTxn;
  }

  public void setTotalTxn(Long totalTxn) {
    this.totalTxn = totalTxn;
  }

  public Long getAll() {
    return all;
  }

  public void setAll(Long all) {
    this.all = all;
  }

  public Long getTotalRequestAmount() {
    return totalRequestAmount;
  }

  public void setTotalRequestAmount(Long totalRequestAmount) {
    this.totalRequestAmount = totalRequestAmount;
  }

  public Long getTotalCapitalValue() {
    return totalCapitalValue;
  }

  public void setTotalCapitalValue(Long totalCapitalValue) {
    this.totalCapitalValue = totalCapitalValue;
  }

  public Long getTotalNetAmount() {
    return totalNetAmount;
  }

  public void setTotalNetAmount(Long totalNetAmount) {
    this.totalNetAmount = totalNetAmount;
  }


}
