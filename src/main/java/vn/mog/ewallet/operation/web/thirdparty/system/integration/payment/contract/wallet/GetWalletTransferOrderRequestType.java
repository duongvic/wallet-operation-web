package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetWalletTransferOrderRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected Long orderId;

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }
}
