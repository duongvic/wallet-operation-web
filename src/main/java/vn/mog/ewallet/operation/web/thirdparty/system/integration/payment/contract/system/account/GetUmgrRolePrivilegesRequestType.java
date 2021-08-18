package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetUmgrRolePrivilegesRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  protected String roleId;
  protected String privilegeId;

  public String getRoleId() {
    return this.roleId;
  }

  public void setRoleId(String value) {
    this.roleId = value;
  }

  public String getPrivilegeId() {
    return this.privilegeId;
  }

  public void setPrivilegeId(String value) {
    this.privilegeId = value;
  }
}
