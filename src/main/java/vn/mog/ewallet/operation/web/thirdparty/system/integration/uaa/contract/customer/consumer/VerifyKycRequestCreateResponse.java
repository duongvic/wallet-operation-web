package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer;

public class VerifyKycRequestCreateResponse extends VerifyKycRequestCreateResponseType {

  private static final long serialVersionUID = 1L;

  private Long requestId;

  public Long getRequestId() {
    return requestId;
  }

  public void setRequestId(Long requestId) {
    this.requestId = requestId;
  }
}
