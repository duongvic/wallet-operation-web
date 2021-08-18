package vn.mog.ewallet.operation.web.controller.balance;

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
import vn.mog.ewallet.operation.web.controller.balance.contract.BalanceProvider;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.exception.MessageNotify;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.FindProviderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.FindProviderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.SummaryBalanceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.SummaryBalanceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.beans.Provider;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.StatsBalanceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.StatsBalanceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.StatsBalanceResponseType.StatsBalance;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.beans.Balance;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.beans.Customer;
import vn.mog.framework.common.utils.NumberUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.Map.Entry;

import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType.*;

@Controller
@RequestMapping(value = "/wallet/balance")
public class BalanceTableMoneyController extends AbstractController {

  public static final String BALANCE_CONTROLLER = "/wallet/balance";
  public static final String BALANCE_TABLE_MONEY = BALANCE_CONTROLLER + "/balance-table-money";
  public static final String BALANCE_ZOTA = "ZOTA";
  public static final String BALANCE_SOF = "SOF";
  public static final String BALANCE_MERCHANT = "MERCHANT";
  public static final String BALANCE_CUSTOMER = "CUSTOMER";
  public static final String BALANCE_AGENT = "AGENT";
  public static final String BALANCE_SALE = "SALE";
  public static final String BALANCE_PROVIDER = "PROVIDER";
  public static final String BALANCE_FA = "FA";
  public static final String BALANCE_POOL = "POOL";

  private static final Logger LOG = LogManager.getLogger(BalanceTableMoneyController.class);


  @RequestMapping(value = "/balance-table-money", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('ADMIN_OPERATION','SALESUPPORT_MANAGER')")
  public String balanceMonitoring(HttpServletRequest request, ModelMap map) throws FrontEndException {

    try {
      List<BalanceProvider> balanceProviders = new ArrayList<>();
      Long totalProviderMoney = 0L;
      FindProviderRequest findReq = new FindProviderRequest();
      findReq.setLimit(50);
      findReq.setOffset(0);
      FindProviderResponse findRes = providerEndpoint.findProviders(findReq);
      if (findRes.getStatus().getCode() == MessageNotify.SUCCESS_CODE) {
        for (Provider providers : findRes.getProviders()) {
          totalProviderMoney += providers.getBalance();
          balanceProviders.add(new BalanceProvider(providers.getName(), providers.getBalance()));
        }
      }


      List<BalanceCustomer> balanceCustomers = new ArrayList<>();
      Long totalCusMoney = 0L;
      ArrayList<Integer> lstCusTypeId = new ArrayList<Integer>(
          Arrays.asList(ID_CUSTOMER, ID_MERCHANT, ID_SALE, ID_AGENT));
      SummaryBalanceRequest sbCusReq = new SummaryBalanceRequest();
      sbCusReq.setCustomerTypeIds(lstCusTypeId);
      SummaryBalanceResponse sbCusRes = sofCashEndpoint.customerBalances(sbCusReq);
      if (sbCusRes.getStatus().getCode() == MessageNotify.SUCCESS_CODE) {
        Map<String, Long> mapCusBalances = sbCusRes.getSummary();
        for (Entry<String, Long> mapCusBalance : mapCusBalances.entrySet()) {
          totalCusMoney += mapCusBalance.getValue();
          balanceCustomers.add(new BalanceCustomer(mapCusBalance.getKey(),mapCusBalance.getValue()));
        }
      }



      Long totalInitiateBalance = 0L;
      List<Integer> numbers = Arrays.asList(balanceCustomers.size(), balanceProviders.size());
      Integer max = Collections.max(numbers);



      map.put("totalInitiateBalance", NumberUtil.formatNumber(totalInitiateBalance));
      map.put("totalProviderMoney", NumberUtil.formatNumber(totalProviderMoney));
      map.put("totalCustomerMoney", NumberUtil.formatNumber(totalCusMoney));
      map.put("totalDifference", NumberUtil.formatNumber(totalProviderMoney - totalCusMoney));

      map.put("maxRow", max);

      map.put("sizeCustomers", balanceCustomers.size());
      map.put("sizeProvider", balanceProviders.size());


      map.put("balanceCustomers", balanceCustomers);
      map.put("balanceProviders", balanceProviders);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/balance/balance_table_money";
  }
}
