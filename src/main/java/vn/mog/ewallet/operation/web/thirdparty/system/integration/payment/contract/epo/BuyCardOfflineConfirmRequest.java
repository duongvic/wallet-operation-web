package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo;

public class BuyCardOfflineConfirmRequest extends BuyCardOfflineConfirmRequestType {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public BuyCardOfflineConfirmRequest(Long purchaserOrderId, String otp) {
    this.purchaserOrderId = purchaserOrderId;
    this.otp = otp;
  }

  public BuyCardOfflineConfirmRequest() {

  }
}
