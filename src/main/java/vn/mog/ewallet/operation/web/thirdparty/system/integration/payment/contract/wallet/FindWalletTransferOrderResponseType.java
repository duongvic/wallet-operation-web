package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

import java.util.Collections;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.WalletTransferOrder;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindWalletTransferOrderResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private List<WalletTransferOrder> orders;
  private Long total; // order quantity
  private Long totalAmount; // total Amount

  public List<WalletTransferOrder> getOrders() {
    if (orders == null) {
      orders = Collections.emptyList();
    }
    return orders;
  }

  public void setOrders(List<WalletTransferOrder> orders) {
    this.orders = orders;
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

  public Long getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(Long totalAmount) {
    this.totalAmount = totalAmount;
  }
}
