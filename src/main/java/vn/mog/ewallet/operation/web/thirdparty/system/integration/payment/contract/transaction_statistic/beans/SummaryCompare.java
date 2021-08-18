package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.beans;

public class SummaryCompare {
  String key;
  SummaryDTO oldData;
  SummaryDTO newData;


  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public SummaryDTO getOldData() {
    return oldData;
  }

  public void setOldData(SummaryDTO oldData) {
    this.oldData = oldData;
  }

  public SummaryDTO getNewData() {
    return newData;
  }

  public void setNewData(SummaryDTO newData) {
    this.newData = newData;
  }

  public Double caculateLN() {
    if (oldData.getSumOfRevenueAmount().longValue() == 0 && newData.getSumOfRevenueAmount().longValue() > 0) {
      return 100.0;
    }

    if (oldData.getSumOfRevenueAmount().longValue() == 0 && newData.getSumOfRevenueAmount().longValue() < 0) {
      return -100.0;
    }

    if (oldData.getSumOfRevenueAmount().longValue() == 0 && newData.getSumOfRevenueAmount().longValue() == 0) {
      return 0.0;
    }
    Double interest = ((double) newData.getSumOfRevenueAmount() - oldData.getSumOfRevenueAmount()) / oldData.getSumOfRevenueAmount() * 100;
    return interest;
  }
}
