package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system;

import vn.mog.framework.contract.base.MobiliserRequestType;

public class FindBankProfileRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  private String bankCode;

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }
}
