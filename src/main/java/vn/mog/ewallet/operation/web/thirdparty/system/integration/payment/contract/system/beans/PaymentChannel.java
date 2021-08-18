package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans;

import java.util.Arrays;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum PaymentChannel {

  ZOTA("ZOTA", "ZOTA Channel (Default)"),
  SAPO("SAPO", "SAPO Channel"),
  ZOTAHOME("ZOTAHOME", "ZOTAHOME Channel"),
  ;

  private String code;
  private String displayText;

  public static Optional<PaymentChannel> fromValue(String code) {
    return Arrays.stream(PaymentChannel.values()).filter(item -> StringUtils
        .equalsIgnoreCase(item.getCode(), code))
        .findFirst();
  }
}
