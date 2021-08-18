/*
package vn.mog.ewallet.operation.web.controller.balance;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.mog.ewallet.operation.web.common.RoleConstants;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.controller.balance.contract.BalanceBank;
import vn.mog.ewallet.operation.web.controller.balance.contract.BalanceCustomer;
import vn.mog.ewallet.operation.web.controller.balance.contract.BalanceFA;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.GetTransactionRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.GetTransactionResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.StaticBalanceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.StaticBalanceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.Balance;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.Customer;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.StatsBalance;
import NumberUtil;
import StringUtil;


@Controller
@RequestMapping(value = "/wallet/balance")
public class BalanceMonitoringController extends AbstractController {

  public static final String BALANCE_CONTROLLER = "/wallet/balance";
  public static final String BALANCE_CUSTOMER_LIST = BALANCE_CONTROLLER + "/balance-customer";
  public static final String BALANCE_MONITORING = BALANCE_CONTROLLER + "/balance-monitoring";
  public static final String BALANCE_ZOTA = "ZOTA";
  public static final String BALANCE_SOF = "SOF";
  public static final String BALANCE_MERCHANT = "MERCHANT";
  public static final String BALANCE_CUSTOMER = "CUSTOMER";
  public static final String BALANCE_AGENT = "AGENT";
  public static final String BALANCE_SALE = "SALE";
  public static final String BALANCE_PROVIDER = "PROVIDER";
  public static final String BALANCE_AF = "FA";

  public static final String BALANCE_POOL = "POOL";
  private static final Logger LOG = LogManager.getLogger(BalanceMonitoringController.class);

  @RequestMapping(value = "/balance-monitoring", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('ADMIN_OPERATION','FINANCE','ACCOUNTING')")
  public String balanceMonitoring(HttpServletRequest request, ModelMap map)
      throws FrontEndException {
    try {
      StaticBalanceRequest sbRequest = new StaticBalanceRequest();
      //StaticBalanceResponse sbResponse = walletEndpoint.statsBalance(sbRequest);
      Long totalInitiateBalance = 0L;

      // pie chart
      Map<String, StatsBalance> stateBalanceMap = sbResponse.getStatsBalances();
      Map<String, Long> arrayOfSOF = new HashMap<>();
      for (Entry<String, StatsBalance> entry : stateBalanceMap.entrySet()) {
        if (entry.getValue() != null && entry.getValue().getBalance() != null
            && entry.getValue().getBalance().getCurrBalance() != null) {
          map.put(entry.getKey(), entry.getValue().getBalance().getCurrBalance());
          if (!entry.getKey().equals("FA")) {
            totalInitiateBalance += entry.getValue().getBalance().getCurrBalance();
          }
        } else {
          map.put(entry.getKey(), 0L);
        }
        if (BALANCE_SOF.equals(entry.getKey())) {
          List<Customer> customers = entry.getValue().getCustomers();
          for (Customer item : customers) {
            String displayName = item.getFullName();
        */
/*
 * if (displayName.contains("Account TM SOF")) {
 * arrayOfSOF.put(displayName.replace("Account TM SOF",
 * StringUtils.EMPTY), item.getBalance().getCurrBalance());
 *
 * } else if (displayName.contains("TM SOF")) { String
 * replace = displayName.replace("TM SOF",
 * StringUtils.EMPTY);
 * arrayOfSOF.put(replace.replace("Account",
 * StringUtils.EMPTY), item.getBalance().getCurrBalance());
 *//*


 */
/*fix sau*//*

//          if (displayName.contains("Account") || displayName.contains("account")) {
//            arrayOfSOF.put(displayName.replace("Account", StringUtils.EMPTY),
//                item.getBalance().getCurrBalance());
//          } else {
//            arrayOfSOF.put(displayName, item.getBalance().getCurrBalance());
//          }

          }
        }
      }

      map.put("arrayOfSOF", arrayOfSOF);

      // table chart
      List<BalanceBank> balanceBanks = new ArrayList<>();
      List<BalanceCustomer> balanceCustomers = new ArrayList<>();
      List<BalanceFA> balanceFAs = new ArrayList<>();

      String key;
      List<Customer> valueAccounts;
      Balance valAccount;
      Long SOF_Amount;

      Map<String, StatsBalance> mapStateBalances = sbResponse.getStatsBalances();
      for (Entry<String, StatsBalance> mapBalance : mapStateBalances.entrySet()) {
        key = mapBalance.getKey();
        switch (key) {
          case BALANCE_POOL:
            valAccount = mapBalance.getValue().getBalance();
            balanceBanks.add(new BalanceBank(mapBalance.getKey(), valAccount.getCurrBalance()));

            break;
          case BALANCE_AF:
            valueAccounts = mapBalance.getValue().getCustomers();
            for (Customer mapAccount : valueAccounts) {
//            SOF_Amount = mapAccount.getBalance().getCurrBalance();
              String replace;
              if (mapAccount.getFullName().contains("TM SOF")) {
                replace = mapAccount.getFullName().replace("TM SOF", StringUtils.EMPTY);
              } else {
                replace = mapAccount.getFullName();
              }

//            balanceFAs.add(new BalanceFA(replace, SOF_Amount));
            }

            break;
          case BALANCE_MERCHANT:
          case BALANCE_CUSTOMER:
          case BALANCE_AGENT:
          case BALANCE_SALE:
          case BALANCE_ZOTA:
          case BALANCE_PROVIDER:
            valAccount = mapBalance.getValue().getBalance();
            balanceCustomers
                .add(new BalanceCustomer(mapBalance.getKey(), valAccount.getCurrBalance()));
            break;
          default:
            break;
        }
      }

      List<Integer> numbers = Arrays
          .asList(balanceBanks.size(), balanceCustomers.size(), balanceFAs.size());
      Integer max = Collections.max(numbers);

      Long totalFAMoney = sbResponse.getVerifyBalance().getFaBalance().getCurrBalance();
      Long totalCustomerMoney = sbResponse.getVerifyBalance().getWalletBalance().getCurrBalance();

      map.put("totalFAMoney", NumberUtil.formatNumber(totalFAMoney));
      map.put("totalCustomerMoney", NumberUtil.formatNumber(totalCustomerMoney));
      map.put("totalInitiateBalance", NumberUtil.formatNumber(totalInitiateBalance));
      map.put("maxRow", max);
      map.put("sizeBanks", balanceBanks.size());
      map.put("sizeCustomers", balanceCustomers.size());
      map.put("sizeFAs", balanceFAs.size());
      map.put("balanceBanks", balanceBanks);
      map.put("balanceCustomers", balanceCustomers);
      map.put("balanceFAs", balanceFAs);

    } catch (Exception e) {
      LOG.error(e.getMessage());
    }

    return "/balance/balance_monitoring";
  }

  @RequestMapping(value = "/balance-customer", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('ADMIN_OPERATION','FINANCE','ACCOUNTING')")
  public String balanceAccount(HttpServletRequest request, ModelMap map) throws FrontEndException {
    try {
      // search
      String paramQuickSearch = StringUtils.trimToEmpty(request.getParameter("search"));
      String paramDateRange = StringUtils.trimToEmpty(request.getParameter("range"));
      String[] paramCustomerTypeIds = request.getParameterValues("customerTypeIds");
      String[] paramStatusIds = request.getParameterValues("status");
      String[] paramUserTypeIds = request.getParameterValues("userTypeIds");
      String[] paramWalletTypeIds = request.getParameterValues("walletTypeIds");

      FindCustomerRequest fbcRequest = new FindCustomerRequest();
      if (StringUtils.isNotBlank(paramDateRange)) {
        Date[] dates = parseDateRange(paramDateRange);
        if (dates[0] != null && dates[1] != null && dates[0].compareTo(dates[1]) != 0) {
          fbcRequest.setFromDate(dates[0]);
          fbcRequest.setToDate(dates[1]);
        }
      }

      int offset = 0;
      int limit = 20;

      if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
        offset = Integer.parseInt(request.getParameter("d-49489-p"));
        if (offset > 0) {
          offset = (offset - 1) * limit;
        }
      }

      fbcRequest.setOffset(offset);
      fbcRequest.setLimit(limit);

      if (StringUtils.isNotEmpty(paramQuickSearch)) {
        fbcRequest.setQuickSearch(paramQuickSearch);
      }

      if (paramCustomerTypeIds != null && paramCustomerTypeIds.length > 0 && StringUtils
          .isNotEmpty(paramCustomerTypeIds[0])) {
        fbcRequest.setCustomerTypeIds(NumberUtil.convertListToInt(paramCustomerTypeIds));
      }

      if (paramUserTypeIds != null && paramUserTypeIds.length > 0 && StringUtils
          .isNotEmpty(paramUserTypeIds[0])) {
        fbcRequest.setUserTypeIds(NumberUtil.convertListToInt(paramUserTypeIds));
      }

      if (paramWalletTypeIds != null && paramWalletTypeIds.length > 0 && StringUtils
          .isNotEmpty(paramWalletTypeIds[0])) {
        fbcRequest.setWalletTypeIds(NumberUtil.convertListToInt(paramWalletTypeIds));
      }

      if (paramStatusIds != null && paramStatusIds.length > 0 && StringUtils
          .isNotEmpty(paramStatusIds[0])) {
        List<Integer> statusId = NumberUtil.convertListToInt(paramStatusIds);

        if (statusId.size() == 1 && statusId.get(0) == 0) {
          fbcRequest.setActive(StringUtil.CHAR_NO);

        } else if (statusId.size() == 1 && statusId.get(0) == 1) {
          fbcRequest.setActive(StringUtil.CHAR_YES);
        }
      }

      FindCustomerResponse fbcResponse = walletEndpoint.findBalanceCustomer(fbcRequest);

      map.put("customers", fbcResponse.getCustomers());
      map.put("total", fbcResponse.getTotal().intValue());
      map.put("totalUserBalance", NumberUtil.formatNumber(fbcResponse.getTotalBalance()));
      map.put("pagesize", limit);
      map.put("offset", offset);

      map.put("customerTypeIds", CustomerType.FULL_CUSTOMER_TYPES);
      map.put("statuses", CustomerType.CUSTOMER_STATUS_IDS);
      map.put("userTypeIds", CustomerType.SERVICE_TYPE_IDS);
      map.put("walletTypeIds", CustomerType.WALLET_TYPE_IDS);
    } catch (Exception e) {
      LOG.error(e.getMessage());
    }
    return "/balance/balance_customer";
  }

  @RequestMapping(value = "/customer/detail", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.STAFF + "','" + RoleConstants.MERCHANT + "','"
      + RoleConstants.CUSTOMER + "')")
  public String get(HttpServletRequest request, HttpServletResponse response, ModelMap map)
      throws FrontEndException {
    try {
      String traceNo = (request.getParameter("traceNo"));

      if (traceNo != null) {
        GetTransactionRequest getRequest = new GetTransactionRequest(traceNo);
        GetTransactionResponse getResponse = transactionEndpoint.getTransaction(getRequest);
        map.put("transaction", getResponse.getTransaction());
        map.put("transactionEvents", getResponse.getTransactionEvents());
        return "/balance/balance_customer_last_txn";
      }
    } catch (Exception e) {
      LOG.error(e.getMessage());
    }
    return "redirect:" + BALANCE_CUSTOMER_LIST;
  }

}*/
