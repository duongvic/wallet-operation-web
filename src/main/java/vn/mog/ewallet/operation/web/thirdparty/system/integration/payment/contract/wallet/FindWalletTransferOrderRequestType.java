package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

import java.util.Date;
import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindWalletTransferOrderRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  private String searchText;
  private List<Long> payerIds;
  private List<Long> payeeIds;
  private List<Integer> stages;
  private List<String> serviceTypes;

  private Date fromDate;
  private Date endDate;

  private int offset;
  private int limit;

  public String getSearchText() {
    return searchText;
  }

  public void setSearchText(String searchText) {
    this.searchText = searchText;
  }

  public List<Long> getPayerIds() {
    return payerIds;
  }

  public void setPayerIds(List<Long> payerIds) {
    this.payerIds = payerIds;
  }

  public List<Long> getPayeeIds() {
    return payeeIds;
  }

  public void setPayeeIds(List<Long> payeeIds) {
    this.payeeIds = payeeIds;
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

  public List<String> getServiceTypes() {
    return serviceTypes;
  }

  public void setServiceTypes(List<String> serviceTypes) {
    this.serviceTypes = serviceTypes;
  }
}
