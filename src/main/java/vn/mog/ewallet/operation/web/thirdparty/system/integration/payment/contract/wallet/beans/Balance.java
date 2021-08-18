package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans;

public class Balance {

  private Long currBalance;
  private Long creditBalance;
  private Long creditReservedBalance;
  private Long debitBalance;
  private Long debitReservedBalance;

  public Balance() {
    this.currBalance = 0L;
    this.creditBalance = 0L;
    this.creditReservedBalance = 0L;
    this.debitBalance = 0L;
    this.debitReservedBalance = 0L;
  }

  public Balance(Long currBalance, Long creditBalance, Long creditReservedBalance, Long debitBalance, Long debitReservedBalance) {
    this.currBalance = currBalance;
    this.creditBalance = creditBalance;
    this.creditReservedBalance = creditReservedBalance;
    this.debitBalance = debitBalance;
    this.debitReservedBalance = debitReservedBalance;
  }

  public Long getCurrBalance() {
    if (currBalance == null) {
      currBalance = 0L;
    }
    return currBalance;
  }

  public void setCurrBalance(Long currBalance) {
    this.currBalance = currBalance;
  }

  public Long getCreditBalance() {
    return creditBalance;
  }

  public void setCreditBalance(Long creditBalance) {
    this.creditBalance = creditBalance;
  }

  public Long getCreditReservedBalance() {
    return creditReservedBalance;
  }

  public void setCreditReservedBalance(Long creditReservedBalance) {
    this.creditReservedBalance = creditReservedBalance;
  }

  public Long getDebitBalance() {
    return debitBalance;
  }

  public void setDebitBalance(Long debitBalance) {
    this.debitBalance = debitBalance;
  }

  public Long getDebitReservedBalance() {
    return debitReservedBalance;
  }

  public void setDebitReservedBalance(Long debitReservedBalance) {
    this.debitReservedBalance = debitReservedBalance;
  }

  public void add(Balance balance) {
    if (balance == null) {
      return;
    }
    this.currBalance += balance.getCurrBalance();
    this.creditBalance += balance.getCreditBalance();
    this.creditReservedBalance += balance.getCreditReservedBalance();
    this.debitBalance += balance.getDebitBalance();
    this.debitReservedBalance += balance.getDebitReservedBalance();

  }

}
