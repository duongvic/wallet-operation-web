package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;


import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.Customer;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetCustomerResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;
  protected Customer customer;

  public Customer getCustomer() {
    return this.customer;
  }

  public void setCustomer(Customer value) {
    this.customer = value;
  }
}
