package vn.mog.ewallet.operation.web.controller.dashboard;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.controller.translog.AbstractTransactionController;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.GeneralResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.SummaryTransactionStatisticByDateRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.SummaryTransactionStatisticByHourRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.beans.SummaryCompare;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.beans.SummaryDTO;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.beans.SummaryTransactionStatisticByDateDTO;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.beans.SummaryTransactionStatisticByHourDTO;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.CustomerTypeEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.ProviderCodeEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.ServiceTypeEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.enums.StatusEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.Customer;
import vn.mog.ewallet.operation.web.utils.WebUtil;
import vn.mog.framework.common.utils.SessionUtil;
import vn.mog.framework.common.utils.Utils;


@Controller
@RequestMapping(value = "/service/transaction-dashboard")
public class TransactionDashboardController extends AbstractTransactionController {

  public static final String DASH_BOARD = "/service/transaction-dashboard/index";
  private static final Logger LOG = LogManager.getLogger(TransactionDashboardController.class);

  @Value("#{'${param.system.provider.ptu.onhold.acl}'.split('\\|')}")
  private Set<String> providers;

  @Value("#{'${param.system.dashboard.acl.email}'.split('\\|')}")
  private Set<String> accessListEmails;

  @RequestMapping(value = "/index", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.STAFF + "')")
  public String index(ModelMap map, HttpServletRequest request) {

    Set<String> roles = WebUtil.getRolesOfUserLogin();
    String email = getEmailFrom((String) SessionUtil.getAttribute("access_token"));
    boolean haveAccessPermission =
        (roles.contains(RoleConstants.STAFF) || roles.contains(RoleConstants.ADMIN_OPERATION))
            && accessListEmails.contains(email);

    if (!haveAccessPermission) {
      return "redirect:/service/transaction-history/list-all";
    }

    try {
      //insert code here
      List<String> hours = new ArrayList<>();
      for (int i = 0; i < 24; i++) {
        hours.add(i < 10 ? "0" + i : String.valueOf(i));
      }

      List<String> statuses = new ArrayList<>();
      for (StatusEnum status : StatusEnum.values()) {
        statuses.add(status.toString());
      }

      String paramDateRange = StringUtils.trimToEmpty(request.getParameter("range"));
      Date[] dates = parseDateRange(paramDateRange, true);

      String[] serviceTypes = request.getParameterValues("serviceTypeIds");
      String[] customerTypes = request.getParameterValues("idOwnerCustomerTypes");
      String[] providerCodes = request.getParameterValues("provider");
      String[] transactionStts = request.getParameterValues("txnStatusIds");
      String[] customerIds = request.getParameterValues("customerIds");

      List<ServiceTypeEnum> serviceTypeEnums = null;
      if (serviceTypes != null && serviceTypes.length > 0) {
        List<ServiceTypeEnum> serviceTypeEnumVals = new ArrayList<>();
        Arrays.stream(serviceTypes).forEach(item -> serviceTypeEnumVals.add(ServiceTypeEnum.valueOf(item)));
        serviceTypeEnums = serviceTypeEnumVals;
      } else {
        serviceTypeEnums = Arrays.asList(ServiceTypeEnum.values());
      }

      List<CustomerTypeEnum> customerTypeEnums = null;
      if (customerTypes != null && customerTypes.length > 0) {
        List<CustomerTypeEnum> customerTypeEnumVals = new ArrayList<>();
        Arrays.stream(customerTypes).forEach(item -> customerTypeEnumVals.add(CustomerTypeEnum.valueOf(item)));
        customerTypeEnums = customerTypeEnumVals;
      }

      List<ProviderCodeEnum> providerCodeEnums = null;
      if (providerCodes != null && providerCodes.length > 0) {
        List<ProviderCodeEnum> providerCodeEnumVals = new ArrayList<>();
        Arrays.stream(providerCodes).forEach(item -> providerCodeEnumVals.add(ProviderCodeEnum.valueOf(item)));
        providerCodeEnums = providerCodeEnumVals;
      }

      List<StatusEnum> statusEnums = null;
      if (transactionStts != null && transactionStts.length > 0) {
        List<StatusEnum> statusEnumVals = new ArrayList<>();
        Arrays.stream(transactionStts).forEach(item -> statusEnumVals.add(StatusEnum.valueOf(item)));
        statusEnums = statusEnumVals;
      }

      List<Customer> customers = new ArrayList<>();
      List<Long> cusIds = new ArrayList<>();
      if (customerIds != null) {
        for (String id : customerIds) {
          cusIds.add(Long.valueOf(id));
          GetCustomerRequest requestCus = new GetCustomerRequest();
          requestCus.setCustomerId(Long.valueOf(id));
          GetCustomerResponse responseCus = walletCustomerEndpoint.getCustomer(requestCus);
          customers.add(responseCus.getCustomer());
        }
      }
      // get name by customer
      map.put("customers", customers);

      if (dates[0] == null || dates[1] == null) {
        //mặc đinh là current day
        Date currDate = new Date();
        Date tmp = new Date();
        tmp.setHours(0);
        tmp.setMinutes(0);
        tmp.setSeconds(0);
        SummaryTransactionStatisticByHourRequest req = new SummaryTransactionStatisticByHourRequest();
        req.setFromHour(0);
        req.setToHour(currDate.getHours() == 23 ? currDate.getHours() : currDate.getHours() + 1);
        req.setStatDate(currDate);
        req.setServiceTypes(serviceTypeEnums);
        req.setCustomerTypes(customerTypeEnums);
        req.setProviderCodes(providerCodeEnums);
        req.setStatus(statusEnums);
        req.setCustomerIds(cusIds.size() > 0 ? cusIds : null);

        GeneralResponse<Object> res = transactionStatisticEndpoint.summaryTransactionStatisticByHour(req);
        SummaryTransactionStatisticByHourDTO dto = Utils.responseToObject(res.getData(), SummaryTransactionStatisticByHourDTO.class);
        Map<String, SummaryDTO> summaryByStatus = dto.getSummaryByStatus();
        Map<String, SummaryDTO> summaryByCustomerType = dto.getSummaryByCustomerType();
        Map<String, SummaryDTO> summaryByHour = dto.getSummaryByHour();
        Map<String, SummaryDTO> summaryByProvider = dto.getSummaryByProvider();
        Map<String, SummaryDTO> summaryByServiceType = dto.getSummaryByServiceType();
        Map<String, SummaryDTO> summaryByCustomer = dto.getSummaryByCustomer();

        SummaryDTO success = summaryByStatus.get("SUCCESS") == null ? new SummaryDTO() : summaryByStatus.get("SUCCESS");
        SummaryDTO fail = summaryByStatus.get("FAIL") == null ? new SummaryDTO() : summaryByStatus.get("FAIL");
        SummaryDTO open = summaryByStatus.get("OPEN") == null ? new SummaryDTO() : summaryByStatus.get("OPEN");
        SummaryDTO pending = summaryByStatus.get("PENDING") == null ? new SummaryDTO() : summaryByStatus.get("PENDING");
        SummaryDTO processing = summaryByStatus.get("PROCESSING") == null ? new SummaryDTO() : summaryByStatus.get("PROCESSING");

        ////////////////////////////////////
        //////////    COMPARE    ///////////
        ////////////////////////////////////

        SummaryTransactionStatisticByHourRequest compareReq = new SummaryTransactionStatisticByHourRequest();
        compareReq.setFromHour(0);
        compareReq.setToHour(currDate.getHours() == 23 ? currDate.getHours() - 1 : currDate.getHours());
        String date0 = getHourViaString(currDate) + " " + convertToLocalDateViaInstant(currDate).minusDays(1).toString();
        compareReq.setStatDate(new SimpleDateFormat("hh:mm:ss yyyy-MM-dd").parse(date0));
        compareReq.setServiceTypes(serviceTypeEnums);
        compareReq.setCustomerTypes(customerTypeEnums);
        compareReq.setProviderCodes(providerCodeEnums);
        compareReq.setStatus(statusEnums);
        compareReq.setCustomerIds(cusIds.size() > 0 ? cusIds : null);

        GeneralResponse<Object> compareRes = transactionStatisticEndpoint.summaryTransactionStatisticByHour(compareReq);
        SummaryTransactionStatisticByHourDTO compareDto = Utils.responseToObject(compareRes.getData(), SummaryTransactionStatisticByHourDTO.class);
        Map<String, SummaryDTO> compareSummaryByStatus = compareDto.getSummaryByStatus();
        Map<String, SummaryDTO> compareSummaryByCustomerType = compareDto.getSummaryByCustomerType();
        Map<String, SummaryDTO> compareSummaryByHour = compareDto.getSummaryByHour();
        Map<String, SummaryDTO> compareSummaryByProvider = compareDto.getSummaryByProvider();
        Map<String, SummaryDTO> compareSummaryByServiceType = compareDto.getSummaryByServiceType();
        Map<String, SummaryDTO> compareSummaryByCustomer = compareDto.getSummaryByCustomer();

        SummaryDTO oldSuccess = compareSummaryByStatus.get("SUCCESS") == null ? new SummaryDTO() : compareSummaryByStatus.get("SUCCESS");
        SummaryDTO oldFail = compareSummaryByStatus.get("FAIL") == null ? new SummaryDTO() : compareSummaryByStatus.get("FAIL");
        SummaryDTO oldOpen = compareSummaryByStatus.get("OPEN") == null ? new SummaryDTO() : compareSummaryByStatus.get("OPEN");
        SummaryDTO oldPending = compareSummaryByStatus.get("PENDING") == null ? new SummaryDTO() : compareSummaryByStatus.get("PENDING");
        SummaryDTO oldProcessing = compareSummaryByStatus.get("PROCESSING") == null ? new SummaryDTO() : compareSummaryByStatus.get("PROCESSING");

        //summary
        map.put("success", success);
        map.put("fail", fail);
        map.put("open", open);
        map.put("pending", pending);
        map.put("processing", processing);

        map.put("oldSuccess", oldSuccess);
        map.put("oldFail", oldFail);
        map.put("oldOpen", oldOpen);
        map.put("oldPending", oldPending);
        map.put("oldProcessing", oldProcessing);


        //line chart
        // tọa dộ Ox
        map.put("hourTo", 0);
        map.put("hourFrom", currDate.getHours() == 23 ? currDate.getHours() : currDate.getHours() + 1);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date[] dates1 = new Date[2];
        dates1[0] = tmp;
        dates1[1] = currDate;
        // label
        map.put("oldDate", dateFormat.format(getReflectHistoryDateRange(dates1)[0]) + " - " + dateFormat.format(getReflectHistoryDateRangeForCurrentDay(dates1)[1]));
        map.put("newDate", dateFormat.format(tmp) + " - " + dateFormat.format(currDate));

        map.put("summaryByCustomerType", summaryByCustomerType);
        map.put("summaryByProvider", summaryByProvider);
        map.put("summaryByServiceType", summaryByServiceType);
        map.put("summaryByHour", summaryByHour);
        map.put("compareSummaryByCustomerType", compareSummaryByCustomerType);
        map.put("compareSummaryByProvider", compareSummaryByProvider);
        map.put("compareSummaryByServiceType", compareSummaryByServiceType);
        map.put("compareSummaryByServiceType", compareSummaryByServiceType);
        map.put("compareSummaryByHour", compareSummaryByHour);

        //pie chart
        List<SummaryCompare> revenueByCus = mergeMapsToObject(summaryByCustomerType, compareSummaryByCustomerType);

        List<SummaryCompare> revenueByProdvider = mergeMapsToObject(summaryByProvider, compareSummaryByProvider);

        List<SummaryCompare> revenueByService = mergeMapsToObject(summaryByServiceType, compareSummaryByServiceType);

        map.put("revenueByCus", revenueByCus);
        map.put("revenueByProdvider", revenueByProdvider);
        map.put("revenueByService", revenueByService);
        map.put("summaryByCustomer", summaryByCustomer);
        map.put("passSummaryByCustomer", compareSummaryByCustomer);

      } else {
        Date currentDate = new Date();

        if (Utils.isSameDay(dates[0], dates[1])) {
          if (Utils.isSameDay(dates[0], currentDate)) {
            //nếu fromDate và toDate là cùng 1 ngày và là current day
            Date currDate = new Date();
            Date tmp = new Date();
            tmp.setHours(0);
            tmp.setMinutes(0);
            tmp.setSeconds(0);
            SummaryTransactionStatisticByHourRequest req = new SummaryTransactionStatisticByHourRequest();
            req.setFromHour(0);
            req.setToHour(currDate.getHours() == 23 ? currDate.getHours() : currDate.getHours() + 1);
            req.setStatDate(currDate);
            req.setServiceTypes(serviceTypeEnums);
            req.setCustomerTypes(customerTypeEnums);
            req.setProviderCodes(providerCodeEnums);
            req.setStatus(statusEnums);
            req.setCustomerIds(cusIds.size() > 0 ? cusIds : null);

            GeneralResponse<Object> res = transactionStatisticEndpoint.summaryTransactionStatisticByHour(req);
            SummaryTransactionStatisticByHourDTO dto = Utils.responseToObject(res.getData(), SummaryTransactionStatisticByHourDTO.class);
            Map<String, SummaryDTO> summaryByStatus = dto.getSummaryByStatus();
            Map<String, SummaryDTO> summaryByCustomerType = dto.getSummaryByCustomerType();
            Map<String, SummaryDTO> summaryByHour = dto.getSummaryByHour();
            Map<String, SummaryDTO> summaryByProvider = dto.getSummaryByProvider();
            Map<String, SummaryDTO> summaryByServiceType = dto.getSummaryByServiceType();
            Map<String, SummaryDTO> summaryByCustomer = dto.getSummaryByCustomer();

            SummaryDTO success = summaryByStatus.get("SUCCESS") == null ? new SummaryDTO() : summaryByStatus.get("SUCCESS");
            SummaryDTO fail = summaryByStatus.get("FAIL") == null ? new SummaryDTO() : summaryByStatus.get("FAIL");
            SummaryDTO open = summaryByStatus.get("OPEN") == null ? new SummaryDTO() : summaryByStatus.get("OPEN");
            SummaryDTO pending = summaryByStatus.get("PENDING") == null ? new SummaryDTO() : summaryByStatus.get("PENDING");
            SummaryDTO processing = summaryByStatus.get("PROCESSING") == null ? new SummaryDTO() : summaryByStatus.get("PROCESSING");

            ////////////////////////////////////
            //////////    COMPARE    ///////////
            ////////////////////////////////////

            SummaryTransactionStatisticByHourRequest compareReq = new SummaryTransactionStatisticByHourRequest();
            compareReq.setFromHour(0);
            compareReq.setToHour(currDate.getHours() == 23 ? currDate.getHours() - 1 : currDate.getHours());
            String date0 = getHourViaString(currDate) + " " + convertToLocalDateViaInstant(currDate).minusDays(1).toString();
            compareReq.setStatDate(new SimpleDateFormat("hh:mm:ss yyyy-MM-dd").parse(date0));
            compareReq.setServiceTypes(serviceTypeEnums);
            compareReq.setCustomerTypes(customerTypeEnums);
            compareReq.setProviderCodes(providerCodeEnums);
            compareReq.setCustomerIds(cusIds.size() > 0 ? cusIds : null);

            GeneralResponse<Object> compareRes = transactionStatisticEndpoint.summaryTransactionStatisticByHour(compareReq);
            SummaryTransactionStatisticByHourDTO compareDto = Utils.responseToObject(compareRes.getData(), SummaryTransactionStatisticByHourDTO.class);
            Map<String, SummaryDTO> compareSummaryByStatus = compareDto.getSummaryByStatus();
            Map<String, SummaryDTO> compareSummaryByCustomerType = compareDto.getSummaryByCustomerType();
            Map<String, SummaryDTO> compareSummaryByHour = compareDto.getSummaryByHour();
            Map<String, SummaryDTO> compareSummaryByProvider = compareDto.getSummaryByProvider();
            Map<String, SummaryDTO> compareSummaryByServiceType = compareDto.getSummaryByServiceType();
            Map<String, SummaryDTO> compareSummaryByCustomer = compareDto.getSummaryByCustomer();

            SummaryDTO oldSuccess = compareSummaryByStatus.get("SUCCESS") == null ? new SummaryDTO() : compareSummaryByStatus.get("SUCCESS");
            SummaryDTO oldFail = compareSummaryByStatus.get("FAIL") == null ? new SummaryDTO() : compareSummaryByStatus.get("FAIL");
            SummaryDTO oldOpen = compareSummaryByStatus.get("OPEN") == null ? new SummaryDTO() : compareSummaryByStatus.get("OPEN");
            SummaryDTO oldPending = compareSummaryByStatus.get("PENDING") == null ? new SummaryDTO() : compareSummaryByStatus.get("PENDING");
            SummaryDTO oldProcessing = compareSummaryByStatus.get("PROCESSING") == null ? new SummaryDTO() : compareSummaryByStatus.get("PROCESSING");

            //summary
            map.put("success", success);
            map.put("fail", fail);
            map.put("open", open);
            map.put("pending", pending);
            map.put("processing", processing);

            map.put("oldSuccess", oldSuccess);
            map.put("oldFail", oldFail);
            map.put("oldOpen", oldOpen);
            map.put("oldPending", oldPending);
            map.put("oldProcessing", oldProcessing);

            //line chart
            // trục Ox
            map.put("hourTo", 0);
            map.put("hourFrom", currDate.getHours() == 23 ? currDate.getHours() : currDate.getHours() + 1);

            // label
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date[] dates1 = new Date[2];
            dates1[0] = tmp;
            dates1[1] = currDate;
            map.put("oldDate", dateFormat.format(getReflectHistoryDateRange(dates1)[0]) + " - " + dateFormat.format(getReflectHistoryDateRangeForCurrentDay(dates1)[1]));
            map.put("newDate", dateFormat.format(tmp) + " - " + dateFormat.format(currDate));

            map.put("summaryByCustomerType", summaryByCustomerType);
            map.put("summaryByProvider", summaryByProvider);
            map.put("summaryByServiceType", summaryByServiceType);
            map.put("summaryByHour", summaryByHour);
            map.put("compareSummaryByCustomerType", compareSummaryByCustomerType);
            map.put("compareSummaryByProvider", compareSummaryByProvider);
            map.put("compareSummaryByServiceType", compareSummaryByServiceType);
            map.put("compareSummaryByServiceType", compareSummaryByServiceType);
            map.put("compareSummaryByHour", compareSummaryByHour);

            //pie chart
            List<SummaryCompare> revenueByCus = mergeMapsToObject(summaryByCustomerType, compareSummaryByCustomerType);

            List<SummaryCompare> revenueByProdvider = mergeMapsToObject(summaryByProvider, compareSummaryByProvider);

            List<SummaryCompare> revenueByService = mergeMapsToObject(summaryByServiceType, compareSummaryByServiceType);

            map.put("revenueByCus", revenueByCus);
            map.put("revenueByProdvider", revenueByProdvider);
            map.put("revenueByService", revenueByService);
            map.put("summaryByCustomer", summaryByCustomer);
            map.put("passSummaryByCustomer", compareSummaryByCustomer);

          } else {
            //nếu fromDate và toDate là cùng 1 ngày nhưng không phải current day
            SummaryTransactionStatisticByHourRequest req = new SummaryTransactionStatisticByHourRequest();
            req.setFromHour(dates[0].getHours());
            req.setToHour(dates[1].getHours() == 23 ? dates[1].getHours() : dates[1].getHours() + 1);
            req.setStatDate(dates[0]);
            req.setServiceTypes(serviceTypeEnums);
            req.setCustomerTypes(customerTypeEnums);
            req.setProviderCodes(providerCodeEnums);
            req.setStatus(statusEnums);
            req.setCustomerIds(cusIds.size() > 0 ? cusIds : null);

            GeneralResponse<Object> res = transactionStatisticEndpoint.summaryTransactionStatisticByHour(req);
            SummaryTransactionStatisticByHourDTO dto = Utils.responseToObject(res.getData(), SummaryTransactionStatisticByHourDTO.class);
            Map<String, SummaryDTO> summaryByStatus = dto.getSummaryByStatus();
            Map<String, SummaryDTO> summaryByCustomerType = dto.getSummaryByCustomerType();
            Map<String, SummaryDTO> summaryByHour = dto.getSummaryByHour();
            Map<String, SummaryDTO> summaryByProvider = dto.getSummaryByProvider();
            Map<String, SummaryDTO> summaryByServiceType = dto.getSummaryByServiceType();
            Map<String, SummaryDTO> summaryByCustomer = dto.getSummaryByCustomer();

            SummaryDTO success = summaryByStatus.get("SUCCESS") == null ? new SummaryDTO() : summaryByStatus.get("SUCCESS");
            SummaryDTO fail = summaryByStatus.get("FAIL") == null ? new SummaryDTO() : summaryByStatus.get("FAIL");
            SummaryDTO open = summaryByStatus.get("OPEN") == null ? new SummaryDTO() : summaryByStatus.get("OPEN");
            SummaryDTO pending = summaryByStatus.get("PENDING") == null ? new SummaryDTO() : summaryByStatus.get("PENDING");
            SummaryDTO processing = summaryByStatus.get("PROCESSING") == null ? new SummaryDTO() : summaryByStatus.get("PROCESSING");

            ////////////////////////////////////
            //////////    COMPARE    ///////////
            ////////////////////////////////////

            SummaryTransactionStatisticByHourRequest compareReq = new SummaryTransactionStatisticByHourRequest();
            compareReq.setFromHour(dates[0].getHours());
            compareReq.setToHour(dates[1].getHours() == 23 ? dates[1].getHours() : dates[1].getHours() + 1);
            String date0 = getHourViaString(dates[0]) + " " + convertToLocalDateViaInstant(dates[0]).minusDays(1).toString();
            compareReq.setStatDate(new SimpleDateFormat("hh:mm:ss yyyy-MM-dd").parse(date0));
            compareReq.setServiceTypes(serviceTypeEnums);
            compareReq.setCustomerTypes(customerTypeEnums);
            compareReq.setProviderCodes(providerCodeEnums);
            compareReq.setStatus(statusEnums);
            compareReq.setCustomerIds(cusIds.size() > 0 ? cusIds : null);

            GeneralResponse<Object> compareRes = transactionStatisticEndpoint.summaryTransactionStatisticByHour(compareReq);
            SummaryTransactionStatisticByHourDTO compareDto = Utils.responseToObject(compareRes.getData(), SummaryTransactionStatisticByHourDTO.class);
            Map<String, SummaryDTO> compareSummaryByStatus = compareDto.getSummaryByStatus();
            Map<String, SummaryDTO> compareSummaryByCustomerType = compareDto.getSummaryByCustomerType();
            Map<String, SummaryDTO> compareSummaryByHour = compareDto.getSummaryByHour();
            Map<String, SummaryDTO> compareSummaryByProvider = compareDto.getSummaryByProvider();
            Map<String, SummaryDTO> compareSummaryByServiceType = compareDto.getSummaryByServiceType();
            Map<String, SummaryDTO> compareSummaryByCustomer = compareDto.getSummaryByCustomer();

            SummaryDTO oldSuccess = compareSummaryByStatus.get("SUCCESS") == null ? new SummaryDTO() : compareSummaryByStatus.get("SUCCESS");
            SummaryDTO oldFail = compareSummaryByStatus.get("FAIL") == null ? new SummaryDTO() : compareSummaryByStatus.get("FAIL");
            SummaryDTO oldOpen = compareSummaryByStatus.get("OPEN") == null ? new SummaryDTO() : compareSummaryByStatus.get("OPEN");
            SummaryDTO oldPending = compareSummaryByStatus.get("PENDING") == null ? new SummaryDTO() : compareSummaryByStatus.get("PENDING");
            SummaryDTO oldProcessing = compareSummaryByStatus.get("PROCESSING") == null ? new SummaryDTO() : compareSummaryByStatus.get("PROCESSING");

            //summary
            map.put("success", success);
            map.put("fail", fail);
            map.put("open", open);
            map.put("pending", pending);
            map.put("processing", processing);

            map.put("oldSuccess", oldSuccess);
            map.put("oldFail", oldFail);
            map.put("oldOpen", oldOpen);
            map.put("oldPending", oldPending);
            map.put("oldProcessing", oldProcessing);

            //line chart
            // trục oX
            map.put("hourTo", dates[0].getHours());
            map.put("hourFrom", dates[1].getHours() == 23 ? dates[1].getHours() : dates[1].getHours() + 1);

            // label
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            map.put("oldDate", dateFormat.format(getReflectHistoryHourRange(dates)[0]) + " - " + dateFormat.format(getReflectHistoryHourRange(dates)[1]));
            map.put("newDate", dateFormat.format(dates[0]) + " - " + dateFormat.format(dates[1]));

            map.put("summaryByCustomerType", summaryByCustomerType);
            map.put("summaryByProvider", summaryByProvider);
            map.put("summaryByServiceType", summaryByServiceType);
            map.put("summaryByHour", summaryByHour);
            map.put("compareSummaryByCustomerType", compareSummaryByCustomerType);
            map.put("compareSummaryByProvider", compareSummaryByProvider);
            map.put("compareSummaryByServiceType", compareSummaryByServiceType);
            map.put("compareSummaryByServiceType", compareSummaryByServiceType);
            map.put("compareSummaryByHour", compareSummaryByHour);

            //pie chart
            List<SummaryCompare> revenueByCus = mergeMapsToObject(summaryByCustomerType, compareSummaryByCustomerType);

            List<SummaryCompare> revenueByProdvider = mergeMapsToObject(summaryByProvider, compareSummaryByProvider);

            List<SummaryCompare> revenueByService = mergeMapsToObject(summaryByServiceType, compareSummaryByServiceType);

            map.put("revenueByCus", revenueByCus);
            map.put("revenueByProdvider", revenueByProdvider);
            map.put("revenueByService", revenueByService);
            map.put("summaryByCustomer", summaryByCustomer);
            map.put("passSummaryByCustomer", compareSummaryByCustomer);
          }
        } else if (isCurrentMonth(dates) && isFirstDayOfMonth(dates[0])) {
          //khoản thời gian là tháng hiện tại => so sánh từ đầu tháng đến ngày hiện tại với khoảng tgian này của tháng trước
          Date curr = new Date();
          SummaryTransactionStatisticByDateRequest req = new SummaryTransactionStatisticByDateRequest();
          req.setFromDate(dates[0]);
          req.setToDate(curr);
          req.setServiceTypes(serviceTypeEnums);
          req.setCustomerTypes(customerTypeEnums);
          req.setProviderCodes(providerCodeEnums);
          req.setStatus(statusEnums);
          req.setCustomerIds(cusIds.size() > 0 ? cusIds : null);

          GeneralResponse<Object> res = transactionStatisticEndpoint.summaryTransactionStatisticByDate(req);
          SummaryTransactionStatisticByDateDTO dto = Utils.responseToObject(res.getData(), SummaryTransactionStatisticByDateDTO.class);
          Map<String, SummaryDTO> summaryByStatus = dto.getSummaryByStatus();
          Map<String, SummaryDTO> summaryByCustomerType = dto.getSummaryByCustomerType();
          Map<String, SummaryDTO> summaryByDate = dto.getSummaryByDate();
          Map<String, SummaryDTO> summaryByProvider = dto.getSummaryByProvider();
          Map<String, SummaryDTO> summaryByServiceType = dto.getSummaryByServiceType();
          Map<String, SummaryDTO> summaryByCustomer = dto.getSummaryByCustomer();

          SummaryDTO success = summaryByStatus.get("SUCCESS") == null ? new SummaryDTO() : summaryByStatus.get("SUCCESS");
          SummaryDTO fail = summaryByStatus.get("FAIL") == null ? new SummaryDTO() : summaryByStatus.get("FAIL");
          SummaryDTO open = summaryByStatus.get("OPEN") == null ? new SummaryDTO() : summaryByStatus.get("OPEN");
          SummaryDTO pending = summaryByStatus.get("PENDING") == null ? new SummaryDTO() : summaryByStatus.get("PENDING");
          SummaryDTO processing = summaryByStatus.get("PROCESSING") == null ? new SummaryDTO() : summaryByStatus.get("PROCESSING");

          ////////////////////////////////////
          //////////    COMPARE    ///////////
          ////////////////////////////////////

          SummaryTransactionStatisticByDateRequest compareReq = new SummaryTransactionStatisticByDateRequest();
          compareReq.setFromDate(getSameRangeWithCurrentMonth(dates)[0]);
          compareReq.setToDate(getSameRangeWithCurrentMonth(dates)[1]);
          compareReq.setServiceTypes(serviceTypeEnums);
          compareReq.setCustomerTypes(customerTypeEnums);
          compareReq.setProviderCodes(providerCodeEnums);
          compareReq.setStatus(statusEnums);
          compareReq.setCustomerIds(cusIds.size() > 0 ? cusIds : null);

          GeneralResponse<Object> compareRes = transactionStatisticEndpoint.summaryTransactionStatisticByDate(compareReq);
          SummaryTransactionStatisticByDateDTO compareDto = Utils.responseToObject(compareRes.getData(), SummaryTransactionStatisticByDateDTO.class);
          Map<String, SummaryDTO> compareSummaryByStatus = compareDto.getSummaryByStatus();
          Map<String, SummaryDTO> compareSummaryByCustomerType = compareDto.getSummaryByCustomerType();
          Map<String, SummaryDTO> compareSummaryByDate = compareDto.getSummaryByDate();
          Map<String, SummaryDTO> compareSummaryByProvider = compareDto.getSummaryByProvider();
          Map<String, SummaryDTO> compareSummaryByServiceType = compareDto.getSummaryByServiceType();
          Map<String, SummaryDTO> compareSummaryByCustomer = compareDto.getSummaryByCustomer();

          SummaryDTO oldSuccess = compareSummaryByStatus.get("SUCCESS") == null ? new SummaryDTO() : compareSummaryByStatus.get("SUCCESS");
          SummaryDTO oldFail = compareSummaryByStatus.get("FAIL") == null ? new SummaryDTO() : compareSummaryByStatus.get("FAIL");
          SummaryDTO oldOpen = compareSummaryByStatus.get("OPEN") == null ? new SummaryDTO() : compareSummaryByStatus.get("OPEN");
          SummaryDTO oldPending = compareSummaryByStatus.get("PENDING") == null ? new SummaryDTO() : compareSummaryByStatus.get("PENDING");
          SummaryDTO oldProcessing = compareSummaryByStatus.get("PROCESSING") == null ? new SummaryDTO() : compareSummaryByStatus.get("PROCESSING");

          //summary
          map.put("success", success);
          map.put("fail", fail);
          map.put("open", open);
          map.put("pending", pending);
          map.put("processing", processing);
          map.put("summaryByDate", summaryByDate);

          map.put("oldSuccess", oldSuccess);
          map.put("oldFail", oldFail);
          map.put("oldOpen", oldOpen);
          map.put("oldPending", oldPending);
          map.put("oldProcessing", oldProcessing);

          //line chart
          map.put("filterByDate", true);

          // trục Ox
          List<String> dateRange = new ArrayList<>();
          for (Map.Entry<String, SummaryDTO> entry : summaryByDate.entrySet()) {
            dateRange.add(entry.getKey());
          }
          map.put("dateRange", dateRange);

          // label
          DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
          map.put("oldDate", dateFormat.format(getSameRangeWithCurrentMonth(dates)[0]) + " - " + dateFormat.format(getSameRangeWithCurrentMonth(dates)[1]));
          map.put("newDate", dateFormat.format(dates[0]) + " - " + dateFormat.format(curr));

          map.put("summaryByCustomerType", summaryByCustomerType);
          map.put("summaryByProvider", summaryByProvider);
          map.put("summaryByServiceType", summaryByServiceType);
          map.put("compareSummaryByCustomerType", compareSummaryByCustomerType);
          map.put("compareSummaryByProvider", compareSummaryByProvider);
          map.put("compareSummaryByServiceType", compareSummaryByServiceType);
          map.put("compareSummaryByDate", compareSummaryByDate);

          //pie chart
          List<SummaryCompare> revenueByCus = mergeMapsToObject(summaryByCustomerType, compareSummaryByCustomerType);

          List<SummaryCompare> revenueByProdvider = mergeMapsToObject(summaryByProvider, compareSummaryByProvider);

          List<SummaryCompare> revenueByService = mergeMapsToObject(summaryByServiceType, compareSummaryByServiceType);

          map.put("revenueByCus", revenueByCus);
          map.put("revenueByProdvider", revenueByProdvider);
          map.put("revenueByService", revenueByService);
          map.put("summaryByCustomer", summaryByCustomer);
          map.put("passSummaryByCustomer", compareSummaryByCustomer);

        } else if (isWholeMonth(dates)) {
          //khoảng thời gian trọn vẹn 1 tháng => so sánh với cả tháng trước
          SummaryTransactionStatisticByDateRequest req = new SummaryTransactionStatisticByDateRequest();
          req.setFromDate(dates[0]);
          req.setToDate(dates[1]);
          req.setServiceTypes(serviceTypeEnums);
          req.setCustomerTypes(customerTypeEnums);
          req.setProviderCodes(providerCodeEnums);
          req.setStatus(statusEnums);
          req.setCustomerIds(cusIds.size() > 0 ? cusIds : null);

          GeneralResponse<Object> res = transactionStatisticEndpoint.summaryTransactionStatisticByDate(req);
          SummaryTransactionStatisticByDateDTO dto = Utils.responseToObject(res.getData(), SummaryTransactionStatisticByDateDTO.class);
          Map<String, SummaryDTO> summaryByStatus = dto.getSummaryByStatus();
          Map<String, SummaryDTO> summaryByCustomerType = dto.getSummaryByCustomerType();
          Map<String, SummaryDTO> summaryByDate = dto.getSummaryByDate();
          Map<String, SummaryDTO> summaryByProvider = dto.getSummaryByProvider();
          Map<String, SummaryDTO> summaryByServiceType = dto.getSummaryByServiceType();
          Map<String, SummaryDTO> summaryByCustomer = dto.getSummaryByCustomer();

          SummaryDTO success = summaryByStatus.get("SUCCESS") == null ? new SummaryDTO() : summaryByStatus.get("SUCCESS");
          SummaryDTO fail = summaryByStatus.get("FAIL") == null ? new SummaryDTO() : summaryByStatus.get("FAIL");
          SummaryDTO open = summaryByStatus.get("OPEN") == null ? new SummaryDTO() : summaryByStatus.get("OPEN");
          SummaryDTO pending = summaryByStatus.get("PENDING") == null ? new SummaryDTO() : summaryByStatus.get("PENDING");
          SummaryDTO processing = summaryByStatus.get("PROCESSING") == null ? new SummaryDTO() : summaryByStatus.get("PROCESSING");

          ////////////////////////////////////
          //////////    COMPARE    ///////////
          ////////////////////////////////////

          SummaryTransactionStatisticByDateRequest compareReq = new SummaryTransactionStatisticByDateRequest();
          compareReq.setFromDate(getPreviousMonth(dates)[0]);
          compareReq.setToDate(getPreviousMonth(dates)[1]);
          compareReq.setServiceTypes(serviceTypeEnums);
          compareReq.setCustomerTypes(customerTypeEnums);
          compareReq.setProviderCodes(providerCodeEnums);
          compareReq.setStatus(statusEnums);
          compareReq.setCustomerIds(cusIds.size() > 0 ? cusIds : null);

          GeneralResponse<Object> compareRes = transactionStatisticEndpoint.summaryTransactionStatisticByDate(compareReq);
          SummaryTransactionStatisticByDateDTO compareDto = Utils.responseToObject(compareRes.getData(), SummaryTransactionStatisticByDateDTO.class);
          Map<String, SummaryDTO> compareSummaryByStatus = compareDto.getSummaryByStatus();
          Map<String, SummaryDTO> compareSummaryByCustomerType = compareDto.getSummaryByCustomerType();
          Map<String, SummaryDTO> compareSummaryByDate = compareDto.getSummaryByDate();
          Map<String, SummaryDTO> compareSummaryByProvider = compareDto.getSummaryByProvider();
          Map<String, SummaryDTO> compareSummaryByServiceType = compareDto.getSummaryByServiceType();
          Map<String, SummaryDTO> compareSummaryByCustomer = compareDto.getSummaryByCustomer();

          SummaryDTO oldSuccess = compareSummaryByStatus.get("SUCCESS") == null ? new SummaryDTO() : compareSummaryByStatus.get("SUCCESS");
          SummaryDTO oldFail = compareSummaryByStatus.get("FAIL") == null ? new SummaryDTO() : compareSummaryByStatus.get("FAIL");
          SummaryDTO oldOpen = compareSummaryByStatus.get("OPEN") == null ? new SummaryDTO() : compareSummaryByStatus.get("OPEN");
          SummaryDTO oldPending = compareSummaryByStatus.get("PENDING") == null ? new SummaryDTO() : compareSummaryByStatus.get("PENDING");
          SummaryDTO oldProcessing = compareSummaryByStatus.get("PROCESSING") == null ? new SummaryDTO() : compareSummaryByStatus.get("PROCESSING");

          //summary
          map.put("success", success);
          map.put("fail", fail);
          map.put("open", open);
          map.put("pending", pending);
          map.put("processing", processing);
          map.put("summaryByDate", summaryByDate);

          map.put("oldSuccess", oldSuccess);
          map.put("oldFail", oldFail);
          map.put("oldOpen", oldOpen);
          map.put("oldPending", oldPending);
          map.put("oldProcessing", oldProcessing);

          //line chart
          map.put("filterByDate", true);

          // trục Ox
          List<String> dateRange = new ArrayList<>();
          for (Map.Entry<String, SummaryDTO> entry : summaryByDate.entrySet()) {
            dateRange.add(entry.getKey());
          }
          map.put("dateRange", dateRange);

          // label
          DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
          map.put("oldDate", dateFormat.format(getPreviousMonth(dates)[0]) + " - " + dateFormat.format(getPreviousMonth(dates)[1]));
          map.put("newDate", dateFormat.format(dates[0]) + " - " + dateFormat.format(dates[1]));

          map.put("summaryByCustomerType", summaryByCustomerType);
          map.put("summaryByProvider", summaryByProvider);
          map.put("summaryByServiceType", summaryByServiceType);
          map.put("compareSummaryByCustomerType", compareSummaryByCustomerType);
          map.put("compareSummaryByProvider", compareSummaryByProvider);
          map.put("compareSummaryByServiceType", compareSummaryByServiceType);
          map.put("compareSummaryByDate", compareSummaryByDate);

          //pie chart
          List<SummaryCompare> revenueByCus = mergeMapsToObject(summaryByCustomerType, compareSummaryByCustomerType);

          List<SummaryCompare> revenueByProdvider = mergeMapsToObject(summaryByProvider, compareSummaryByProvider);

          List<SummaryCompare> revenueByService = mergeMapsToObject(summaryByServiceType, compareSummaryByServiceType);

          map.put("revenueByCus", revenueByCus);
          map.put("revenueByProdvider", revenueByProdvider);
          map.put("revenueByService", revenueByService);
          map.put("summaryByCustomer", summaryByCustomer);
          map.put("passSummaryByCustomer", compareSummaryByCustomer);


        } else {
          // khoản thời gian là 1 khoảng thời điểm bất kì trong quá khứ
          // => so sánh khoảng thời gian này với khoảng thời gian trước đó ví dụ 1-5/04/2021 -> 26-31/03/2021
          SummaryTransactionStatisticByDateRequest req = new SummaryTransactionStatisticByDateRequest();
          req.setFromDate(dates[0]);
          req.setToDate(dates[1]);
          req.setServiceTypes(serviceTypeEnums);
          req.setCustomerTypes(customerTypeEnums);
          req.setProviderCodes(providerCodeEnums);
          req.setStatus(statusEnums);
          req.setCustomerIds(cusIds.size() > 0 ? cusIds : null);

          GeneralResponse<Object> res = transactionStatisticEndpoint.summaryTransactionStatisticByDate(req);
          SummaryTransactionStatisticByDateDTO dto = Utils.responseToObject(res.getData(), SummaryTransactionStatisticByDateDTO.class);
          Map<String, SummaryDTO> summaryByStatus = dto.getSummaryByStatus();
          Map<String, SummaryDTO> summaryByCustomerType = dto.getSummaryByCustomerType();
          Map<String, SummaryDTO> summaryByDate = dto.getSummaryByDate();
          Map<String, SummaryDTO> summaryByProvider = dto.getSummaryByProvider();
          Map<String, SummaryDTO> summaryByServiceType = dto.getSummaryByServiceType();
          Map<String, SummaryDTO> summaryByCustomer = dto.getSummaryByCustomer();

          SummaryDTO success = summaryByStatus.get("SUCCESS") == null ? new SummaryDTO() : summaryByStatus.get("SUCCESS");
          SummaryDTO fail = summaryByStatus.get("FAIL") == null ? new SummaryDTO() : summaryByStatus.get("FAIL");
          SummaryDTO open = summaryByStatus.get("OPEN") == null ? new SummaryDTO() : summaryByStatus.get("OPEN");
          SummaryDTO pending = summaryByStatus.get("PENDING") == null ? new SummaryDTO() : summaryByStatus.get("PENDING");
          SummaryDTO processing = summaryByStatus.get("PROCESSING") == null ? new SummaryDTO() : summaryByStatus.get("PROCESSING");

          ////////////////////////////////////
          //////////    COMPARE    ///////////
          ////////////////////////////////////

          SummaryTransactionStatisticByDateRequest compareReq = new SummaryTransactionStatisticByDateRequest();
          compareReq.setFromDate(getReflectHistoryDateRange(dates)[0]);
          compareReq.setToDate(getReflectHistoryDateRange(dates)[1]);
          compareReq.setServiceTypes(serviceTypeEnums);
          compareReq.setCustomerTypes(customerTypeEnums);
          compareReq.setProviderCodes(providerCodeEnums);
          compareReq.setStatus(statusEnums);
          compareReq.setCustomerIds(cusIds.size() > 0 ? cusIds : null);

          GeneralResponse<Object> compareRes = transactionStatisticEndpoint.summaryTransactionStatisticByDate(compareReq);
          SummaryTransactionStatisticByDateDTO compareDto = Utils.responseToObject(compareRes.getData(), SummaryTransactionStatisticByDateDTO.class);
          Map<String, SummaryDTO> compareSummaryByStatus = compareDto.getSummaryByStatus();
          Map<String, SummaryDTO> compareSummaryByCustomerType = compareDto.getSummaryByCustomerType();
          Map<String, SummaryDTO> compareSummaryByDate = compareDto.getSummaryByDate();
          Map<String, SummaryDTO> compareSummaryByProvider = compareDto.getSummaryByProvider();
          Map<String, SummaryDTO> compareSummaryByServiceType = compareDto.getSummaryByServiceType();
          Map<String, SummaryDTO> compareSummaryByCustomer = compareDto.getSummaryByCustomer();

          SummaryDTO oldSuccess = compareSummaryByStatus.get("SUCCESS") == null ? new SummaryDTO() : compareSummaryByStatus.get("SUCCESS");
          SummaryDTO oldFail = compareSummaryByStatus.get("FAIL") == null ? new SummaryDTO() : compareSummaryByStatus.get("FAIL");
          SummaryDTO oldOpen = compareSummaryByStatus.get("OPEN") == null ? new SummaryDTO() : compareSummaryByStatus.get("OPEN");
          SummaryDTO oldPending = compareSummaryByStatus.get("PENDING") == null ? new SummaryDTO() : compareSummaryByStatus.get("PENDING");
          SummaryDTO oldProcessing = compareSummaryByStatus.get("PROCESSING") == null ? new SummaryDTO() : compareSummaryByStatus.get("PROCESSING");

          //summary
          map.put("success", success);
          map.put("fail", fail);
          map.put("open", open);
          map.put("pending", pending);
          map.put("processing", processing);
          map.put("summaryByDate", summaryByDate);

          map.put("oldSuccess", oldSuccess);
          map.put("oldFail", oldFail);
          map.put("oldOpen", oldOpen);
          map.put("oldPending", oldPending);
          map.put("oldProcessing", oldProcessing);

          //line chart
          map.put("filterByDate", true);

          // trục Ox
          List<String> dateRange = new ArrayList<>();
          for (Map.Entry<String, SummaryDTO> entry : summaryByDate.entrySet()) {
            dateRange.add(entry.getKey());
          }
          map.put("dateRange", dateRange);

          // label
          DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
          map.put("oldDate", dateFormat.format(getReflectHistoryDateRange(dates)[0]) + " - " + dateFormat.format(getReflectHistoryDateRange(dates)[1]));
          map.put("newDate", dateFormat.format(dates[0]) + " - " + dateFormat.format(dates[1]));

          map.put("summaryByCustomerType", summaryByCustomerType);
          map.put("summaryByProvider", summaryByProvider);
          map.put("summaryByServiceType", summaryByServiceType);
          map.put("compareSummaryByCustomerType", compareSummaryByCustomerType);
          map.put("compareSummaryByProvider", compareSummaryByProvider);
          map.put("compareSummaryByServiceType", compareSummaryByServiceType);
          map.put("compareSummaryByDate", compareSummaryByDate);

          //pie chart
          List<SummaryCompare> revenueByCus = mergeMapsToObject(summaryByCustomerType, compareSummaryByCustomerType);

          List<SummaryCompare> revenueByProdvider = mergeMapsToObject(summaryByProvider, compareSummaryByProvider);

          List<SummaryCompare> revenueByService = mergeMapsToObject(summaryByServiceType, compareSummaryByServiceType);

          map.put("revenueByCus", revenueByCus);
          map.put("revenueByProdvider", revenueByProdvider);
          map.put("revenueByService", revenueByService);
          map.put("summaryByCustomer", summaryByCustomer);
          map.put("passSummaryByCustomer", compareSummaryByCustomer);
        }
      }

      map.put("serviceTypeSeleted", serviceTypeEnums);
      map.put("customerTypeSelected", customerTypeEnums);
      map.put("providerCodeSelected", providerCodeEnums);
      map.put("serviceTypes", ServiceTypeEnum.PAYMENT_TYPES);
      map.put("providerTypes", Arrays.asList(ProviderCodeEnum.values()));
      map.put("txnStatuses", StatusEnum.TRANSACTION_STATUSES);

    } catch (Exception e) {
      LOG.error(e.getMessage());
    }
    return "/transaction_dashboard/dashboard";
  }

  public Date[] getReflectHistoryDateRange(Date[] dates) throws ParseException {
    if (dates[0] == null || dates[1] == null) {
      return null;
    }

    Date[] reflect = new Date[2];
    Long range = ChronoUnit.DAYS.between(convertToLocalDateViaInstant(dates[0]), convertToLocalDateViaInstant(dates[1]));

    String d0 = getHourViaString(dates[0]) + " " + convertToLocalDateViaInstant(dates[0]).minusDays(1 + range).toString();
    String d1 = getHourViaString(dates[1]) + " " + convertToLocalDateViaInstant(dates[0]).minusDays(1).toString();
    reflect[0] = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd").parse(d0);
    reflect[1] = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd").parse(d1);
    return reflect;
  }

  public Date[] getReflectHistoryDateRangeForCurrentDay(Date[] dates) throws ParseException {
    if (dates[0] == null || dates[1] == null) {
      return null;
    }

    Date[] reflect = new Date[2];
    Long range = ChronoUnit.DAYS.between(convertToLocalDateViaInstant(dates[0]), convertToLocalDateViaInstant(dates[1]));

    String d0 = getHourViaString(dates[0]) + " " + convertToLocalDateViaInstant(dates[0]).minusDays(1 + range).toString();
    String d1 = getHourViaStringForCurrentDate(dates[1]) + " " + convertToLocalDateViaInstant(dates[0]).minusDays(1).toString();
    reflect[0] = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd").parse(d0);
    reflect[1] = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd").parse(d1);
    return reflect;
  }

  public Date[] getReflectHistoryHourRange(Date[] dates) throws ParseException {
    if (dates[0] == null || dates[1] == null) {
      return null;
    }

    Date[] reflect = new Date[2];

    String date0 = getHourViaString(dates[0]) + " " + convertToLocalDateViaInstant(dates[0]).minusDays(1).toString();
    String date1 = getHourViaString(dates[1]) + " " + convertToLocalDateViaInstant(dates[1]).minusDays(1).toString();

    reflect[0] = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd").parse(date0);
    reflect[1] = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd").parse(date1);
    return reflect;
  }

  public boolean isWholeMonth(Date[] dates) {
    if (dates[0].getMonth() == dates[1].getMonth() && dates[0].getYear() == dates[1].getYear()) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(dates[0]);
      Calendar cal2 = Calendar.getInstance();
      cal2.setTime(dates[1]);
      int dayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
      if (cal.get(Calendar.DAY_OF_MONTH) == 1 && cal2.get(Calendar.DAY_OF_MONTH) == dayOfMonth) {
        return true;
      }
    }
    return false;
  }

  public Date[] getPreviousMonth(Date[] dates) throws ParseException {
    if (dates[0] == null || dates[1] == null) {
      return null;
    }

    Date[] reflect = new Date[2];
    Integer[] month1 = new Integer[]{0, 2, 4, 6, 7, 9, 11};
    Integer[] month2 = new Integer[]{3, 5, 8, 10};

    List<Integer> list1 = Arrays.asList(month1);
    List<Integer> list2 = Arrays.asList(month2);

    String date0 = null;
    String date1 = null;


    if (list1.contains(dates[0].getMonth())) {
      date0 = getHourViaString(dates[0]) + " " + convertToLocalDateViaInstant(dates[0]).minusDays(30).toString();
      date1 = getHourViaString(dates[1]) + " " + convertToLocalDateViaInstant(dates[1]).minusDays(31).toString();

    } else if (list2.contains(dates[0].getMonth())) {
      date0 = getHourViaString(dates[0]) + " " + convertToLocalDateViaInstant(dates[0]).minusDays(31).toString();
      date1 = getHourViaString(dates[1]) + " " + convertToLocalDateViaInstant(dates[1]).minusDays(30).toString();
    } else {
      if ((dates[0].getYear() + 1900) % 4 == 0 && (dates[0].getYear() + 1900) % 100 > 0) {
        date0 = getHourViaString(dates[0]) + " " + convertToLocalDateViaInstant(dates[0]).minusDays(31).toString();
        date1 = getHourViaString(dates[1]) + " " + convertToLocalDateViaInstant(dates[1]).minusDays(29).toString();
      } else {
        date0 = getHourViaString(dates[0]) + " " + convertToLocalDateViaInstant(dates[0]).minusDays(31).toString();
        date1 = getHourViaString(dates[1]) + " " + convertToLocalDateViaInstant(dates[1]).minusDays(28).toString();
      }
    }

    reflect[0] = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd").parse(date0);
    reflect[1] = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd").parse(date1);
    return reflect;
  }

  public Date[] getSameRangeWithCurrentMonth(Date[] dates) throws ParseException {
    if (dates[0] == null || dates[1] == null) {
      return null;
    }

    Date[] reflect = new Date[2];
    Date curr = new Date();

    String date0 = getHourViaString(dates[0]) + " " + convertToLocalDateViaInstant(dates[0]).minusMonths(1).toString();
    String date1 = getHourViaString(curr) + " " + convertToLocalDateViaInstant(curr).minusMonths(1).toString();

    reflect[0] = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd").parse(date0);
    reflect[1] = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd").parse(date1);
    return reflect;
  }


  public boolean isCurrentMonth(Date[] dates) {
    Date curr = new Date();
    int currMonth = curr.getMonth();
    if (dates[0].getMonth() == currMonth && dates[0].getYear() == curr.getYear()) {
      return true;
    }
    return false;
  }

  public boolean isFirstDayOfMonth(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);

    if (cal.get(Calendar.DAY_OF_MONTH) == 1) {
      return true;
    }
    return false;
  }

  public List<SummaryCompare> mergeMapsToObject(Map<String, SummaryDTO> currentTimeMap, Map<String, SummaryDTO> pastReferenceTimeMap) {
    List<SummaryCompare> revenueByCus = new ArrayList<>();
    for (Map.Entry<String, SummaryDTO> entry : currentTimeMap.entrySet()) {
      SummaryCompare summaryCompare = new SummaryCompare();
      summaryCompare.setKey(entry.getKey());
      SummaryDTO summaryDTO = new SummaryDTO();
      summaryDTO.setSumOfRevenueAmount(entry.getValue().getSumOfRevenueAmount());
      summaryDTO.setNumOfTrans(entry.getValue().getNumOfTrans());
      summaryDTO.setSumOfRequestAmount(entry.getValue().getSumOfRequestAmount());
      summaryCompare.setNewData(summaryDTO);
      revenueByCus.add(summaryCompare);
    }


    for (Map.Entry<String, SummaryDTO> entry : pastReferenceTimeMap.entrySet()) {
      for (SummaryCompare sc : revenueByCus) {
        if (sc.getKey().equals(entry.getKey())) {
          SummaryDTO summaryDTO = new SummaryDTO();
          summaryDTO.setSumOfRevenueAmount(entry.getValue().getSumOfRevenueAmount());
          summaryDTO.setNumOfTrans(entry.getValue().getNumOfTrans());
          summaryDTO.setSumOfRequestAmount(entry.getValue().getSumOfRequestAmount());
          sc.setOldData(summaryDTO);
          break;
        }
      }
    }

    return revenueByCus;
  }

  public String getEmailFrom(String accessToken) {
    try {
      DecodedJWT jwt = JWT.decode(accessToken);
      String email = jwt.getClaim("email").asString();
      return email;
    } catch (JWTDecodeException exception) {
      LOG.error(exception.getMessage(), exception);
    }
    return StringUtils.EMPTY;
  }

}
