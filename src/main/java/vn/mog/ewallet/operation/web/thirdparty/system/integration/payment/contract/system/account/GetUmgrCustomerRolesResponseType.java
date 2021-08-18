package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import java.util.ArrayList;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean.CustomerRole;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetUmgrCustomerRolesResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;
  protected List<CustomerRole> umgrRoles;

  public List<CustomerRole> getUmgrRoles() {
    if (this.umgrRoles == null) {
      this.umgrRoles = new ArrayList<>();
    }
    return this.umgrRoles;
  }
}
