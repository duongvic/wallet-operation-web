package vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class BalanceInquiryResponseType extends MobiliserResponseType implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String currency;

  protected Long balance;

  protected Long debitLimit;

  protected Long creditLimit;

  public String getCurrency() {
    return this.currency;
  }

  public void setCurrency(String value) {
    this.currency = value;
  }

  public Long getBalance() {
    return this.balance;
  }

  public void setBalance(Long value) {
    this.balance = value;
  }

  public Long getDebitLimit() {
    return this.debitLimit;
  }

  public void setDebitLimit(Long value) {
    this.debitLimit = value;
  }

  public Long getCreditLimit() {
    return this.creditLimit;
  }

  public void setCreditLimit(Long value) {
    this.creditLimit = value;
  }
}
