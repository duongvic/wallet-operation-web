package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean;

import java.io.Serializable;

public class UmgrPrivilege implements Serializable {

  private static final long serialVersionUID = 1L;
  protected String privilege;
  protected String description;
  protected Character active;

  public UmgrPrivilege() {
  }

  public UmgrPrivilege(String privilege, String description, Character active) {
    super();
    this.privilege = privilege;
    this.description = description;
    this.active = active;
  }

  public String getPrivilege() {
    return this.privilege;
  }

  public void setPrivilege(String value) {
    this.privilege = value;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String value) {
    this.description = value;
  }

  public Character getActive() {
    return active;
  }

  public void setActive(Character active) {
    this.active = active;
  }

}
