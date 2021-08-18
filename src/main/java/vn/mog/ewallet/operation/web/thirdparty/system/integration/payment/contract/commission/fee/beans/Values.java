package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.beans;

public class Values {

  private Double commission;
  private Double fee;
  private Long commissionFixedAmount;
  private Long feeFixedAmount;
  private Integer level;

  public Values() {
  }

  public Double getCommission() {
    return commission;
  }

  public void setCommission(Double commission) {
    this.commission = commission;
  }

  public Double getFee() {
    return fee;
  }

  public void setFee(Double fee) {
    this.fee = fee;
  }

  public Long getCommissionFixedAmount() {
    return commissionFixedAmount;
  }

  public void setCommissionFixedAmount(Long commissionFixedAmount) {
    this.commissionFixedAmount = commissionFixedAmount;
  }

  public Long getFeeFixedAmount() {
    return feeFixedAmount;
  }

  public void setFeeFixedAmount(Long feeFixedAmount) {
    this.feeFixedAmount = feeFixedAmount;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }
}