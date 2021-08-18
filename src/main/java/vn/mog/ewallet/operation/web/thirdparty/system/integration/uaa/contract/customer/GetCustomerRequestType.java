package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetCustomerRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected Long customerId;
  protected String customerCif;

  public Long getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(long value) {
    this.customerId = value;
  }

  public String getCustomerCif() {
    return customerCif;
  }

  public void setCustomerCif(String customerCif) {
    this.customerCif = customerCif;
  }

}
