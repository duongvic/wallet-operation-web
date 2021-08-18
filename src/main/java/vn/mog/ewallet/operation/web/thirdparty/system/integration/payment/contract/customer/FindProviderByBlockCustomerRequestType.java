package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer;

import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindProviderByBlockCustomerRequestType extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;
  private String textSearch;
  private List<String> serviceTypeIds;
  private List<String> customersCifs;

  private Boolean active;

  private Integer offset;
  private Integer limit;

  public String getTextSearch() {
    return textSearch;
  }

  public void setTextSearch(String textSearch) {
    this.textSearch = textSearch;
  }

  public List<String> getServiceTypeIds() {
    return serviceTypeIds;
  }

  public void setServiceTypes(List<String> serviceTypeIds) {
    this.serviceTypeIds = serviceTypeIds;
  }

  public List<String> getCustomersCifs() {
    return customersCifs;
  }

  public void setCustomersCifs(List<String> customersCifs) {
    this.customersCifs = customersCifs;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
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
