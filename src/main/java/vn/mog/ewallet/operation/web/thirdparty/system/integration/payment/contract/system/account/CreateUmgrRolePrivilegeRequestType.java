package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean.UmgrRolePrivilege;
import vn.mog.framework.contract.base.TraceableRequestType;

public class CreateUmgrRolePrivilegeRequestType extends TraceableRequestType {

  private static final long serialVersionUID = 1L;

  protected UmgrRolePrivilege rolePrivilege;

  public UmgrRolePrivilege getRolePrivilege() {
    return this.rolePrivilege;
  }

  public void setRolePrivilege(UmgrRolePrivilege value) {
    this.rolePrivilege = value;
  }
}
