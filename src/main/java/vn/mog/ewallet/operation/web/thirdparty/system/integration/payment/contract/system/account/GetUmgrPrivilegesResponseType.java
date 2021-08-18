package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import java.util.ArrayList;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.account.bean.UmgrPrivilege;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetUmgrPrivilegesResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;
  protected List<UmgrPrivilege> privileges;

  protected Long total;
  protected Long all;

  public List<UmgrPrivilege> getPrivileges() {
    if (this.privileges == null) {
      this.privileges = new ArrayList<UmgrPrivilege>();
    }
    return this.privileges;
  }

  public void setPrivileges(List<UmgrPrivilege> privileges) {
    this.privileges = privileges;
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
