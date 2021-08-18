package vn.mog.ewallet.operation.web.controller.translog;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.contract.AjaxResponse;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.exception.MessageNotify;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindFullCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.CreateTransactionRuleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.CreateTransactionRuleResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.FindTransactionRuleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.FindTransactionRuleResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetTransactionRuleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetTransactionRuleResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.RemoveTransactionRuleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.RemoveTransactionRuleResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.UpdateTransactionRuleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.UpdateTransactionRuleResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.TransactionRule;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.TransactionRuleStep;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.EventType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.SourceOfFundType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.Customer;
import vn.mog.ewallet.operation.web.utils.ValidationUtil;
import vn.mog.framework.common.utils.NumberUtil;
import vn.mog.framework.common.utils.SessionUtil;
import vn.mog.framework.common.utils.StringUtil;


@Controller
@RequestMapping(value = "/wallet/transaction/rule")
public class TransactionRuleController extends AbstractController {

  public static final String TRANS_RULE_CONTROLLER = "/wallet/transaction/rule";
  public static final String TRANS_RULE_LIST = TRANS_RULE_CONTROLLER + "/list";
  public static final String TRANS_RULE_DETAIL = TRANS_RULE_CONTROLLER + "/detail";

  public static final String REDIRECT_TRANS_RULE_ADD = "redirect:" + TRANS_RULE_CONTROLLER + "/add";
  public static final String REDIRECT_TRANS_RULE_UPDATE =
      "redirect:" + TRANS_RULE_CONTROLLER + "/updateRule";

  private static final Logger LOG = LogManager.getLogger(TransactionRuleController.class);

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "')")
  public String list(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws FrontEndException {

    String quickSearch = StringUtils.trimToEmpty(request.getParameter("quickSearch"));
    String[] paramServiceTypeIds = request.getParameterValues("serviceTypeIds");

    int offset = 0;
    int limit = 20;

    if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
      offset = Integer.parseInt(request.getParameter("d-49489-p"));
      if (offset > 0) {
        offset = (offset - 1) * limit;
      }
    }

    List<ServiceType> serviceTypes = new ArrayList<>();
    if (paramServiceTypeIds != null && paramServiceTypeIds.length > 0 && StringUtils
        .isNotEmpty(paramServiceTypeIds[0])) {
      for (String item : paramServiceTypeIds) {
        serviceTypes.add(ServiceType.getServiceType(item));
      }
    }

    FindTransactionRuleRequest ftRuleRequest = new FindTransactionRuleRequest();
    ftRuleRequest.setSearch(quickSearch);
    ftRuleRequest.setServiceTypes(serviceTypes);
    ftRuleRequest.setLimit(limit);
    ftRuleRequest.setOffset(offset);

    FindTransactionRuleResponse ftRuleResponse = transactionEndpoint
        .findTransactionRules(ftRuleRequest);
    List<TransactionRule> transactionRules = ftRuleResponse.getTransactionRules();

    map.put("transRules", transactionRules);
    map.put("total", ftRuleResponse.getTotal().intValue());
    map.put("offset", offset);
    map.put("pagesize", limit);
    map.put("serviceTypes", ServiceType.FULL_SERVICE_TYPES);

    return "/transaction_rule/trans_rule";
  }

  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "')")
  public String get(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws FrontEndException {

    Long txnRuleId = NumberUtil.convertToLong(request.getParameter("tranRuleId"));

    String quickSearch = StringUtils.trimToEmpty(request.getParameter("quickSearch"));
    String[] searchService = request.getParameterValues("service");
    String page = StringUtils.trimToEmpty(request.getParameter("d-49489-p"));

    GetTransactionRuleRequest getRequest = new GetTransactionRuleRequest(txnRuleId);
    GetTransactionRuleResponse getResponse = transactionEndpoint.getTransactionRule(getRequest);
    TransactionRule transactionRule = getResponse.getTransactionRule();

    map.put("tranRule", transactionRule);
    map.put("tranRuleSteps", transactionRule.getSteps());

    map.put("service", searchService);
    map.put("quickSearch", quickSearch);
    map.put("numberPage", page);

    return "/transaction_rule/trans_rule_steps";
  }

  @RequestMapping(value = "/deleteTransRule", method = RequestMethod.POST)
  @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "')")
  public ResponseEntity<?> deleteTransRule(HttpServletRequest request, ModelMap map)
      throws FrontEndException {
    try {
      Long tranRuleId = NumberUtil.convertToLong(request.getParameter("transRuleId"));
      if (tranRuleId < 0) {
        return ResponseEntity
            .ok(new AjaxResponse(MessageNotify.ERROR_CODE, validation.notify(ValidationUtil.TRANS_RULE_ID_NULL)));
      }

      RemoveTransactionRuleRequest rtrRequest = new RemoveTransactionRuleRequest(tranRuleId);
      RemoveTransactionRuleResponse rtrResponse = paymentSystemEndpoint.removeTransactionRule(rtrRequest);
      return responseAjax(rtrResponse.getStatus().getCode(), rtrResponse.getStatus().getValue());

    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.ok(new AjaxResponse(MessageNotify.ERROR_CODE, e.getMessage()));
    }
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "')")
  public String addTransactionRule(HttpServletRequest request, ModelMap map)
      throws FrontEndException {

    putParamTranRuleToRequest(map);

    map.put("numberRow", 0);

    return "/transaction_rule/trans_rule_add";
  }

  @RequestMapping(value = "/updateRule", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "')")
  public String updateRule(HttpServletRequest request, ModelMap map) throws FrontEndException {

    putParamTranRuleToRequest(map);

    Long id = NumberUtil.convertToLong(request.getParameter("id"));
    if (id > 0) {
      GetTransactionRuleRequest gtrRequest = new GetTransactionRuleRequest(id);
      GetTransactionRuleResponse gtrResponse = transactionEndpoint.getTransactionRule(gtrRequest);

      TransactionRule transRule =
          (gtrResponse != null && gtrResponse.getTransactionRule() != null) ? gtrResponse
              .getTransactionRule() : null;
      if (transRule != null) {
        map.put("transRule", transRule);

        if (transRule.getSteps().isEmpty()) {
          map.put("numberRow", 0);
        } else {
          map.put("numberRow", transRule.getSteps().size());
          map.put("transRuleSteps", transRule.getSteps());
        }
        return "/transaction_rule/trans_rule_add";
      }
    }

    return "redirect:" + TRANS_RULE_LIST;
  }

  @RequestMapping(value = "/updateTxnRule", method = RequestMethod.POST)
  @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "')")
  public String updateRule(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws FrontEndException, IOException {

    Long transRuleId = NumberUtil.convertToLong(request.getParameter("transRuleId"));

    String serviceTypeId = request.getParameter("serviceTypeId");
    String ruleCode = request.getParameter("ruleCode");
    String ruleName = request.getParameter("ruleName");

    putParamTranRuleToRequest(map);

    AjaxResponse validRule = validation.updateRule(serviceTypeId, ruleCode, ruleName);
    if (validRule.getCode() == 1) {
      map.put(MessageNotify.codeErr, validRule.getCode());
      map.put(MessageNotify.mesErr, validRule.getMessage());
      map.put("tranRule", new TransactionRule(ruleCode, ruleName));
      return "/transaction_rule/trans_rule_add";
    }

    int numberRow = NumberUtil.convertToInt(request.getParameter("numberRow"));
    List<TransactionRuleStep> ruleSteps = new ArrayList<>();
    TransactionRuleStep ruleStep;

    for (int i = 0; i < numberRow; i++) {

      String order = request.getParameter("order" + i);
      String sourceActor = request.getParameter("sourceActor" + i);
      String sourceAccount = request.getParameter("sourceAccount" + i);
      String desActor = request.getParameter("desActor" + i);
      String desAccount = request.getParameter("desAccount" + i);
      String eventTypeId = request.getParameter("eventTypeId" + i);
      String sofTypeId = request.getParameter("sofTypeId" + i);

      AjaxResponse validRuleStep = validation.updateRuleStep();
      if (validRuleStep.getCode() == 1) {
        map.put(MessageNotify.codeErr, validRule.getCode());
        map.put(MessageNotify.mesErr, validRule.getMessage());
        return "/transaction_rule/trans_rule_add";
      }

      if (order != null && eventTypeId != null) {
        ruleStep = new TransactionRuleStep();
        ruleStep.setOrder(NumberUtil.convertToInt(order));
        ruleStep.setEventType(EventType.getEventType(eventTypeId));
        ruleStep.setSourceOfFundType(SourceOfFundType.getSofType(sofTypeId));

        if (StringUtils.EMPTY.equals(sourceActor)) {
          ruleStep.setSource(sourceAccount);
          ruleStep.setIsSourceCif(StringUtil.CHAR_YES);

        } else if (StringUtil.PAYEE.equals(sourceActor)) {
          ruleStep.setSource(StringUtil.PAYEE);
          ruleStep.setIsSourceCif(StringUtil.CHAR_NO);

        } else if (StringUtil.PAYER.equals(sourceActor)) {
          ruleStep.setSource(StringUtil.PAYER);
          ruleStep.setIsSourceCif(StringUtil.CHAR_NO);
        }

        if (StringUtils.EMPTY.equals(desActor)) {
          ruleStep.setDest(desAccount);
          ruleStep.setIsDestCif(StringUtil.CHAR_YES);

        } else if (StringUtil.PAYEE.equals(desActor)) {
          ruleStep.setDest(StringUtil.PAYEE);
          ruleStep.setIsDestCif(StringUtil.CHAR_NO);

        } else if (StringUtil.PAYER.equals(desActor)) {
          ruleStep.setDest(StringUtil.PAYER);
          ruleStep.setIsDestCif(StringUtil.CHAR_NO);
        }

        ruleSteps.add(ruleStep);
      }
    }

    ServiceType serviceType = ServiceType.getServiceType(serviceTypeId);
    TransactionRule transRule = new TransactionRule(transRuleId, serviceType, ruleCode, ruleName);
    transRule.setSteps(ruleSteps);

    if (transRuleId > 0) {
      return updateTranRule(request, response, map, transRule, numberRow);
    } else {
      return createTranRule(request, response, map, transRule, numberRow);
    }
  }

  private String createTranRule(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, TransactionRule transRule, int numberRow)
      throws IOException {

    CreateTransactionRuleRequest ctrRequest = new CreateTransactionRuleRequest();
    transRule.setId(null);
    ctrRequest.setTxnRule(transRule);

    if (transRule.getSteps() != null && !transRule.getSteps().isEmpty()) {
      ctrRequest.setIncludeTxnRuleStep(true);
    } else {
      ctrRequest.setIncludeTxnRuleStep(false);
    }

    CreateTransactionRuleResponse ctrResponse = paymentSystemEndpoint.createTransactionRule(ctrRequest);
    if (ctrResponse.getStatus().getCode() == 0 && ctrResponse.getTxnRuleId() > 0) {
      SessionUtil
          .setAttribute(
              MessageNotify.SESSION_MESSAGE_NOTIFY, new MessageNotify(MessageNotify.SUCCESS_CODE, MessageNotify.SUCCESS_NAME));
      return "redirect:" + TRANS_RULE_LIST;
    }

    map.put(MessageNotify.codeErr, ctrResponse.getStatus().getCode());
    map.put(MessageNotify.mesErr, ctrResponse.getStatus().getValue());
    // map.put("numberRow", numberRow);
    return REDIRECT_TRANS_RULE_ADD;
  }

  private String updateTranRule(HttpServletRequest request, HttpServletResponse response,
      ModelMap map, TransactionRule transRule, int numberRow)
      throws IOException {
    UpdateTransactionRuleRequest utrRequest = new UpdateTransactionRuleRequest();

    utrRequest.setTxnRule(transRule);
    if (transRule.getSteps() != null && !transRule.getSteps().isEmpty()) {
      utrRequest.setIncludeTxnRuleStep(true);
    } else {
      utrRequest.setIncludeTxnRuleStep(false);
    }

    UpdateTransactionRuleResponse utrResponse = paymentSystemEndpoint.updateTransactionRule(utrRequest);
    if (utrResponse.getStatus().getCode() == 0) {
      SessionUtil
          .setAttribute(
              MessageNotify.SESSION_MESSAGE_NOTIFY, new MessageNotify(MessageNotify.SUCCESS_CODE, MessageNotify.SUCCESS_NAME));
      return "redirect:" + TRANS_RULE_LIST;
    }
    map.put(MessageNotify.codeErr, utrResponse.getStatus().getCode());
    map.put(MessageNotify.mesErr, utrResponse.getStatus().getValue());
    map.put("id", transRule.getId());
    // map.put("numberRow", numberRow);
    return REDIRECT_TRANS_RULE_UPDATE;
  }

  private void putParamTranRuleToRequest(ModelMap map) {
    map.put("eventTypes", EventType.BALANCE_EVENT_TYPES);
    map.put("customers", getCustomers());
    map.put("actorTypes", ACTOR_TYPES);
    map.put("serviceTypes", ServiceType.FULL_SERVICE_TYPES);
    map.put("sofTypes", SourceOfFundType.SOF_ID_TYPES);
  }

  private List<Customer> getCustomers() {
    try {
      FindFullCustomerRequest ffcRequest = new FindFullCustomerRequest();
//      ffcRequest.setCustomerTypes(Arrays
//          .asList(CustomerType.ID_MERCHANT, CustomerType.ID_SALE, CustomerType.ID_ZOTA, CustomerType.ID_POOL, CustomerType.ID_SOF, CustomerType.ID_STAFF, CustomerType.ID_ADMIN,
//              CustomerType.ID_SYSTEM));
      ffcRequest.setCustomerTypes(Arrays
          .asList(CustomerType.ID_ZOTA, CustomerType.ID_POOL, CustomerType.ID_SOF,
              CustomerType.ID_PROVIDER));
      return paymentCustomerEndpoint.findCustomers(ffcRequest).getCustomers();
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return Collections.emptyList();
    }
  }
}
