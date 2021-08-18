package vn.mog.ewallet.operation.web.controller.epin.beans;

import java.io.Serializable;
import java.util.List;

public class EpinPurchaseOrderSummaryTmp implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long balance;
  private Long totalDiscountAmount;
  private Long totalFeeAmount;
  private Long totalQuantity;
  private Long totalMoney;
  private List<EpinPurchaseOrderDetailTmp> purchaseOrderDetails;

  public Long getTotalQuantity() {
    return totalQuantity;
  }

  public void setTotalQuantity(Long totalQuantity) {
    this.totalQuantity = totalQuantity;
  }

  public Long getTotalMoney() {
    return totalMoney;
  }

  public void setTotalMoney(Long totalMoney) {
    this.totalMoney = totalMoney;
  }

  public Long getBalance() {
    return balance;
  }

  public void setBalance(Long balance) {
    this.balance = balance;
  }

  public Long getTotalDiscountAmount() {
    return totalDiscountAmount;
  }

  public void setTotalDiscountAmount(Long totalDiscountAmount) {
    this.totalDiscountAmount = totalDiscountAmount;
  }

  public Long getTotalFeeAmount() {
    return totalFeeAmount;
  }

  public void setTotalFeeAmount(Long totalFeeAmount) {
    this.totalFeeAmount = totalFeeAmount;
  }

  public List<EpinPurchaseOrderDetailTmp> getPurchaseOrderDetails() {
    return purchaseOrderDetails;
  }

  public void setPurchaseOrderDetails(List<EpinPurchaseOrderDetailTmp> purchaseOrderDetails) {
    this.purchaseOrderDetails = purchaseOrderDetails;
  }

}
