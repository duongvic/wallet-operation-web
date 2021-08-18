package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.beans.EpinPurchaseOrderAttachment;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetEpinPurchaseOrderAttachmentResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  protected EpinPurchaseOrderAttachment attachment;

  public EpinPurchaseOrderAttachment getAttachment() {
    return attachment;
  }

  public void setAttachment(EpinPurchaseOrderAttachment attachment) {
    this.attachment = attachment;
  }
}
