package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetUmgrPrivilegesRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  protected String privilegeIdSearchParameter;
  protected String descriptionSearchParameter;
  protected Character active;
  protected Integer limit;
  protected Integer offset;

  public String getPrivilegeIdSearchParameter() {
    return this.privilegeIdSearchParameter;
  }

  public void setPrivilegeIdSearchParameter(String value) {
    this.privilegeIdSearchParameter = value;
  }

  public String getDescriptionSearchParameter() {
    return this.descriptionSearchParameter;
  }

  public void setDescriptionSearchParameter(String value) {
    this.descriptionSearchParameter = value;
  }

  public Character getActive() {
    return active;
  }

  public void setActive(Character active) {
    this.active = active;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

}
