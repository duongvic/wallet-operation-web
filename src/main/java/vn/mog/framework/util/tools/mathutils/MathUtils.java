package vn.mog.framework.util.tools.mathutils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class MathUtils {

  public static BigDecimal getPercentage(BigDecimal amount, BigDecimal percentage) {
    return amount.multiply(percentage).divide(new BigDecimal(100));
  }

  public static BigDecimal getPercentage(long amount, BigDecimal percentage) {
    return getPercentage(new BigDecimal(amount), percentage);
  }

  public static long getPercentageAsLong(BigDecimal amount, BigDecimal percentage,
      RoundingMode roundingMode) throws ArithmeticException {
    return getPercentage(amount, percentage).setScale(0, roundingMode).longValueExact();
  }

  public static long getPercentageAsLong(long amount, BigDecimal percentage,
      RoundingMode roundingMode) {
    return getPercentageAsLong(new BigDecimal(amount), percentage, roundingMode);
  }

  public static long getPercentageAsLong(BigDecimal amount, BigDecimal percentage) {
    return getPercentageAsLong(amount, percentage, RoundingMode.HALF_UP);
  }

  public static long getPercentageAsLong(long amount, BigDecimal percentage) {
    return getPercentageAsLong(new BigDecimal(amount), percentage);
  }

  public static BigDecimal getNetAmount(BigDecimal grossAmount, BigDecimal percentage, int scale,
      RoundingMode roundingMode) {
    BigDecimal rate = new BigDecimal(1).add(percentage.divide(new BigDecimal(100)));
    try {
      return grossAmount.divide(rate);
    } catch (ArithmeticException aE) {
    }
    return grossAmount.divide(rate, scale, roundingMode);
  }

  public static BigDecimal getNetAmount(long grossAmount, BigDecimal percentage, int scale,
      RoundingMode roundingMode) {
    return getNetAmount(new BigDecimal(grossAmount), percentage, scale, roundingMode);
  }

  public static BigDecimal getNetAmount(BigDecimal grossAmount, BigDecimal percentage, int scale) {
    return getNetAmount(grossAmount, percentage, scale, RoundingMode.HALF_UP);
  }

  public static BigDecimal getNetAmount(long grossAmount, BigDecimal percentage, int scale) {
    return getNetAmount(new BigDecimal(grossAmount), percentage, scale);
  }

  public static BigDecimal getNetAmount(BigDecimal grossAmount, BigDecimal percentage) {
    return getNetAmount(grossAmount, percentage, 9, RoundingMode.HALF_UP);
  }

  public static BigDecimal getNetAmount(long grossAmount, BigDecimal percentage) {
    return getNetAmount(new BigDecimal(grossAmount), percentage);
  }

  public static long getNetAmountAsLong(BigDecimal grossAmount, BigDecimal percentage,
      RoundingMode roundingMode) {
    return getNetAmount(grossAmount, percentage, 0, roundingMode).setScale(0, roundingMode)
        .longValueExact();
  }

  public static long getNetAmountAsLong(long grossAmount, BigDecimal percentage) {
    return getNetAmountAsLong(new BigDecimal(grossAmount), percentage, RoundingMode.HALF_UP);
  }
}
