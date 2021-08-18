package vn.mog.ewallet.operation.web.controller.balance.contract;


public class BalanceBank {

  private String name;
  private Long money;

  public BalanceBank() {
  }

  public BalanceBank(String name, Long money) {
    this.name = name;
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
