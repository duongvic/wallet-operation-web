package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction;

public class GetTransactionRequest extends GetTransactionRequestType {

  private static final long serialVersionUID = 1L;

  public GetTransactionRequest(Long txnId) {
    this.txnId = txnId;
  }

  public GetTransactionRequest(String traceNo) {
    this.traceNo = traceNo;
  }

  public GetTransactionRequest() {

  }
}
