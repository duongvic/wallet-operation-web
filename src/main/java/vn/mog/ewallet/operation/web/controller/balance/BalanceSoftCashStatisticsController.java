package vn.mog.ewallet.operation.web.controller.balance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.controller.balance.contract.BalanceBank;
import vn.mog.ewallet.operation.web.controller.balance.contract.BalanceCustomer;
import vn.mog.ewallet.operation.web.controller.balance.contract.BalanceFA;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.StatsBalanceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.StatsBalanceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.StatsBalanceResponseType.StatsBalance;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.beans.Balance;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.beans.Customer;
import vn.mog.framework.common.utils.NumberUtil;

@Controller
@RequestMapping(value = "/wallet/balance")
public class BalanceSoftCashStatisticsController extends AbstractController {

  public static final String BALANCE_CONTROLLER = "/wallet/balance";
  public static final String BALANCE_CUSTOMER_LIST = BALANCE_CONTROLLER + "/balance-customer";
  public static final String BALANCE_MONITORING = BALANCE_CONTROLLER + "/balance-monitoring";
  public static final String BALANCE_ZOTA = "ZOTA";
  public static final String BALANCE_SOF = "SOF";
  public static final String BALANCE_MERCHANT = "MERCHANT";
  public static final String BALANCE_CUSTOMER = "CUSTOMER";
  public static final String BALANCE_AGENT = "AGENT";
  public static final String BALANCE_PROPERTY_MANAGER = "PROPERTY_MANAGER";
  public static final String BALANCE_SALE = "SALE";
  public static final String BALANCE_PROVIDER = "PROVIDER";
  public static final String BALANCE_FA = "FA";
  public static final String BALANCE_POOL = "POOL";

  private static final Logger LOG = LogManager.getLogger(BalanceSoftCashStatisticsController.class);


  @RequestMapping(value = "/balance-monitoring", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('ADMIN_OPERATION','FINANCE','ACCOUNTING')")
  public String balanceMonitoring(HttpServletRequest request, ModelMap map) throws FrontEndException {

    try {

      StatsBalanceRequest sbReq = new StatsBalanceRequest();
      StatsBalanceResponse sbRes = sofCashEndpoint.statsBalance(sbReq);

      Long totalInitiateBalance = 0L;

      // pie chart
      Map<String, StatsBalance> stateBalanceMap = sbRes.getStatsBalances();
      Map<String, Long> arrayOfSOF = new HashMap<>();
      for (Entry<String, StatsBalance> entry : stateBalanceMap.entrySet()) {
        if (entry.getValue() != null && entry.getValue().getBalance() != null && entry.getValue().getBalance().getBalance() != null) {
          map.put(entry.getKey(), entry.getValue().getBalance().getBalance());
          if (!entry.getKey().equals(BALANCE_FA)) {
            totalInitiateBalance += entry.getValue().getBalance().getBalance();
          }
        } else {
          map.put(entry.getKey(), 0L);
        }

        if (BALANCE_SOF.equals(entry.getKey())) {
          List<Customer> customers = entry.getValue().getCustomers();
          for (Customer item : customers) {
            String displayName = item.getDisplayName();
            /*
            if (displayName.contains("Account TM SOF")) {
              arrayOfSOF.put(displayName.replace("Account TM SOF", StringUtils.EMPTY), item.getBalance().getBalance());

            } else if (displayName.contains("TM SOF")) {
              String replace = displayName.replace("TM SOF", StringUtils.EMPTY);
              arrayOfSOF.put(replace.replace("Account", StringUtils.EMPTY), item.getBalance().getBalance());

              *//*fix sau*//*
              if (displayName.contains("Account") || displayName.contains("account")) {
                arrayOfSOF.put(displayName.replace("Account", StringUtils.EMPTY), item.getBalance().getBalance());
              } else {
                arrayOfSOF.put(displayName, item.getBalance().getBalance());
              }
            }*/
            arrayOfSOF.put(displayName, item.getBalance().getBalance());
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

      Map<String, StatsBalance> mapStateBalances = sbRes.getStatsBalances();
      for (Entry<String, StatsBalance> mapBalance : mapStateBalances.entrySet()) {

        //if (mapBalance.getValue() != null) {
        key = mapBalance.getKey();
        switch (key) {
          case BALANCE_POOL:
            valAccount = mapBalance.getValue().getBalance();
            if (valAccount != null) {
              balanceBanks.add(new BalanceBank(mapBalance.getKey(), valAccount.getBalance()));
            }
            break;

          case BALANCE_FA:
            valueAccounts = mapBalance.getValue().getCustomers();
            if (valueAccounts != null) {
              for (Customer mapAccount : valueAccounts) {
                SOF_Amount = mapAccount.getBalance().getBalance();
              /*String replace;
              if (mapAccount.getDisplayName().contains("TM SOF")) {
                replace = mapAccount.getDisplayName().replace("TM SOF", StringUtils.EMPTY);
              } else {
                replace = mapAccount.getDisplayName();
              }*/
                String replace = mapAccount.getDisplayName();
                balanceFAs.add(new BalanceFA(replace, SOF_Amount));
              }
            }

            break;

          case BALANCE_MERCHANT:
          case BALANCE_CUSTOMER:
          case BALANCE_AGENT:
          case BALANCE_PROPERTY_MANAGER:
//          case BALANCE_SALE:
          case BALANCE_ZOTA:
          case BALANCE_PROVIDER:
            valAccount = mapBalance.getValue().getBalance();
            if (valAccount != null) {
              if(mapBalance.getKey()== BALANCE_ZOTA){
                balanceCustomers.add(new BalanceCustomer("ZO-TA", valAccount.getBalance()));
              }else
              balanceCustomers.add(new BalanceCustomer(mapBalance.getKey(), valAccount.getBalance()));
            }

            break;
          default:
            break;
        }
        //}

      }

      List<Integer> numbers = Arrays.asList(balanceBanks.size(), balanceCustomers.size(), balanceFAs.size());
      Integer max = Collections.max(numbers);

      Long totalFAMoney = sbRes.getVerifyBalance().getFaBalance().getBalance();
      Long totalCustomerMoney = sbRes.getVerifyBalance().getWalletBalance().getBalance();
      Long totalInitPoolBalance = sbRes.getVerifyBalance().getInitPoolBalance().getBalance();

      map.put("totalFAMoney", NumberUtil.formatNumber(totalFAMoney));
      map.put("totalCustomerMoney", NumberUtil.formatNumber(totalCustomerMoney));
      map.put("totalInitiateBalance", NumberUtil.formatNumber(totalInitiateBalance));
      map.put("totalInitPoolBalance", NumberUtil.formatNumber(totalInitPoolBalance));
      map.put("maxRow", max);
      map.put("sizeBanks", balanceBanks.size());
      map.put("sizeCustomers", balanceCustomers.size());
      map.put("sizeFAs", balanceFAs.size());
      map.put("balanceBanks", balanceBanks);
      map.put("balanceCustomers", balanceCustomers);
      map.put("balanceFAs", balanceFAs);


    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/balance/balance_monitoring";
  }
}
