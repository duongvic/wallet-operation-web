package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo;

public class GetEpinPurchaseOrderRequest extends GetEpinPurchaseOrderRequestType {

  private static final long serialVersionUID = 1L;
  private Long purchaseOrderId;
  private String purchaserOrderCode;
  private boolean includePODetail;

  public GetEpinPurchaseOrderRequest() {
  }

  public GetEpinPurchaseOrderRequest(String poCode, boolean includePODetail) {
    this.purchaserOrderCode = poCode;
    this.includePODetail = includePODetail;
  }

  public GetEpinPurchaseOrderRequest(Long purchaseOrderId) {
    this.purchaseOrderId = purchaseOrderId;
  }

  public GetEpinPurchaseOrderRequest(Long purchaseOrderId, boolean includePODetail) {
    this.purchaseOrderId = purchaseOrderId;
    this.includePODetail = includePODetail;
  }

  public GetEpinPurchaseOrderRequest(Long poMerchantId, String poCode, boolean includePODetail) {
    this.purchaseOrderId = poMerchantId;
    this.purchaserOrderCode = poCode;
    this.includePODetail = includePODetail;
  }

  public Long getPurchaseOrderId() {
    return purchaseOrderId;
  }

  public void setPurchaseOrderId(Long purchaseOrderId) {
    this.purchaseOrderId = purchaseOrderId;
  }

  public String getPoCode() {
    return purchaserOrderCode;
  }

  public void setPoCode(String poCode) {
    this.purchaserOrderCode = poCode;
  }

  public boolean getIncludePODetail() {
    return includePODetail;
  }

  public boolean isIncludePODetail() {
    return includePODetail;
  }

  public void setIncludePODetail(boolean includePODetail) {
    this.includePODetail = includePODetail;
  }
}
