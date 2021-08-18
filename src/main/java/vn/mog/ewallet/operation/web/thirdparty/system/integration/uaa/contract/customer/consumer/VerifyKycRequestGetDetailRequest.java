package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer;

public class VerifyKycRequestGetDetailRequest extends VerifyKycRequestGetDetailRequestType {

  private static final long serialVersionUID = 1L;

  private Long requestId;

  public VerifyKycRequestGetDetailRequest(Long requestId) {
    this.requestId = requestId;
  }

  public Long getRequestId() {
    return requestId;
  }

  public void setRequestId(Long requestId) {
    this.requestId = requestId;
  }
}
