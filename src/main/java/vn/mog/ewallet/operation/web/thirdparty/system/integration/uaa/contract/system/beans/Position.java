package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.beans;

import java.io.Serializable;

public class Position implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private String name;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
