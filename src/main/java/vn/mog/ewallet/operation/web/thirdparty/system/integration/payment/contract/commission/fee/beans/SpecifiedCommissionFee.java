package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.beans;

public class SpecifiedCommissionFee extends CommissionFee {

  private Values specifiedCommissionFeeValues;
  private Boolean isUseSpecifiedCommission;

  public Values getSpecifiedCommissionFeeValues() {
    return specifiedCommissionFeeValues;
  }

  public void setSpecifiedCommissionFeeValues(
      Values specifiedCommissionFeeValues) {
    this.specifiedCommissionFeeValues = specifiedCommissionFeeValues;
  }

  public Boolean getIsUseSpecifiedCommission() {
    return isUseSpecifiedCommission;
  }

  public void setIsUseSpecifiedCommission(Boolean useSpecifiedCommission) {
    isUseSpecifiedCommission = useSpecifiedCommission;
  }
}
