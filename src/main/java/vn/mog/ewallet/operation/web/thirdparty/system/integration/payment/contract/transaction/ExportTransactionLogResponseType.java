package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.LogFile;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class ExportTransactionLogResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private LogFile logFile;

  public LogFile getLogFile() {
    return logFile;
  }

  public void setLogFile(LogFile logFile) {
    this.logFile = logFile;
  }
}
