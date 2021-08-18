package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans;

import java.io.Serializable;

public class RiskCategory implements Serializable {

  private static final long serialVersionUID = 1L;
  protected int id;

  protected String name;
  protected Long limitSetId;
  protected String umgrRole;

  public int getId() {
    return this.id;
  }

  public void setId(int value) {
    this.id = value;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String value) {
    this.name = value;
  }

  public Long getLimitSetId() {
    return this.limitSetId;
  }

  public void setLimitSetId(Long value) {
    this.limitSetId = value;
  }

  public String getUmgrRole() {
    return this.umgrRole;
  }

  public void setUmgrRole(String value) {
    this.umgrRole = value;
  }
}
