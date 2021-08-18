package vn.mog.framework.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;

public class NumberUtil {

  public static String formatNumber(Long x) {
    try {
      if (x == null) {
        return StringUtils.EMPTY;
      }
      return String.format("%,d", x).replace(',', '.');
    } catch (NumberFormatException e) {
      return StringUtils.EMPTY;
    }
  }

  public static String formatNumber(Double x) {
    try {
      if (x == null) {
        return StringUtils.EMPTY;
      }
      return String.format("%,d", x).replace(',', '.');
    } catch (NumberFormatException e) {
      return StringUtils.EMPTY;
    }
  }

  public static String formatNumber(Integer x) {
    try {
      if (x == null) {
        return StringUtils.EMPTY;
      }
      return String.format("%,d", x).replace(',', '.');
    } catch (NumberFormatException e) {
      return StringUtils.EMPTY;
    }
  }

  public static String formatNumberComma(Long x) {
    try {
      if (x == null) {
        return StringUtils.EMPTY;
      }
      return String.format("%,d", x);
    } catch (NumberFormatException e) {
      return StringUtils.EMPTY;
    }
  }

  public static String formatNumberComma(Double x) {
    try {
      if (x == null) {
        return StringUtils.EMPTY;
      }
      return String.format("%,d", x);
    } catch (NumberFormatException e) {
      return StringUtils.EMPTY;
    }
  }

  public static String formatNumberComma(Integer x) {
    try {
      if (x == null) {
        return StringUtils.EMPTY;
      }
      return String.format("%,d", x).replace(',', '.');
    } catch (NumberFormatException e) {
      return StringUtils.EMPTY;
    }
  }

  public static List<Long> convertListToLong(String[] params) {
    List<Long> items = new ArrayList<>();
    for (String item : params) {
      items.add(NumberUtil.convertToLong(item));
    }
    return items;
  }

  public static List<Long> convertListToLongRemoveBlank(String[] params) {
    List<Long> items = new ArrayList<>();
    for (String item : params) {
      if (StringUtils.isNotBlank(item)) {
        items.add(NumberUtil.convertToLong(item));
      }
    }
    return items;
  }

  public static List<Integer> convertListToInt(String[] params) {
    List<Integer> items = new ArrayList<>();
    for (String item : params) {
      items.add(NumberUtil.convertToInt(item));
    }
    return items;
  }

  public static int randomNumber(int min, int max) {
    int range = (max - min) + 1;
    Random random = new Random();
    int result = random.nextInt(range) + min;
    return result;
  }

  public static Long convertToLong(String number) {
    try {
      if (StringUtils.isEmpty(number)) {
        return 0L;
      }
      return Long.parseLong(number);
    } catch (NumberFormatException ex) {
      return 0L;
    }
  }

  public static int convertToInt(String number) {
    try {
      if (StringUtils.isEmpty(number)) {
        return 0;
      }
      return Integer.parseInt(number);
    } catch (NumberFormatException ex) {
      return 0;
    }
  }

  public static boolean validatePhone(String phoneNo) {
    if (phoneNo.startsWith("84")) {
      if (phoneNo.length() == 11 || phoneNo.length() == 12) {
        return true;
      }
      return false;
    } else {
      if (!phoneNo.startsWith("0")) {
        return false;
      } else if (phoneNo.startsWith("00")) {
        return false;
      } else {
        return phoneNo.matches("^\\+?\\d{1,3}?[- .]?\\(?(?:\\d{2,3})\\)?[- .]?\\d\\d\\d[- .]?\\d\\d\\d\\d$");
      }
    }
  }

  protected List<String> getPhoneNumbers(String value) {
    List<String> phoneNumbers = new ArrayList<>();
    try {
      for (String val : value.split(",")) {
        if (StringUtils.isBlank(val)) {
          continue;
        }

        val = val.trim();
        if (!val.startsWith("84")) {
          if (val.startsWith("0")) {
            val = val.substring(1);
          }
          val = "84" + val;
        }
        if (val.length() < 10 || val.length() > 13) {
          continue;
        }
        phoneNumbers.add(val);
      }
    } catch (Exception e) {
    }
    return phoneNumbers;
  }

  /*
   * public static int isPositive(int i) { if (i == 0) return 0; if (i >> 31
   * != 0) return -1; return +1; } public static int isPositive(long i) { if
   * (i == 0) return 0; if (i >> 63 != 0) return -1; return +1; } public
   * static int isPositive(double f) { if (f != f) throw new
   * IllegalArgumentException("NaN"); if (f == 0) return 0; f *=
   * Double.POSITIVE_INFINITY; if (f == Double.POSITIVE_INFINITY) return +1;
   * if (f == Double.NEGATIVE_INFINITY) return -1; //this should never be
   * reached, but I've been wrong before... throw new
   * IllegalArgumentException("Unfathomed double"); }
   */
}
