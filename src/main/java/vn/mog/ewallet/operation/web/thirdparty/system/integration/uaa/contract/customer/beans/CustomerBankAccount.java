package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans;

import java.io.Serializable;
import java.util.Date;

public class CustomerBankAccount implements Serializable {

  private static final long serialVersionUID = 1L;
  protected Long id;
  protected Long customerId;
  protected String accountHolderName;
  protected String accountNumber;
  protected String displayNumber;
  protected String bankCode;
  protected String branchCode;
  protected String branchName;
  protected String bankName;
  protected Date registerDate;
  protected String bankCountry;
  protected String bankCity;
  protected String changeTime;
  protected String registerSource;
  protected Boolean active;
  protected boolean verify;
  protected Date endDate;
  protected Date lastUpdate;

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

  public String getAccountHolderName() {
    return this.accountHolderName;
  }

  public void setAccountHolderName(String value) {
    this.accountHolderName = value;
  }

  public String getAccountNumber() {
    return this.accountNumber;
  }

  public void setAccountNumber(String value) {
    this.accountNumber = value;
  }

  public String getDisplayNumber() {
    return this.displayNumber;
  }

  public void setDisplayNumber(String value) {
    this.displayNumber = value;
  }

  public String getBankCode() {
    return this.bankCode;
  }

  public void setBankCode(String value) {
    this.bankCode = value;
  }

  public String getBranchCode() {
    return this.branchCode;
  }

  public void setBranchCode(String value) {
    this.branchCode = value;
  }

  public String getBankName() {
    return this.bankName;
  }

  public void setBankName(String value) {
    this.bankName = value;
  }

  public String getBankCountry() {
    return this.bankCountry;
  }

  public void setBankCountry(String value) {
    this.bankCountry = value;
  }

  public String getBankCity() {
    return this.bankCity;
  }

  public void setBankCity(String value) {
    this.bankCity = value;
  }

  public Date getLastUpdate() {
    return this.lastUpdate;
  }

  public void setLastUpdate(Date value) {
    this.lastUpdate = value;
  }

  public Boolean isActive() {
    return this.active;
  }

  public boolean isVerify() {
    return this.verify;
  }

  public void setVerify(boolean value) {
    this.verify = value;
  }

  public String getBranchName() {
    return branchName;
  }

  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }

  public Date getRegisterDate() {
    return registerDate;
  }

  public void setRegisterDate(Date registerDate) {
    this.registerDate = registerDate;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean value) {
    this.active = value;
  }

  public String getChangeTime() {
    return changeTime;
  }

  public void setChangeTime(String changeTime) {
    this.changeTime = changeTime;
  }

  public String getRegisterSoure() {
    return registerSource;
  }

  public void setRegisterSoure(String registerSource) {
    this.registerSource = registerSource;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }


}
