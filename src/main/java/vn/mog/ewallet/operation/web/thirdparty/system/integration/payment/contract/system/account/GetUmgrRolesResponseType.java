package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import java.util.ArrayList;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean.UmgrRole;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetUmgrRolesResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;
  protected List<UmgrRole> roles;
  protected Long total;
  protected Long all;

  public List<UmgrRole> getRoles() {
    if (this.roles == null) {
      this.roles = new ArrayList<UmgrRole>();
    }
    return this.roles;
  }

  public void setRoles(List<UmgrRole> roles) {
    this.roles = roles;
  }

  public Long getTotal() {
    return total == null ? 0L : total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }

  public Long getAll() {
    return all == null ? 0L : all;
  }

  public void setAll(Long all) {
    this.all = all;
  }
}
