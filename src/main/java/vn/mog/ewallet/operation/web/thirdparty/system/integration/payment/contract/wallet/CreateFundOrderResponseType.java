package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class CreateFundOrderResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  protected Long fundOrderId;

  public Long getFundOrderId() {
    return fundOrderId;
  }

  public void setFundOrderId(Long fundOrderId) {
    this.fundOrderId = fundOrderId;
  }
}
