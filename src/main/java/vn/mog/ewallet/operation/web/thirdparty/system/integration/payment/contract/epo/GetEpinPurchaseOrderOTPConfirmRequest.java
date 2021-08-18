package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo;

@SuppressWarnings("serial")
public class GetEpinPurchaseOrderOTPConfirmRequest extends GetEpinPurchaseOrderOTPConfirmRequestType {

  private Long purchaseOrderId;

  public GetEpinPurchaseOrderOTPConfirmRequest() {
  }

  public GetEpinPurchaseOrderOTPConfirmRequest(Long purchaseOrderId) {
    this.purchaseOrderId = purchaseOrderId;
  }

  public Long getPurchaseOrderId() {
    return purchaseOrderId;
  }

  public void setPurchaseOrderId(Long purchaseOrderId) {
    this.purchaseOrderId = purchaseOrderId;
  }
}
