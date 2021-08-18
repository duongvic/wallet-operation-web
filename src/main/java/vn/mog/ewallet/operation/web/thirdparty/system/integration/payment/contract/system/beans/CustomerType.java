package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomerType implements Serializable {

  public final static transient Integer ID_CUSTOMER = 1;
  public final static transient Integer ID_AGENT = 2;
  public final static transient Integer ID_MERCHANT = 3;
  public final static transient Integer ID_SALE = 4;
  public final static transient Integer ID_ZOTA = 5;
  public final static transient Integer ID_POOL = 6;
  public final static transient Integer ID_SOF = 7;
  public final static transient Integer ID_STAFF = 8;
  public final static transient Integer ID_ADMIN = 9;
  public final static transient Integer ID_SYSTEM = 10;
  public final static transient Integer ID_PROVIDER = 11;
  public final static transient Integer ID_PROPERTY_MANAGER = 12;

  public final static transient Integer CUST_ACTIVE = 1;
  public final static transient Integer CUST_IN_ACTIVE = 0;

  public final static transient Integer ACCOUNT = 1;
  public final static transient Integer WALLET = 2;

  public final static transient Integer NOTIFICATION_MODE_ID_NONE = 0;
  public final static transient Integer NOTIFICATION_MODE_ID_EMAIL = 1;
  public final static transient Integer NOTIFICATION_MODE_ID_SMS = 2;
  public final static transient Integer NOTIFICATION_MODE_ID_BOTH = 3;

  public static final Map<Integer, String> FULL_CUSTOMER_TYPES = new LinkedHashMap<>();
  public static final Map<Integer, String> STAFF_CUSTOMER_TYPES = new LinkedHashMap<>();
  public static final Map<Integer, String> BALANCE_CUSTOMER_TYPES = new LinkedHashMap<>();
  public static final Map<Integer, String> CUSTOMER_TYPE_IDS_WALLET = new LinkedHashMap<>();
  public static final Map<Integer, String> CUSTOMER_STATUS_IDS = new LinkedHashMap<>();
  //public static final Map<Integer, String> SERVICE_TYPE_IDS = new LinkedHashMap<>();
  public static final Map<Integer, String> WALLET_TYPE_IDS = new LinkedHashMap<>();

  public static final List<Integer> CUSTOMER_TYPE_SYSTEM_STAFF = new ArrayList<>();
  public static final List<Integer> CUSTOMER_TYPE_SYSTEM_WALLET = new ArrayList<>();
  public static final List<Integer> CUSTOMER_TYPE_SYSTEM_ACCOUNT = new ArrayList<>();

  public static final Map<Integer, String>
      NOTIFICATION_MODE_IDS = new LinkedHashMap<>();

  public static final Map<Integer, String> USER_CUSTOMER_TYPES = new LinkedHashMap<>();
  public static final Map<Integer, String> USER_AGENT_TYPES = new LinkedHashMap<>();
  private static final long serialVersionUID = 1L;

  static {

    USER_AGENT_TYPES.put(ID_AGENT, "Agent");

    USER_CUSTOMER_TYPES.put(ID_CUSTOMER, "Customer");
    USER_CUSTOMER_TYPES.put(ID_MERCHANT, "Merchant");
    USER_CUSTOMER_TYPES.put(ID_PROVIDER, "Provider");
    USER_CUSTOMER_TYPES.put(ID_AGENT, "Agent");
    USER_CUSTOMER_TYPES.put(ID_PROPERTY_MANAGER, "Property Manager");


    FULL_CUSTOMER_TYPES.put(ID_CUSTOMER, "Customer");
    FULL_CUSTOMER_TYPES.put(ID_AGENT, "Agent");
    FULL_CUSTOMER_TYPES.put(ID_MERCHANT, "Merchant");
    FULL_CUSTOMER_TYPES.put(ID_PROPERTY_MANAGER, "Property Manager");
    FULL_CUSTOMER_TYPES.put(ID_SALE, "Sale");
    FULL_CUSTOMER_TYPES.put(ID_ZOTA, "ZO-TA");
    FULL_CUSTOMER_TYPES.put(ID_POOL, "Actual System Account");
    FULL_CUSTOMER_TYPES.put(ID_STAFF, "Staff");
    FULL_CUSTOMER_TYPES.put(ID_SOF, "SOF");
    FULL_CUSTOMER_TYPES.put(ID_ADMIN, "Admin");
    FULL_CUSTOMER_TYPES.put(ID_SYSTEM, "System");
    FULL_CUSTOMER_TYPES.put(ID_PROVIDER, "Provider");

    STAFF_CUSTOMER_TYPES.put(ID_STAFF, "Staff");
    STAFF_CUSTOMER_TYPES.put(ID_ADMIN, "Admin");
//    STAFF_CUSTOMER_TYPES.put(ID_SYSTEM, "System");

    BALANCE_CUSTOMER_TYPES.put(ID_CUSTOMER, "Customer");
    BALANCE_CUSTOMER_TYPES.put(ID_AGENT, "Agent");
    BALANCE_CUSTOMER_TYPES.put(ID_MERCHANT, "Merchant");
    BALANCE_CUSTOMER_TYPES.put(ID_PROPERTY_MANAGER, "Property Manager");

    BALANCE_CUSTOMER_TYPES.put(ID_ZOTA, "ZO-TA");
    BALANCE_CUSTOMER_TYPES.put(ID_POOL, "Actual System Account");
    BALANCE_CUSTOMER_TYPES.put(ID_SOF, "SOF");
    BALANCE_CUSTOMER_TYPES.put(ID_PROVIDER, "Provider");

    CUSTOMER_TYPE_IDS_WALLET.put(ID_POOL, "POOL");
    CUSTOMER_TYPE_IDS_WALLET.put(ID_SOF, "SOF");
    CUSTOMER_TYPE_IDS_WALLET.put(ID_ZOTA, "ZO-TA");

    CUSTOMER_TYPE_SYSTEM_STAFF.add(ID_STAFF);
    CUSTOMER_TYPE_SYSTEM_STAFF.add(ID_ADMIN);
//    CUSTOMER_TYPE_SYSTEM_STAFF.add(ID_SYSTEM);

    CUSTOMER_TYPE_SYSTEM_WALLET.add(ID_POOL);
    CUSTOMER_TYPE_SYSTEM_WALLET.add(ID_SOF);
    CUSTOMER_TYPE_SYSTEM_WALLET.add(ID_ZOTA);

    CUSTOMER_TYPE_SYSTEM_ACCOUNT.add(ID_CUSTOMER);
    CUSTOMER_TYPE_SYSTEM_ACCOUNT.add(ID_MERCHANT);
    CUSTOMER_TYPE_SYSTEM_ACCOUNT.add(ID_PROVIDER);
    CUSTOMER_TYPE_SYSTEM_ACCOUNT.add(ID_AGENT);
    CUSTOMER_TYPE_SYSTEM_ACCOUNT.add(ID_PROPERTY_MANAGER);

    CUSTOMER_STATUS_IDS.put(CUST_IN_ACTIVE, "user.status.inactive");
    CUSTOMER_STATUS_IDS.put(CUST_ACTIVE, "user.status.active");

    WALLET_TYPE_IDS.put(ACCOUNT, "Account");
    WALLET_TYPE_IDS.put(WALLET, "Wallet");

    NOTIFICATION_MODE_IDS.put(NOTIFICATION_MODE_ID_NONE, "NONE");
    NOTIFICATION_MODE_IDS.put(NOTIFICATION_MODE_ID_EMAIL, "EMAIL");
    NOTIFICATION_MODE_IDS.put(NOTIFICATION_MODE_ID_SMS, "SMS");
    NOTIFICATION_MODE_IDS.put(NOTIFICATION_MODE_ID_BOTH, "BOTH");
  }

  protected Integer id;
  protected String name;
  protected String role;
  protected boolean internal;

  public CustomerType() {
  }

  public CustomerType(Integer id) {
    this.id = id;
  }

  public CustomerType(Integer id, String name, String role, boolean internal) {
    this.id = id;
    this.name = name;
    this.role = role;
    this.internal = internal;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer value) {
    this.id = value;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String value) {
    this.name = value;
  }

  public String getRole() {
    return this.role;
  }

  public void setRole(String value) {
    this.role = value;
  }

  public boolean isInternal() {
    return this.internal;
  }

  public void setInternal(boolean value) {
    this.internal = value;
  }

  @Override
  public String toString() {
    return "CustomerType{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", role='" + role + '\'' +
        ", internal=" + internal +
        '}';
  }
}
