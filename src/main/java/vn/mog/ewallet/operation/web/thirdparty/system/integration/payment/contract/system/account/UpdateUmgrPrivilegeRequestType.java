package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean.UmgrPrivilege;
import vn.mog.framework.contract.base.TraceableRequestType;

public class UpdateUmgrPrivilegeRequestType extends TraceableRequestType {

  private static final long serialVersionUID = 1L;

  protected UmgrPrivilege privilege;

  public UmgrPrivilege getPrivilege() {
    return this.privilege;
  }

  public void setPrivilege(UmgrPrivilege value) {
    this.privilege = value;
  }
}
