package vn.mog.ewallet.operation.web.controller.balance;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.UserType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.SearchPaymentInstrumentRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.SearchPaymentInstrumentResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Customer;
import vn.mog.framework.common.utils.NumberUtil;


@Controller
@RequestMapping(value = "/wallet/balance")
public class BalanceSoftCashController extends AbstractController {

  public static final String BALANCE_CONTROLLER = "/wallet/balance";
  public static final String BALANCE_SOFT_OF_CASH_LIST = BALANCE_CONTROLLER + "/sofcash";

  private static final Logger logger = LogManager.getLogger(BalanceSoftCashController.class);

  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/sofcash", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('ADMIN_OPERATION',"
      + "'FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE',"
      + "'ACCOUNTING',"
      + "'SALESUPPORT_MANAGER' , 'SALESUPPORT', 'SALE_EXCUTIVE','SALE_AGENT',"
      + "'CUSTOMERCARE_MANAGER',"
      + "'RECONCILIATION_LEADER','RECONCILIATION')")
  public String balanceSoftOfCash(HttpServletRequest request, ModelMap map) throws Exception {

    try {
      // search
      String paramQuickSearch = StringUtils.trimToEmpty(request.getParameter("search"));
      String paramDateRange = StringUtils.trimToEmpty(request.getParameter("range"));
      String[] paramCustomerTypeIds = request.getParameterValues("customerTypeIds");
      String[] paramStatusIds = request.getParameterValues("status");
      String[] paramUserTypeIds = request.getParameterValues("userTypeIds");
      String[] paramWalletTypeIds = request.getParameterValues("walletTypeIds");
      String[] paramCustomerIds = request.getParameterValues("customerIds");

      SearchPaymentInstrumentRequest spiRequest = new SearchPaymentInstrumentRequest();

      Date[] dates = parseDateRange(paramDateRange, true);
      spiRequest.setFromDate(dates[0]);
      spiRequest.setToDate(dates[1]);

      int offset = 0;
      int limit = 20;

      if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
        offset = Integer.parseInt(request.getParameter("d-49489-p"));
        if (offset > 0) {
          offset = (offset - 1) * limit;
        }
      }

      spiRequest.setOffset(offset);
      spiRequest.setLimit(limit);

      if (StringUtils.isNotEmpty(paramQuickSearch)) {
        spiRequest.setQuickSearch(paramQuickSearch);
      }

      if (paramCustomerTypeIds != null && paramCustomerTypeIds.length > 0 && StringUtils
          .isNotEmpty(paramCustomerTypeIds[0])) {
        spiRequest.setCustomerTypeIds(NumberUtil.convertListToInt(paramCustomerTypeIds));
      } else {
        spiRequest
            .setCustomerTypeIds(new ArrayList<>(CustomerType.BALANCE_CUSTOMER_TYPES.keySet()));
      }

      if (paramUserTypeIds != null && paramUserTypeIds.length > 0 && StringUtils
          .isNotEmpty(paramUserTypeIds[0])) {
        spiRequest.setUserTypeIds(NumberUtil.convertListToInt(paramUserTypeIds));
      }

      if (paramWalletTypeIds != null && paramWalletTypeIds.length > 0 && StringUtils
          .isNotEmpty(paramWalletTypeIds[0])) {
        spiRequest.setWalletTypeIds(NumberUtil.convertListToInt(paramWalletTypeIds));
      }

      if (paramStatusIds != null && paramStatusIds.length > 0 && StringUtils
          .isNotEmpty(paramStatusIds[0])) {
        List<Integer> statusId = NumberUtil.convertListToInt(paramStatusIds);

        if (statusId.size() == 1 && statusId.get(0) == 0) {
          spiRequest.setActive(Boolean.FALSE);

        } else if (statusId.size() == 1 && statusId.get(0) == 1) {
          spiRequest.setActive(Boolean.TRUE);
        }
      }

      List<Long> customerIds = null;
      List customerList;
      if (paramCustomerIds != null) {
        customerIds = NumberUtil.convertListToLong(paramCustomerIds);
      }

      List<String> rolesOfCaller = getRolesOfCaller();
      customerList = getListSaleByParent(rolesOfCaller);
      // If Caller is Sale, show all Caller's agents' transactions
      // Get list Agents' Id to show Transaction
      // Sale doesn't have own wallet, so no need to show Sale's transactions
      if (!rolesOfCaller.contains(RoleConstants.ADMIN_OPERATION)
          && (customerIds == null || customerIds.isEmpty()) && customerList != null) {
        customerIds = new ArrayList<>();
        try {
          if(customerList.size() > 0){
            for (Customer customer : (List<Customer>) customerList) {
              customerIds.add(customer.getId());
            }
          }else {
            customerIds.add(callerUtil.getCallerInformation().getActorId());
          }

        } catch (ClassCastException e) {
          customerIds = null;
        }
      }

      if (customerIds != null) {
        customerIds.remove(null);
      }
      spiRequest.setCustomerIds(customerIds);

      SearchPaymentInstrumentResponse spiResponse = sofCashEndpoint
          .searchPaymentInstruments(spiRequest, callerUtil.getCallerInformation());

      map.put("paymentInstruments", spiResponse.getPaymentInstruments());
      map.put("total", spiResponse.getSummary().getTotalPi().intValue());
      map.put("totalUserBalance",
          NumberUtil.formatNumber(spiResponse.getSummary().getTotalBalance()));
      map.put("pagesize", limit);
      map.put("offset", offset);

      map.put("customerTypeIds", CustomerType.BALANCE_CUSTOMER_TYPES);
      map.put("statuses", CustomerType.CUSTOMER_STATUS_IDS);
      map.put("userTypeIds", UserType.USER_TYPE_IDS);
      map.put("walletTypeIds", CustomerType.WALLET_TYPE_IDS);
      map.put("customers", customerList);
    } catch (Exception ex) {
      logger.error(ex.getMessage(), ex);
    }
    return "/balance/balance_sof_cash";
  }

}