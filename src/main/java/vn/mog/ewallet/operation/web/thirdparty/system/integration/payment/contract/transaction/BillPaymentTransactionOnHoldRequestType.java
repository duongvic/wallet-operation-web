package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import vn.mog.framework.contract.base.MobiliserRequestType;

@Setter
@Getter
public class BillPaymentTransactionOnHoldRequestType extends MobiliserRequestType
    implements Serializable {

  private static final long serialVersionUID = 1L;
  protected Long txnId;
  protected String provider;
  protected String note;
}
