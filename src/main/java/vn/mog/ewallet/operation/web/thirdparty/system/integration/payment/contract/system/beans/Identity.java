package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

public class Identity implements Serializable {
  private static final long serialVersionUID = 1L;
  protected Long id;
  protected Long customerId;
  protected String identity;
  protected Integer identityType;
  protected String issuer;
  protected Integer status;

  protected Date dateIssued;

  protected Date dateExpires;
  protected String issuePlace;
  protected String issueCountry;

  protected Date created;
  protected Boolean active;

  protected String fullname;
  protected Integer genderId;

  public Long getId() {
    return id;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public String getIdentity() {
    return identity;
  }

  public Integer getIdentityType() {
    return identityType;
  }

  public String getIssuer() {
    return issuer;
  }

  public Integer getStatus() {
    return status;
  }

  public Date getDateIssued() {
    return dateIssued;
  }

  public Date getDateExpires() {
    return dateExpires;
  }

  public String getIssuePlace() {
    return issuePlace;
  }

  public String getIssueCountry() {
    return issueCountry;
  }

  public Date getCreated() {
    return created;
  }

  public Boolean getActive() {
    return active;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public void setIdentity(String identity) {
    this.identity = identity;
  }

  public void setIdentityType(Integer identityType) {
    this.identityType = identityType;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public void setDateIssued(Date dateIssued) {
    this.dateIssued = dateIssued;
  }

  public void setDateExpires(Date dateExpires) {
    this.dateExpires = dateExpires;
  }

  public void setIssuePlace(String issuePlace) {
    this.issuePlace = issuePlace;
  }

  public void setIssueCountry(String issueCountry) {
    this.issueCountry = issueCountry;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Boolean isActive() {

    return this.active;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public Integer getGenderId() {
    return genderId;
  }

  public void setGenderId(Integer genderId) {
    this.genderId = genderId;
  }


  public String radioGenger(String gender) {
    if (this.genderId.equals(Integer.valueOf(gender))) {
      return "checked";
    }
    return StringUtils.EMPTY;
  }

  public String selectCountry(String country) {
    if (country.equals(this.issueCountry)) {
      return "selected";
    }
    return StringUtils.EMPTY;
  }
  
  

}
