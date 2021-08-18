package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans;

import java.io.Serializable;

public class Bank implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private String bankName;
  private String bankCode;
  private String displayName;

  private Character dbActive = Character.valueOf('N');

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public Character getDbActive() {
    return dbActive;
  }

  public void setDbActive(Character dbActive) {
    this.dbActive = dbActive;
  }

}
