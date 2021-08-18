package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.SenderType;

public class CustomerVerifyKycRequest {

  protected Long id;
  protected Long customerId;
  protected String cif;
  protected Integer requestStatusId;
  protected String remark;
  protected String msisdn;
  protected String fistName;
  protected String lastName;
  protected Integer kycStatus;
  protected Date dateOfBirth;

  protected Identity identity;
  protected String permanentResident;
  protected String province;
  protected String district;

  protected String frontName;
  protected String frontContentType;
  protected String frontContent;

  protected String backName;
  protected String backContentType;
  protected String backContent;

  protected String selfieName;
  protected String selfieContentType;
  protected String selfieContent;
  private SenderType senderType;

  //Enterprise
  private KycType kycType;
  private String companyName;
  private String crn;
  private Date crnDateIssued;
  private String crnIssuePlace;
  private String identityImgName;
  private AttachmentContentType identityImgContentType;
  private String identityImgContent;
  private String representativeName;
  private String representativePosition;

  protected Date created;
  protected Date updated;

  public String textCreatedDate() {
    if (this.created != null) {
      return DateFormatUtils.format(this.created, "dd/MM/yyyy");
    } else {
      return StringUtils.EMPTY;
    }
  }

  public String textUpdatedDate() {
    if (this.updated != null) {
      return DateFormatUtils.format(this.updated, "dd/MM/yyyy");
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

  public String textRequestStatusId() {
    return KycRequestStatus.getKycRequestStatus(String.valueOf(this.requestStatusId)).getName();
  }

  public String textKycStatus() {
    return SecurityTaskStatus.getSecurityTaskStatus(String.valueOf(this.kycStatus)).getName();
  }

  public String base64FrontImage() {
    return "data:" + this.frontContentType + ";base64," + this.frontContent;
  }

  public boolean existFrontImage() {
    return this.frontContent == null;
  }

  public String base64BackImage() {
    return "data:" + this.backContentType + ";base64," + this.backContent;
  }

  public boolean existBackImage() {
    return this.backContent == null;
  }

  public String base64SelfieImage() {
    return "data:" + this.selfieContentType + ";base64," + this.selfieContent;
  }

  public boolean existSelfieImage() {
    return this.selfieContent == null;
  }

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

  public Integer getRequestStatusId() {
    return requestStatusId;
  }

  public void setRequestStatusId(Integer requestStatusId) {
    this.requestStatusId = requestStatusId;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public String getFistName() {
    return fistName;
  }

  public void setFistName(String fistName) {
    this.fistName = fistName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getCif() {
    return cif;
  }

  public void setCif(String cif) {
    this.cif = cif;
  }

  public Integer getKycStatus() {
    return kycStatus;
  }

  public void setKycStatus(Integer kycStatus) {
    this.kycStatus = kycStatus;
  }

  public String getFrontName() {
    return frontName;
  }

  public void setFrontName(String frontName) {
    this.frontName = frontName;
  }

  public String getFrontContentType() {
    return frontContentType;
  }

  public void setFrontContentType(String frontContentType) {
    this.frontContentType = frontContentType;
  }

  public String getBackName() {
    return backName;
  }

  public void setBackName(String backName) {
    this.backName = backName;
  }

  public String getBackContentType() {
    return backContentType;
  }

  public void setBackContentType(String backContentType) {
    this.backContentType = backContentType;
  }

  public String getSelfieName() {
    return selfieName;
  }

  public void setSelfieName(String selfieName) {
    this.selfieName = selfieName;
  }

  public String getSelfieContentType() {
    return selfieContentType;
  }

  public void setSelfieContentType(String selfieContentType) {
    this.selfieContentType = selfieContentType;
  }

  public Identity getIdentity() {
    return identity;
  }

  public void setIdentity(Identity identity) {
    this.identity = identity;
  }

  public String getPermanentResident() {
    return permanentResident;
  }

  public void setPermanentResident(String permanentResident) {
    this.permanentResident = permanentResident;
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

  public String getFrontContent() {
    return frontContent;
  }

  public void setFrontContent(String frontContent) {
    this.frontContent = frontContent;
  }

  public String getBackContent() {
    return backContent;
  }

  public void setBackContent(String backContent) {
    this.backContent = backContent;
  }

  public String getSelfieContent() {
    return selfieContent;
  }

  public void setSelfieContent(String selfieContent) {
    this.selfieContent = selfieContent;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public SenderType getSenderType() {
    return senderType;
  }

  public void setSenderType(
      SenderType senderType) {
    this.senderType = senderType;
  }

  public KycType getKycType() {
    return kycType;
  }

  public void setKycType(
      KycType kycType) {
    this.kycType = kycType;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getCrn() {
    return crn;
  }

  public void setCrn(String crn) {
    this.crn = crn;
  }

  public Date getCrnDateIssued() {
    return crnDateIssued;
  }

  public void setCrnDateIssued(Date crnDateIssued) {
    this.crnDateIssued = crnDateIssued;
  }

  public String getCrnIssuePlace() {
    return crnIssuePlace;
  }

  public void setCrnIssuePlace(String crnIssuePlace) {
    this.crnIssuePlace = crnIssuePlace;
  }

  public String getIdentityImgName() {
    return identityImgName;
  }

  public void setIdentityImgName(String identityImgName) {
    this.identityImgName = identityImgName;
  }

  public AttachmentContentType getIdentityImgContentType() {
    return identityImgContentType;
  }

  public void setIdentityImgContentType(
      AttachmentContentType identityImgContentType) {
    this.identityImgContentType = identityImgContentType;
  }

  public String getIdentityImgContent() {
    return identityImgContent;
  }

  public void setIdentityImgContent(String identityImgContent) {
    this.identityImgContent = identityImgContent;
  }

  public String getRepresentativeName() {
    return representativeName;
  }

  public void setRepresentativeName(String representativeName) {
    this.representativeName = representativeName;
  }

  public String getRepresentativePosition() {
    return representativePosition;
  }

  public void setRepresentativePosition(String representativePosition) {
    this.representativePosition = representativePosition;
  }
}
