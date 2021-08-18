package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.OrgUnit;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean.CustomerPrivilege;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean.CustomerRole;

public class Customer implements Serializable {

  private static final long serialVersionUID = 1L;
  protected Long id;
  protected Long parentId;
  protected String parentName;
  protected String prarentEmail;
  protected String parentCif;
  protected String parentMsisdn;
  protected List<Address> addresses;

  protected OrgUnit orgUnit;
  protected Integer blackListReason;
  protected Boolean active;
  protected boolean test;
  protected String displayName;
  protected String timeZone;
  protected String language;
  protected String country;
  protected String securityQuestion;
  protected String securityAnswer;

  protected Date dateOfBirth;
  protected Integer riskCategoryId;
  protected CustomerType customerType;
  protected Integer cancellationReasonId;
  protected String referralCode;
  protected String txnText;
  protected Long limitSetId;
  protected Integer notificationModeId;
  protected Integer classificationId;

  // ----------------
  protected String cif;
  protected String msisdn;
  protected String email;
  protected String firstName;
  protected String lastName;
  protected Integer gender;

  protected String livingAddress;
  protected String province;
  protected String district;
  protected String commune;
  protected String state;
  protected String region;
  protected String jobOccupation;
  protected String jobPosition;

  protected Integer kycStatus;
  protected Integer verifyType;

  protected Integer wfState;
  protected String provinceAddr;
  // ----------------

  protected Integer level;
  protected String refAccount;

  protected Integer walletTypeId;
  protected Integer userTypeId;
  protected String description;

  protected Date created;
  protected Date updated;

  protected Long balance;
  protected Boolean linked;
// ----------------

  private Collection<CustomerRole> customerRoles;

  private Collection<CustomerPrivilege> customerPrivileges;
  private String bizChannelId;

  public Customer() {
  }

  public Customer(Long id, Long parentId, String parentName, String prarentEmail,
      String parentCif, String parentMsisdn,
      List<Address> addresses, OrgUnit orgUnit, Integer blackListReason, Boolean active,
      boolean test,
      String displayName, String timeZone, String language, String country,
      String securityQuestion, String securityAnswer, Date dateOfBirth,
      Integer riskCategoryId, CustomerType customerType, Integer cancellationReasonId,
      String referralCode, String txnText, Long limitSetId, Integer notificationModeId,
      Integer classificationId, String cif, String msisdn, String email, String firstName,
      String lastName, Integer gender, String livingAddress, String province,
      String district, String commune, String state, String region, String jobOccupation,
      String jobPosition, Integer kycStatus, Integer verifyType, Integer wfState,
      String provinceAddr, Integer level, String refAccount, Integer walletTypeId,
      Integer userTypeId, Date created, Date updated,
      Collection<CustomerRole> customerRoles,
      Collection<CustomerPrivilege> customerPrivileges, Long balance) {
    this.id = id;
    this.parentId = parentId;
    this.parentName = parentName;
    this.prarentEmail = prarentEmail;
    this.parentCif = parentCif;
    this.parentMsisdn = parentMsisdn;
    this.addresses = addresses;
    this.orgUnit = orgUnit;
    this.blackListReason = blackListReason;
    this.active = active;
    this.test = test;
    this.displayName = displayName;
    this.timeZone = timeZone;
    this.language = language;
    this.country = country;
    this.securityQuestion = securityQuestion;
    this.securityAnswer = securityAnswer;
    this.dateOfBirth = dateOfBirth;
    this.riskCategoryId = riskCategoryId;
    this.customerType = customerType;
    this.cancellationReasonId = cancellationReasonId;
    this.referralCode = referralCode;
    this.txnText = txnText;
    this.limitSetId = limitSetId;
    this.notificationModeId = notificationModeId;
    this.classificationId = classificationId;
    this.cif = cif;
    this.msisdn = msisdn;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.livingAddress = livingAddress;
    this.province = province;
    this.district = district;
    this.commune = commune;
    this.state = state;
    this.region = region;
    this.jobOccupation = jobOccupation;
    this.jobPosition = jobPosition;
    this.kycStatus = kycStatus;
    this.verifyType = verifyType;
    this.wfState = wfState;
    this.provinceAddr = provinceAddr;
    this.level = level;
    this.refAccount = refAccount;
    this.walletTypeId = walletTypeId;
    this.userTypeId = userTypeId;
    this.created = created;
    this.updated = updated;
    this.customerRoles = customerRoles;
    this.customerPrivileges = customerPrivileges;
    this.balance = balance;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long value) {
    this.id = value;
  }

  public Long getParentId() {
    return this.parentId;
  }

  public void setParentId(Long value) {
    this.parentId = value;
  }

  public String getParentName() {
    return parentName;
  }

  public void setParentName(String parentName) {
    this.parentName = parentName;
  }

  public String getPrarentEmail() {
    return prarentEmail;
  }

  public void setPrarentEmail(String prarentEmail) {
    this.prarentEmail = prarentEmail;
  }

  public String getParentCif() {
    return parentCif;
  }

  public void setParentCif(String parentCif) {
    this.parentCif = parentCif;
  }

  public String getParentMsisdn() {
    return parentMsisdn;
  }

  public void setParentMsisdn(String parentMsisdn) {
    this.parentMsisdn = parentMsisdn;
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  public OrgUnit getOrgUnit() {
    return orgUnit;
  }

  public void setOrgUnit(OrgUnit orgUnit) {
    this.orgUnit = orgUnit;
  }

  public Integer getBlackListReason() {
    return this.blackListReason;
  }

  public void setBlackListReason(Integer value) {
    this.blackListReason = value;
  }

  public Boolean isActive() {
    return this.active;
  }

  public boolean isTest() {
    return this.test;
  }

  public void setTest(boolean value) {
    this.test = value;
  }

  public String getDisplayName() {
    return this.displayName;
  }

  public void setDisplayName(String value) {
    this.displayName = value;
  }

  public String getTimeZone() {
    return this.timeZone;
  }

  public void setTimeZone(String value) {
    this.timeZone = value;
  }

  public String getLanguage() {
    return this.language;
  }

  public void setLanguage(String value) {
    this.language = value;
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String value) {
    this.country = value;
  }

  public String getSecurityQuestion() {
    return this.securityQuestion;
  }

  public void setSecurityQuestion(String value) {
    this.securityQuestion = value;
  }

  public String getSecurityAnswer() {
    return this.securityAnswer;
  }

  public void setSecurityAnswer(String value) {
    this.securityAnswer = value;
  }

  public Date getDateOfBirth() {
    return this.dateOfBirth;
  }

  public void setDateOfBirth(Date value) {
    this.dateOfBirth = value;
  }

  public Integer getRiskCategoryId() {
    return riskCategoryId;
  }

  public void setRiskCategoryId(Integer riskCategoryId) {
    this.riskCategoryId = riskCategoryId;
  }

  public CustomerType getCustomerType() {
    return customerType;
  }

  public void setCustomerType(CustomerType customerType) {
    this.customerType = customerType;
  }

  public Integer getCancellationReasonId() {
    return this.cancellationReasonId;
  }

  public void setCancellationReasonId(Integer value) {
    this.cancellationReasonId = value;
  }

  public String getReferralCode() {
    return this.referralCode;
  }

  public void setReferralCode(String value) {
    this.referralCode = value;
  }

  public String getTxnText() {
    return this.txnText;
  }

  public void setTxnText(String value) {
    this.txnText = value;
  }

  public Long getLimitSetId() {
    return this.limitSetId;
  }

  public void setLimitSetId(Long value) {
    this.limitSetId = value;
  }

  public Integer getNotificationModeId() {
    return this.notificationModeId;
  }

  // ----------------

  public void setNotificationModeId(Integer value) {
    this.notificationModeId = value;
  }

  public String getCif() {
    return cif;
  }

  public void setCif(String cif) {
    this.cif = cif;
  }

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public String getLivingAddress() {
    return livingAddress;
  }

  public void setLivingAddress(String livingAddress) {
    this.livingAddress = livingAddress;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getCommune() {
    return commune;
  }

  public void setCommune(String commune) {
    this.commune = commune;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public Integer getWfState() {
    return wfState;
  }

  public void setWfState(Integer wfState) {
    this.wfState = wfState;
  }

  public String getJobOccupation() {
    return jobOccupation;
  }

  public void setJobOccupation(String jobOccupation) {
    this.jobOccupation = jobOccupation;
  }

  public String getJobPosition() {
    return jobPosition;
  }

  public void setJobPosition(String jobPosition) {
    this.jobPosition = jobPosition;
  }

  public Integer getKycStatus() {
    return kycStatus;
  }

  public void setKycStatus(Integer kycStatus) {
    this.kycStatus = kycStatus;
  }

  public Integer getVerifyType() {
    return verifyType;
  }

  // ----------------

  public void setVerifyType(Integer verifyType) {
    this.verifyType = verifyType;
  }

  public String getRefAccount() {
    return refAccount;
  }

  public void setRefAccount(String refAccount) {
    this.refAccount = refAccount;
  }

  public Date getCreated() {
    return this.created;
  }

  public void setCreated(Date value) {
    this.created = value;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean value) {
    this.active = value;
  }

  public String getProvinceAddr() {
    return provinceAddr;
  }

  public void setProvinceAddr(String provinceAddr) {
    this.provinceAddr = provinceAddr;
  }

  public Integer getClassificationId() {
    return classificationId;
  }

  public void setClassificationId(Integer classificationId) {
    this.classificationId = classificationId;
  }

  // ----------------
  public Collection<CustomerRole> getCustomerRoles() {
    return customerRoles;
  }

  public void setCustomerRoles(Collection<CustomerRole> customerRoles) {
    this.customerRoles = customerRoles;
  }

  public boolean isSetCustomerRoles() {
    return customerRoles != null;
  }

  public Collection<CustomerPrivilege> getCustomerPrivileges() {
    return customerPrivileges;
  }

  public void setCustomerPrivileges(Collection<CustomerPrivilege> customerPrivileges) {
    this.customerPrivileges = customerPrivileges;
  }

  public boolean isSetCustomerPrivileges() {
    return customerPrivileges != null;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
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

  public Long getBalance() {
    return balance;
  }

  public void setBalance(Long balance) {
    this.balance = balance;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public String textKycStatus() {
    if (this.kycStatus != null) {
      return KycRequestStatus.getKycRequestStatus(String.valueOf(this.kycStatus)).getName();
    } else {
      return StringUtils.EMPTY;
    }
  }

  public String textDateOfBirth() {
    if (this.dateOfBirth != null) {
      return DateFormatUtils.format(dateOfBirth, "dd/MM/yyyy");
    } else {
      return StringUtils.EMPTY;
    }
  }

  public String textCreatedDate() {
    if (this.created != null) {
      return DateFormatUtils.format(created, "dd/MM/yyyy");
    } else {
      return StringUtils.EMPTY;
    }
  }

  public String textUpdatedDate() {
    if (this.updated != null) {
      return DateFormatUtils.format(updated, "dd/MM/yyyy");
    } else {
      return StringUtils.EMPTY;
    }
  }

  public Boolean getLinked() {
    return linked;
  }

  public void setLinked(Boolean linked) {
    this.linked = linked;
  }

  public String getBizChannelId() {
    return bizChannelId;
  }

  public void setBizChannelId(String bizChannelId) {
    this.bizChannelId = bizChannelId;
  }

  @Override
  public String toString() {
    return "Customer{" +
        "id=" + id +
        ", parentId=" + parentId +
        ", parentName='" + parentName + '\'' +
        ", prarentEmail='" + prarentEmail + '\'' +
        ", parentCif='" + parentCif + '\'' +
        ", parentMsisdn='" + parentMsisdn + '\'' +
        ", addresses=" + addresses +
        ", orgUnit=" + orgUnit +
        ", blackListReason=" + blackListReason +
        ", active=" + active +
        ", test=" + test +
        ", displayName='" + displayName + '\'' +
        ", timeZone='" + timeZone + '\'' +
        ", language='" + language + '\'' +
        ", country='" + country + '\'' +
        ", securityQuestion='" + securityQuestion + '\'' +
        ", securityAnswer='" + securityAnswer + '\'' +
        ", dateOfBirth=" + dateOfBirth +
        ", riskCategoryId=" + riskCategoryId +
        ", customerType=" + customerType +
        ", cancellationReasonId=" + cancellationReasonId +
        ", referralCode='" + referralCode + '\'' +
        ", txnText='" + txnText + '\'' +
        ", limitSetId=" + limitSetId +
        ", notificationModeId=" + notificationModeId +
        ", classificationId=" + classificationId +
        ", cif='" + cif + '\'' +
        ", msisdn='" + msisdn + '\'' +
        ", email='" + email + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", gender=" + gender +
        ", livingAddress='" + livingAddress + '\'' +
        ", province='" + province + '\'' +
        ", district='" + district + '\'' +
        ", commune='" + commune + '\'' +
        ", state='" + state + '\'' +
        ", region='" + region + '\'' +
        ", jobOccupation='" + jobOccupation + '\'' +
        ", jobPosition='" + jobPosition + '\'' +
        ", kycStatus=" + kycStatus +
        ", verifyType=" + verifyType +
        ", wfState=" + wfState +
        ", provinceAddr='" + provinceAddr + '\'' +
        ", level=" + level +
        ", refAccount='" + refAccount + '\'' +
        ", walletTypeId=" + walletTypeId +
        ", userTypeId=" + userTypeId +
        ", description='" + description + '\'' +
        ", created=" + created +
        ", updated=" + updated +
        ", balance=" + balance +
        ", customerRoles=" + customerRoles +
        ", customerPrivileges=" + customerPrivileges +
        '}';
  }
}
