package vn.mog.framework.util.tools.formatutils;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.lang3.StringUtils;

public class FormatUtils {
  public static final String DEFAULT_NULL_VALUE = "NULL";
  public static final String DEFAULT_ASSIGNMENT = "=";
  public static final String DEFAULT_SEPERATOR = "|";
  public static final String DEFAULT_BRACKET_OPEN = "[";
  public static final String DEFAULT_BRACKET_CLOSE = "]";

  public static String formatString(String text, int width) {
    return formatString(text, width, width, false);
  }

  public static String formatString(String text, int width, boolean leftAlign) {
    return formatString(text, width, width, leftAlign);
  }

  public static String formatString(String text, int minWidth, int maxWidth) {
    return formatString(text, minWidth, maxWidth, false);
  }

  public static String formatString(String text, int minWidth, int maxWidth, boolean leftAlign) {
    if (text == null) {
      return null;
    }

    if ((maxWidth != -1) && (text.length() > maxWidth)) {
      return ((leftAlign) ? text.substring(0, maxWidth)
          : text.substring(text.length() - maxWidth, text.length()));
    }

    if ((minWidth != -1) && (text.length() < minWidth)) {
      return ((leftAlign) ? StringUtils.rightPad(text, minWidth)
          : StringUtils.leftPad(text, minWidth));
    }

    return text;
  }

  @SuppressWarnings("rawtypes")
  public static String toString(Map<?, ?> map, String nullValue, String assignValue,
      String seperator) {
    if ((map == null) || (map.size() < 1)) {
      return null;
    }

    StringBuilder buf = new StringBuilder(32 * map.size());

    Iterator it = map.entrySet().iterator();
    while (true) {
      Map.Entry entry = (Map.Entry) it.next();

      buf.append(entry.getKey());
      buf.append(assignValue);
      String s = toString(entry.getValue());

      buf.append((s == null) ? nullValue : s);

      if (!(it.hasNext())) {
        break;
      }

      buf.append(seperator);
    }

    return buf.toString();
  }

  public static String toString(Map<?, ?> map, String assignValue, String seperator) {
    return toString(map, "NULL", assignValue, seperator);
  }

  public static String toString(Map<?, ?> map) {
    return toString(map, "NULL", "=", "|");
  }

  @SuppressWarnings("rawtypes")
  public static String toString(Collection<?> c, String nullValue, String seperator) {
    if ((c == null) || (c.size() < 1)) {
      return null;
    }
    StringBuilder buf = new StringBuilder(32 * c.size());
    Iterator it = c.iterator();
    int i = 0;
    while (true) {
      buf.append("#");
      buf.append(i++);
      buf.append(':');

      String s = toString(it.next());

      buf.append((s == null) ? nullValue : s);

      if (!(it.hasNext())) {
        break;
      }

      buf.append(seperator);
    }
    return buf.toString();
  }

  public static String toString(Collection<?> c, String seperator) {
    return toString(c, "NULL", seperator);
  }

  public static String toString(Collection<?> c) {
    return toString(c, "NULL", "|");
  }

  public static String toString(Object[] array, String nullValue, String seperator) {
    if ((array == null) || (array.length < 1)) {
      return null;
    }
    StringBuilder buf = new StringBuilder(32 * array.length);
    int i = 0;
    while (true) {
      buf.append("#");
      buf.append(i);
      buf.append(':');
      String s = toString(array[i]);
      buf.append((s == null) ? nullValue : s);
      if (++i >= array.length) {
        break;
      }
      buf.append(seperator);
    }
    return buf.toString();
  }

  public static String toString(Object[] array, String seperator) {
    return toString(array, "NULL", seperator);
  }

  public static String toString(Object[] array) {
    return toString(array, "NULL", "|");
  }

  public static String arrayToString(Object array, String nullValue, String seperator) {
    int length = Array.getLength(array);
    if ((array == null) || (length < 1)) {
      return null;
    }
    StringBuilder buf = new StringBuilder(32 * length);
    int i = 0;
    while (true) {
      buf.append("#");
      buf.append(i);
      buf.append(':');
      Object o = Array.get(array, i);

      buf.append((o == null) ? nullValue : o);
      if (++i >= length) {
        break;
      }
      buf.append(seperator);
    }
    return buf.toString();
  }

  public static String arrayToString(Object array, String seperator) {
    return arrayToString(array, "NULL", seperator);
  }

  public static String arrayToString(Object array) {
    return arrayToString(array, "NULL", "|");
  }

  @SuppressWarnings("rawtypes")
  public static String toString(Object o, String openBracket, String closeBracket) {
    if (o == null) {
      return null;
    }
    if (o instanceof Collection) {
      return new StringBuilder().append(openBracket).append(toString((Collection) o))
          .append(closeBracket).toString();
    }
    if (o instanceof Map) {
      return new StringBuilder().append(openBracket).append(toString((Map) o)).append(closeBracket)
          .toString();
    }
    if (o instanceof Object[]) {
      return new StringBuilder().append(openBracket).append(toString((Object[]) (Object[]) o))
          .append(closeBracket).toString();
    }
    if (o.getClass().isArray()) {
      return new StringBuilder().append(openBracket).append(arrayToString(o)).append(closeBracket)
          .toString();
    }
    return String.valueOf(o);
  }

  public static String toString(Object o) {
    return toString(o, "[", "]");
  }

  public static String formatDuration(long d) {
    int ms = (int) (d % 1000L);
    d /= 1000L;

    int s = (int) (d % 60L);
    d /= 60L;

    int m = (int) (d % 60L);
    d /= 60L;

    int h = (int) (d % 24L);
    d /= 24L;

    StringBuilder buf = new StringBuilder();
    if (d > 0L) {
      buf.append(d);
      buf.append("d ");
    }
    if (h < 10) {
      buf.append('0');
    }
    buf.append(h);
    buf.append(':');
    if (m < 10) {
      buf.append('0');
    }
    buf.append(m);
    buf.append(':');
    if (s < 10) {
      buf.append('0');
    }
    buf.append(s);
    if (ms > 0) {
      buf.append(".");
      if (ms < 100) {
        buf.append('0');
      }
      if (ms < 10) {
        buf.append('0');
      }
      buf.append(ms);
    }
    return buf.toString();
  }

  public static BigDecimal toCurrency(long amount, Currency currency) {
    return new BigDecimal(amount)
        .divide(new BigDecimal(10).pow(currency.getDefaultFractionDigits()))
        .setScale(currency.getDefaultFractionDigits());
  }

  public static final String formatCurrencyAmount(BigDecimal amount, Currency currency,
      Locale locale) {
    NumberFormat format = NumberFormat.getNumberInstance(locale);
    format.setMaximumFractionDigits(currency.getDefaultFractionDigits());
    format.setMinimumFractionDigits(currency.getDefaultFractionDigits());
    return format.format(amount);
  }

  public static final String formatCurrencyAmount(long amount, Currency currency, Locale locale) {
    return formatCurrencyAmount(toCurrency(amount, currency), currency, locale);
  }

  public static final String formatCurrencyAmount(long amount, Locale locale) {
    Currency c = Currency.getInstance(locale);
    return formatCurrencyAmount(toCurrency(amount, c), c, locale);
  }

  public static long toAmount(String amount, Locale locale) throws ParseException {
    return toAmount(amount, Currency.getInstance(locale), locale);
  }

  public static long toAmount(String amount, Currency currency, Locale locale)
      throws ParseException {
    NumberFormat nf = NumberFormat.getInstance(locale);
    int fraction = currency.getDefaultFractionDigits();

    Number n = nf.parse(amount);

    BigDecimal bd = new BigDecimal(n.toString());
    return bd.movePointRight(fraction).setScale(fraction, 4).longValue();
  }

  public static XMLGregorianCalendar getSaveXMLGregorianCalendar(Date date) {
    if (date == null) {
      return null;
    }
    try {
      GregorianCalendar gc = new GregorianCalendar();
      gc.setTime(date);

      return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
    } catch (Exception e) {
    }
    return null;
  }

  public static Date getSaveDate(XMLGregorianCalendar calendar) {
    if (calendar == null) {
      return null;
    }
    try {
      Calendar c = calendar.toGregorianCalendar();

      return c.getTime();
    } catch (Exception e) {
    }
    return null;
  }

  public static Map<String, String> splitParameters(String parameters) {
    if (parameters == null) {
      return null;
    }

    Map<String, String> params = new HashMap<String, String>();

    StringTokenizer st = new StringTokenizer(parameters, ",");
    while (st.hasMoreTokens()) {
      String token = st.nextToken().trim();

      if (token.length() > 0)
        ;
      int index = token.indexOf(61);
      String name;
      String value;
      if (index != -1) {
        name = token.substring(0, index);
        try {
          value = token.substring(index + 1);
        } catch (Exception ex) {
          value = "";
        }
      } else {
        name = token;
        value = null;
      }

      params.put(name, value);
    }

    return params;
  }
}
