package vn.mog.ewallet.operation.web.thirdparty.system.integration.stock;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.mog.framework.contract.base.MobiliserRequestType;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeleteBankHistoryRequest extends MobiliserRequestType {

  private Long id;
}
