package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo;

import java.util.ArrayList;
import java.util.Collection;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.beans.EpinPurchaseOrder;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindEpinPurchaseOrderResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  protected Long count; // PO quantity
  protected Long quantity; // Card quantity
  protected Long capitalValue;
  protected Long amount; // total Value

  protected Collection<EpinPurchaseOrder> purchaseOrders;

  public Collection<EpinPurchaseOrder> getPurchaseOrders() {
    if (purchaseOrders == null) {
      purchaseOrders = new ArrayList<>();
    }
    return purchaseOrders;
  }

  public void setPurchaseOrders(Collection<EpinPurchaseOrder> purchaseOrders) {
    this.purchaseOrders = purchaseOrders;
  }

  public Long getCount() {
    if (count == null) {
      count = 0L;
    }
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  public Long getQuantity() {
    if (quantity == null) {
      quantity = 0L;
    }
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public Long getCapitalValue() {
    if (capitalValue == null) {
      capitalValue = 0L;
    }
    return capitalValue;
  }

  public void setCapitalValue(Long capitalValue) {
    this.capitalValue = capitalValue;
  }

  public Long getAmount() {
    if (amount == null) {
      amount = 0L;
    }
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

}
