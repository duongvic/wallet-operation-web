package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction;

import java.util.ArrayList;
import java.util.Collection;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.Transaction;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindTransactionsResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;
  protected Collection<Transaction> transactions;
  protected Long totalTxn;
  protected Long all;
  protected Long totalRequestAmount;
  protected Long totalCapitalValue;
  protected Long totalNetAmount;
  protected Long totalCommision;
  protected Long totalFee;
  protected Long totalGrossProfit;
  protected Long totalCashBack;

  public Collection<Transaction> getTransactions() {
    return transactions == null ? new ArrayList<>() : transactions;
  }

  public void setTransactions(Collection<Transaction> transactions) {
    this.transactions = transactions;
  }

  public Long getTotalTxn() {
    return totalTxn == null ? 0L : totalTxn;
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
    return totalCapitalValue == null ? 0L : totalCapitalValue;
  }

  public void setTotalCapitalValue(Long totalCapitalValue) {
    this.totalCapitalValue = totalCapitalValue;
  }

  public Long getTotalNetAmount() {
    return totalNetAmount == null ? 0L : totalNetAmount;
  }

  public void setTotalNetAmount(Long totalNetAmount) {
    this.totalNetAmount = totalNetAmount;
  }

  public Long getTotalCommision() {
    return totalCommision == null ? 0L : totalCommision;
  }

  public void setTotalCommision(Long totalCommision) {
    this.totalCommision = totalCommision;
  }

  public Long getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(Long totalFee) {
    this.totalFee = totalFee;
  }

  public Long getTotalGrossProfit() {
    return totalGrossProfit;
  }

  public void setTotalGrossProfit(Long totalGrossProfit) {
    this.totalGrossProfit = totalGrossProfit;
  }

  public Long getTotalCashBack() {
    return totalCashBack;
  }

  public void setTotalCashBack(Long totalCashBack) {
    this.totalCashBack = totalCashBack;
  }
}
