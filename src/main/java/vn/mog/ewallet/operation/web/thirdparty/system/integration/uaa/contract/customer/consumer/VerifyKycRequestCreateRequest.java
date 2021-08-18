package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer;


import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.CustomerVerifyKycRequest;

public class VerifyKycRequestCreateRequest extends VerifyKycRequestCreateRequestType {

  private static final long serialVersionUID = 1L;

  private CustomerVerifyKycRequest customerVerifyRequest;
  private boolean bothUpdate;

  public CustomerVerifyKycRequest getCustomerVerifyRequest() {
    return customerVerifyRequest;
  }

  public void setCustomerVerifyRequest(CustomerVerifyKycRequest customerVerifyRequest) {
    this.customerVerifyRequest = customerVerifyRequest;
  }

  public boolean isBothUpdate() {
    return bothUpdate;
  }

  public void setBothUpdate(boolean bothUpdate) {
    this.bothUpdate = bothUpdate;
  }
}
