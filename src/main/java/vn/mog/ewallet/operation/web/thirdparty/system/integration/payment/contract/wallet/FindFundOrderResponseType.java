package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

import java.util.Collections;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.FundOrder;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindFundOrderResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private List<FundOrder> fundOrders;
  private Long count; // order quantity
  private Long amount; // total Amount
  private Long fee; // total fee

  public List<FundOrder> getFundOrders() {
    if (fundOrders == null) {
      fundOrders = Collections.emptyList();
    }
    return fundOrders;
  }

  public void setFundOrders(List<FundOrder> fundOrders) {
    this.fundOrders = fundOrders;
  }

  public Long getCount() {
    if (count == null) {
      count = 0L;
    }
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public Long getFee() {
    return fee;
  }

  public void setFee(Long fee) {
    this.fee = fee;
  }
}
