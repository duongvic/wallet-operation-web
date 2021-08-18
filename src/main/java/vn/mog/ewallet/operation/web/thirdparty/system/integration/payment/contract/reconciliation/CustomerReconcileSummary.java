package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation;

import lombok.Data;

@Data
public class CustomerReconcileSummary {

  private Long customerId;
  private String cif;
  private String name;
  private String msisdn;
  private Integer customerType;
  private ReconcileSummary reconcileSummary;
  private Character balanceMatch;
}
