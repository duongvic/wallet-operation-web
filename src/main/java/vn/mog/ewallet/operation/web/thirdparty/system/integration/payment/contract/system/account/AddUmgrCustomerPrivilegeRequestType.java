package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean.CustomerPrivilege;
import vn.mog.framework.contract.base.TraceableRequestType;

public class AddUmgrCustomerPrivilegeRequestType extends TraceableRequestType {

  private static final long serialVersionUID = 1L;

  protected CustomerPrivilege customerPrivilege;

  public CustomerPrivilege getCustomerPrivilege() {
    return this.customerPrivilege;
  }

  public void setCustomerPrivilege(CustomerPrivilege value) {
    this.customerPrivilege = value;
  }
}
