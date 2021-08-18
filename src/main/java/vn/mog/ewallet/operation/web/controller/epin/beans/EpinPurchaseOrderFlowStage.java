package vn.mog.ewallet.operation.web.controller.epin.beans;

public enum EpinPurchaseOrderFlowStage {

  // @formatter:off
    /*
     * INIT(0, "Declared"), SALESUPPORT_REJECTED (1, "Sale Support rejected"),
     * SALESUPPORT_READY_TO_VERIFY(2, "Sale Support ready to verify"),
     * MERCHANT_CANCEL_ORDER(3, "Merchant cancel Order"), EPIN_EXPORT_ALLOWED(4,
     * "Epin export Allowed"), FINISHED(6, "Finished");
     */
    INIT(0, "epin.flow.stage.declared"), SALESUPPORT_REJECTED(1, "epin.flow.stage.salesupport.rejected"), SALESUPPORT_READY_TO_VERIFY(2,
	    "epin.flow.stage.salesupport.verify"), MERCHANT_CANCEL_ORDER(3,
		    "epin.flow.stage.merchant.cancel.order"), EPIN_EXPORT_ALLOWED(4, "epin.flow.stage.export.allowed"), FINISHED(6, "epin.flow.stage.finished");
    // @formatter:on

  public int code;
  public String displayText;

  EpinPurchaseOrderFlowStage(int value, String displayText) {
    this.code = value;
    this.displayText = displayText;
  }

  public static EpinPurchaseOrderFlowStage getWorkFlowState(int value) {
    for (EpinPurchaseOrderFlowStage state : EpinPurchaseOrderFlowStage.values()) {
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
