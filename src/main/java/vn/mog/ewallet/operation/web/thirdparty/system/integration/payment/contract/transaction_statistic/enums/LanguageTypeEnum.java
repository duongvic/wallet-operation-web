package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums;

import org.apache.commons.lang3.StringUtils;

public enum LanguageTypeEnum {

  VI,
  EN;

  public static LanguageTypeEnum getLanguageType(String language) {
    if (StringUtils.isBlank(language)) {
      return LanguageTypeEnum.VI;
    }

    for (LanguageTypeEnum languageTypeEnum : LanguageTypeEnum.values()) {
      if (languageTypeEnum.name().equalsIgnoreCase(language)) {
        return languageTypeEnum;
      }
    }

    return LanguageTypeEnum.VI;
  }
}
