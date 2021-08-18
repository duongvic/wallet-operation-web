package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetOTPConfirmRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected Long orderId;

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }
}
