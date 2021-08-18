package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer;


import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.beans.ProviderService;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindProviderServicesByBlockCustomerResponseType extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;

  private List<ProviderService> providerServices;
  private Long total;
  private Long all;

  public List<ProviderService> getProviderServices() {
    return providerServices;
  }

  public void setProviderServices(
      List<ProviderService> providerServices) {
    this.providerServices = providerServices;
  }

  public Long getTotal() {
    if (total == null) {
      total = 0L;
    }
    return total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }

  public Long getAll() {
    return all;
  }

  public void setAll(Long all) {
    this.all = all;
  }
}
