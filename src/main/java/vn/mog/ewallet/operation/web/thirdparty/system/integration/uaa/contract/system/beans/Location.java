package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.beans;

import java.io.Serializable;

public class Location implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private Long id;
  private String name;
  private String code;
  private String parentCode;
  private Integer locationType;

  public static final Integer COUNTRY = 0;
  public static final Integer CITY = 1;
  public static final Integer DISTRICT = 2;
  public static final Integer COMMUE = 3;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getParentCode() {
    return parentCode;
  }

  public void setParentCode(String parentCode) {
    this.parentCode = parentCode;
  }

  public Integer getLocationType() {
    return locationType;
  }

  public void setLocationType(Integer locationType) {
    this.locationType = locationType;
  }


}
