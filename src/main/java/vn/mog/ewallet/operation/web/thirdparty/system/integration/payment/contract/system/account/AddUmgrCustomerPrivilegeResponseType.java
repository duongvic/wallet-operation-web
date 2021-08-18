package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class AddUmgrCustomerPrivilegeResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;
  protected Long umgrCustomerPrivilege;

  public Long getUmgrCustomerPrivilege() {
    return this.umgrCustomerPrivilege;
  }

  public void setUmgrCustomerPrivilege(Long value) {
    this.umgrCustomerPrivilege = value;
  }
}
