package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service;

import java.util.Date;
import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindTrueServiceRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected String quickSearch;
  protected List<String> serviceTypes;
  protected Character active;
  protected Integer level;
  protected Character inTree;

  protected Date fromDate;
  protected Date endDate;
  protected Integer limit;
  protected Integer offset;

  public String getQuickSearch() {
    return quickSearch;
  }

  public void setQuickSearch(String quickSearch) {
    this.quickSearch = quickSearch;
  }

  public List<String> getServiceTypes() {
    return serviceTypes;
  }

  public void setServiceTypes(List<String> serviceTypes) {
    this.serviceTypes = serviceTypes;
  }

  public Character getActive() {
    return active;
  }

  public void setActive(Character active) {
    this.active = active;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public Date getFromDate() {
    return fromDate;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
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

  public Character getInTree() {
    return inTree;
  }

  public void setInTree(Character inTree) {
    this.inTree = inTree;
  }
}
