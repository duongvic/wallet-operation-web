package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee;

import vn.mog.framework.contract.base.MobiliserResponseType;

public class UpdateSpecifiedCommissionFreeResponseType extends MobiliserResponseType {
  private static final long serialVersionUID = 1L;

  private Long specifiedCommissionFreeId;

  public Long getSpecifiedCommissionFreeId() {
    return specifiedCommissionFreeId;
  }

  public void setSpecifiedCommissionFreeId(Long specifiedCommissionFreeId) {
    this.specifiedCommissionFreeId = specifiedCommissionFreeId;
  }
}
