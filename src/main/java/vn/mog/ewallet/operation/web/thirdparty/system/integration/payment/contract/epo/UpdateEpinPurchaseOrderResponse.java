package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo;

@SuppressWarnings("serial")
public class UpdateEpinPurchaseOrderResponse extends UpdateEpinPurchaseOrderResponseType {

  private Long purchaseOrderId;
  private String purchaseOrderCode;

  public UpdateEpinPurchaseOrderResponse() {
  }

  public Long getPoId() {
    return purchaseOrderId;
  }

  public void setPoId(Long poId) {
    this.purchaseOrderId = poId;
  }

  public String getPoCode() {
    return purchaseOrderCode;
  }

  public void setPoCode(String poCode) {
    this.purchaseOrderCode = poCode;
  }
}
