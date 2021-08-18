package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type;

import java.util.LinkedHashMap;
import java.util.Map;

public class WalletTransferOrderFlowStage {

  public static final int OPERATION_INIT = 0;// , "Operation staff
  // initiated"),
  public static final int FINANCE_REJECTED = 1;// , "Finance staff rejected"),
  public static final int FINANCE_READY_TO_VERIFIED = 2;// , "Ready to
  // verified by Finance
  // Staff"),
  public static final int FINANCE_LEADER_REJECTED = 3;// , "Finance Leader
  // rejected"),
  public static final int FINANCE_LEADER_READY_TO_REVIEWED = 4;// , "Ready to
  // reviewed by
  // Finance
  // Leader"),
  public static final int FINANCE_MANAGER_REJECTED = 5;// , "Finance Manager
  // rejected"),
  public static final int FINANCE_MANAGER_READY_TO_APPROVED = 6;// , "Ready to
  // approved by
  // Finance
  // Manager"),
  public static final int FINISHED = 8;// , "Finance Manager approved.
  // Finish!");

  public static Map<Integer, String> WALLET_TRANSFER_STAGES = new LinkedHashMap<>();

  static {
    WALLET_TRANSFER_STAGES.put(OPERATION_INIT, "wallet.transfer.stages.operation.init");
    WALLET_TRANSFER_STAGES.put(FINANCE_REJECTED, "wallet.transfer.stages.finance.rejected");
    WALLET_TRANSFER_STAGES.put(FINANCE_READY_TO_VERIFIED, "wallet.transfer.stages.finance.ready.to.verified");
    WALLET_TRANSFER_STAGES.put(FINANCE_LEADER_REJECTED, "wallet.transfer.stages.finance.leader.rejected");
    WALLET_TRANSFER_STAGES.put(FINANCE_LEADER_READY_TO_REVIEWED, "wallet.transfer.stages.finance.leader.ready.to.reviewed");
    WALLET_TRANSFER_STAGES.put(FINANCE_MANAGER_REJECTED, "wallet.transfer.stages.finance.manager.rejected");
    WALLET_TRANSFER_STAGES.put(FINANCE_MANAGER_READY_TO_APPROVED, "wallet.transfer.stages.finance.manager.ready.to.approved");
    WALLET_TRANSFER_STAGES.put(FINISHED, "wallet.transfer.stages.finished");
  }

}
