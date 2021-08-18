package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class WalletTransferRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected Long orderId;
  protected String otp;

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public String getOtp() {
    return otp;
  }

  public void setOtp(String otp) {
    this.otp = otp;
  }
}
