package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo;

import java.util.Date;
import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindEpinPurchaseOrderRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  private String quickSearch;
  private List<Long> customerIds;
  private List<String> statusIds;

  private Date fromDate;
  private Date toDate;

  private String[] orderBy;
  private boolean[] desc;

  private int offset;
  private int limit;

  private List<String> storeTypes;

  public String getQuickSearch() {
    return quickSearch;
  }

  public void setQuickSearch(String quickSearch) {
    this.quickSearch = quickSearch;
  }

  public List<Long> getCustomerIds() {
    return customerIds;
  }

  public void setCustomerIds(List<Long> customerIds) {
    this.customerIds = customerIds;
  }

  public List<String> getStatusIds() {
    return statusIds;
  }

  public void setStatusIds(List<String> statusIds) {
    this.statusIds = statusIds;
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

  public String[] getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String[] orderBy) {
    this.orderBy = orderBy;
  }

  public boolean[] getDesc() {
    return desc;
  }

  public void setDesc(boolean[] desc) {
    this.desc = desc;
  }

  public int getOffset() {
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public List<String> getStoreTypes() {
    return storeTypes;
  }

  public void setStoreTypes(List<String> storeTypes) {
    this.storeTypes = storeTypes;
  }
}
