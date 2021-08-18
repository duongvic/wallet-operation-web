package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

import java.io.Serializable;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.FundOrderAttachment;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class CreateFundOrderAttachmentRequestType extends MobiliserRequestType implements Serializable {

  private static final long serialVersionUID = 1L;

  protected FundOrderAttachment attachment;
  
  public FundOrderAttachment getAttachment() {
    return attachment;
  }
  
  public void setAttachment(FundOrderAttachment attachment) {
    this.attachment = attachment;
  }

}
