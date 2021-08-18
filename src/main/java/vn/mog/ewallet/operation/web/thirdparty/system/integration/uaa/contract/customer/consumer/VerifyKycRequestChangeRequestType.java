package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer;


import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.CustomerVerifyKycRequest;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class VerifyKycRequestChangeRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  private CustomerVerifyKycRequest customerVerifyRequest;

  public CustomerVerifyKycRequest getCustomerVerifyRequest() {
    return customerVerifyRequest;
  }

  public void setCustomerVerifyRequest(CustomerVerifyKycRequest customerVerifyRequest) {
    this.customerVerifyRequest = customerVerifyRequest;
  }
  
}
