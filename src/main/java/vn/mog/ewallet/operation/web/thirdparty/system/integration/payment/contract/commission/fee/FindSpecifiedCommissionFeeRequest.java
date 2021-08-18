package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee;

import lombok.Getter;
import lombok.Setter;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.FindTrueServiceRequest;

@Getter
@Setter
public class FindSpecifiedCommissionFeeRequest extends FindTrueServiceRequest {

  private Long customerId;
  private Integer customerType;
  private String paymentChannelId;

}
