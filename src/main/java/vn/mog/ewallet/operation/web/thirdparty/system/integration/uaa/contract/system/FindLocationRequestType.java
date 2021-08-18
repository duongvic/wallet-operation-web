package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.system.beans.LocationType;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindLocationRequestType extends MobiliserRequestType {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  protected String parentCode;
  protected Integer locationType = LocationType.All.code;
  protected List<String> codes;


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

  public List<String> getCodes() {
    return codes;
  }

  public void setCodes(List<String> codes) {
    this.codes = codes;
  }
}
