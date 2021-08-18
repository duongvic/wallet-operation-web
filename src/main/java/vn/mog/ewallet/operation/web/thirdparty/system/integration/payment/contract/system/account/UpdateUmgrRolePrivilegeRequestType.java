package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;


import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean.UmgrRolePrivilege;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class UpdateUmgrRolePrivilegeRequestType extends MobiliserRequestType {

  protected UmgrRolePrivilege rolePrivilege;

  public UmgrRolePrivilege getRolePrivilege() {
    return rolePrivilege;
  }

  public void setRolePrivilege(UmgrRolePrivilege rolePrivilege) {
    this.rolePrivilege = rolePrivilege;
  }

}
