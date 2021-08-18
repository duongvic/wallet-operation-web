package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class AddUmgrCustomerRoleResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;
  protected Long umgrCustomerRoleId;

  public Long getUmgrCustomerRoleId() {
    return this.umgrCustomerRoleId;
  }

  public void setUmgrCustomerRoleId(Long value) {
    this.umgrCustomerRoleId = value;
  }
}
