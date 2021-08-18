package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo;

import java.io.Serializable;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class ExportEpinPurchaseOrderFormResponseType extends MobiliserResponseType
    implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long purchaseOrderId;
  private String name;
  private byte[] content;
  private String contentType;

  public Long getPurchaseOrderId() {
    return purchaseOrderId;
  }

  public void setPurchaseOrderId(Long purchaseOrderId) {
    this.purchaseOrderId = purchaseOrderId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public byte[] getContent() {
    return content;
  }

  public void setContent(byte[] content) {
    this.content = content;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }
}
