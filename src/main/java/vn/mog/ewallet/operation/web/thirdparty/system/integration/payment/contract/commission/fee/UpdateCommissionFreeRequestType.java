package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee;

import lombok.Getter;
import lombok.Setter;
import vn.mog.framework.contract.base.MobiliserRequestType;

@Getter
@Setter
public class UpdateCommissionFreeRequestType extends MobiliserRequestType {
  // There must be one of these
  private Long serviceId; // Optional 1st
  private String serviceCode; // Optional

  private Integer customerTypeId;
  private String paymentChannelId;

  private Double commission;
  private Double fee;
  private Long commissionFixedAmount;
  private Long feeFixedAmount;

}
