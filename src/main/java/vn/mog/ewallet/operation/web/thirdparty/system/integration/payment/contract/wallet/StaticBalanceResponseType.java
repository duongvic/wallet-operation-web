package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet;

import java.util.HashMap;
import java.util.Map;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.CompareFaAndWalletBalance;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.StatsBalance;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class StaticBalanceResponseType extends MobiliserResponseType {

  private static final long serialVersionUID = 1L;
  protected Map<String, StatsBalance> statsBalances;

  protected CompareFaAndWalletBalance verifyBalance;

  public Map<String, StatsBalance> getStatsBalances() {
    if (statsBalances == null) {
      statsBalances = new HashMap<>();
    }
    return statsBalances;
  }

  public void setStatsBalances(Map<String, StatsBalance> statsBalances) {
    this.statsBalances = statsBalances;
  }

  public CompareFaAndWalletBalance getVerifyBalance() {
    if (verifyBalance == null) {
      verifyBalance = new CompareFaAndWalletBalance();
    }
    return verifyBalance;
  }

  public void setVerifyBalance(CompareFaAndWalletBalance verifyBalance) {
    this.verifyBalance = verifyBalance;
  }
}
