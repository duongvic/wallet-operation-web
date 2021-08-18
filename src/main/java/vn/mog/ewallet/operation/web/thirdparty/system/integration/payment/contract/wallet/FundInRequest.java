package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

public class FundInRequest extends FundInRequestType {

  private static final long serialVersionUID = 1L;

  public FundInRequest(String requestId, Long fundOrderId, String otp) {
    this.requestId = requestId;
    this.fundOrderId = fundOrderId;
    this.otp = otp;
  }
}
