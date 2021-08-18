package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer;


import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Customer;

public class CreateCustomerRequest extends CreateCustomerRequestType {

  private Customer customer;
  private String notificationMode;

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public String getNotificationMode() {
    return notificationMode;
  }

  public void setNotificationMode(String notificationMode) {
    this.notificationMode = notificationMode;
  }

}
