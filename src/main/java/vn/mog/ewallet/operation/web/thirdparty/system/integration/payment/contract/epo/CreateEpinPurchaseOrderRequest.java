package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.beans.EpinPurchaseOrder;

public class CreateEpinPurchaseOrderRequest extends CreateEpinPurchaseOrderRequestType {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private EpinPurchaseOrder purchaseOrder;
  private Action action;

  public CreateEpinPurchaseOrderRequest() {
  }

  public CreateEpinPurchaseOrderRequest(EpinPurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }

  public EpinPurchaseOrder getPurchaseOrder() {
    return purchaseOrder;
  }

  public void setPurchaseOrder(EpinPurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }

  public Action getAction() {
    return action;
  }

  public void setAction(Action action) {
    this.action = action;
  }

  public enum Action {
    SAVE, NEXT
  }
}
