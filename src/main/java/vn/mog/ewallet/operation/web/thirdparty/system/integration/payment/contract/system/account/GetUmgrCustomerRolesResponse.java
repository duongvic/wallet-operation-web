package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import java.util.List;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean.CustomerRole;

public class GetUmgrCustomerRolesResponse extends GetUmgrCustomerRolesResponseType {

  private static final long serialVersionUID = 1L;

  public boolean isCustomerAgent() {
    List<CustomerRole> umgrRoles = getUmgrRoles();
    for (CustomerRole role : umgrRoles) {
      if (RoleConstants.AGENT.equals(role.getRole())) {
        return true;
      }
    }
    return false;
  }

  public boolean isMerchantUser() {
    List<CustomerRole> umgrRoles = getUmgrRoles();
    for (CustomerRole role : umgrRoles) {
      if (RoleConstants.MERCHANT.equals(role.getRole())) {
        return true;
      }
    }
    return false;
  }

  public boolean isFinanceUser() {
    List<CustomerRole> umgrRoles = getUmgrRoles();
    for (CustomerRole role : umgrRoles) {
      if (RoleConstants.FINANCE_LEADER.equals(role.getRole())
          || RoleConstants.FINANCESUPPORT_LEADER.equals(role.getRole())
          || RoleConstants.FA_MANAGER.equals(role.getRole())) {
        return true;
      }
    }
    return false;
  }
}
