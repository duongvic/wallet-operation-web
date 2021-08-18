package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.reconciliation;

import java.io.Serializable;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.LogFile;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class ExportTransactionLogResponseType extends MobiliserResponseType
    implements Serializable {

  private static final long serialVersionUID = 1L;

  private LogFile logFile;
  private LogFile logDoubtTransactions;

  public LogFile getLogFile() {
    return logFile;
  }

  public void setLogFile(LogFile logFile) {
    this.logFile = logFile;
  }

  public LogFile getLogDoubtTransactions() {
    return logDoubtTransactions;
  }

  public void setLogDoubtTransactions(LogFile logDoubtTransactions) {
    this.logDoubtTransactions = logDoubtTransactions;
  }
}
