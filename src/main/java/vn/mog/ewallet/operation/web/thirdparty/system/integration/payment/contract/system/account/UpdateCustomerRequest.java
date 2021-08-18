package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Customer;

public class UpdateCustomerRequest extends UpdateCustomerRequestType {

  private static final long serialVersionUID = 1L;

  public UpdateCustomerRequest(Customer customer) {
    this.customer = customer;
  }
}
