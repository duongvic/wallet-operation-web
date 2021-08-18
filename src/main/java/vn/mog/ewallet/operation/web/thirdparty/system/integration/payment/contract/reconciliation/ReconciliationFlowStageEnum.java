package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@AllArgsConstructor
public enum ReconciliationFlowStageEnum {
  INIT("INIT", "label.INIT"),

  OPERATION_MANAGER_REJECTED("OPERATION_MANAGER_REJECTED", "label.OPERATION_MANAGER_REJECTED"),
  OPERATION_STAFF_SUBMITED("OPERATION_STAFF_SUBMITED", "label.OPERATION_STAFF_SUBMITED"),

  MERCHANT_REJECTED("MERCHANT_REJECTED", "label.MERCHANT_REJECTED"),
  OPERATION_MANAGER_APPROVED("OPERATION_MANAGER_APPROVED", "label.OPERATION_MANAGER_APPROVED"),

  FINANCE_MANAGER_REJECTED("FINANCE_MANAGER_REJECTED", "label.FINANCE_MANAGER_REJECTED"),
  MERCHANT_CONFIRMED("MERCHANT_CONFIRMED", "label.MERCHANT_CONFIRMED"),

  FINISHED("FINISHED", "label.FINISHED"),
  ;

  public String code;

  public String displayText;

  public static final Map<String, String> STAGES = new LinkedHashMap<>();

  static {
    STAGES.put(ReconciliationFlowStageEnum.INIT.code, ReconciliationFlowStageEnum.INIT.displayText);
    STAGES.put(ReconciliationFlowStageEnum.OPERATION_MANAGER_REJECTED.code, ReconciliationFlowStageEnum.OPERATION_MANAGER_REJECTED.displayText);
    STAGES.put(ReconciliationFlowStageEnum.OPERATION_STAFF_SUBMITED.code, ReconciliationFlowStageEnum.OPERATION_STAFF_SUBMITED.displayText);
    STAGES.put(ReconciliationFlowStageEnum.MERCHANT_REJECTED.code, ReconciliationFlowStageEnum.MERCHANT_REJECTED.displayText);
    STAGES.put(ReconciliationFlowStageEnum.OPERATION_MANAGER_APPROVED.code, ReconciliationFlowStageEnum.OPERATION_MANAGER_APPROVED.displayText);
    STAGES.put(ReconciliationFlowStageEnum.FINANCE_MANAGER_REJECTED.code, ReconciliationFlowStageEnum.FINANCE_MANAGER_REJECTED.displayText);
    STAGES.put(ReconciliationFlowStageEnum.MERCHANT_CONFIRMED.code, ReconciliationFlowStageEnum.MERCHANT_CONFIRMED.displayText);
    STAGES.put(ReconciliationFlowStageEnum.FINISHED.code, ReconciliationFlowStageEnum.FINISHED.displayText);
  }
}
