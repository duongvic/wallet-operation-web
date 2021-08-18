package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetFullCustomerRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  protected Long customerId;


  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

}
