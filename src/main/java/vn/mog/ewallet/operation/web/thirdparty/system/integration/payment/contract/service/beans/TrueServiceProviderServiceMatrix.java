package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.beans;

import java.io.Serializable;
import java.util.Date;

public class TrueServiceProviderServiceMatrix implements Serializable {

  private static final long serialVersionUID = 1L;
  protected long serviceId;
  protected long providerServiceId;
  protected String description;

  protected Date created;

  public long getServiceId() {
    return serviceId;
  }

  public void setServiceId(long serviceId) {
    this.serviceId = serviceId;
  }

  public long getProviderServiceId() {
    return providerServiceId;
  }

  public void setProviderServiceId(long providerServiceId) {
    this.providerServiceId = providerServiceId;
  }

  public Date getCreated() {
    return this.created;
  }

  public void setCreated(Date value) {
    this.created = value;
  }
}
