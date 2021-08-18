package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer;


import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Attachment;

public class UpdateImageProfileCustomerRequest extends UpdateImageProfileCustomerRequestType {

  private Attachment attachment;

  public UpdateImageProfileCustomerRequest() {
  }

  public UpdateImageProfileCustomerRequest(Attachment attachment) {
    this.attachment = attachment;
  }

  public Attachment getAttachment() {
    return attachment;
  }

  public void setAttachment(Attachment attachment) {
    this.attachment = attachment;
  }
}
