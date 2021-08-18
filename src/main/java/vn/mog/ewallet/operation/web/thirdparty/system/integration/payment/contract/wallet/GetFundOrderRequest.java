package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

public class GetFundOrderRequest extends GetFundOrderRequestType {

  private static final long serialVersionUID = 1L;

  public GetFundOrderRequest(Long fundOrderId) {
    this.fundOrderId = fundOrderId;
  }

  public GetFundOrderRequest() {

  }
}
