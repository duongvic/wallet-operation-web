package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindTransactionRuleRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected String search;
  protected List<ServiceType> serviceTypes;
  protected int offset;
  protected int limit;

  public String getSearch() {
    return search;
  }

  public void setSearch(String search) {
    this.search = search;
  }

  public List<ServiceType> getServiceTypes() {
    return serviceTypes;
  }

  public void setServiceTypes(List<ServiceType> serviceTypes) {
    this.serviceTypes = serviceTypes;
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
}
