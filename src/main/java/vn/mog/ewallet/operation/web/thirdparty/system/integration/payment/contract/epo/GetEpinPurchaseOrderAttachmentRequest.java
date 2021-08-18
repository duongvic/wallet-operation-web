package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo;

@SuppressWarnings("serial")
public class GetEpinPurchaseOrderAttachmentRequest extends GetEpinPurchaseOrderAttachmentRequestType {

  private Long purchaseOrderId;
  private Action action; // Get file or get Password

  public GetEpinPurchaseOrderAttachmentRequest() {
  }

  public GetEpinPurchaseOrderAttachmentRequest(Long purchaseOrderId, Action action) {
    this.purchaseOrderId = purchaseOrderId;
    this.action = action;
  }

  public Long getPurchaseOrderId() {
    return purchaseOrderId;
  }

  public void setPurchaseOrderId(Long purchaseOrderId) {
    this.purchaseOrderId = purchaseOrderId;
  }

  public Action getAction() {
    return action;
  }

  public void setAction(Action action) {
    this.action = action;
  }

  public enum Action {
    GET_FILE, GET_PASSWORD
  }
}
