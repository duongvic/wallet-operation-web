package vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.beans;

import java.util.List;


public class ElementList<T> {


  private List<T> elementList;

  private long total;

  public List<T> getElementList() {
    return elementList;
  }

  public void setElementList(List<T> elementList) {
    this.elementList = elementList;
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }
}
