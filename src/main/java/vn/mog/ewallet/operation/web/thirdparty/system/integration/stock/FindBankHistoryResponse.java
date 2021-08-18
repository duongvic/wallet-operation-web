package vn.mog.ewallet.operation.web.thirdparty.system.integration.stock;


import java.util.Collection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.mog.framework.contract.base.MobiliserResponseType;

@Data
@EqualsAndHashCode(callSuper = true)
public class FindBankHistoryResponse extends MobiliserResponseType {

  private Collection<BankHistory> bankHistories;
  private Long totalFiltered;
  private Long totalCreditAmount;
  private Long totalDebitAmount;


}
