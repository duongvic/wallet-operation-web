package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean;

import java.io.Serializable;
import java.util.Date;

public class CustomerPrivilege implements Serializable {

  private static final long serialVersionUID = 1L;
  protected Long id;
  protected Long customerId;

  protected String privilege;
  protected Boolean grantOption;

  protected Date validFromDate;

  protected Date validToDate;
  protected String orgUnit;

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

  public String getPrivilege() {
    return this.privilege;
  }

  public void setPrivilege(String value) {
    this.privilege = value;
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
}
