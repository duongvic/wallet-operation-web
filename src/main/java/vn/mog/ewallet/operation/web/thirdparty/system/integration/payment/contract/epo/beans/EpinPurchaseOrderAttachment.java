package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.beans;

import java.io.Serializable;
import java.util.Date;

public class EpinPurchaseOrderAttachment implements Serializable {

  private static final long serialVersionUID = 1L;
  protected Long id;
  protected Long purchaseOrderId;

  protected String name;
  protected byte[] content;
  protected String contentType;

  protected Date created;
  protected int status;

  public Long getId() {
    return this.id;
  }

  public void setId(Long value) {
    this.id = value;
  }

  public Long getPurchaseOrderId() {
    return purchaseOrderId;
  }

  public void setPurchaseOrderId(Long purchaseOrderId) {
    this.purchaseOrderId = purchaseOrderId;
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

  public byte[] getContent() {
    return this.content;
  }

  public void setContent(byte[] value) {
    this.content = value;
  }

  public Date getCreated() {
    return this.created;
  }

  public void setCreated(Date value) {
    this.created = value;
  }

  public int getStatus() {
    return this.status;
  }

  public void setStatus(int value) {
    this.status = value;
  }
}
