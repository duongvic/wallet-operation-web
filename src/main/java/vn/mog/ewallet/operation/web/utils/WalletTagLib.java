package vn.mog.ewallet.operation.web.utils;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.beans.Provider;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.InvoiceType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.CardType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.FundOrderFlowStageType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.TxnStatus;
import vn.mog.framework.common.utils.DateUtil;
import vn.mog.framework.common.utils.NumberUtil;
import vn.mog.framework.common.utils.StringUtil;
import vn.mog.framework.common.utils.Utils;

import static vn.mog.framework.common.utils.Utils.RomanNumerals;

@Slf4j
@Component
public class WalletTagLib {

  private static CacheDataUtil cacheDataUtil;

  @Autowired
  public WalletTagLib(CacheDataUtil cacheDataUtil) {
    WalletTagLib.cacheDataUtil = cacheDataUtil;
  }

  public static String getNameTelco(String code) {
    try {
      CardType card = CardType.getCardType(code);
      if (card == null) {
        return code;
      }
      return card.name;
    } catch (Exception ex) {
      return code;
    }
  }

  public static String numberToText(String number) {
    try {
      String langLocal = LocaleContextHolder.getLocale().getLanguage();
      if (langLocal.equals("vi")) {
        return Utils.numberToText(number);
      } else {
        number = number.replaceAll("\\D+", "");
        return Utils.numberToTextEn(NumberUtil.convertToLong(number));
      }
    } catch (Exception ex) {
      return number;
    }
  }

  public static String formatNumber(String number) {
    try {
      Long numberL = NumberUtil.convertToLong(number);
      return NumberUtil.formatNumber(numberL);
    } catch (Exception ex) {
      return StringUtils.EMPTY;
    }
  }

  public static String getInvoiceTypeCodeName(String invoiceTypeId) {
    return InvoiceType.BILL_PAYMENT_LIST.get(NumberUtil.convertToInt(invoiceTypeId));
  }

  public static String getTxnStatusName(Integer txnStatusId) {
    return TxnStatus.TXN_STATUS_LIST.get(txnStatusId);
  }

  public static String getFundOrderStatusName(Integer status) {
    if (status == null) {
      return StringUtils.EMPTY;
    }
    return FundOrderFlowStageType.getWorkFlowState(status).displayText;
  }

  public static String isSignature(String url, String menu) {

    if (StringUtils.isNotEmpty(menu)) {
      return url + StringUtil.QUESTION_MARK + "menu=" + menu;
    } else {
      return url;
    }
  }

  public static String toUpperCase(String str) {
    String s = str;
    try {
      return s.toUpperCase();
    } catch (Exception ex) {
      return s;
    }
  }

  public static String getProviderBizCode(String code) {
    try {
      List<Provider> providers = cacheDataUtil.getCachingProviders();
      if (providers != null && !providers.isEmpty()) {
        Provider provider =
                providers.stream()
                        .filter(item -> StringUtils.equalsIgnoreCase(code, item.getProviderCode().name()))
                        .findFirst()
                        .orElse(null);
        if (provider != null) {
          return provider.getProviderBizCode();
        }
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return code;
  }

  public static String getRankingGroupString(Integer rank) {
    switch (rank) {
      case 1:
        return "Group A";
      case 2:
        return "Group B";
      case 3:
        return "Group C";
      default:
        return String.valueOf(rank);
    }
  }

  public static String getRankingLevelString(Integer rank) {
    switch (rank) {
      case 1:
        return "Level 1";
      case 2:
        return "Level 2";
      case 3:
        return "Level 3";
      default:
        return String.valueOf(rank);
    }
  }

  public static String getTopupViettelNameByCode(String code) {
    switch (code) {
      case "06010100":
        return "Topup Viettel 10K";
      case "06010101":
        return "Topup Viettel 20K";
      case "06010103":
        return "Topup Viettel 50K";
      case "06010104":
        return "Topup Viettel 100K";
      case "06010105":
        return "Topup Viettel 200K";
      case "06010107":
        return "Topup Viettel 500K";
      default:
        return code;
    }
  }

  public static Double getGrowthPercent(Long current, Long pass) {
    try {
      if(pass.longValue() == 0){
        return 0.0;
      }
      Double result = ((double) current.longValue() - pass.longValue()) / pass.longValue() * 100;
      return result;
    } catch (Exception e) {
    }
    return 0.0;
  }

  public static String getRomanNumeralByInt(Integer i) {
    try{
      return RomanNumerals(i);
    } catch (Exception e) {
      return StringUtils.EMPTY;
    }
  }

  public static Integer getDayBetween(Date date1, Date date2) {
    try {
      return Utils.daysBetween(date1, date2);
    } catch (Exception e) {

    }
    return 0;
  }
}
