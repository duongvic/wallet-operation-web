package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean.UmgrRole;
import vn.mog.framework.contract.base.TraceableRequestType;

public class CreateUmgrRoleRequestType extends TraceableRequestType {

  private static final long serialVersionUID = 1L;

  protected UmgrRole role;

  public UmgrRole getRole() {
    return this.role;
  }

  public void setRole(UmgrRole value) {
    this.role = value;
  }
}
