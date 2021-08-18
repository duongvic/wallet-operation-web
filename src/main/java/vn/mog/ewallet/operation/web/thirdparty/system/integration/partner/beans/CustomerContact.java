package vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.beans;


import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.type.GenderType;

public class CustomerContact implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private Long tenantId;
  private ContactType contactType;
  private Long customerId;
  private String contactName;
  private String contactSurname;
  private String contactMail;
  private String contactMsisdn;
  private String relationship;
  private String livingAddress;
  private String province;
  private String district;
  private String commune;
  private GenderType gender;
  private String emergencyName;
  private String emergencyMsisdn;
  private String emergencyMail;
  private Date dateOfBirth;
  private String created;

  public String textDateOfBirth() {
    if (this.dateOfBirth != null) {
      return DateFormatUtils.format(dateOfBirth, "dd/MM/yyyy");
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

  public Long getTenantId() {
    return tenantId;
  }

  public void setTenantId(Long tenantId) {
    this.tenantId = tenantId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ContactType getContactType() {
    return contactType;
  }

  public void setContactType(ContactType contactType) {
    this.contactType = contactType;
  }

  public String getContactName() {
    return contactName;
  }

  public void setContactName(String contactName) {
    this.contactName = contactName;
  }

  public String getContactMail() {
    return contactMail;
  }

  public void setContactMail(String contactMail) {
    this.contactMail = contactMail;
  }

  public String getRelationship() {
    return relationship;
  }

  public void setRelationship(String relationship) {
    this.relationship = relationship;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  public String getContactMsisdn() {
    return contactMsisdn;
  }

  public void setContactMsisdn(String contactMsisdn) {
    this.contactMsisdn = contactMsisdn;
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

  public GenderType getGender() {
    return gender;
  }

  public void setGender(GenderType gender) {
    this.gender = gender;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getEmergencyName() {
    return emergencyName;
  }

  public void setEmergencyName(String emergencyName) {
    this.emergencyName = emergencyName;
  }

  public String getEmergencyMsisdn() {
    return emergencyMsisdn;
  }

  public void setEmergencyMsisdn(String emergencyMsisdn) {
    this.emergencyMsisdn = emergencyMsisdn;
  }

  public String getEmergencyMail() {
    return emergencyMail;
  }

  public void setEmergencyMail(String emergencyMail) {
    this.emergencyMail = emergencyMail;
  }

  public String getContactSurname() {
    return contactSurname;
  }

  public void setContactSurname(String contactSurname) {
    this.contactSurname = contactSurname;
  }
}
