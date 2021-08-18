package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import vn.mog.framework.contract.base.TraceableRequestType;

public class DeleteCustomerAttributeRequestType extends TraceableRequestType {

  private static final long serialVersionUID = 1L;
  protected long customerId;
  protected int customerAttributeTypeId;

  public long getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(long value) {
    this.customerId = value;
  }

  public int getCustomerAttributeTypeId() {
    return this.customerAttributeTypeId;
  }

  public void setCustomerAttributeTypeId(int value) {
    this.customerAttributeTypeId = value;
  }
}
