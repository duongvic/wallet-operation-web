package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean;

import java.io.Serializable;
import java.util.Date;

public class UmgrRolePrivilege implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String role;

  protected String privilege;
  protected Character active;
  private Date validFrom;
  private Date validTo;

  public String getRole() {
    return this.role;
  }

  public void setRole(String value) {
    this.role = value;
  }

  public String getPrivilege() {
    return this.privilege;
  }

  public void setPrivilege(String value) {
    this.privilege = value;
  }

  public Date getValidFrom() {
    return validFrom;
  }

  public void setValidFrom(Date validFrom) {
    this.validFrom = validFrom;
  }

  public Date getValidTo() {
    return validTo;
  }

  public void setValidTo(Date validTo) {
    this.validTo = validTo;
  }

  public Character getActive() {
    return active;
  }

  public void setActive(Character active) {
    this.active = active;
  }

}
