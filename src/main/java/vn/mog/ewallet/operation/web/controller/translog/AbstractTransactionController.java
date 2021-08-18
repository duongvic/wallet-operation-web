package vn.mog.ewallet.operation.web.controller.translog;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.ModelMap;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.contract.FindCommonTransactionRequestModel;
import vn.mog.ewallet.operation.web.contract.form.FundInOutForm;
import vn.mog.ewallet.operation.web.contract.form.SearchDataForm;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.beans.QuickSearchType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.FindUmgrCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.FindUmgrCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ProviderCode;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.SourceOfFundType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.ExportTransactionLogRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.ExportTransactionLogResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.FindTransactionsRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.FindTransactionsRequestType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.FindTransactionsResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.GetTransactionRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.GetTransactionResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.*;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Customer;
import vn.mog.ewallet.operation.web.utils.WebUtil;
import vn.mog.framework.common.utils.NumberUtil;
import vn.mog.framework.contract.base.KeyValue;

import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.*;

public class AbstractTransactionController extends AbstractController {

  private static final Logger LOG = LogManager.getLogger(AbstractTransactionController.class);

  public static final Map<String, Integer> USER_CUSTOMER_TYPES = new LinkedHashMap<>();

  static {
    USER_CUSTOMER_TYPES.put("CUSTOMER", ID_CUSTOMER);
    USER_CUSTOMER_TYPES.put("MERCHANT", ID_MERCHANT);
    USER_CUSTOMER_TYPES.put("PROVIDER", ID_PROVIDER);
    USER_CUSTOMER_TYPES.put("AGENT", ID_AGENT);
    USER_CUSTOMER_TYPES.put("PROPERTY_MANAGER", ID_PROPERTY_MANAGER);
  }

  @SuppressWarnings("unchecked")
  protected void searchFundoutTrans(
      HttpServletRequest request, ModelMap map, FundInOutForm fundInOutForm) {
    // Search box
    String paramText = StringUtils.trimToEmpty(request.getParameter("search"));
    String[] paramTxnStatusIds = request.getParameterValues("txnStatusIds");
    String paramServiceTypeId = StringUtils.trimToEmpty(fundInOutForm.getType());
    String[] paramSourceOfFund = request.getParameterValues("sourceOfFundTypeIds");
    String[] paramIdOwnerCustomer = request.getParameterValues("idOwnerCustomerTypes");
    String paramDateRange = StringUtils.trimToEmpty(fundInOutForm.getRange());
    String[] paramCustomerIds = request.getParameterValues("customerIds");

    Date[] dates = parseDateRange(paramDateRange, false);
    Date fromDate = dates[0];
    Date endDate = dates[1];

    int limit = 20;
    int offset = 0;
    if (StringUtils.isNumeric(request.getParameter("d-149386-p"))) {
      Integer p = Integer.parseInt(request.getParameter("d-149386-p"));
      offset = limit * (p - 1);
    }
    FindTransactionsRequest ftRequest = new FindTransactionsRequest();

    List<Long> customerIds = null;
    List customerList = null;
    if (paramCustomerIds != null) {
      customerIds = NumberUtil.convertListToLong(paramCustomerIds);
    }
    List<String> roleList = getRolesOfCaller();
    customerList = getListSaleByParent(roleList);

    // If Caller is Sale, show all Caller's agents' transactions
    // Get list Agents' Id to show Transaction
    // Sale doesn't have own wallet, so no need to show Sale's transactions
    if (!roleList.contains(RoleConstants.ADMIN_OPERATION)
        && (customerIds == null || customerIds.isEmpty())
        && customerList != null) {
      customerIds = new ArrayList<>();
      try {
        for (Customer customer : (List<Customer>) customerList) {
          customerIds.add(customer.getId());
        }
      } catch (ClassCastException e) {
        customerIds = null;
      }
    }

    if (customerIds != null) {
      customerIds.remove(null);
    }

    ftRequest.setCustomerIds(customerIds);

    List<Integer> txnStatusIds = null;
    if (paramTxnStatusIds != null
        && paramTxnStatusIds.length > 0
        && StringUtils.isNotEmpty(paramTxnStatusIds[0])) {
      txnStatusIds = NumberUtil.convertListToInt(paramTxnStatusIds);
    }

    List<String> sourceOfFundTypes = null;
    if (paramSourceOfFund != null
        && paramSourceOfFund.length > 0
        && StringUtils.isNotEmpty(paramSourceOfFund[0])) {
      sourceOfFundTypes = Arrays.asList(paramSourceOfFund);
    }
    List<String> idOwnerCustomerTypes = null;
    if (paramIdOwnerCustomer != null && paramIdOwnerCustomer.length > 0) {
      idOwnerCustomerTypes = new ArrayList<>();
      idOwnerCustomerTypes.addAll(Arrays.asList(paramIdOwnerCustomer));
      idOwnerCustomerTypes.remove(null);
      idOwnerCustomerTypes.remove(StringUtils.EMPTY);
    }

    ftRequest.setTextSearch(paramText);
    ftRequest.setStatusIds(txnStatusIds);
    ftRequest.setServiceTypeIds(Collections.singletonList(ServiceType.FUND_OUT.name()));
    ftRequest.setSourceOfFundTypeIds(sourceOfFundTypes);
    ftRequest.setIdOwnerCustomerTypes(idOwnerCustomerTypes);
    ftRequest.setFromDate(fromDate);
    ftRequest.setEndDate(endDate);
    ftRequest.setOffset(offset);
    ftRequest.setLimit(limit);

    FindTransactionsResponse ftResponse = transactionEndpoint.findTransactions(ftRequest);

    Collection<Transaction> transactions = null;
    Long total = 0L;
    Long realAmount = 0L;
    Long commision = 0L;
    if (ftResponse != null) {
      transactions = ftResponse.getTransactions();
      total = ftResponse.getTotalTxn();
      realAmount = ftResponse.getTotalNetAmount();
      commision = ftResponse.getTotalCommision();
    }

    map.put("realAmount", realAmount);
    map.put("commision", commision);
    map.put("list", transactions);
    map.put("pagesize", limit);
    map.put("offset", offset);
    map.put("total", total.intValue());
    map.put("realAmount", realAmount);
    map.put("commision", commision);
    map.put("customers", customerList);
    map.put("txnStatuses", TxnStatus.TRANSACTION_STATUSES);
  }

  protected void searchFundinTrans(
      HttpServletRequest request, ModelMap map, FundInOutForm fundInOutForm) {
    // Search box
    String paramQuickSearch = StringUtils.trimToEmpty(request.getParameter("search"));
    if (StringUtils.isBlank(paramQuickSearch)) {
      paramQuickSearch = null;
    }
    String[] paramTxnStatusIds = request.getParameterValues("txnStatusIds");
    String[] paramCustomerIds = request.getParameterValues("customerIds");
    String[] paramSourceOfFund = request.getParameterValues("sourceOfFundTypeIds");
    String[] paramIdOwnerCustomer = request.getParameterValues("idOwnerCustomerTypes");
    String[] channelIds = request.getParameterValues("channels");
    String paramDateRange = StringUtils.trimToEmpty(fundInOutForm.getRange());

    Date[] dates = parseDateRange(paramDateRange, false);
    Date fromDate = dates[0];
    Date endDate = dates[1];

    FindTransactionsRequest ffRequest = new FindTransactionsRequest();

    List<Long> customerIds = null;
    List customerList = null;
    if (paramCustomerIds != null) {
      customerIds = NumberUtil.convertListToLongRemoveBlank(paramCustomerIds);
    }
    List<String> roleList = getRolesOfCaller();
    customerList = getListSaleByParent(roleList);

    // If Caller is Sale, show all Caller's agents' transactions
    // Get list Agents' Id to show Transaction
    // Sale doesn't have own wallet, so no need to show Sale's transactions
    if (!roleList.contains(RoleConstants.ADMIN_OPERATION)
        && (customerIds == null || customerIds.isEmpty())
        && customerList != null) {
      customerIds = new ArrayList<>();
      try {
        for (Customer customer : (List<Customer>) customerList) {
          customerIds.add(customer.getId());
        }
      } catch (ClassCastException e) {
        customerIds = null;
      }
    }

    if (customerIds != null) {
      customerIds.remove(null);
    }

    ffRequest.setCustomerIds(customerIds);

    List<Integer> txnStatusIds = null;
    if (paramTxnStatusIds != null
        && paramTxnStatusIds.length > 0
        && StringUtils.isNotEmpty(paramTxnStatusIds[0])) {
      txnStatusIds = NumberUtil.convertListToInt(paramTxnStatusIds);
    }

    List<String> sourceOfFundTypes = null;
    if (paramSourceOfFund != null
        && paramSourceOfFund.length > 0
        && StringUtils.isNotEmpty(paramSourceOfFund[0])) {
      sourceOfFundTypes = Arrays.asList(paramSourceOfFund);
    }
    List<String> idOwnerCustomerTypes = null;
    if (paramIdOwnerCustomer != null && paramIdOwnerCustomer.length > 0) {
      idOwnerCustomerTypes = new ArrayList<>();
      idOwnerCustomerTypes.addAll(Arrays.asList(paramIdOwnerCustomer));
      idOwnerCustomerTypes.remove(null);
      idOwnerCustomerTypes.remove(StringUtils.EMPTY);
    }

    List<String> channelIdList = null;
    if (channelIds != null
            && channelIds.length > 0
            && StringUtils.isNotEmpty(channelIds[0])) {
      channelIdList = Arrays.asList(channelIds);
      ffRequest.setPaymentChannelIds(channelIdList);
    }

    int limit = 20;
    int offset = 0;
    if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
      Integer p = Integer.parseInt(request.getParameter("d-49489-p"));
      offset = limit * (p - 1);
    }

    ffRequest.setTextSearch(paramQuickSearch);
    ffRequest.setStatusIds(txnStatusIds);
    ffRequest.setServiceTypeIds(Collections.singletonList(ServiceType.FUND_IN.name()));
    ffRequest.setSourceOfFundTypeIds(sourceOfFundTypes);
    ffRequest.setIdOwnerCustomerTypes(idOwnerCustomerTypes);
    ffRequest.setFromDate(fromDate);
    ffRequest.setEndDate(endDate);
    ffRequest.setOffset(offset);
    ffRequest.setLimit(limit);

    FindTransactionsResponse ffResponse = transactionEndpoint.findTransactions(ffRequest);

    Collection<Transaction> transactions;
    Long total = 0L;
    Long realAmount = 0L;
    Long commision = 0L;
    if (ffResponse != null) {
      transactions = ffResponse.getTransactions();
      total = ffResponse.getTotalTxn();
      realAmount = ffResponse.getTotalNetAmount();
      commision = ffResponse.getTotalCommision();
    } else {
      transactions = new ArrayList<>();
    }

    map.put("list", transactions);
    map.put("pagesize", limit);
    map.put("offset", offset);
    map.put("total", total.intValue());
    map.put("realAmount", realAmount);
    map.put("commision", commision);
    map.put("sourceOfFundTypes", SourceOfFundType.SOF_ID_TYPES);
    map.put("customers", customerList);
    map.put("txnStatuses", TxnStatus.TRANSACTION_STATUSES);
  }

  @SuppressWarnings("unchecked")
  protected FindCommonTransactionRequestModel buildSearchCommonTransRequest(
      HttpServletRequest request, SearchDataForm searchDataForm) {

    FindCommonTransactionRequestModel requestModel = null;
    // Search box
    String paramText = searchDataForm.getQuickSearch();

    String[] paramQuickSearchType = request.getParameterValues("textSearchTypes");
    List<String> textSearchTypeList = new ArrayList<>();
    if (paramQuickSearchType != null
        && paramQuickSearchType.length > 0
        && !StringUtils.EMPTY.equals(paramQuickSearchType[0])) {
      for (String s : paramQuickSearchType) {
        textSearchTypeList.add((s));
      }
    }

    String searchDateRange = searchDataForm.getRange();

    Date[] dates = parseDateRange(searchDateRange, false);
    Date fromDate = dates[0];
    Date endDate = dates[1];

    int offset = 0;
    int limit = 20;

    if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
      offset = Integer.parseInt(request.getParameter("d-49489-p"));
      if (offset > 0) {
        offset = (offset - 1) * limit;
      }
    }

    List<String> providerCodes = searchDataForm.getProvider();
    List<String> sourceOfFundTypes = searchDataForm.getSourceOfFundTypeIds();
    List<String> orderChannelIds = searchDataForm.getOrderChannelIds();
    List<Long> customerIds = searchDataForm.getCustomerIds();
    List<String> serviceCodes =
        searchDataForm.getServiceTypeIds() == null
            ? new ArrayList<>(ServiceType.PAYMENT_TYPES.keySet())
            : searchDataForm.getServiceTypeIds();
    List<Long> idOwners = searchDataForm.getIdOwners();
    List<String> idOwnerCustomerTypes = searchDataForm.getIdOwnerCustomerTypes();
    List<Integer> txnStatusIds = searchDataForm.getTxnStatusIds();

    if (providerCodes != null && !providerCodes.isEmpty()) {
      providerCodes.remove(null);
      providerCodes.remove(StringUtils.EMPTY);
    }
    if (sourceOfFundTypes != null && !sourceOfFundTypes.isEmpty()) {
      sourceOfFundTypes.remove(null);
      sourceOfFundTypes.remove(StringUtils.EMPTY);
    }
    if (orderChannelIds != null && !orderChannelIds.isEmpty()) {
      orderChannelIds.remove(null);
      orderChannelIds.remove(StringUtils.EMPTY);
    }
    if (customerIds != null && !customerIds.isEmpty()) {
      customerIds.remove(null);
    }
    if (serviceCodes != null && !serviceCodes.isEmpty()) {
      serviceCodes.remove(null);
      serviceCodes.remove(StringUtils.EMPTY);
    }
    if (idOwners != null && !idOwners.isEmpty()) {
      idOwners.remove(null);
    }
    if (idOwnerCustomerTypes != null && !idOwnerCustomerTypes.isEmpty()) {
      idOwnerCustomerTypes.remove(null);
      idOwnerCustomerTypes.remove(StringUtils.EMPTY);
    }
    if (txnStatusIds != null && !txnStatusIds.isEmpty()) {
      txnStatusIds.remove(null);
    }

    List<Customer> customerList;
    try {
      long time01 = new Date().getTime();

      List<String> rolesOfCaller = getRolesOfCaller();
      boolean isAdmin = rolesOfCaller.contains(RoleConstants.ADMIN_OPERATION);
      if (isAdmin && idOwnerCustomerTypes != null && !idOwnerCustomerTypes.isEmpty()) {
        FindUmgrCustomerRequest findCustomerRequest = new FindUmgrCustomerRequest();
        List<Integer> customerTypeStrings = new ArrayList<>();
        for (String customerTypeString : idOwnerCustomerTypes) {
          customerTypeStrings.add(USER_CUSTOMER_TYPES.get(customerTypeString.toUpperCase()));
        }

        findCustomerRequest.setCustomerTypes(customerTypeStrings);
        FindUmgrCustomerResponse findUmgrCustomerResponse =
            umgrRolesPrivilegesEndpoint.findUmgrCustomers(findCustomerRequest);

        if (findUmgrCustomerResponse != null
            && findUmgrCustomerResponse.getStatus().getCode() == 0) {
          customerList = findUmgrCustomerResponse.getCustomers();

        } else {
          customerList = new ArrayList<>();
        }
      } else {
        customerList = getListSaleByParent(rolesOfCaller);
      }

      long time02 = new Date().getTime() - time01;

      LOG.info("loginTime:", (time02));
      System.out.println("get-Customer-Time:" + time02);

      // If Caller is Sale, show all Caller's agents' transactions
      // Get list Agents' Id to show Transaction
      // Sale doesn't have own wallet, so no need to show Sale's transactions
      if (!isAdmin && (customerIds == null || customerIds.isEmpty()) && customerList != null) {
        customerIds = new ArrayList<>();
        try {
          for (Customer customer : (List<Customer>) customerList) {
            customerIds.add(customer.getId());
          }
        } catch (ClassCastException e) {
          customerIds = null;
        }
      }

      if (customerIds != null) {
        customerIds.remove(null);
      }

      /* Check Sale account */
      if (isSale(rolesOfCaller) && (customerIds == null || customerIds.isEmpty())) {
        customerIds = Arrays.asList(callerUtil.getCallerId());
      }

      FindTransactionsRequestType findTransactionsRequestType = new FindTransactionsRequestType();
      findTransactionsRequestType.setCustomerIds(customerIds);
      findTransactionsRequestType.setProviderCodes(providerCodes);
      findTransactionsRequestType.setSourceOfFundTypeIds(sourceOfFundTypes);
      findTransactionsRequestType.setOrderChannelIds(orderChannelIds);
      findTransactionsRequestType.setTextSearch(paramText);
      findTransactionsRequestType.setStatusIds(txnStatusIds);
      findTransactionsRequestType.setServiceTypeIds(serviceCodes);
      findTransactionsRequestType.setServiceCodes(serviceCodes);
      findTransactionsRequestType.setIdOwners(idOwners);
      findTransactionsRequestType.setIdOwnerCustomerTypes(idOwnerCustomerTypes);
      findTransactionsRequestType.setFromDate(fromDate);
      findTransactionsRequestType.setEndDate(endDate);
      findTransactionsRequestType.setOffset(offset);
      findTransactionsRequestType.setLimit(limit);
      findTransactionsRequestType.setTextSearchTypes(textSearchTypeList);

      requestModel = new FindCommonTransactionRequestModel();

      requestModel.setRequestType(findTransactionsRequestType);

      int cusSize = customerList != null ? customerList.size() : 0;
      if (customerList != null) if (customerList.size() < cusSize) cusSize = customerList.size();

      List<Customer> cusResp = new ArrayList<>();
      for (int i = 0; i < cusSize; i++) {
        cusResp.add(customerList.get(i));
      }

      requestModel.setCustomerList(cusResp);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }

    return requestModel;
  }

  protected void searchCommonTrans(
      HttpServletRequest request,
      HttpServletResponse response,
      ModelMap model,
      SearchDataForm searchDataForm) {
    // Search box
    String paramText = searchDataForm.getQuickSearch();

    List<String> paramQuickSearchType = searchDataForm.getTextSearchTypes();

    //    String[] paramQuickSearchType = request.getParameterValues("textSearchTypes");

    int offset = 0;
    int limit = 20;

    if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
      offset = Integer.parseInt(request.getParameter("d-49489-p"));
      if (offset > 0) {
        offset = (offset - 1) * limit;
      }
    }

    List<String> serviceCodes =
        searchDataForm.getServiceTypeIds() == null
            ? new ArrayList<>(ServiceType.PAYMENT_TYPES.keySet())
            : searchDataForm.getServiceTypeIds();

    //    List<TransactionTextSearchType> transactionTextSearchTypes =
    // searchDataForm.getTextSearchTypes() == null ?
    //        new ArrayList<>(Arrays.asList(TransactionTextSearchType.ID_TXN,
    // TransactionTextSearchType.STR_ORDER_ID,
    //            TransactionTextSearchType.STR_ORDER_INFO, TransactionTextSearchType.STR_TRACE_NO))
    // : searchDataForm.getTextSearchTypes();

    FindTransactionsResponse ftResponse = null;
    try {
      long time01 = new Date().getTime();
      FindCommonTransactionRequestModel requestModel =
          buildSearchCommonTransRequest(request, searchDataForm);
      long time02 = new Date().getTime();
      System.out.println("================Take time to build request:" + (time02 - time01));
      FindTransactionsRequest tfRequest = new FindTransactionsRequest();
      FindTransactionsRequestType requestType = requestModel.getRequestType();
      String paymentChannelId = request.getParameter("paymentChannelId");
      requestType.setPaymentChannelIds(
          StringUtils.isNotBlank(paymentChannelId)
              ? Collections.singletonList(paymentChannelId)
              : null);
      BeanUtils.copyProperties(tfRequest, requestType);
      ftResponse = transactionEndpoint.findTransactions(tfRequest);
      long time03 = new Date().getTime();
      System.out.println("=================Take time to call backend:" + (time03 - time02));
      model.put("customers", requestModel.getCustomerList());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }

    Collection<Transaction> transactions = null;
    Long total = 0L;
    Long realAmount = 0L;
    Long roseAmount = 0L;
    Long commision = 0L;
    Long capitalValue = 0L;
    Long cardQuantity = 0L;
    Long totalRequestAmount = 0L;
    Long totalGrossProfit = 0L;
    Long totalCashBack = 0L;
    Double percentGrossProfit = 0D;
    Double percentGrossProfitAfterCashBackNoVAT = 0D;
    Long afterCashBackAmountNoVAT = 0L;
    Long totalFee = 0L;

    if (ftResponse != null) {
      transactions = ftResponse.getTransactions();
      total = ftResponse.getTotalTxn() != null ? ftResponse.getTotalTxn() : 0L;
      realAmount = ftResponse.getTotalNetAmount() != null ? ftResponse.getTotalNetAmount() : 0L;
      commision = ftResponse.getTotalCommision() != null ? ftResponse.getTotalCommision() : 0L;
      totalFee = ftResponse.getTotalFee() != null ? ftResponse.getTotalFee() : 0L;
      capitalValue =
          ftResponse.getTotalCapitalValue() != null ? ftResponse.getTotalCapitalValue() : 0L;
      totalRequestAmount =
          ftResponse.getTotalRequestAmount() != null ? ftResponse.getTotalRequestAmount() : 0L;
      totalGrossProfit =
          ftResponse.getTotalGrossProfit() != null ? ftResponse.getTotalGrossProfit() : 0L;
      totalCashBack = ftResponse.getTotalCashBack() != null ? ftResponse.getTotalCashBack() : 0L;
      BigDecimal afterCashBackAmountHasVAT = new BigDecimal((totalGrossProfit - totalCashBack));
      afterCashBackAmountNoVAT =
          new BigDecimal(afterCashBackAmountHasVAT.longValue() / 1.1).longValue();
      roseAmount = new BigDecimal(afterCashBackAmountNoVAT.longValue() * 0.4).longValue();
      if (totalRequestAmount > 0) {
        percentGrossProfit =
            new BigDecimal(totalGrossProfit)
                .divide(new BigDecimal(totalRequestAmount), 4, RoundingMode.HALF_EVEN)
                .multiply(new BigDecimal(100))
                .doubleValue();
        percentGrossProfitAfterCashBackNoVAT =
            new BigDecimal(afterCashBackAmountNoVAT)
                .divide(new BigDecimal(totalRequestAmount), 4, RoundingMode.HALF_EVEN)
                .multiply(new BigDecimal(100))
                .doubleValue();
      }
    }
    model.put("transactions", transactions);
    model.put("total", total.intValue());
    model.put("offset", offset);
    model.put("pagesize", limit);
    model.put("quickSearch", paramText);
    model.put(
        "textSearchTypes",
        paramQuickSearchType != null ? paramQuickSearchType.get(0) : Collections.emptyList());

    model.put("sourceOfFundTypeIds", request.getParameter("sourceOfFundTypeIds"));
    model.put("totalCapitalValue", capitalValue);
    model.put("totalRequestAmount", totalRequestAmount);
    model.put("totalGrossProfit", totalGrossProfit);
    model.put("totalCashBack", totalCashBack);
    model.put("percentGrossProfit", percentGrossProfit);
    model.put("percentGrossProfitAfterCashBack", percentGrossProfitAfterCashBackNoVAT);
    model.put("realAmount", realAmount);
    model.put("commision", commision);
    model.put("totalFee", totalFee);
    model.put("cardQuantity", cardQuantity);
    model.put("afterCashBackAmount", afterCashBackAmountNoVAT.longValue());
    model.put("roseAmount", roseAmount.longValue());

    model.put("serviceTypeIds", serviceCodes);

    model.put("serviceTypes", ServiceType.PAYMENT_TYPES);
    model.put("txnStatuses", TxnStatus.TRANSACTION_STATUSES);
    model.put("providerTypes", ProviderCode.PAYMENT_PROVIDER_CODES);
    model.put("sourceOfFundTypes", SourceOfFundType.SOF_ID_TYPES);
    model.put("listTransactionTextSearchType", TransactionTextSearchType.values());

    List<
            vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans
                .Customer>
        customers = new ArrayList<>();
    if (searchDataForm.getCustomerIds() != null) {
      for (Long id : searchDataForm.getCustomerIds()) {
        GetCustomerRequest requestCus = new GetCustomerRequest();
        requestCus.setCustomerId(id);
        GetCustomerResponse responseCus = walletCustomerEndpoint.getCustomer(requestCus);
        customers.add(responseCus.getCustomer());
      }
    }
    model.put("customers", customers);
  }

  protected void detailCommonTrans(HttpServletRequest request, ModelMap model) {
    Long txnId = NumberUtil.convertToLong(request.getParameter("txnId"));
    String traceNo = (request.getParameter("traceNo"));

    String quickSearch =
        StringUtils.trimToEmpty(request.getParameter("quickSearch")).replaceAll("\\s+", " ");
    String[] customerIds = request.getParameterValues("customerIds");
    String[] txnStatusIds = request.getParameterValues("txnStatusIds");
    String searchType = StringUtils.trimToEmpty(request.getParameter("type"));
    String[] searchService = request.getParameterValues("service");
    String[] providers = request.getParameterValues("provider");
    String dateRange = StringUtils.trimToEmpty(request.getParameter("range"));
    String page = StringUtils.trimToEmpty(request.getParameter("d-49489-p"));

    GetTransactionResponse gtxnResponse;
    if (traceNo != null) {
      GetTransactionRequest gtxnRequest = new GetTransactionRequest(traceNo);
      gtxnResponse = transactionEndpoint.getTransaction(gtxnRequest);

    } else {
      GetTransactionRequest getRequest = new GetTransactionRequest(txnId);
      gtxnResponse = transactionEndpoint.getTransaction(getRequest);
    }

    String customerMsisdsn = null;
    if (gtxnResponse != null && gtxnResponse.getUnstructuredData() != null) {
      List<KeyValue> keyValuelist = gtxnResponse.getUnstructuredData();
      for (KeyValue keyValue : keyValuelist) {
        if (UnstructuredDataKey.BILL_PAYMENT_CUSTOMER_PHONE.name().equals(keyValue.getKey())) {
          customerMsisdsn = keyValue.getValue();
        }
      }
    }
    Long externalFee = 0L;
    Long fee = 0L;
    if (gtxnResponse != null
        && gtxnResponse.getTransaction() != null
        && gtxnResponse.getTransaction().getAttributes() != null) {
      fee = gtxnResponse.getTransaction().getFee();
      for (TransactionAttribute item : gtxnResponse.getTransaction().getAttributes()) {
        if (item.getTransactionAttributeType().equalsIgnoreCase("EXTERNAL_FEE")) {
          if (StringUtils.isNotBlank(item.getTransactionAttributeValue()))
            externalFee = Long.valueOf(item.getTransactionAttributeValue());
          fee = fee - externalFee;
          break;
        }
      }
    }
    model.put("externalFee", externalFee);
    model.put("fee", fee);
    model.put("customerMsisdsn", customerMsisdsn);
    model.put("transaction", gtxnResponse.getTransaction());
    model.put("transactionEvents", gtxnResponse.getTransactionEvents());

    model.put("quickSearch", quickSearch);
    model.put("customerIds", customerIds);

    model.put("txnStatusIds", txnStatusIds);
    model.put("type", searchType);
    model.put("service", searchService);
    model.put("provider", providers);
    model.put("range", dateRange);
    model.put("numberPage", page);
  }

  protected void export(
      HttpServletRequest request, HttpServletResponse response, SearchDataForm searchDataForm)
      throws Exception {
    FindCommonTransactionRequestModel requestModel =
        buildSearchCommonTransRequest(request, searchDataForm);

    ExportTransactionLogRequest etlRequest = new ExportTransactionLogRequest();
    FindTransactionsRequestType requestType = requestModel.getRequestType();
    BeanUtils.copyProperties(etlRequest, requestType);
    etlRequest.setOffset(null);
    etlRequest.setLimit(null);

    ExportTransactionLogResponse etlResponse = transactionEndpoint.exportTransaction(etlRequest);

    LogFile logFile = etlResponse.getLogFile();

    WebUtil.exportFile(response, logFile);
  }
}
