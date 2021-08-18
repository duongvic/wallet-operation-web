package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean;

import java.io.Serializable;

public class UmgrRole implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String role;
  protected Character active;
  protected String description;

  public String getRole() {
    return this.role;
  }

  public void setRole(String value) {
    this.role = value;
  }

  public Character getActive() {
    return active;
  }

  public void setActive(Character active) {
    this.active = active;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String value) {
    this.description = value;
  }
}
