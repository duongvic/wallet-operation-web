package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.beans;

import lombok.Getter;
import lombok.Setter;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.beans.TrueService;

@Getter
@Setter
public class CommissionFee extends TrueService {
  private String paymentChannelId;
  private Values commissionFeeValues;
}
