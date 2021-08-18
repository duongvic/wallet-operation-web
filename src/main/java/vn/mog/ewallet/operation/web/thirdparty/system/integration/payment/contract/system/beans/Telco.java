package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans;

import java.io.Serializable;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.TelcoType;


public class Telco implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String prefix;

  private TelcoType telcoType;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public TelcoType getTelcoType() {
    return telcoType;
  }

  public void setTelcoType(TelcoType telcoType) {
    this.telcoType = telcoType;
  }

}
