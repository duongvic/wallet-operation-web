package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

import java.util.List;

public class DataTableResponse<T> {

  private Long draw;
  private Long recordsTotal;
  private Long recordsFiltered;
  private List<T> dataList;
  private Long total1;
  private Long total2;
  private Long total3;
  private Long total4;

  public Long getDraw() {
    return draw;
  }

  public void setDraw(Long draw) {
    this.draw = draw;
  }

  public Long getRecordsTotal() {
    return recordsTotal;
  }

  public void setRecordsTotal(Long recordsTotal) {
    this.recordsTotal = recordsTotal;
  }

  public Long getRecordsFiltered() {
    return recordsFiltered;
  }

  public void setRecordsFiltered(Long recordsFiltered) {
    this.recordsFiltered = recordsFiltered;
  }

  public List<T> getDataList() {
    return dataList;
  }

  public void setDataList(List<T> dataList) {
    this.dataList = dataList;
  }

  public Long getTotal1() {
    return total1;
  }

  public void setTotal1(Long total1) {
    this.total1 = total1;
  }

  public Long getTotal2() {
    return total2;
  }

  public void setTotal2(Long total2) {
    this.total2 = total2;
  }

  public Long getTotal3() {
    return total3;
  }

  public void setTotal3(Long total3) {
    this.total3 = total3;
  }

  public Long getTotal4() {
    return total4;
  }

  public void setTotal4(Long total4) {
    this.total4 = total4;
  }
}
