package vn.mog.ewallet.operation.web.thirdparty.system.integration.stock;


import java.util.Collection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.mog.framework.contract.base.MobiliserResponseType;

@Data
@EqualsAndHashCode(callSuper = true)
public class BankBalanceStatisticResponse extends MobiliserResponseType {

  private Collection<BankHistoryStatistic> bankHistoryStatistics;
  private Long totalFiltered;
  private Long totalBalanceBeginOfPeriod;
  private Long totalBalanceEndOfPeriod;
}
