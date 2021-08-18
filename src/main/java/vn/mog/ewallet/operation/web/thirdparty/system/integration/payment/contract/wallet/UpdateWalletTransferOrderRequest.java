package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

import java.util.List;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.WalletTransferOrder;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.WalletTransferOrderAttachment;

public class UpdateWalletTransferOrderRequest extends UpdateWalletTransferOrderRequestType {

  private static final long serialVersionUID = 1L;

  public UpdateWalletTransferOrderRequest(WalletTransferOrder order) {
    this.order = order;
  }

  public UpdateWalletTransferOrderRequest(WalletTransferOrder order, List<WalletTransferOrderAttachment> attachments) {
    this.order = order;
    this.attachments = attachments;
  }
}
