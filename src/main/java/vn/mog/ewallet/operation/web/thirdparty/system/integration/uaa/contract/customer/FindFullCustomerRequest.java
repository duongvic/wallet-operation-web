package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer;

import java.util.List;

public class FindFullCustomerRequest extends FindFullCustomerRequestType {

  private static final long serialVersionUID = 1L;
  protected Boolean includeBalance;
  private String textSearch;
  private List<Integer> customerTypes;
  private int offset;
  private int limit;

  public FindFullCustomerRequest(String textSearch, int offset, int limit) {
    this.textSearch = textSearch;
    this.offset = offset;
    this.limit = limit;
  }

  public FindFullCustomerRequest() {

  }

  public Boolean getIncludeBalance() {
    return includeBalance;
  }

  public void setIncludeBalance(Boolean includeBalance) {
    this.includeBalance = includeBalance;
  }

  public String getTextSearch() {
    return textSearch;
  }

  public void setTextSearch(String textSearch) {
    this.textSearch = textSearch;
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
}
