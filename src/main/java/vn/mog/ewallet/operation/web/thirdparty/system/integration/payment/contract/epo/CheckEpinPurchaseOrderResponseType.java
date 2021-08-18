package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class CheckEpinPurchaseOrderResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  protected Long balance;
  protected Long totalDiscountAmount;
  protected Long totalFeeAmount;

  public Long getBalance() {
    if (balance == null) {
      balance = 0L;
    }
    return balance;
  }

  public void setBalance(Long balance) {
    this.balance = balance;
  }

  public Long getTotalDiscountAmount() {
    if (totalDiscountAmount == null) {
      totalDiscountAmount = 0L;
    }
    return totalDiscountAmount;
  }

  public void setTotalDiscountAmount(Long totalDiscountAmount) {
    this.totalDiscountAmount = totalDiscountAmount;
  }

  public Long getTotalFeeAmount() {
    if (totalFeeAmount == null) {
      totalFeeAmount = 0L;
    }
    return totalFeeAmount;
  }

  public void setTotalFeeAmount(Long totalFeeAmount) {
    this.totalFeeAmount = totalFeeAmount;
  }
}
