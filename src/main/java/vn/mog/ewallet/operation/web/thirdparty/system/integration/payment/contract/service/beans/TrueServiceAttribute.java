package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.beans;

import java.io.Serializable;

public class TrueServiceAttribute implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long serviceId;
  private String serviceAttributeType;
  private String value;

  public TrueServiceAttribute() {
  }

  public TrueServiceAttribute(String serviceAttributeType, String value) {
    this.serviceAttributeType = serviceAttributeType;
    this.value = value;
  }

  public Long getServiceId() {
    return serviceId;
  }

  public void setServiceId(Long serviceId) {
    this.serviceId = serviceId;
  }

  public String getServiceAttributeType() {
    return serviceAttributeType;
  }

  public void setServiceAttributeType(String serviceAttributeType) {
    this.serviceAttributeType = serviceAttributeType;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
