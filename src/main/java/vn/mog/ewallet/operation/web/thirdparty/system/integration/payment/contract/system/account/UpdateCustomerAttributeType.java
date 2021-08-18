package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.CustomerAttribute;
import vn.mog.framework.contract.base.TraceableRequestType;

public class UpdateCustomerAttributeType extends TraceableRequestType {

  private static final long serialVersionUID = 1L;

  private List<CustomerAttribute> customerAttributes;
  private Long customerId;

  public UpdateCustomerAttributeType() {
  }

  public UpdateCustomerAttributeType(List<CustomerAttribute> customerAttributes) {
    this.customerAttributes = customerAttributes;
  }

  public List<CustomerAttribute> getCustomerAttributes() {
    return customerAttributes;
  }

  public void setCustomerAttributes(List<CustomerAttribute> customerAttributes) {
    this.customerAttributes = customerAttributes;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

}
