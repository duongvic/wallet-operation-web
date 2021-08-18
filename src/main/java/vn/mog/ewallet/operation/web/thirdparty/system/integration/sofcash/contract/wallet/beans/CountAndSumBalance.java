package vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.beans;

public class CountAndSumBalance {

  private Long totalPi;
  private Long totalBalance;

  public Long getTotalPi() {
    return totalPi == null ? 0L : totalPi;
  }

  public void setTotalPi(Long totalPi) {
    this.totalPi = totalPi;
  }

  public Long getTotalBalance() {
    return totalBalance == null ? 0L : totalBalance;
  }

  public void setTotalBalance(Long totalBalance) {
    this.totalBalance = totalBalance;
  }

}
