package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

import java.util.Collections;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.Customer;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindCustomerResponeType extends MobiliserRequestType {

  protected List<Customer> customers;
  protected Long total;
  protected Long all;
  protected Long totalBalance;

  public List<Customer> getCustomers() {
    if (customers == null) {
      customers = Collections.emptyList();
    }
    return customers;
  }

  public void setCustomers(List<Customer> customers) {
    this.customers = customers;
  }

  public Long getTotalBalance() {
    if (totalBalance == null) {
      totalBalance = 0L;
    }
    return totalBalance;
  }

  public void setTotalBalance(Long totalBalance) {
    this.totalBalance = totalBalance;
  }

  public Long getTotal() {
    return total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }

  public Long getAll() {
    return all;
  }

  public void setAll(Long all) {
    this.all = all;
  }

}
