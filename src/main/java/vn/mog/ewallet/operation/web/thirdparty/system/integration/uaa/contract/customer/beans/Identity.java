package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

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

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public String getIdentity() {
    return identity;
  }

  public void setIdentity(String identity) {
    this.identity = identity;
  }

  public Integer getIdentityType() {
    return identityType;
  }

  public void setIdentityType(Integer identityType) {
    this.identityType = identityType;
  }

  public String getIssuer() {
    return issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Date getDateIssued() {
    return dateIssued;
  }

  public void setDateIssued(Date dateIssued) {
    this.dateIssued = dateIssued;
  }

  public Date getDateExpires() {
    return dateExpires;
  }

  public void setDateExpires(Date dateExpires) {
    this.dateExpires = dateExpires;
  }

  public String getIssuePlace() {
    return issuePlace;
  }

  public void setIssuePlace(String issuePlace) {
    this.issuePlace = issuePlace;
  }

  public String getIssueCountry() {
    return issueCountry;
  }

  public void setIssueCountry(String issueCountry) {
    this.issueCountry = issueCountry;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Boolean getActive() {
    return active;
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

  public String textDateOfIssuedDate() {
    if (this.dateIssued != null) {
      return DateFormatUtils.format(dateIssued, "dd/MM/yyyy");
    } else {
      return StringUtils.EMPTY;
    }
  }


}
