package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type;

public enum FundOrderFlowStageType {

  FUND_ORDER_INIT(0, "stage.declared"), 
  
  SALE_EXCUTIVE_REJECTED(1, "stage.sale.excutive.rejected"), 
  SALE_EXCUTIVE_VERIFY(2, "stage.sale.excutive.ready.to.verify"),

  FINANCE_SUPPORT_REJECTED(3, "stage.finance.support.rejected"),
  FINANCE_SUPPORT_VERIFY(4, "stage.finance.support.ready.to.verify"),

  FINANCE_MANAGER_REJECTED(5, "stage.finance.manager.rejected"), 
  FINANCE_MAMAGER_APPROVE(6, "stage.finance.manager.ready.to.verify"),
  
  FINANCE_MANAGER_REPAYMENT_REJECTED(7, "stage.finance.manager.repayment.rejected"),
  FINANCE_MANAGER_REPAYMENT_APPROVE(8, "stage.finance.manager.repayment.approve"),
  
  FUND_ORDER_FINISHED(10, "stage.finished");

  public int code;
  public String displayText;

  FundOrderFlowStageType(int value, String displayText) {
    this.code = value;
    this.displayText = displayText;
  }

  public static FundOrderFlowStageType getWorkFlowState(int value) {
    for (FundOrderFlowStageType state : FundOrderFlowStageType.values()) {
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
