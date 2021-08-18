package vn.mog.ewallet.operation.web.contract.form;

import java.io.Serializable;
import java.util.List;

public class DashboardDataForm implements Serializable {

  private String service;
  private String range;
  private List<String> multiselect;
  private String type;

  public String getService() {
    return service;
  }

  public void setService(String service) {
    this.service = service;
  }

  public String getRange() {
    return range;
  }

  public void setRange(String range) {
    this.range = range;
  }

  public List<String> getMultiselect() {
    return multiselect;
  }

  public void setMultiselect(List<String> multiselect) {
    this.multiselect = multiselect;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
