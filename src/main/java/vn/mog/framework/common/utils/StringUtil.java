package vn.mog.framework.common.utils;

public class StringUtil {

  public static final String QUESTION_MARK = "?";
  public static final String SPACE_SYMBOL = "\\s";

  public static final String SPECIAL_NAME_MATCHERS = "[?/<~#`!$%^&*()+=}|:\";',>{]";
  public static final String SPECIAL_NAME_MATCHERS_TWO = "[?/<~#`!$%^&*()+=}:\";',>{]";
  public static final String SPECIAL_NAME_MATCHERS_THREE = ";/<>";

  public static final String PAYER = "PAYER";
  public static final String PAYEE = "PAYEE";

  public static final String TRUE = "true";
  public static final String FALSE = "false";

  public static final String UNDER_LINE = "_";
  public static final String POINT = ".";

  public static final Character CHAR_YES = "Y".charAt(0);
  public static final Character CHAR_NO = "N".charAt(0);

  public static final String S_YES = "Y";
  public static final String S_NO = "N";

  public static final String CHECKED = "checked=\"\"";

  public static boolean specialSymbols(String value, String symbols) {
    if (value == null || value.trim().isEmpty()) {
      return true;
    }
    if (value.contains(SPACE_SYMBOL)) {
      return true;
    }
    int theCount = 0;
    int lengthSymbol = symbols.length();
    for (int i = 0; i < lengthSymbol; i++) {
      if (value.contains(symbols.substring(i, i + 1))) {
        theCount++;
      }
      if (theCount > 0) {
        return true;
      }
    }
    return false;
  }

}
