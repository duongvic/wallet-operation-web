package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation;


import java.util.Collection;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.Transaction;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.Customer;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class ReconcileTransactionFindResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private Collection<Transaction> transactions;
  private Long firstPreBalance;
  private Long lastPostBalance;
  private Long totalCashIn;
  private Long totalCashOut;
  private Long endBalance;
  private Long totalTxn;

  private Customer customer;

  public Collection<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(Collection<Transaction> transactions) {
    this.transactions = transactions;
  }

  public Long getFirstPreBalance() {
    return firstPreBalance;
  }

  public void setFirstPreBalance(Long firstPreBalance) {
    this.firstPreBalance = firstPreBalance;
  }

  public Long getLastPostBalance() {
    return lastPostBalance;
  }

  public void setLastPostBalance(Long lastPostBalance) {
    this.lastPostBalance = lastPostBalance;
  }

  public Long getEndBalance() {
    return endBalance;
  }

  public void setEndBalance(Long endBalance) {
    this.endBalance = endBalance;
  }

  public Long getTotalTxn() {
    return totalTxn;
  }

  public void setTotalTxn(Long totalTxn) {
    this.totalTxn = totalTxn;
  }

  public Long getTotalCashIn() {
    return totalCashIn;
  }

  public void setTotalCashIn(Long totalCashIn) {
    this.totalCashIn = totalCashIn;
  }

  public Long getTotalCashOut() {
    return totalCashOut;
  }

  public void setTotalCashOut(Long totalCashOut) {
    this.totalCashOut = totalCashOut;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(
      Customer customer) {
    this.customer = customer;
  }
}
