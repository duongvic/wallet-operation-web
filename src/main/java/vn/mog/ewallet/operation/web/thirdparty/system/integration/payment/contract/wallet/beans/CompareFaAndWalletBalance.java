package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans;


public class CompareFaAndWalletBalance {

  private Balance faBalance;
  private Balance walletBalance;
  private Boolean isEqual;

  public Balance getFaBalance() {
    if (faBalance == null) {
      faBalance = new Balance();
    }
    return faBalance;
  }

  public void setFaBalance(Balance faBalance) {
    this.faBalance = faBalance;
  }

  public Balance getWalletBalance() {
    if (walletBalance == null) {
      walletBalance = new Balance();
    }
    return walletBalance;
  }

  public void setWalletBalance(Balance walletBalance) {
    this.walletBalance = walletBalance;
  }

  public Boolean getIsEqual() {
    return isEqual;
  }

  public void setIsEqual(Boolean isEqual) {
    this.isEqual = isEqual;
  }
}
