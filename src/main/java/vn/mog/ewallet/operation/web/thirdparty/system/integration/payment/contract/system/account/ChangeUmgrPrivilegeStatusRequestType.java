package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class ChangeUmgrPrivilegeStatusRequestType extends MobiliserRequestType {

  protected String privilegeId;
  protected Character active;

  public String getPrivilegeId() {
    return privilegeId;
  }

  public void setPrivilegeId(String privilegeId) {
    this.privilegeId = privilegeId;
  }

  public Character getActive() {
    return active;
  }

  public void setActive(Character active) {
    this.active = active;
  }

}
