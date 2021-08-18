package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction;

import java.util.Date;
import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class ExportTransactionLogRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected List<Integer> statusIds;
  protected String serviceTypeId;
  protected String serviceTypeCode;

  protected Date fromDate;
  protected Date endDate;

  protected Integer offset;
  protected Integer limit;

  public List<Integer> getStatusIds() {
    return statusIds;
  }

  public void setStatusIds(List<Integer> statusIds) {
    this.statusIds = statusIds;
  }

  public String getServiceTypeId() {
    return serviceTypeId;
  }

  public void setServiceTypeId(String serviceTypeId) {
    this.serviceTypeId = serviceTypeId;
  }

  public String getServiceTypeCode() {
    return serviceTypeCode;
  }

  public void setServiceTypeCode(String serviceTypeCode) {
    this.serviceTypeCode = serviceTypeCode;
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
}
