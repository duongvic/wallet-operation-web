package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean.CustomerRole;
import vn.mog.framework.contract.base.TraceableRequestType;

public class AddUmgrCustomerRoleRequestType extends TraceableRequestType {

  private static final long serialVersionUID = 1L;

  protected CustomerRole customerRole;

  public CustomerRole getCustomerRole() {
    return this.customerRole;
  }

  public void setCustomerRole(CustomerRole value) {
    this.customerRole = value;
  }
}
