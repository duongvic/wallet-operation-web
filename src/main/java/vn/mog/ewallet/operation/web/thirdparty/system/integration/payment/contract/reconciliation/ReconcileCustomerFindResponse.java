package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.mog.framework.contract.base.MobiliserResponseType;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReconcileCustomerFindResponse extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private List<CustomerReconcileSummary> customerReconcileSummaries;
  private Integer total;
}
