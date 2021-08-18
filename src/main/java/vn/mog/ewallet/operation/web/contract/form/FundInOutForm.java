package vn.mog.ewallet.operation.web.contract.form;

import java.io.Serializable;
import java.util.List;

public class FundInOutForm implements Serializable {

  private String id;
  private String range;
  private List<String> multiselect;
  private String type;
  private String status;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
