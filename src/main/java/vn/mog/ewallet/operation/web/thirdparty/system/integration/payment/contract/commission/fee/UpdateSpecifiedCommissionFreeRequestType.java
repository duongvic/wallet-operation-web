package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class UpdateSpecifiedCommissionFreeRequestType extends MobiliserRequestType {
  private static final long serialVersionUID = 1L;

  // There must be one of these
  private Long serviceId; // Optional 1st
  private String serviceCode; // Optional

  private Long customerId;

  private Double specifiedCommission;
  private Double specifiedFee;
  private Long specifiedCommissionFixedAmount;
  private Long specifiedFeeFixedAmount;

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

  public Double getSpecifiedCommission() {
    return specifiedCommission;
  }

  public void setSpecifiedCommission(Double specifiedCommission) {
    this.specifiedCommission = specifiedCommission;
  }

  public Double getSpecifiedFee() {
    return specifiedFee;
  }

  public void setSpecifiedFee(Double specifiedFee) {
    this.specifiedFee = specifiedFee;
  }

  public Long getSpecifiedCommissionFixedAmount() {
    return specifiedCommissionFixedAmount;
  }

  public void setSpecifiedCommissionFixedAmount(Long specifiedCommissionFixedAmount) {
    this.specifiedCommissionFixedAmount = specifiedCommissionFixedAmount;
  }

  public Long getSpecifiedFeeFixedAmount() {
    return specifiedFeeFixedAmount;
  }

  public void setSpecifiedFeeFixedAmount(Long specifiedFeeFixedAmount) {
    this.specifiedFeeFixedAmount = specifiedFeeFixedAmount;
  }
}
