package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer;

import java.util.List;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindProviderServicesByBlockCustomerRequestType extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;

  private List<String> providerCodes;
  private List<String> serviceCodes;
  private List<String> customersCifs;

  private Boolean active;

  private Integer offset;
  private Integer limit;

  public List<String> getProviderCodes() {
    return providerCodes;
  }

  public void setProviderCodes(List<String> providerCodes) {
    this.providerCodes = providerCodes;
  }

  public List<String> getServiceCodes() {
    return serviceCodes;
  }

  public void setServiceCodes(List<String> serviceCodes) {
    this.serviceCodes = serviceCodes;
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
