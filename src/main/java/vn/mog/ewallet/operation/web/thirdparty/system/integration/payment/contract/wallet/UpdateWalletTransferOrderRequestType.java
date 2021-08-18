package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

import java.util.ArrayList;
import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.WalletTransferOrder;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.WalletTransferOrderAttachment;
import vn.mog.framework.contract.base.MobiliserRequestType;

public class UpdateWalletTransferOrderRequestType extends MobiliserRequestType {

  private static final long serialVersionUID = 1L;

  protected WalletTransferOrder order;
  protected List<WalletTransferOrderAttachment> attachments;

  public WalletTransferOrder getOrder() {
    return order;
  }

  public void setOrder(WalletTransferOrder order) {
    this.order = order;
  }

  public List<WalletTransferOrderAttachment> getAttachments() {
    if (attachments == null) {
      attachments = new ArrayList<>();
    }
    return attachments;
  }

  public void setAttachments(List<WalletTransferOrderAttachment> attachments) {
    this.attachments = attachments;
  }
}
