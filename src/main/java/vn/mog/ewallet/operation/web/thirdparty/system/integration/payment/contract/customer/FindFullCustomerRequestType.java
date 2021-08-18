package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer;

import java.io.Serializable;
import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindFullCustomerRequestType extends MobiliserRequestType implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String textSearch;
  protected Boolean active;
  protected List<Integer> customerTypes;
  protected Boolean includeBalance;

  protected int offset;
  protected int limit;

  public String getTextSearch() {
    return textSearch;
  }

  public void setTextSearch(String textSearch) {
    this.textSearch = textSearch;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public int getOffset() {
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public List<Integer> getCustomerTypes() {
    return customerTypes;
  }

  public void setCustomerTypes(List<Integer> customerTypes) {
    this.customerTypes = customerTypes;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public Boolean getIncludeBalance() {
    return includeBalance;
  }

  public void setIncludeBalance(Boolean includeBalance) {
    this.includeBalance = includeBalance;
  }
}
