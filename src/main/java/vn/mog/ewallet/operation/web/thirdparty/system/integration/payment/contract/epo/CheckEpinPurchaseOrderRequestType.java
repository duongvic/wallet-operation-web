package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.beans.EpinPurchaseOrderDetail;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class CheckEpinPurchaseOrderRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  protected List<EpinPurchaseOrderDetail> purchaseOrderDetails;

  public List<EpinPurchaseOrderDetail> getPurchaseOrderDetails() {
    return purchaseOrderDetails;
  }

  public void setPurchaseOrderDetails(List<EpinPurchaseOrderDetail> purchaseOrderDetails) {
    this.purchaseOrderDetails = purchaseOrderDetails;
  }

}
