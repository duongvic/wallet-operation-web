package vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.beans;

public class Balance {

  private Long balance;
  private Long creditLimit;
  private String currency;
  private Long debitLimit;

  public Balance() {
    this.balance = 0L;
    this.creditLimit = 0L;
    this.debitLimit = 0L;
  }

  public Balance(Long balance, Long creditLimit, String currency, Long debitLimit) {
    super();
    this.balance = balance;
    this.creditLimit = creditLimit;
    this.currency = currency;
    this.debitLimit = debitLimit;
  }

  public Long getBalance() {
    return this.balance;
  }

  public void setBalance(Long balance) {
    this.balance = balance;
  }

  public Long getCreditLimit() {
    return this.creditLimit;
  }

  public void setCreditLimit(Long creditLimit) {
    this.creditLimit = creditLimit;
  }

  public String getCurrency() {
    return this.currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Long getDebitLimit() {
    return this.debitLimit;
  }

  public void setDebitLimit(Long debitLimit) {
    this.debitLimit = debitLimit;
  }

  public void add(long amount) {
    this.balance = this.balance + amount;
  }

  public Balance clone() {
    Balance balance = new Balance(this.balance, this.creditLimit, this.currency, this.debitLimit);
    return balance;
  }
}
