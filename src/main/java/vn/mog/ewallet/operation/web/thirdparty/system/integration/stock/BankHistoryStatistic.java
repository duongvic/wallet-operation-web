package vn.mog.ewallet.operation.web.thirdparty.system.integration.stock;

import java.io.Serializable;
import lombok.Data;

@Data
public class BankHistoryStatistic implements Serializable {

  private BankAccount bankAccount;
  private BankHistory balanceBeginOfPeriod;
  private BankHistory balanceEndOfPeriod;
}
