package vn.mog.ewallet.operation.web.utils;


import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import vn.mog.ewallet.operation.web.constant.SharedConstants;
import vn.mog.ewallet.operation.web.contract.AjaxResponse;
import vn.mog.ewallet.operation.web.exception.MessageNotify;
import vn.mog.framework.common.utils.NumberUtil;
import vn.mog.framework.common.utils.StringUtil;


@Component
public class ValidationUtil {

  public static final String DATA_INVALIDATE = "message.data.invalidate";

  public static final String MESAGE_ERROR = "message.response.value.error";
  public static final String MESAGE_SUCCESS = "message.response.value.success";

  public static final String TRANS_RULE_ID_NULL = "system.txn.rule.id.null";

  public static final String SERVICE_PARENT_ID_NULL = "system.trueservice.message.parent.id.null";
  public static final String SERVICE_CODE_NULL = "system.trueservice.message.servicecode.null";
  public static final String SERVICE_CODE_SPECIAL = "system.trueservice.message.servicecode.specialcharacters";

  public static final String MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS = "message.order.flow.process.approve.success";
  public static final String MESAGE_ORDER_FLOW_APPROVE_PROCESS_ERROR = "message.order.flow.process.approve.error";
  public static final String MESAGE_ORDER_FLOW_REJECT_PROCESS_SUCCESS = "message.order.flow.process.reject.success";
  public static final String MESAGE_ORDER_FLOW_REJECT_PROCESS_ERROR = "message.order.flow.process.reject.error";
  public static final String TXN_RULE_CODE_SPECIAL = "system.txn.rule.code.special";
  public static final String TXN_RULE_NAME_SPECIAL = "system.txn.rule.name.special";
  public static final String TXN_RULE_CODE_NULL = "system.txn.rule.code.null";
  public static final String TXN_RULE_NAME_NULL = "system.txn.rule.name.null";
  @Autowired
  protected MessageSource messageSource;

  public String notify(String code) {
    return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
  }

  public String notify(String code, String defaultMessage) {
    return messageSource.getMessage(code, null, (defaultMessage == null) ? "Thực hiện lỗi" : defaultMessage, LocaleContextHolder.getLocale());
  }

  public String notify(String code, Object[] params) {
    return messageSource.getMessage(code, params, LocaleContextHolder.getLocale());
  }

  // -------------- True Service------------------------
  public AjaxResponse createTrueService(String serviceType, String serviceCode, String serviceName, String isActorPayAdd) {

    if (StringUtils.EMPTY.equals(serviceType)) {
      return new AjaxResponse(1, notify("system.trueservice.message.servicetype.null"));
    }

    if (StringUtils.EMPTY.equals(serviceName)) {
      return new AjaxResponse(1, notify("system.trueservice.message.servicename.null"));
    }

    // service code
    if (StringUtils.EMPTY.equals(serviceCode)) {
      return new AjaxResponse(1, notify(SERVICE_CODE_NULL));
    }

    if (StringUtil.specialSymbols(serviceCode, StringUtil.SPECIAL_NAME_MATCHERS)) {
      return new AjaxResponse(1, notify(SERVICE_CODE_SPECIAL));
    }

    if (StringUtils.trimToEmpty(serviceCode).length() < 3) {
      return new AjaxResponse(1, notify("system.trueservice.message.servicecode.3characters"));
    }

    if (StringUtils.trimToEmpty(serviceCode).length() > 32) {
      return new AjaxResponse(1, notify("system.trueservice.message.servicecode.32characters"));
    }

    // service name
    if (StringUtil.specialSymbols(serviceName, StringUtil.SPECIAL_NAME_MATCHERS)) {
      return new AjaxResponse(1, notify("system.trueservice.message.servicename.specialcharacters"));
    }

    if (StringUtils.trimToEmpty(serviceName).length() < 3) {
      return new AjaxResponse(1, notify("system.trueservice.message.servicename.3characters"));
    }

    if (!isActorPayAdd.equals("Y") && !isActorPayAdd.equals("N")) {
      return new AjaxResponse(1, notify("system.trueservice.message.actorpay.null"));
    }

    return new AjaxResponse(0, notify("system.trueservice.message.validate.invalidate"));
  }

  public AjaxResponse updateTrueService(Long serviceId, Long parentServiceId, String serviceType, String serviceCode, String serviceName, String isActor) {

    if (serviceId == 0L) {
      return new AjaxResponse(1, notify(SERVICE_CODE_NULL));
    }

    /*
     * if (parentServiceId == 0L) { return new AjaxResponse(1,
     * notify(PARENT_SERVICE_ID_NULL)); }
     */

    if (StringUtils.EMPTY.equals(serviceType)) {
      return new AjaxResponse(1, notify("system.trueservice.message.servicetype.null"));
    }
    if (StringUtils.EMPTY.equals(serviceCode)) {
      return new AjaxResponse(1, notify(SERVICE_CODE_NULL));
    }

    if (StringUtils.EMPTY.equals(serviceName)) {
      return new AjaxResponse(1, notify("system.trueservice.message.servicename.null"));
    }

    // service code
    if (StringUtil.specialSymbols(serviceCode, StringUtil.SPECIAL_NAME_MATCHERS)) {
      return new AjaxResponse(1, notify(SERVICE_CODE_SPECIAL));
    }

    if (StringUtils.trimToEmpty(serviceCode).length() < 3) {
      return new AjaxResponse(1, notify("system.trueservice.message.servicecode.3characters"));
    }

    if (StringUtils.trimToEmpty(serviceCode).length() > 32) {
      return new AjaxResponse(1, notify("system.trueservice.message.servicecode.32characters"));
    }

    // service name
    if (StringUtil.specialSymbols(serviceName, StringUtil.SPECIAL_NAME_MATCHERS)) {
      return new AjaxResponse(1, notify("system.trueservice.message.servicename.specialcharacters"));
    }

    if (StringUtils.trimToEmpty(serviceName).length() < 3) {
      return new AjaxResponse(1, notify("system.trueservice.message.servicename.3characters"));
    }

    if (!isActor.equals("Y") && !isActor.equals("N")) {
      return new AjaxResponse(1, notify("system.trueservice.message.actorpay.null"));
    }

    return new AjaxResponse(MessageNotify.SUCCESS_CODE, notify(MessageNotify.SUCCESS_NAME));
  }

  public AjaxResponse changeTrueServiceStatus(Long serviceId, String active) {
    if (serviceId == 0L) {
      return new AjaxResponse(1, notify(ValidationUtil.SERVICE_CODE_NULL));
    }

    if (StringUtils.EMPTY.equals(active)) {
      return new AjaxResponse(1, notify("system.true.service.active.data.null"));
    }

    if (!"Y".equalsIgnoreCase(active) && !"N".equalsIgnoreCase(active)) {
      return new AjaxResponse(1, notify("system.true.service.active.data.invalid"));
    }

    return new AjaxResponse(MessageNotify.SUCCESS_CODE, MessageNotify.SUCCESS_NAME);
  }

  public String isLinkBankTranfer(Long amountOfLinkBank, long balance) {
    if (amountOfLinkBank == 0) {
      return notify("error.fundout.linkbank.01");

    } else if (amountOfLinkBank > balance) {
      return notify("error.fundout.linkbank.02");

    } else if (amountOfLinkBank < SharedConstants.FUND_OUT_ORDER_MIN_VALUE_PER_ORDER) {

      String minNumber = NumberUtil.formatNumber(SharedConstants.FUND_OUT_ORDER_MIN_VALUE_PER_ORDER);
      String linkBankNumber = NumberUtil.formatNumber(amountOfLinkBank);
      List<String> strings = Arrays.asList(linkBankNumber, minNumber);
      return notify("error.fundout.linkbank.03", strings.toArray());
    }
    return StringUtils.EMPTY;
  }

  public AjaxResponse addTrueServiceMatrix(Long serviceId, Long transactionRuleId) {

    if (serviceId == 0L) {
      return new AjaxResponse(1, notify(ValidationUtil.SERVICE_CODE_NULL));
    }

    if (transactionRuleId == 0L) {
      return new AjaxResponse(1, notify("system.true.service.txnn.rule.id.null"));
    }
    return new AjaxResponse(MessageNotify.SUCCESS_CODE, MessageNotify.SUCCESS_NAME);
  }

  public AjaxResponse updateRule(String serviceTypeId, String ruleCode, String ruleName) {

    if (StringUtils.isEmpty(serviceTypeId)) {
      return new AjaxResponse(MessageNotify.ERROR_CODE, notify("system.txn.rule.servicetype.null"));
    }

    if (StringUtils.isEmpty(ruleCode)) {
      return new AjaxResponse(MessageNotify.ERROR_CODE, notify(TXN_RULE_CODE_NULL));
    }

    if (StringUtils.isEmpty(ruleName)) {
      return new AjaxResponse(MessageNotify.ERROR_CODE, notify(TXN_RULE_NAME_NULL));
    }

    if (StringUtil.specialSymbols(ruleCode, StringUtil.SPECIAL_NAME_MATCHERS)) {
      return new AjaxResponse(MessageNotify.ERROR_CODE, notify(TXN_RULE_CODE_SPECIAL));
    }

    if (StringUtil.specialSymbols(ruleName, StringUtil.SPECIAL_NAME_MATCHERS)) {
      return new AjaxResponse(MessageNotify.ERROR_CODE, notify(TXN_RULE_NAME_SPECIAL));
    }

    return new AjaxResponse(MessageNotify.SUCCESS_CODE, MessageNotify.SUCCESS_NAME);
  }

  public AjaxResponse updateRuleStep() {
    return new AjaxResponse(MessageNotify.SUCCESS_CODE, MessageNotify.SUCCESS_NAME);
  }

  public AjaxResponse requestSubmitTransfer() {
    return new AjaxResponse(MessageNotify.SUCCESS_CODE, MessageNotify.SUCCESS_NAME);

  }

  public void validateOrderInitiate() {

  }

  public MessageNotify simpleSpecific(String... roles) {
    for (String item : roles) {
      if (!StringUtils.EMPTY.equals(item) && StringUtil.specialSymbols(item, StringUtil.SPECIAL_NAME_MATCHERS)) {
        return new MessageNotify(MessageNotify.ERROR_CODE, notify("message.name.specialcharacters"));
      }
    }
    return new MessageNotify(MessageNotify.SUCCESS_CODE, MessageNotify.SUCCESS_NAME);
  }

  public AjaxResponse updateSpecifiedCommissionFee(Double commission, Long commissionFixedAmount,
      Double fee, Long feeFixedAmount) {
    if (commission < 0D || fee < 0D) {
      return new AjaxResponse(MessageNotify.ERROR_CODE,
          notify("system.specified.commission.fee.invalid.rate"));
    }
    if (commissionFixedAmount < 0L || feeFixedAmount < 0L) {
      return new AjaxResponse(MessageNotify.ERROR_CODE,
          notify("system.specified.commission.fee.invalid.fixed.amount"));
    }

    return new AjaxResponse(MessageNotify.SUCCESS_CODE, MessageNotify.SUCCESS_NAME);
  }

}
