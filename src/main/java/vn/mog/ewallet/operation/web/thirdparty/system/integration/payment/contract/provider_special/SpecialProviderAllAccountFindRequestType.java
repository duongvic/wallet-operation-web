package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special;

import java.io.Serializable;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ProviderCode;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class SpecialProviderAllAccountFindRequestType extends MobiliserRequestType
    implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private ProviderCode providerCode;
  private String textSearch;
  private Boolean active;

  private Integer offset;
  private Integer limit;
  
  public ProviderCode getProviderCode() {
    return providerCode;
  }

  public void setProviderCode(ProviderCode providerCode) {
    this.providerCode = providerCode;
  }

  public String getTextSearch() {
    return textSearch;
  }

  public void setTextSearch(String textSearch) {
    this.textSearch = textSearch;
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
