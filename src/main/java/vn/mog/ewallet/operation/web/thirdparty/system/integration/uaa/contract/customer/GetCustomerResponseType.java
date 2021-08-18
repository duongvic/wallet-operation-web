package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Customer;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.CustomerAttribute;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetCustomerResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;
  protected Customer customer;
  protected List<CustomerAttribute> attributes;

  public Customer getCustomer() {
    return this.customer;
  }

  public void setCustomer(Customer value) {
    this.customer = value;
  }

  public List<CustomerAttribute> getAttributes() {
    return attributes;
  }

  public void setAttributes(List<CustomerAttribute> attributes) {
    this.attributes = attributes;
  }
}
