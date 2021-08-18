package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans;

import java.io.Serializable;
import java.util.Date;

public class CustomerContract implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private Long id;
  private Long customerId;
  private String contractNo;
  private Integer contractType;
  private Date issueDate;
  private Date signDate;
  private Date expiredDate;
  private String version;
  private Integer contractExtendedType;
  private Character isReceivedMail = Character.valueOf('N');
  private Integer reconcilation;
  private String taxNumber;
  private String taxPayer;
  private String taxAddress;

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

  public String getContractNo() {
    return contractNo;
  }

  public void setContractNo(String contractNo) {
    this.contractNo = contractNo;
  }

  public Integer getContractType() {
    return contractType;
  }

  public void setContractType(Integer contractType) {
    this.contractType = contractType;
  }

  public Date getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(Date issueDate) {
    this.issueDate = issueDate;
  }

  public Date getSignDate() {
    return signDate;
  }

  public void setSignDate(Date signDate) {
    this.signDate = signDate;
  }

  public Date getExpiredDate() {
    return expiredDate;
  }

  public void setExpiredDate(Date expiredDate) {
    this.expiredDate = expiredDate;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public Integer getContractExtendedType() {
    return contractExtendedType;
  }

  public void setContractExtendedType(Integer contractExtendedType) {
    this.contractExtendedType = contractExtendedType;
  }

  public Character getIsReceivedMail() {
    return isReceivedMail;
  }

  public void setIsReceivedMail(Character isReceivedMail) {
    this.isReceivedMail = isReceivedMail;
  }

  public String getTaxNumber() {
    return taxNumber;
  }

  public void setTaxNumber(String taxNumber) {
    this.taxNumber = taxNumber;
  }

  public String getTaxPayer() {
    return taxPayer;
  }

  public void setTaxPayer(String taxPayer) {
    this.taxPayer = taxPayer;
  }

  public String getTaxAddress() {
    return taxAddress;
  }

  public void setTaxAddress(String taxAddress) {
    this.taxAddress = taxAddress;
  }

  public Integer getReconcilation() {
    return reconcilation;
  }

  public void setReconcilation(Integer reconcilation) {
    this.reconcilation = reconcilation;
  }

}
