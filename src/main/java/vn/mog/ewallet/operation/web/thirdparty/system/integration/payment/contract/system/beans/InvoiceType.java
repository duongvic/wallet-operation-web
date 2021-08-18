package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class InvoiceType {

  public final static transient Integer BILL_PAYMENT_PHONE = 1001;
  public final static transient Integer BILL_PAYMENT_WATER = 1002;
  public final static transient Integer BILL_PAYMENT_ELECTRICITY = 1003;
  public final static transient Integer DISBURSEMENT_FECREDIT = 2001;
  public final static transient Integer DISBURSEMENT_HOMECREDIT = 2002;
  public final static transient Integer DISBURSEMENT_ONEWORLD = 2003;
  public final static transient Integer TOPUP_GAME_LQMB = 3001;
  public final static transient Integer TOPUP_GAME_LMHT = 3002;
  public final static transient Integer TOPUP_GAME_BLADE_SOUL = 3003;
  public static final Map<Integer, String> BILL_PAYMENT_LIST = new LinkedHashMap<>();
  public static final Map<Integer, String> DISBURSEMENT_LIST = new LinkedHashMap<>();
  public static final Map<Integer, String> TOPUP_GAME_LIST = new LinkedHashMap<>();

  static {
    BILL_PAYMENT_LIST.put(BILL_PAYMENT_PHONE, "service.bill.payment.type.phone");
    BILL_PAYMENT_LIST.put(BILL_PAYMENT_WATER, "service.bill.payment.type.water");
    BILL_PAYMENT_LIST.put(BILL_PAYMENT_ELECTRICITY, "service.bill.payment.type.electricity");
  }

  static {
    DISBURSEMENT_LIST.put(DISBURSEMENT_FECREDIT, "service.disbursement.type.fe.credit");
    DISBURSEMENT_LIST.put(DISBURSEMENT_HOMECREDIT, "service.disbursement.type.home.credit");
    DISBURSEMENT_LIST.put(DISBURSEMENT_ONEWORLD, "service.disbursement.type.one.world");
  }

  static {
    TOPUP_GAME_LIST.put(TOPUP_GAME_LQMB, "service.topupgame.type.lqmb");
    TOPUP_GAME_LIST.put(TOPUP_GAME_LMHT, "service.topupgame.type.lmht");
    TOPUP_GAME_LIST.put(TOPUP_GAME_BLADE_SOUL, "service.topupgame.blade.soul");
  }

  private Integer id;
  private String name;
  private Date created;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

}
