package vn.mog.ewallet.operation.web.thirdparty.system.integration.stock;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.mog.framework.contract.base.MobiliserResponseType;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateBankHistoryResponse extends MobiliserResponseType {

  private BankHistory bankHistory;
}
