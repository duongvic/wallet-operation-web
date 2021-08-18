package vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet;

import java.util.Date;
import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class SearchPaymentInstrumentRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  protected String quickSearch;
  protected Boolean active;
  protected List<Integer> customerTypeIds;
  protected List<Integer> userTypeIds;
  protected List<Integer> walletTypeIds;
  protected List<Long> customerIds;

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

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
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

  public List<Integer> getUserTypeIds() {
    return userTypeIds;
  }

  public void setUserTypeIds(List<Integer> userTypeIds) {
    this.userTypeIds = userTypeIds;
  }

  public List<Integer> getWalletTypeIds() {
    return walletTypeIds;
  }

  public void setWalletTypeIds(List<Integer> walletTypeIds) {
    this.walletTypeIds = walletTypeIds;
  }

  public List<Long> getCustomerIds() {
    return customerIds;
  }

  public void setCustomerIds(List<Long> customerIds) {
    this.customerIds = customerIds;
  }
}
