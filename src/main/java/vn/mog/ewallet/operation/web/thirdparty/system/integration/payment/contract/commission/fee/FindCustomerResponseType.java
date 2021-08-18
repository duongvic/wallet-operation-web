package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.beans.Customer;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindCustomerResponseType extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;

  protected List<Customer> customers;
  protected Long total;
  protected Long all;

  protected Long totalBalance;

  public List<Customer> getCustomers() {
    return customers;
  }

  public Long getTotal() {
    return total == null ? 0L : total;
  }

  public Long getAll() {
    return all == null ? 0L : all;
  }

  public void setCustomers(List<Customer> customers) {
    this.customers = customers;
  }

  public void setTotal(Long total) {
    this.total = total;
  }

  public void setAll(Long all) {
    this.all = all;
  }

  public Long getTotalBalance() {
    return totalBalance == null ? 0L : totalBalance;
  }

  public void setTotalBalance(Long totalBalance) {
    this.totalBalance = totalBalance;
  }
}
