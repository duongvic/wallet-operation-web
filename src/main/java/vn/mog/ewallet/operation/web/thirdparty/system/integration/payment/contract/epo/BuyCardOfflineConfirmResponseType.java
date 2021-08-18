package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class BuyCardOfflineConfirmResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;
  protected Long purchaseOrderId;
  protected String purchaseOrderCode;

  public Long getPurchaseOrderId() {
    return purchaseOrderId;
  }

  public void setPurchaseOrderId(Long purchaseOrderId) {
    this.purchaseOrderId = purchaseOrderId;
  }

  public String getPurchaseOrderCode() {
    return purchaseOrderCode;
  }

  public void setPurchaseOrderCode(String purchaseOrderCode) {
    this.purchaseOrderCode = purchaseOrderCode;
  }
}
