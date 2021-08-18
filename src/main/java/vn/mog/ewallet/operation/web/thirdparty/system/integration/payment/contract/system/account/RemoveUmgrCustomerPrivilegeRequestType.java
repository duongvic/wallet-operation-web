package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import vn.mog.framework.contract.base.TraceableRequestType;

public class RemoveUmgrCustomerPrivilegeRequestType extends TraceableRequestType {

  private static final long serialVersionUID = 1L;
  protected Long id;
  protected long customerId;
  protected String umgrPrivilege;
  protected String orgUnit;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public long getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(long value) {
    this.customerId = value;
  }

  public String getUmgrPrivilege() {
    return this.umgrPrivilege;
  }

  public void setUmgrPrivilege(String value) {
    this.umgrPrivilege = value;
  }

  public String getOrgUnit() {
    return this.orgUnit;
  }

  public void setOrgUnit(String value) {
    this.orgUnit = value;
  }
}
