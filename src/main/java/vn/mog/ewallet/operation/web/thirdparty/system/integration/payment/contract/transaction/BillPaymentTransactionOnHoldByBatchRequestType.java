package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import vn.mog.framework.contract.base.MobiliserRequestType;

@Setter
@Getter
public class BillPaymentTransactionOnHoldByBatchRequestType extends MobiliserRequestType {

  protected List<String> txnRefIds;
  protected String provider;
  protected String note;
}
