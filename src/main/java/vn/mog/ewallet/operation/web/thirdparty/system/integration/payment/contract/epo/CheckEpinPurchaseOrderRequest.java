package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.beans.EpinPurchaseOrderDetail;

public class CheckEpinPurchaseOrderRequest extends CheckEpinPurchaseOrderRequestType {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public CheckEpinPurchaseOrderRequest(List<EpinPurchaseOrderDetail> purchaseOrderDetails) {
    this.purchaseOrderDetails = purchaseOrderDetails;
  }

  public CheckEpinPurchaseOrderRequest() {

  }
}
