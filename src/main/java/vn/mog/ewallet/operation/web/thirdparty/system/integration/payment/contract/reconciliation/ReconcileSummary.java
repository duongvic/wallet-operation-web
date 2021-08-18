package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation;

public class ReconcileSummary {

  private Long totalTxn;
  private Long firstPreBalance;
  private Long lastPostBalance;
  private Long totalCredit;
  private Long totalDebit;
  private Long actualBalance;

  public ReconcileSummary() {
  }

  public ReconcileSummary(Long totalTxn, Long firstPreBalance, Long lastPostBalance,
      Long totalCredit, Long totalDebit, Long actualBalance) {
    this.totalTxn = totalTxn;
    this.firstPreBalance = firstPreBalance;
    this.lastPostBalance = lastPostBalance;
    this.totalCredit = totalCredit;
    this.totalDebit = totalDebit;
    this.actualBalance = actualBalance;
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

  public Long getTotalCredit() {
    return totalCredit;
  }

  public void setTotalCredit(Long totalCredit) {
    this.totalCredit = totalCredit;
  }

  public Long getTotalDebit() {
    return totalDebit;
  }

  public void setTotalDebit(Long totalDebit) {
    this.totalDebit = totalDebit;
  }

  public Long getActualBalance() {
    return actualBalance;
  }

  public void setActualBalance(Long actualBalance) {
    this.actualBalance = actualBalance;
  }

  public Long getTotalTxn() {
    return totalTxn;
  }

  public void setTotalTxn(Long totalTxn) {
    this.totalTxn = totalTxn;
  }


}
