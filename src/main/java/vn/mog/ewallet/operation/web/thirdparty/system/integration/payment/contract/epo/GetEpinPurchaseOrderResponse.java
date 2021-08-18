package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo;


import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.beans.EpinPurchaseOrder;

@SuppressWarnings("serial")
public class GetEpinPurchaseOrderResponse extends GetEpinPurchaseOrderResponseType {

  private EpinPurchaseOrder purchaseOrder;

  public GetEpinPurchaseOrderResponse() {
  }

  public EpinPurchaseOrder getPurchaseOrder() {
    return purchaseOrder;
  }

  public void setPurchaseOrder(EpinPurchaseOrder purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }
}
