package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee;

import lombok.Getter;
import lombok.Setter;
import vn.mog.framework.contract.base.MobiliserRequestType;

@Getter
@Setter
public class GetCommissionFeeRequestType extends MobiliserRequestType {

  private Long serviceId;
  private String serviceCode;
  private Integer customerTypeId;
  private String paymentChannelId;

}
