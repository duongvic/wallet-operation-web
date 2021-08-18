package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Attachment;

public class GetStoreS3UrlResponse extends GetStoreS3UrlResponseType {

  private String s3Url;
  private Boolean usedS3;
  private Attachment attachment;

  public GetStoreS3UrlResponse() {

  }

  public GetStoreS3UrlResponse(String s3Url, Boolean usedS3, Attachment attachment) {
    this.s3Url = s3Url;
    this.usedS3 = usedS3;
    this.attachment = attachment;
  }

  public String getS3Url() {
    return s3Url;
  }

  public void setS3Url(String s3Url) {
    this.s3Url = s3Url;
  }

  public Boolean getUsedS3() {
    return usedS3;
  }

  public void setUsedS3(Boolean usedS3) {
    this.usedS3 = usedS3;
  }

  public Attachment getAttachment() {
    return attachment;
  }

  public void setAttachment(Attachment attachment) {
    this.attachment = attachment;
  }
}
