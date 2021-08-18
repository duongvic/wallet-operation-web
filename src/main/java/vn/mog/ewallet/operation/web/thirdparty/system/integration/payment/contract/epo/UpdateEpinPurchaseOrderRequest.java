package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo;


import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.beans.EpinPurchaseOrder;

@SuppressWarnings("serial")
public class UpdateEpinPurchaseOrderRequest extends UpdateEpinPurchaseOrderRequestType {

  private EpinPurchaseOrder purchaseOrder;
  private Action action;

  public UpdateEpinPurchaseOrderRequest(EpinPurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }

  public EpinPurchaseOrder getPurchaseOrder() {
    return purchaseOrder;
  }

  public void setPurchaseOrder(EpinPurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }

  public UpdateEpinPurchaseOrderRequest.Action getAction() {
    return action;
  }

  public void setAction(UpdateEpinPurchaseOrderRequest.Action action) {
    this.action = action;
  }

  public enum Action {
    SAVE, NEXT
  }
}
