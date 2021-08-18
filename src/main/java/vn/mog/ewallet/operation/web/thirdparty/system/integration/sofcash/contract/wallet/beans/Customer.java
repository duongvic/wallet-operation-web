package vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.beans;

import java.util.Date;

public class Customer implements java.io.Serializable {

  private Long id;
  private Long piId;
  private Long parentId;
  private String cif;
  private String userName;
  private String displayName;
  private String timeZone;
  private String language;
  private String country;
  private Date dateOfBirth;
  private String securityQuestion;
  private String securityAnswer;
  private String customerType;
  private String customerStatus;
  private Date createdTime;

  private Integer walletTypeId;
  private Integer userTypeId;

  private Balance balance;

  public Customer() {
    super();
    // TODO Auto-generated constructor stub
  }

  public Customer(Long id, Long parentId, String cif, String userName, String displayName, String timeZone,
      String language, String country, Date dateOfBirth, String securityQuestion, String securityAnswer,
      String customerType, String customerStatus, Date createdTime, Integer walletTypeId, Integer userTypeId,
      Balance balance) {
    super();
    this.id = id;
    this.parentId = parentId;
    this.cif = cif;
    this.userName = userName;
    this.displayName = displayName;
    this.timeZone = timeZone;
    this.language = language;
    this.country = country;
    this.dateOfBirth = dateOfBirth;
    this.securityQuestion = securityQuestion;
    this.securityAnswer = securityAnswer;
    this.customerType = customerType;
    this.customerStatus = customerStatus;
    this.createdTime = createdTime;
    this.walletTypeId = walletTypeId;
    this.userTypeId = userTypeId;
    this.balance = balance;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getPiId() {
    return piId;
  }

  public void setPiId(Long piId) {
    this.piId = piId;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public String getCif() {
    return this.cif;
  }

  public void setCif(String cif) {
    this.cif = cif;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getDisplayName() {
    return this.displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getTimeZone() {
    return timeZone;
  }

  public void setTimeZone(String timeZone) {
    this.timeZone = timeZone;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getSecurityQuestion() {
    return securityQuestion;
  }

  public void setSecurityQuestion(String securityQuestion) {
    this.securityQuestion = securityQuestion;
  }

  public String getSecurityAnswer() {
    return securityAnswer;
  }

  public void setSecurityAnswer(String securityAnswer) {
    this.securityAnswer = securityAnswer;
  }

  public String getCustomerType() {
    return customerType;
  }

  public void setCustomerType(String customerTypeId) {
    this.customerType = customerTypeId;
  }

  public String getCustomerStatus() {
    return customerStatus;
  }

  public void setCustomerStatus(String customerStatus) {
    this.customerStatus = customerStatus;
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  public Balance getBalance() {
    return balance;
  }

  public void setBalance(Balance balance) {
    this.balance = balance;
  }

  public Integer getWalletTypeId() {
    return walletTypeId;
  }

  public void setWalletTypeId(Integer walletTypeId) {
    this.walletTypeId = walletTypeId;
  }

  public Integer getUserTypeId() {
    return userTypeId;
  }

  public void setUserTypeId(Integer userTypeId) {
    this.userTypeId = userTypeId;
  }

  public Customer clone() {
    Customer customer = new Customer(this.id, this.parentId, this.cif, this.userName, this.displayName,
        this.timeZone, this.language, this.country, this.dateOfBirth, this.securityQuestion,
        this.securityAnswer, this.customerType, this.customerStatus, this.createdTime, this.walletTypeId,
        this.userTypeId, this.balance.clone());
    return customer;
  }

}
