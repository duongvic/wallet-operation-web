package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans;

import java.io.Serializable;
import java.util.Date;

public class Attachment implements Serializable {

  private static final long serialVersionUID = 1L;
  protected Long id;
  protected Long customerId;
  protected Long txnId;
  protected Integer attachmentType;

  protected String name;

  protected String contentType;
  protected Long contentLength;
  protected String content;

  protected Date created;
  protected Integer status;

  public Long getId() {
    return this.id;
  }

  public void setId(Long value) {
    this.id = value;
  }

  public Long getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(Long value) {
    this.customerId = value;
  }

  public Long getTxnId() {
    return this.txnId;
  }

  public void setTxnId(Long value) {
    this.txnId = value;
  }

  public Integer getAttachmentType() {
    return this.attachmentType;
  }

  public void setAttachmentType(Integer value) {
    this.attachmentType = value;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String value) {
    this.name = value;
  }

  public String getContentType() {
    return this.contentType;
  }

  public void setContentType(String value) {
    this.contentType = value;
  }

  public Date getCreated() {
    return this.created;
  }

  public void setCreated(Date value) {
    this.created = value;
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setStatus(Integer value) {
    this.status = value;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Long getContentLength() {
    return contentLength;
  }

  public void setContentLength(long contentLength) {
    this.contentLength = contentLength;
  }

  public String getBase64Image() {
    return "data:" + this.contentType + ";base64," + this.content;
  }

}
