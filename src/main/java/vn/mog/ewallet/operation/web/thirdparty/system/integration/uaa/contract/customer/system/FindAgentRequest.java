package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.system;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindAgentRequest extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  private String msisdn;
  private Integer offset;
  private Integer limit;

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
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
}
