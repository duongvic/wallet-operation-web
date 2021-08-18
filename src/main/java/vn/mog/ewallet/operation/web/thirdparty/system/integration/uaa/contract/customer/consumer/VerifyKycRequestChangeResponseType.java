package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class VerifyKycRequestChangeResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private Long requestId;

  public Long getRequestId() {
    return requestId;
  }

  public void setRequestId(Long requestId) {
    this.requestId = requestId;
  }
}
