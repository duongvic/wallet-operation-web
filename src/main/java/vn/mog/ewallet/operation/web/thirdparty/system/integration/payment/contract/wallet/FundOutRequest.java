package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

public class FundOutRequest extends FundOutRequestType {

  private static final long serialVersionUID = 1L;

  public FundOutRequest(String requestId, Long fundOrderId, String otp) {
    this.requestId = requestId;
    this.fundOrderId = fundOrderId;
    this.otp = otp;
  }

  public FundOutRequest() {

  }
}
