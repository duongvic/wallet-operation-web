package vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.beans.Balance;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.beans.Customer;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class StatsBalanceResponseType extends MobiliserResponseType {

  protected Map<String, StatsBalance> statsBalances;

  protected CompareFaAndWalletBalance verifyBalance;

  public Map<String, StatsBalance> getStatsBalances() {
    return statsBalances;
  }

  public void setStatsBalances(Map<String, StatsBalance> statsBalances) {
    this.statsBalances = statsBalances;
  }

  public CompareFaAndWalletBalance getVerifyBalance() {
    return verifyBalance;
  }

  public void setVerifyBalance(CompareFaAndWalletBalance verifyBalance) {
    this.verifyBalance = verifyBalance;
  }

  public static class StatsBalance implements Serializable {

    private Balance balance;
    private List<Customer> customers;

    public Balance getBalance() {
      return balance;
    }

    public void setBalance(Balance balance) {
      this.balance = balance;
    }

    public List<Customer> getCustomers() {
      return customers;
    }

    public void setCustomers(List<Customer> customers) {
      this.customers = customers;
    }

  }

  public static class CompareFaAndWalletBalance {

    private Balance initPoolBalance;
    private Balance faBalance;
    private Balance walletBalance;
    private Boolean isEqual;

    public Balance getFaBalance() {
      return faBalance;
    }

    public void setFaBalance(Balance faBalance) {
      this.faBalance = faBalance;
    }

    public Balance getWalletBalance() {
      return walletBalance;
    }

    public void setWalletBalance(Balance walletBalance) {
      this.walletBalance = walletBalance;
    }

    public Boolean getIsEqual() {
      return isEqual;
    }

    public void setIsEqual(Boolean isEqual) {
      this.isEqual = isEqual;
    }

    public Balance getInitPoolBalance() {
      return initPoolBalance;
    }

    public void setInitPoolBalance(Balance initPoolBalance) {
      this.initPoolBalance = initPoolBalance;
    }

    public Boolean getEqual() {
      return isEqual;
    }

    public void setEqual(Boolean equal) {
      isEqual = equal;
    }
  }
}
