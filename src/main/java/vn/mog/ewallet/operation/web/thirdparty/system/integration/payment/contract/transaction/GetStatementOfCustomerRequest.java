package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction;

public class GetStatementOfCustomerRequest extends FindTransactionsRequestType {

  private static final long serialVersionUID = 1L;

  private Boolean isActorPayer;

  public Boolean getIsActorPayer() {
    return isActorPayer;
  }

  public void setIsActorPayer(Boolean isActorPayer) {
    this.isActorPayer = isActorPayer;
  }

}
