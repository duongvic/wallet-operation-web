package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class BuyCardOfflineConfirmRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  protected Long purchaserOrderId;
  protected String otp;

  public Long getPurchaserOrderId() {
    return purchaserOrderId;
  }

  public void setPurchaserOrderId(Long purchaserOrderId) {
    this.purchaserOrderId = purchaserOrderId;
  }

  public String getOtp() {
    return otp;
  }

  public void setOtp(String otp) {
    this.otp = otp;
  }

}
