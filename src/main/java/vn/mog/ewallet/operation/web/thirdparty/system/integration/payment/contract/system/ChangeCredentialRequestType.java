package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class ChangeCredentialRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;
  protected Long customerId;
  protected String cif;
  protected String oldCredential;

  protected String newCredential;
  protected int credentialType;

  public String getCif() {
    return cif;
  }

  public void setCif(String cif) {
    this.cif = cif;
  }

  public Long getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(Long value) {
    this.customerId = value;
  }

  public String getOldCredential() {
    return this.oldCredential;
  }

  public void setOldCredential(String value) {
    this.oldCredential = value;
  }

  public String getNewCredential() {
    return this.newCredential;
  }

  public void setNewCredential(String value) {
    this.newCredential = value;
  }

  public int getCredentialType() {
    return this.credentialType;
  }

  public void setCredentialType(int value) {
    this.credentialType = value;
  }
}
