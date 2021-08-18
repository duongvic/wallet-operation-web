package vn.mog.ewallet.operation.web.contract;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.FindTransactionsRequestType;

public class FindCommonTransactionRequestModel {

  private FindTransactionsRequestType requestType;

  private List customerList;

  public FindTransactionsRequestType getRequestType() {
    return requestType;
  }

  public void setRequestType(
      FindTransactionsRequestType requestType) {
    this.requestType = requestType;
  }

  public List getCustomerList() {
    return customerList;
  }

  public void setCustomerList(List customerList) {
    this.customerList = customerList;
  }
}
