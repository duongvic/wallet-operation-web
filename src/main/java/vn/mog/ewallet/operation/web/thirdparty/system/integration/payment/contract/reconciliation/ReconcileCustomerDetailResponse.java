package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation;

import java.util.Collection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.Transaction;
import vn.mog.framework.contract.base.MobiliserResponseType;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReconcileCustomerDetailResponse extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private Collection<Transaction> transactions;
  private CustomerReconcileSummary customerReconcileSummary;
}
