package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class DeleteSpecifiedCommissionFreeRequestType extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;

  // There must be one of these
  private Long serviceId; // Optional 1st
  private String serviceCode; // Optional

  private Long customerId;

  public Long getServiceId() {
    return serviceId;
  }

  public void setServiceId(Long serviceId) {
    this.serviceId = serviceId;
  }

  public String getServiceCode() {
    return serviceCode;
  }

  public void setServiceCode(String serviceCode) {
    this.serviceCode = serviceCode;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }
}
