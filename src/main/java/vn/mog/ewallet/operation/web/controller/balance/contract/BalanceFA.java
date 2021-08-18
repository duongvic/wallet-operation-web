package vn.mog.ewallet.operation.web.controller.balance.contract;

import org.apache.commons.lang3.StringUtils;


public class BalanceFA {

  public static final String PRE_FIX = "Account TM SOF";
  private String name;
  private Long money;

  public BalanceFA() {
  }

  public BalanceFA(String name, Long money) {
    if (name != null && name.contains(PRE_FIX)) {
      String replace = name.replace(PRE_FIX, StringUtils.EMPTY);
      this.name = replace.trim();
    } else {
      this.name = name;
    }
    this.money = money;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getMoney() {
    return money;
  }

  public void setMoney(Long money) {
    this.money = money;
  }
}
