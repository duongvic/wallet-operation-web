package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider_special.beans.VoucherRange;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ProviderCode;
import vn.mog.framework.contract.base.MobiliserRequestType;

@Getter
@Setter
public class SpecialProviderAccountChangeRequestType extends MobiliserRequestType {

  private ProviderCode providerCode;
  private String accountName;
  private String password;
  private String paymentPass;
  private List<VoucherRange> voucherRanges;
  private List<VoucherRange> financeVoucherRanges;
  private int status; // 0 = INACTIVE, 1 = ACTIVE
  private String phoneNumber;
  private String serialNumber;
  private String type;
}
