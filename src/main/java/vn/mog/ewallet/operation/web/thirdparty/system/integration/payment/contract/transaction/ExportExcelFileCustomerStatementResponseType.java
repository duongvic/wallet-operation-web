package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.StatementAttachment;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class ExportExcelFileCustomerStatementResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;

  private StatementAttachment attachment;

  public StatementAttachment getAttachment() {
    return attachment;
  }

  public void setAttachment(StatementAttachment attachment) {
    this.attachment = attachment;
  }
}
