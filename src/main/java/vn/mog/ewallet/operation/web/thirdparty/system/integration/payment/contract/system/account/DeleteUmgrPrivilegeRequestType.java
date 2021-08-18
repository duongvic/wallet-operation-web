package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import vn.mog.framework.contract.base.TraceableRequestType;

public class DeleteUmgrPrivilegeRequestType extends TraceableRequestType {

  private static final long serialVersionUID = 1L;

  protected String privilegeId;

  public String getPrivilegeId() {
    return this.privilegeId;
  }

  public void setPrivilegeId(String value) {
    this.privilegeId = value;
  }
}
