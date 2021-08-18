package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import java.util.ArrayList;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean.CustomerPrivilege;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetUmgrCustomerPrivilegesResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;
  protected List<CustomerPrivilege> umgrPrivileges;

  public List<CustomerPrivilege> getUmgrPrivileges() {
    if (this.umgrPrivileges == null) {
      this.umgrPrivileges = new ArrayList<CustomerPrivilege>();
    }
    return this.umgrPrivileges;
  }
}
