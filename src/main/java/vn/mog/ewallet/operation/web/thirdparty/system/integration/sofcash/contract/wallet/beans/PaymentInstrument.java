package vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.beans;

import java.io.Serializable;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType;

public class PaymentInstrument implements Serializable {

  private static final long serialVersionUID = 1L;
  protected Long id;
  protected Long customerId;
  protected String customerCif;
  protected String alias;
  protected boolean active;
  protected String currency;
  protected Long creditBalance;
  protected Long creditReserved;
  protected Long debitBalance;
  protected Long debitReserved;

  protected Long maxBalance;
  protected Long minBalance;


  protected Integer customerTypeId;
  protected Integer userTypeId;
  protected Integer walletTypeId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public String getCustomerCif() {
    return customerCif;
  }

  public void setCustomerCif(String customerCif) {
    this.customerCif = customerCif;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public boolean isActive() {
    return this.active;
  }

  public void setActive(boolean value) {
    this.active = value;
  }

  public String getCurrency() {
    return this.currency;
  }

  public void setCurrency(String value) {
    this.currency = value;
  }

  public Long getBalance() {
    return (this.creditBalance - this.debitReserved - this.debitBalance);
  }

  public Long getCreditBalance() {
    return this.creditBalance;
  }

  public void setCreditBalance(Long value) {
    this.creditBalance = value;
  }

  public Long getCreditReserved() {
    return this.creditReserved;
  }

  public void setCreditReserved(Long value) {
    this.creditReserved = value;
  }

  public Long getDebitBalance() {
    return this.debitBalance;
  }

  public void setDebitBalance(Long value) {
    this.debitBalance = value;
  }

  public Long getDebitReserved() {
    return this.debitReserved;
  }

  public void setDebitReserved(Long value) {
    this.debitReserved = value;
  }

  public Long getMaxBalance() {
    return this.maxBalance;
  }

  public void setMaxBalance(Long value) {
    this.maxBalance = value;
  }

  public Long getMinBalance() {
    return this.minBalance;
  }

  public void setMinBalance(Long value) {
    this.minBalance = value;
  }

  public Integer getCustomerTypeId() {
    return customerTypeId;
  }

  public void setCustomerTypeId(Integer customerTypeId) {
    this.customerTypeId = customerTypeId;
  }

  public Integer getUserTypeId() {
    return userTypeId;
  }

  public void setUserTypeId(Integer userTypeId) {
    this.userTypeId = userTypeId;
  }

  public Integer getWalletTypeId() {
    return walletTypeId;
  }

  public void setWalletTypeId(Integer walletTypeId) {
    this.walletTypeId = walletTypeId;
  }

  public String getCustomerTypeDisplayName() {
    return CustomerType.FULL_CUSTOMER_TYPES.get(this.customerTypeId);
  }
}
