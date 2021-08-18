package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.consumer;

public class VerifyKycRequestUpdateRequest extends VerifyKycRequestUpdateRequestType {

  private static final long serialVersionUID = 1L;
  protected String frontName;
  protected String frontContentType;
  protected String frontContent;
  protected String backName;
  protected String backContentType;
  protected String backContent;
  protected String selfieName;
  protected String selfieContentType;
  protected String selfieContent;
  private Long customerId;
  private Long reqId;

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public Long getReqId() {
    return reqId;
  }

  public void setReqId(Long reqId) {
    this.reqId = reqId;
  }

  public String getFrontName() {
    return frontName;
  }

  public void setFrontName(String frontName) {
    this.frontName = frontName;
  }

  public String getFrontContentType() {
    return frontContentType;
  }

  public void setFrontContentType(String frontContentType) {
    this.frontContentType = frontContentType;
  }

  public String getFrontContent() {
    return frontContent;
  }

  public void setFrontContent(String frontContent) {
    this.frontContent = frontContent;
  }

  public String getBackName() {
    return backName;
  }

  public void setBackName(String backName) {
    this.backName = backName;
  }

  public String getBackContentType() {
    return backContentType;
  }

  public void setBackContentType(String backContentType) {
    this.backContentType = backContentType;
  }

  public String getBackContent() {
    return backContent;
  }

  public void setBackContent(String backContent) {
    this.backContent = backContent;
  }

  public String getSelfieName() {
    return selfieName;
  }

  public void setSelfieName(String selfieName) {
    this.selfieName = selfieName;
  }

  public String getSelfieContentType() {
    return selfieContentType;
  }

  public void setSelfieContentType(String selfieContentType) {
    this.selfieContentType = selfieContentType;
  }

  public String getSelfieContent() {
    return selfieContent;
  }

  public void setSelfieContent(String selfieContent) {
    this.selfieContent = selfieContent;
  }

}
