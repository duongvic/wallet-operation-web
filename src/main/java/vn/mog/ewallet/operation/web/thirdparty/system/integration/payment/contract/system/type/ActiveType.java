package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type;

public enum ActiveType {
  ON(1), OFF(0);
  public int code;

  ActiveType(int code) {
    this.code = code;
  }

}
