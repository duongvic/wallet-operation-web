package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import vn.mog.framework.contract.base.TraceableRequestType;

public class DeleteUmgrRoleRequestType extends TraceableRequestType {

  private static final long serialVersionUID = 1L;

  protected String roleId;

  public String getRoleId() {
    return this.roleId;
  }

  public void setRoleId(String value) {
    this.roleId = value;
  }
}
