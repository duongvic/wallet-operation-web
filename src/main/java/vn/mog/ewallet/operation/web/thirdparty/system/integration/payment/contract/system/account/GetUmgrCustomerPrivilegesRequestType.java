package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class GetUmgrCustomerPrivilegesRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  protected long customerId;

  public long getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(long value) {
    this.customerId = value;
  }
}
