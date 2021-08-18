package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class ContinuePendingCustomerResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;
  protected Long customerId;

  public Long getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(Long value) {
    this.customerId = value;
  }
}
