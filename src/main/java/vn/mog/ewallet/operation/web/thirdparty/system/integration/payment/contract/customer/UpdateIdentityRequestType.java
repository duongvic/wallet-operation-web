package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.Identity;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class UpdateIdentityRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  private Identity identity;
  private String frontName;
  private String frontContentType;
  private String frontContent;

  private String backName;
  private String backContentType;
  private String backContent;

  private String selfieName;
  private String selfieContentType;
  private String selfieContent;

  public UpdateIdentityRequestType() {

  }

  public Identity getIdentity() {
    return identity;
  }

  public void setIdentity(Identity identity) {
    this.identity = identity;
  }

  public UpdateIdentityRequestType(Identity identity) {
    this.identity = identity;
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
