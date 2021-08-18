package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer;

import java.util.ArrayList;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.Customer;

public class FindFullCustomerResponse extends FindFullCustomerResponseType {

  private static final long serialVersionUID = 1L;

  protected List<Customer> customers;
  protected Long total;

  public FindFullCustomerResponse() {
  }

  public List<Customer> getCustomers() {
    if (customers == null) {
      customers = new ArrayList<>();
    }
    return customers;
  }

  public void setCustomers(List<Customer> customers) {
    this.customers = customers;
  }

  public Long getTotal() {
    return (total == null) ? 0 : total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }
}
