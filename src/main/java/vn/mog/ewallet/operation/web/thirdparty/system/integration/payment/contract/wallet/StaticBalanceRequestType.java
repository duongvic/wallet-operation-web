package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

import java.util.Date;
import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class StaticBalanceRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  protected String quickSearch;
  protected List<Integer> customerTypeIds;
  protected Character active;

  protected Long fromBalance;
  protected Long toBalance;

  protected Date fromDate;
  protected Date toDate;
  protected Integer limit;
  protected Integer offset;

  public String getQuickSearch() {
    return quickSearch;
  }

  public void setQuickSearch(String quickSearch) {
    this.quickSearch = quickSearch;
  }

  public List<Integer> getCustomerTypeIds() {
    return customerTypeIds;
  }

  public void setCustomerTypeIds(List<Integer> customerTypeIds) {
    this.customerTypeIds = customerTypeIds;
  }

  public Character getActive() {
    return active;
  }

  public void setActive(Character active) {
    this.active = active;
  }

  public Long getFromBalance() {
    return fromBalance;
  }

  public void setFromBalance(Long fromBalance) {
    this.fromBalance = fromBalance;
  }

  public Long getToBalance() {
    return toBalance;
  }

  public void setToBalance(Long toBalance) {
    this.toBalance = toBalance;
  }

  public Date getFromDate() {
    return fromDate;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public Date getToDate() {
    return toDate;
  }

  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }
}
