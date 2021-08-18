package vn.mog.ewallet.operation.web.controller.translog;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.contract.form.SearchDataForm;
import vn.mog.ewallet.operation.web.exception.MessageNotify;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.PaymentChannel;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.TelcoType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.*;
import vn.mog.framework.common.utils.StringUtil;

@Controller
@RequestMapping(value = "/service/transaction-history")
public class TransactionLogController extends AbstractTransactionController {

  public static final String TRANSACTION_CONTROLLER = "/service/transaction-history";
  public static final String TRANSACTION_LIST = TRANSACTION_CONTROLLER + "/list";
  public static final String TRANSACTION_LIST_ALL = TRANSACTION_CONTROLLER + "/list-all";
  public static final String TRANSACTION_DETAIL = TRANSACTION_CONTROLLER + "/detail";
  public static final String TRANSACTION_DEFAULT_FILTER = "txnStatusIds=10";

  private static final Logger LOG = LogManager.getLogger(TransactionLogController.class);

  @Value("#{'${param.system.provider.ptu.onhold.acl}'.split('\\|')}")
  private Set<String> providers;

  @Value("#{'${param.system.provider.billpayment.onhold.acl}'.split('\\|')}")
  private Set<String> providerBillPaymentOnHolds;

  @RequestMapping(value = "/list-all", method = RequestMethod.GET)
  @PreAuthorize("isAuthenticated()")
  public String searchAll(
      HttpServletRequest request,
      HttpServletResponse response,
      ModelMap map,
      @ModelAttribute SearchDataForm searchDataForm)
      throws Exception {

    //    List<String> serviceTypes = searchDataForm.getServiceTypeIds();
    //    if (serviceTypes == null || serviceTypes.isEmpty()) {
    //      searchDataForm.setServiceTypeIds(new ArrayList<>(ServiceType.PAYMENT_TYPES.keySet()));
    //    }
    //    searchCommonTrans(request, response, map, searchDataForm);
    //    map.put("serviceTypes", ServiceType.PAYMENT_TYPES);
    //
    //    return "/transaction/transaction-logs-all";

    return searchSourceOfFund(request, response, map, searchDataForm);
  }

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @PreAuthorize("isAuthenticated()")
  public String searchSourceOfFund(
      HttpServletRequest request,
      HttpServletResponse response,
      ModelMap map,
      @ModelAttribute SearchDataForm searchDataForm)
      throws Exception {
    long time01 = new Date().getTime();
    List<String> serviceTypes = searchDataForm.getServiceTypeIds();
    if (serviceTypes.contains("BILL_PAYMENT") || serviceTypes.size() == 0) {
      map.put("hasBillPayment", true);
    }

    if (serviceTypes == null || serviceTypes.isEmpty()) {
      searchDataForm.setServiceTypeIds(new ArrayList<>(ServiceType.PAYMENT_TYPES.keySet()));
    }
    searchCommonTrans(request, response, map, searchDataForm);
    map.put("serviceTypes", ServiceType.PAYMENT_TYPES);
    long time02 = new Date().getTime();
    System.out.println("Thoi gian controller:" + (time02 - time01));

    map.put("providers", providers);
    map.put("providerBillPaymentOnHolds", providerBillPaymentOnHolds);
    map.put("telcoTypes", getTelcoTypes());
    map.put(
            "paymentChannels",
            new PaymentChannel[] {PaymentChannel.ZOTA, PaymentChannel.SAPO, PaymentChannel.ZOTAHOME});

    return "/transaction/transaction-logs";
  }

  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.STAFF
          + "','"
          + RoleConstants.MERCHANT
          + "','"
          + RoleConstants.CUSTOMER
          + "')")
  public String get(HttpServletRequest request, ModelMap map) throws Exception {

    detailCommonTrans(request, map);

    map.put("providers", providers);
    map.put("providerBillPaymentOnHolds", providerBillPaymentOnHolds);
    map.put("telcoTypes", getTelcoTypes());
    return "/transaction/transaction-logs-detail";
  }

  public List<Enum> getTelcoTypes() {
    List<Enum> telcoTypes = new ArrayList<>();
    telcoTypes.add(TelcoType.VMS);
    telcoTypes.add(TelcoType.VNM);
    telcoTypes.add(TelcoType.VNP);
    telcoTypes.add(TelcoType.VTM);
    telcoTypes.add(TelcoType.GMOBILE);
    return telcoTypes;
  }

  @RequestMapping(value = "/export", method = RequestMethod.GET)
  @PreAuthorize("isAuthenticated()")
  public void export(
      HttpServletRequest request,
      HttpServletResponse response,
      @ModelAttribute SearchDataForm searchDataForm)
      throws Exception {
    List<String> serviceTypes = searchDataForm.getServiceTypeIds();
    if (serviceTypes == null || serviceTypes.isEmpty()) {
      searchDataForm.setServiceTypeIds(new ArrayList<>(ServiceType.PAYMENT_TYPES.keySet()));
    }
    super.export(request, response, searchDataForm);
  }

  @RequestMapping(value = "/reversal", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + RoleConstants.CUSTOMERCARE_MANAGER
          + "','"
          + RoleConstants.CUSTOMERCARE
          + "', '"
          + RoleConstants.FINANCE
          + "' ,'"
          + RoleConstants.FINANCE_SUPPORT
          + "' , '"
          + RoleConstants.FINANCESUPPORT_LEADER
          + "', '"
          + RoleConstants.FINANCE_LEADER
          + "')")
  public String reversalTxn(HttpServletRequest request, ModelMap map) throws Exception {

    detailCommonTrans(request, map);

    return "/transaction_reversal/transaction-reversal-request";
  }

  @RequestMapping(value = "/get-phone-topup-transaction-on-hold", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> fundinPhoneConsumer(HttpServletRequest request, ModelMap map)
      throws Exception {
    try {
      Long txId = Long.valueOf(request.getParameter("txId"));
      String provider = request.getParameter("provider");
      String telcoType = request.getParameter("telcoType");
      String noteContent = StringUtils.trimToEmpty(request.getParameter("noteContent"));

      PhoneTopupTransactionOnHoldRequest phoneTopupTransactionOnHoldRequest =
          new PhoneTopupTransactionOnHoldRequest();
      phoneTopupTransactionOnHoldRequest.setTxnId(txId);
      if (provider != null && !provider.isEmpty())
        phoneTopupTransactionOnHoldRequest.setProvider(provider);
      if (telcoType != null && !telcoType.isEmpty())
        phoneTopupTransactionOnHoldRequest.setTelcoType(telcoType);
      if (noteContent != null && !noteContent.isEmpty())
        phoneTopupTransactionOnHoldRequest.setNote(noteContent);

      PhoneTopupTransactionOnHoldResponse res =
          transactionEndpoint.getPhoneTopupTransactionOnHold(phoneTopupTransactionOnHoldRequest);
      if (res.getStatus().getCode() != MessageNotify.SUCCESS_CODE)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);

    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
    }
    return ResponseEntity.ok(null);
  }

  @RequestMapping(value = "/get-bill-payment-transaction-on-hold", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> billPaymentConsumer(HttpServletRequest request, ModelMap map)
      throws Exception {
    try {
      Long txId = Long.valueOf(request.getParameter("txId"));
      String provider = request.getParameter("provider");
      String noteContent = StringUtils.trimToEmpty(request.getParameter("noteContent"));

      BillPaymentTransactionOnHoldRequest billPaymentTransactionOnHoldRequest =
          new BillPaymentTransactionOnHoldRequest();
      billPaymentTransactionOnHoldRequest.setTxnId(txId);
      if (provider != null && !provider.isEmpty())
        billPaymentTransactionOnHoldRequest.setProvider(provider);
      if (noteContent != null && !noteContent.isEmpty())
        billPaymentTransactionOnHoldRequest.setNote(noteContent);

      BillPaymentTransactionOnHoldResponse res =
          transactionEndpoint.getBillPaymentTransactionOnHold(billPaymentTransactionOnHoldRequest);
      if (res.getStatus().getCode() != MessageNotify.SUCCESS_CODE)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);

    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
    }
    return ResponseEntity.ok(null);
  }

  @RequestMapping(
      value = "/get-phone-topup-transaction-on-hold-by-batch",
      method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> topupPhoneByBatch(HttpServletRequest request) throws Exception {
    PhoneTopupTransactionOnHoldByBatchResponse res = null;
    try {
      String[] tracenoIds = request.getParameterValues("tracenoIds[]");
      String provider = request.getParameter("provider");
      String noteContent = StringUtils.trimToEmpty(request.getParameter("noteContent"));

      PhoneTopupTransactionOnHoldByBatchRequest phoneTopupTransactionOnHoldByBatchRequest =
          new PhoneTopupTransactionOnHoldByBatchRequest();
      if (tracenoIds != null && tracenoIds.length > 0)
        phoneTopupTransactionOnHoldByBatchRequest.setTxnRefIds(Arrays.asList(tracenoIds));
      if (provider != null && !provider.isEmpty())
        phoneTopupTransactionOnHoldByBatchRequest.setProvider(provider);
      if (noteContent != null && !noteContent.isEmpty())
        phoneTopupTransactionOnHoldByBatchRequest.setNote(noteContent);

      res =
          transactionEndpoint.getPhoneTopupTransactionOnHoldBatch(
              phoneTopupTransactionOnHoldByBatchRequest);
      if (res.getStatus().getCode() != MessageNotify.SUCCESS_CODE)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
    }
    return ResponseEntity.ok(res);
  }

  @RequestMapping(
      value = "/get-bill-payment-transaction-on-hold-by-batch",
      method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> billPaymentByBatch(HttpServletRequest request) throws Exception {
    BillPaymentTransactionOnHoldByBatchResponse res = null;
    try {
      String[] tracenoIds = request.getParameterValues("tracenoIds[]");
      String provider = request.getParameter("provider");
      String noteContent = StringUtils.trimToEmpty(request.getParameter("noteContent"));

      BillPaymentTransactionOnHoldByBatchRequest billPaymentTransactionOnHoldByBatchRequest =
          new BillPaymentTransactionOnHoldByBatchRequest();
      if (tracenoIds != null && tracenoIds.length > 0)
        billPaymentTransactionOnHoldByBatchRequest.setTxnRefIds(Arrays.asList(tracenoIds));
      if (provider != null && !provider.isEmpty())
        billPaymentTransactionOnHoldByBatchRequest.setProvider(provider);
      if (noteContent != null && !noteContent.isEmpty())
        billPaymentTransactionOnHoldByBatchRequest.setNote(noteContent);

      res =
          transactionEndpoint.getBillPaymentTransactionOnHoldBatch(billPaymentTransactionOnHoldByBatchRequest);
      if (res.getStatus().getCode() != MessageNotify.SUCCESS_CODE)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
    }
    return ResponseEntity.ok(res);
  }
}
