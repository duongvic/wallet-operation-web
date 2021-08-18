package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans;

import java.io.Serializable;
import java.util.Date;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.Bank;

public class CustomerBankDirect implements Serializable {

  private static final long serialVersionUID = 1L;
  /*    */
  private Date createdTime;
  private Long creatorId;
  private Date lastUpdatedTime;
  private Long lastUpdatedId;
  /*    */
  private Customer customer;
  private Bank bank;
  private String bankAccountNumber;
  private String bankAccountName;
  private Date linkedDate;
  private Date unlinkedDate;
  private String status;

  public CustomerBankDirect() {
    super();
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  public Long getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(Long creatorId) {
    this.creatorId = creatorId;
  }

  public Date getLastUpdatedTime() {
    return lastUpdatedTime;
  }

  public void setLastUpdatedTime(Date lastUpdatedTime) {
    this.lastUpdatedTime = lastUpdatedTime;
  }

  public Long getLastUpdatedId() {
    return lastUpdatedId;
  }

  public void setLastUpdatedId(Long lastUpdatedId) {
    this.lastUpdatedId = lastUpdatedId;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(
      Customer customer) {
    this.customer = customer;
  }

  public Bank getBank() {
    return bank;
  }

  public void setBank(Bank bank) {
    this.bank = bank;
  }

  public Date getLinkedDate() {
    return linkedDate;
  }

  public void setLinkedDate(Date linkedDate) {
    this.linkedDate = linkedDate;
  }

  public Date getUnlinkedDate() {
    return unlinkedDate;
  }

  public void setUnlinkedDate(Date unlinkedDate) {
    this.unlinkedDate = unlinkedDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getBankAccountNumber() {
    return bankAccountNumber;
  }

  public void setBankAccountNumber(String bankAccountNumber) {
    this.bankAccountNumber = bankAccountNumber;
  }

  public String getBankAccountName() {
    return bankAccountName;
  }

  public void setBankAccountName(String bankAccountName) {
    this.bankAccountName = bankAccountName;
  }

}
