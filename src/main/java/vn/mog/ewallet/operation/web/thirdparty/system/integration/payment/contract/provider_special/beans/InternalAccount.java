package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.beans;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InternalAccount implements Serializable {

  private static final long serialVersionUID = 1L;
  // ------------------------------------------------
  protected String accountName;
  protected String phoneNumber;
  protected String password;
  protected String paymentPass;
  protected String serialNumber;
  protected Integer status;
  // ------------------------------------------------
  protected Long amount; // (balance) So du kha dung
  protected Long freezeAmount; // So du dong bang
  protected Long totalAmount; // Tong so du
  // ------------------------------------------------
  protected List<VoucherRange> voucherRanges;
  protected List<VoucherRange> financeVoucherRanges;
  // ------------------------------------------------
  protected Integer dailyRequestCount;
}
