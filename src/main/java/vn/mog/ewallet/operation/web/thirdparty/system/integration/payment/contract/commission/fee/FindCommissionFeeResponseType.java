package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee;

import java.util.Collection;
import java.util.Collections;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.beans.CommissionFee;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindCommissionFeeResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;
  private Collection<CommissionFee> commissionFees;
  protected Integer total;
  protected Integer all;

  public Collection<CommissionFee> getCommissionFees() {
    return commissionFees == null ? Collections.emptyList() : commissionFees;
  }

  public void setCommissionFees(
      Collection<CommissionFee> commissionFees) {
    this.commissionFees = commissionFees;
  }

  public Integer getTotal() {
    return total == null ? 0 : total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public Integer getAll() {
    return all == null ? 0 : all;
  }

  public void setAll(Integer all) {
    this.all = all;
  }
}
