package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee;

import java.util.Collection;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.beans.SpecifiedCommissionFee;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class FindSpecifiedCommissionFeeResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;
  private Collection<SpecifiedCommissionFee> specifiedCommissionFees;
  protected Integer total;
  protected Integer all;

  public Collection<SpecifiedCommissionFee> getSpecifiedCommissionFees() {
    return specifiedCommissionFees;
  }

  public void setSpecifiedCommissionFees(
      Collection<SpecifiedCommissionFee> specifiedCommissionFees) {
    this.specifiedCommissionFees = specifiedCommissionFees;
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
