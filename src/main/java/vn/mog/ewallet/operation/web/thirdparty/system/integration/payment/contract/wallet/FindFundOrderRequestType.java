package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

import java.util.Date;
import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindFundOrderRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected String searchText;
  protected List<Long> customerIds;
  protected List<String> serviceTypeIds;
  protected List<String> orderChannelIds;
  protected List<Integer> stages;
  protected List<String> sourceOfFundTypeIds;

  protected Boolean isDebt;
  
  protected Date fromDate;
  protected Date endDate;

  protected int offset;
  protected int limit;

  public String getSearchText() {
    return searchText;
  }

  public void setSearchText(String searchText) {
    this.searchText = searchText;
  }

  public List<String> getServiceTypeIds() {
    return serviceTypeIds;
  }

  public void setServiceTypeIds(List<String> serviceTypeIds) {
    this.serviceTypeIds = serviceTypeIds;
  }

  public List<String> getOrderChannelIds() {
    return orderChannelIds;
  }

  public void setOrderChannelIds(List<String> orderChannelIds) {
    this.orderChannelIds = orderChannelIds;
  }

  public List<Integer> getStages() {
    return stages;
  }

  public void setStages(List<Integer> stages) {
    this.stages = stages;
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

  public List<Long> getCustomerIds() {
    return customerIds;
  }

  public void setCustomerIds(List<Long> customerIds) {
    this.customerIds = customerIds;
  }

  public List<String> getSourceOfFundTypeIds() {
    return sourceOfFundTypeIds;
  }

  public void setSourceOfFundTypeIds(List<String> sourceOfFundTypeIds) {
    this.sourceOfFundTypeIds = sourceOfFundTypeIds;
  }
  
  public Boolean getIsDebt() {
    return isDebt;
  }
  
  public void setIsDebt(Boolean isDebt) {
    this.isDebt = isDebt;
  }
}
