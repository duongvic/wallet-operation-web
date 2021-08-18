package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean;

import java.io.Serializable;
import java.util.Date;

public class CustomerRole implements Serializable {

  private static final long serialVersionUID = 1L;
  protected Long id;
  protected Long customerId;

  protected String role;
  protected Boolean grantOption;

  protected Date validFromDate;

  protected Date validToDate;
  protected String orgUnit;
  protected Character active;

  protected Boolean isDefault = false;

  public Long getId() {
    return this.id;
  }

  public void setId(Long value) {
    this.id = value;
  }

  public Long getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(Long value) {
    this.customerId = value;
  }

  public String getRole() {
    return this.role;
  }

  public void setRole(String value) {
    this.role = value;
  }

  public Boolean isGrantOption() {
    return this.grantOption;
  }

  public void setGrantOption(Boolean value) {
    this.grantOption = value;
  }

  public Date getValidFromDate() {
    return this.validFromDate;
  }

  public void setValidFromDate(Date value) {
    this.validFromDate = value;
  }

  public Date getValidToDate() {
    return this.validToDate;
  }

  public void setValidToDate(Date value) {
    this.validToDate = value;
  }

  public String getOrgUnit() {
    return this.orgUnit;
  }

  public void setOrgUnit(String value) {
    this.orgUnit = value;
  }

  public Boolean getIsDefault() {
    return isDefault;
  }

  public void setIsDefault(Boolean isDefault) {
    this.isDefault = isDefault;
  }

  public Character getActive() {
    return active;
  }

  public void setActive(Character active) {
    this.active = active;
  }

}
