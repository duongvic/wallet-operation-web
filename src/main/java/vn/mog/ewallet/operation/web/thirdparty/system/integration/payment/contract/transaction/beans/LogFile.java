package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans;

import java.io.Serializable;

public class LogFile implements Serializable {

  private static final long serialVersionUID = 1L;

  private String name;
  private byte[] content;
  private String contentType;

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
