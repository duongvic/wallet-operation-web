package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class CreateFundOrderAttachmentResponseType extends MobiliserResponseType implements Serializable {
  private static final long serialVersionUID = 1L;

  protected Long attachmentId;
  protected Long fundOrderId;
  

  public Long getAttachmentId() {
    return attachmentId;
  }
  
  public void setAttachmentId(Long attachmentId) {
    this.attachmentId = attachmentId;
  }
  
  public Long getFundOrderId() {
    return fundOrderId;
  }

  public void setFundOrderId(Long fundOrderId) {
    this.fundOrderId = fundOrderId;
  }

}
