package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetTrueServiceTransactionRuleMatrixRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected Long trueServiceId;
  protected int offset;
  protected int limit;

  public Long getTrueServiceId() {
    return trueServiceId;
  }

  public void setTrueServiceId(Long trueServiceId) {
    this.trueServiceId = trueServiceId;
  }

  public int getOffset() {
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

}
