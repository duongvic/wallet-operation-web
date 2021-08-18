package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class UpdateCommissionFreeResponseType extends MobiliserResponseType {
  private Long commissionFreeId;

  public Long getCommissionFreeId() {
    return commissionFreeId;
  }

  public void setCommissionFreeId(Long commissionFreeId) {
    this.commissionFreeId = commissionFreeId;
  }
}
