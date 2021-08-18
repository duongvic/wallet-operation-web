package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import java.util.ArrayList;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean.UmgrRolePrivilege;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetUmgrRolePrivilegesResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;
  protected List<UmgrRolePrivilege> rolePrivileges;

  public List<UmgrRolePrivilege> getRolePrivileges() {
    if (this.rolePrivileges == null) {
      this.rolePrivileges = new ArrayList<UmgrRolePrivilege>();
    }
    return this.rolePrivileges;
  }
}
