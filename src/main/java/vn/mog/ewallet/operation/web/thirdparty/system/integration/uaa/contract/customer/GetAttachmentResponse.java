package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer;

import java.util.Collections;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Attachment;

public class GetAttachmentResponse extends GetAttachmentResponseType {

  private List<Attachment> attachments;

  public List<Attachment> getAttachments() {
    return attachments != null ? attachments : Collections.emptyList();
  }

  public void setAttachments(List<Attachment> attachments) {
    this.attachments = attachments;
  }


}
