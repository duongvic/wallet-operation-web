package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.beans.CommissionFee;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GetCommissionFeeResponseType extends MobiliserResponseType {

  private CommissionFee commissionFee;

  public CommissionFee getCommissionFee() {
    return commissionFee;
  }

  public void setCommissionFee(CommissionFee commissionFee) {
    this.commissionFee = commissionFee;
  }
}
