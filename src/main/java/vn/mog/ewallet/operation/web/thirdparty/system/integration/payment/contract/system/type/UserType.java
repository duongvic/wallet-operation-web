package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserType {

  public final static transient Integer SYSTEM = 1;
  public final static transient Integer USER = 2;

  public static final List<Integer> LIST_USER_TYPE_ID = new ArrayList<>();

  public static final Map<Integer, String> USER_TYPE_IDS = new LinkedHashMap<>();

  static {
    USER_TYPE_IDS.put(SYSTEM, "System");
    USER_TYPE_IDS.put(USER, "User");
    //SERVICE_TYPE_IDS.put(USER_TYPE_USER, "User");

    LIST_USER_TYPE_ID.add(SYSTEM);
    LIST_USER_TYPE_ID.add(USER);
  }

}
