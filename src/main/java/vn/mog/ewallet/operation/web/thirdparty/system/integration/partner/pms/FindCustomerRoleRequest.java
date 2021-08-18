package vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class FindCustomerRoleRequest implements Serializable {

  private List<Long> ids;
  private Set<Long> customerIds;
  private List<Long> umgrRoleIds;
  private String codeName;
  private Boolean active;
  private Integer offset;
  private Integer limit;

  public List<Long> getIds() {
    return ids;
  }

  public void setIds(List<Long> ids) {
    this.ids = ids;
  }

  public Set<Long> getCustomerIds() {
    return customerIds;
  }

  public void setCustomerIds(Set<Long> customerIds) {
    this.customerIds = customerIds;
  }

  public List<Long> getUmgrRoleIds() {
    return umgrRoleIds;
  }

  public void setUmgrRoleIds(List<Long> umgrRoleIds) {
    this.umgrRoleIds = umgrRoleIds;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public String getCodeName() {
    return codeName;
  }

  public void setCodeName(String codeName) {
    this.codeName = codeName;
  }
}
