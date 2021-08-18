package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PositionType {

  public final static transient Integer STUDENT = 1;
  public final static transient Integer STAFF = 2;
  public final static transient Integer MANAGER = 3;
  public final static transient Integer DIRECTOR_GROUP  = 4;
  public final static transient Integer VICE_CEO_PRESIDENT = 5;
  public final static transient Integer CEO_PRESIDENT = 6;
  public final static transient Integer OTHER = 7;
  
  public static final List<Integer> LIST_POSITION_TYPE = new ArrayList<>();

  public static final Map<Integer, String> POSITION_TYPE_IDS = new LinkedHashMap<>();

  static {
    POSITION_TYPE_IDS.put(STUDENT, "Student");
    POSITION_TYPE_IDS.put(STAFF, "Staff");
    POSITION_TYPE_IDS.put(MANAGER, "Manager");
    POSITION_TYPE_IDS.put(DIRECTOR_GROUP, "Director / Vice Director");
    POSITION_TYPE_IDS.put(VICE_CEO_PRESIDENT, "Vice President / The Deputy General Manage");
    POSITION_TYPE_IDS.put(CEO_PRESIDENT, "President / Ceo");
    POSITION_TYPE_IDS.put(OTHER, "Other");

    LIST_POSITION_TYPE.add(STUDENT);
    LIST_POSITION_TYPE.add(STAFF);
    LIST_POSITION_TYPE.add(MANAGER);
    LIST_POSITION_TYPE.add(DIRECTOR_GROUP);
    LIST_POSITION_TYPE.add(VICE_CEO_PRESIDENT);
    LIST_POSITION_TYPE.add(CEO_PRESIDENT);
    LIST_POSITION_TYPE.add(OTHER);
  }

}
