package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.CustomerVerifyKycRequest;

public class VerifyKycRequestGetDetailResponse extends VerifyKycRequestGetDetailResponseType {

  private static final long serialVersionUID = 1L;

  private CustomerVerifyKycRequest customerVerifyKycRequest;

  public CustomerVerifyKycRequest getCustomerVerifyKycRequest() {
    return customerVerifyKycRequest;
  }

  public void setCustomerVerifyKycRequest(CustomerVerifyKycRequest customerVerifyKycRequest) {
    this.customerVerifyKycRequest = customerVerifyKycRequest;
  }

}
