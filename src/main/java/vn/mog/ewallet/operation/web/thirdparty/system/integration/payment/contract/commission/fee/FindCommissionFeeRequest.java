package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.FindTrueServiceRequest;

@Data
@EqualsAndHashCode(callSuper = true)
public class FindCommissionFeeRequest extends FindTrueServiceRequest {

  private Integer customerType;
  private String paymentChannelId;

}
