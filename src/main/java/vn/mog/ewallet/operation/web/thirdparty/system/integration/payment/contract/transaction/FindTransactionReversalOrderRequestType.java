package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction;

import java.util.Date;
import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindTransactionReversalOrderRequestType extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;
  private String textSearch;
  private Date fromDate;
  private Date endDate;
  private List<String> serviceTypeIds;
  private List<Integer> stageIds;

  private Integer offset;
  private Integer limit;

  public String getTextSearch() {
    return textSearch;
  }

  public void setTextSearch(String textSearch) {
    this.textSearch = textSearch;
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

  public List<String> getServiceTypeIds() {
    return serviceTypeIds;
  }

  public void setServiceTypeIds(List<String> serviceTypeIds) {
    this.serviceTypeIds = serviceTypeIds;
  }

  public List<Integer> getStageIds() {
    return stageIds;
  }

  public void setStageIds(List<Integer> stageIds) {
    this.stageIds = stageIds;
  }
}
