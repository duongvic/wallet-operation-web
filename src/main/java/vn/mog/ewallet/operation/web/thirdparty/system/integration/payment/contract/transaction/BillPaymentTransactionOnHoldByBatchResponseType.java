package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import vn.mog.framework.contract.base.MobiliserResponseType;

@Getter
@Setter
public class BillPaymentTransactionOnHoldByBatchResponseType extends MobiliserResponseType {

  protected Map<String, Boolean> results;

}
