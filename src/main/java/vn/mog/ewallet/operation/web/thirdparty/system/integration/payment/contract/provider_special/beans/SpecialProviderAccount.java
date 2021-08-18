package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.beans;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecialProviderAccount extends InternalAccount {

  public SpecialProviderAccount() {
    super();
  }

  public SpecialProviderAccount(String accountName, Integer status, String password,
      String phoneNumber, String serialNumber, Long amount) {
    super();
    this.accountName = accountName;
    this.amount = amount;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.serialNumber = serialNumber;
    this.status = status;
  }

  public SpecialProviderAccount(String accountName, Long amount, Long freezeAmount,
      Long totalAmount, Integer status, Integer dailyRequestCount) {
    super();
    this.accountName = accountName;
    this.amount = amount;
    this.dailyRequestCount = dailyRequestCount;
    this.freezeAmount = freezeAmount;
    this.status = status;
    this.totalAmount = totalAmount;
  }

  public SpecialProviderAccount(String accountName, Integer status, String password,
      String paymentPass, List<VoucherRange> voucherRanges, List<VoucherRange> financeVoucherRanges,
      String serialNumber, Integer dailyRequestCount) {
    super();
    this.accountName = accountName;
    this.dailyRequestCount = dailyRequestCount;
    this.password = password;
    this.paymentPass = paymentPass;
    this.serialNumber = serialNumber;
    this.status = status;
    this.voucherRanges = voucherRanges;
    this.financeVoucherRanges = financeVoucherRanges;
  }
}
