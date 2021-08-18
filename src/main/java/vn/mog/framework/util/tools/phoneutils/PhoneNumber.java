package vn.mog.framework.util.tools.phoneutils;

import java.util.Arrays;
import java.util.List;

public class PhoneNumber {
  private static final List<String> PREFIXES_SIZE2 = Arrays.asList(new String[] {"20", "27", "30",
      "31", "32", "33", "34", "36", "39", "40", "41", "43", "44", "45", "46", "47", "48", "49",
      "51", "52", "53", "54", "55", "56", "57", "58", "60", "61", "62", "63", "64", "65", "66",
      "81", "82", "83", "84", "86", "90", "91", "92", "93", "94", "95", "98", "37", "38", "42"});
  public static final String DEFAULT_COUNTRY_CODE = "84";
  private final String msisdn;

  public PhoneNumber(String msisdn, String countryCode) {
    String trimmedMsisdn = msisdn.replaceAll("[^0-9]", "");

    if (("1".equals(countryCode)) && (trimmedMsisdn.length() == 10))
      this.msisdn = "+1" + trimmedMsisdn;
    else if (trimmedMsisdn.startsWith("0000"))
      this.msisdn = "+" + countryCode + trimmedMsisdn.substring(1);
    else if (trimmedMsisdn.startsWith("000"))
      this.msisdn = "+" + countryCode + trimmedMsisdn;
    else if (trimmedMsisdn.startsWith("00"))
      this.msisdn = "+" + trimmedMsisdn.substring(2);
    else if (trimmedMsisdn.startsWith("0"))
      this.msisdn = "+" + countryCode + trimmedMsisdn.substring(1);
    else
      this.msisdn = "+" + trimmedMsisdn;
  }

  public PhoneNumber(String msisdn) {
    this(msisdn, DEFAULT_COUNTRY_CODE);
  }

  public String getInternationalFormat() {
    return this.msisdn;
  }

  public String getNumericInternationalFormat() {
    return "00" + this.msisdn.substring(1);
  }

  public String getShortInternationalFormat() {
    return this.msisdn.substring(1);
  }

  public String getNationalFormat() {
    int chars = 3;

    if ((this.msisdn.charAt(1) == '1') || (this.msisdn.charAt(1) == '7'))
      chars = 1;
    if (PREFIXES_SIZE2.contains(this.msisdn.substring(1, 3)))
      chars = 2;
    return "0" + this.msisdn.substring(chars + 1);
  }

  public String toString() {
    return getInternationalFormat();
  }

  public boolean equals(Object o) {
    if (!(o instanceof PhoneNumber))
      return false;
    return getInternationalFormat().equals(((PhoneNumber) o).getInternationalFormat());
  }

  public int hashCode() {
    return getInternationalFormat().hashCode();
  }

  public static void main(String[] args) {
    String msisdn = "0936663369";
    PhoneNumber phoneNumber = new PhoneNumber(msisdn);
    System.out.println(phoneNumber.toString());
  }
}
