package vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.beans;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.OrgUnit;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.type.GenderType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Address;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.KycRequestStatus;

public class Customer implements Serializable {

  private static final long serialVersionUID = 1L;
  protected Long id;
  protected Long parentId;
  protected String parentName;
  protected String prarentEmail;
  protected String parentCif;
  protected String parentMsisdn;
  private List<Address> addresses;

  protected OrgUnit orgUnit;
  protected Integer blackListReason;
  protected Boolean active;
  protected boolean test;
  protected String displayName;
  protected String nickName;
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
  protected Long limitSetId;
  protected String txnText;
  protected Integer notificationModeId;
  protected Integer classificationId;

  // ----------------
  protected String cif;
  protected String msisdn;
  protected String email;
  protected String firstName;
  protected String lastName;
  protected GenderType gender;

  protected String livingAddress;
  protected String province;
  protected String district;
  protected String commune;
  protected String state;
  protected String region;
  protected Integer jobOccupation;
  protected Integer jobPosition;

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
  protected String remark;

  protected Date created;
  protected Date updated;

  private List<CustomerContact> customerContacts;

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

  public String getPrarentEmail() {
    return prarentEmail;
  }

  public void setParentName(String parentName) {
    this.parentName = parentName;
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

  public void setActive(Boolean value) {
    this.active = value;
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

  public CustomerType getCustomerType() {
    return customerType;
  }

  public void setRiskCategoryId(Integer riskCategoryId) {
    this.riskCategoryId = riskCategoryId;
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

  public void setNotificationModeId(Integer value) {
    this.notificationModeId = value;
  }

  // ----------------

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

  public GenderType getGender() {
    return gender;
  }

  public void setGender(GenderType gender) {
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

  public Integer getWfState() {
    return wfState;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public void setWfState(Integer wfState) {
    this.wfState = wfState;
  }

  public Integer getJobOccupation() {
    return jobOccupation;
  }

  public void setJobOccupation(Integer jobOccupation) {
    this.jobOccupation = jobOccupation;
  }

  public Integer getJobPosition() {
    return jobPosition;
  }

  public void setJobPosition(Integer jobPosition) {
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

  public void setVerifyType(Integer verifyType) {
    this.verifyType = verifyType;
  }

  // ----------------

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

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public Integer getWalletTypeId() {
    return walletTypeId;
  }

  public Integer getUserTypeId() {
    return userTypeId;
  }

  public void setWalletTypeId(Integer walletTypeId) {
    this.walletTypeId = walletTypeId;
  }

  public void setUserTypeId(Integer userTypeId) {
    this.userTypeId = userTypeId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public List<CustomerContact> getCustomerContacts() {
    return customerContacts;
  }

  public void setCustomerContacts(List<CustomerContact> customerContacts) {
    this.customerContacts = customerContacts;
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

//  public String transferGenderToString() {
//    if (this.gender != null) {
//      return this.gender.getName();
//    } else {
//      return StringUtils.EMPTY;
//    }
//  }

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
        ", nickName='" + nickName + '\'' +
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
        ", limitSetId=" + limitSetId +
        ", txnText='" + txnText + '\'' +
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
        ", jobOccupation=" + jobOccupation +
        ", jobPosition=" + jobPosition +
        ", kycStatus=" + kycStatus +
        ", verifyType=" + verifyType +
        ", wfState=" + wfState +
        ", provinceAddr='" + provinceAddr + '\'' +
        ", level=" + level +
        ", refAccount='" + refAccount + '\'' +
        ", walletTypeId=" + walletTypeId +
        ", userTypeId=" + userTypeId +
        ", description='" + description + '\'' +
        ", remark='" + remark + '\'' +
        ", created=" + created +
        ", updated=" + updated +
        ", customerContacts=" + customerContacts +
        '}';
  }
}
