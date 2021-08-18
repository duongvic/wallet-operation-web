package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans;

import java.util.LinkedHashMap;
import java.util.Map;

public enum TransactionReversalOrderFlowStage {

    INIT(0, "reversal.flow.stage.declared"), FINANCE_REJECTED(1, "reversal.flow.stage.finance.rejected"), FINANCE_READY_TO_VERIFY(2,
	    "reversal.flow.stage.finance.verify"), FINISHED(4, "reversal.flow.stage.finished");
    // @formatter:on

  public static final Map<Integer, String> STATES_REVERSAL_TYPES = new LinkedHashMap<>();

  static {
    STATES_REVERSAL_TYPES.put(TransactionReversalOrderFlowStage.FINANCE_READY_TO_VERIFY.code,"reversal.states.2");
    STATES_REVERSAL_TYPES.put(TransactionReversalOrderFlowStage.FINANCE_REJECTED.code,"reversal.states.1");
    STATES_REVERSAL_TYPES.put(TransactionReversalOrderFlowStage.FINISHED.code,"reversal.states.4");

  }
  public int code;
  public String displayText;

  TransactionReversalOrderFlowStage(int value, String displayText) {
    this.code = value;
    this.displayText = displayText;
  }

  public static TransactionReversalOrderFlowStage getWorkFlowState(int value) {
    for (TransactionReversalOrderFlowStage state : TransactionReversalOrderFlowStage.values()) {
      if (state.code == value) {
        return state;
      }
    }
    return null;
  }

  public int value() {
    return this.code;
  }

  public String displayText() {
    return this.displayText;
  }

}
