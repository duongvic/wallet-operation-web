package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import java.util.ArrayList;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Customer;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindUmgrCustomerResponseType extends MobiliserResponseType {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private List<Customer> customers;
  private long total;

  public List<Customer> getCustomers() {
    return this.customers == null ? new ArrayList<Customer>() : this.customers;
  }

  public void setCustomers(List<Customer> customers) {
    this.customers = customers;
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

}
