package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation;

import java.util.Date;
import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class ReconcileTransactionFindRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  private String textSearch;
  private Date fromDate;
  private Date endDate;
  private List<String> idOwnerCustomerTypes;
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

  public List<String> getIdOwnerCustomerTypes() {
    return idOwnerCustomerTypes;
  }

  public void setIdOwnerCustomerTypes(List<String> idOwnerCustomerTypes) {
    this.idOwnerCustomerTypes = idOwnerCustomerTypes;
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
