package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import java.util.Date;
import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindUmgrCustomerRequestType extends MobiliserRequestType {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  protected String quickSearch;
  protected List<Long> parentIds;
  protected List<Long> restrictedIds;
  protected List<Integer> customerTypes;
  protected List<String> umgrRoleIds;
  protected List<Integer> blacklistReasons;
  protected List<Integer> walletTypeIds;
  protected List<Integer> userTypeIds;
  protected List<Integer> jobPositionIds;
  protected Date fromDate;
  protected Date toDate;

  protected Integer offset;
  protected Integer limit;
  protected List<String> bizChannelIds;

  public List<String> getBizChannelIds() {
    return bizChannelIds;
  }

  public void setBizChannelIds(List<String> bizChannelIds) {
    this.bizChannelIds = bizChannelIds;
  }

  public String getQuickSearch() {
    return quickSearch;
  }

  public void setQuickSearch(String quickSearch) {
    this.quickSearch = quickSearch;
  }

  public List<Long> getParentIds() {
    return parentIds;
  }

  public void setParentIds(List<Long> parentIds) {
    this.parentIds = parentIds;
  }

  public List<Long> getRestrictedIds() {
    return restrictedIds;
  }

  public void setRestrictedIds(List<Long> restrictedIds) {
    this.restrictedIds = restrictedIds;
  }

  public List<Integer> getCustomerTypes() {
    return customerTypes;
  }

  public void setCustomerTypes(List<Integer> customerTypes) {
    this.customerTypes = customerTypes;
  }

  public List<String> getUmgrRoleIds() {
    return umgrRoleIds;
  }

  public void setUmgrRoleIds(List<String> umgrRoleIds) {
    this.umgrRoleIds = umgrRoleIds;
  }

  public List<Integer> getBlacklistReasons() {
    return blacklistReasons;
  }

  public void setBlacklistReasons(List<Integer> blacklistReasons) {
    this.blacklistReasons = blacklistReasons;
  }

  public List<Integer> getWalletTypeIds() {
    return walletTypeIds;
  }

  public void setWalletTypeIds(List<Integer> walletTypeIds) {
    this.walletTypeIds = walletTypeIds;
  }

  public List<Integer> getUserTypeIds() {
    return userTypeIds;
  }

  public void setUserTypeIds(List<Integer> userTypeIds) {
    this.userTypeIds = userTypeIds;
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

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public List<Integer> getJobPositionIds() {
    return jobPositionIds;
  }

  public void setJobPositionIds(List<Integer> jobPositionIds) {
    this.jobPositionIds = jobPositionIds;
  }
  
}
