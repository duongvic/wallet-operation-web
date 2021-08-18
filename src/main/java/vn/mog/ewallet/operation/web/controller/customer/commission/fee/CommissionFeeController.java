package vn.mog.ewallet.operation.web.controller.customer.commission.fee;

import static org.apache.commons.lang.StringUtils.EMPTY;
import static vn.mog.ewallet.operation.web.controller.service.TrueServiceController.SESSION_SERVICE_TYPE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.contract.AjaxResponse;
import vn.mog.ewallet.operation.web.contract.form.SearchDataForm;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.exception.MessageNotify;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.DeleteCommissionFreeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.DeleteSpecifiedCommissionFreeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.FindCommissionFeeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.FindCommissionFeeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.FindSpecifiedCommissionFeeCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.FindSpecifiedCommissionFeeCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.GetCommissionFeeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.GetCommissionFeeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.UpdateCommissionFreeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.UpdateCommissionFreeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.UpdateSpecifiedCommissionFreeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.UpdateSpecifiedCommissionFreeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.beans.Customer;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.PaymentChannel;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;
import vn.mog.framework.common.utils.NumberUtil;
import vn.mog.framework.common.utils.SessionUtil;
import vn.mog.framework.common.utils.StringUtil;
import vn.mog.framework.contract.base.MobiliserResponseType;

@Controller
@RequestMapping(value = "")
public class CommissionFeeController extends AbstractController {

  private static final Logger LOG = LogManager.getLogger(CommissionFeeController.class);
  public static final String CUSTOMER_FEE_STRUCTURE_TYPE = "/customer/fee-type/list";
  public static final String SPECIFIED_COMMISSION_FEE_CUSTOMERS =
      "/customer/fee-type/specified/customers";

  /*  @GetMapping(value = "/customer/fee-type/list-all")
  @PreAuthorize("isAuthenticated()")
  public String searchAll(HttpServletRequest request, HttpServletResponse response, ModelMap model,
      @ModelAttribute SearchDataForm searchDataForm)
      throws RuntimeException {

    searchCommonCommissionFees(request, response, model, searchDataForm);

    return "/fee_structure/all_type_fee";
  }*/

  @GetMapping(value = "/customer/fee-type/list")
  @PreAuthorize("isAuthenticated()")
  public String searchSourceOfFund(
      HttpServletRequest request,
      HttpServletResponse response,
      ModelMap model,
      @ModelAttribute SearchDataForm searchDataForm)
      throws RuntimeException {
    Long customerTypeId = NumberUtil.convertToLong(request.getParameter("customerTypeId"));
    if (customerTypeId > 0) {
      searchDataForm.setCustomerIds(Collections.singletonList(customerTypeId));
    }
    searchCommonCommissionFees(request, response, model, searchDataForm);

    return "/fee_structure/once_type_fee";
  }

  private void searchCommonCommissionFees(
      HttpServletRequest request,
      HttpServletResponse response,
      ModelMap model,
      SearchDataForm searchDataForm) {

    String paramServiceTypeIds = StringUtils.trimToEmpty(request.getParameter("serviceTypeIds"));

    FindCommissionFeeRequest commissionFeeReq = new FindCommissionFeeRequest();

    // choose default service type if it null
    if (EMPTY.equals(paramServiceTypeIds)) {
      String sServiceType = (String) SessionUtil.getAttribute(SESSION_SERVICE_TYPE);
      if (sServiceType == null) {
        commissionFeeReq.setServiceTypes(Collections.singletonList(ServiceType.FUND_IN.name()));
      } else {
        commissionFeeReq.setServiceTypes(Collections.singletonList(sServiceType));
      }
    } else {
      SessionUtil.setAttribute(SESSION_SERVICE_TYPE, paramServiceTypeIds);
      commissionFeeReq.setServiceTypes(Collections.singletonList(paramServiceTypeIds));
    }

    commissionFeeReq.setQuickSearch(searchDataForm.getQuickSearch());
    commissionFeeReq.setCustomerType(searchDataForm.getCustomerTypeIdOrDefault());
    commissionFeeReq.setPaymentChannelId(searchDataForm.getPaymentChannelIdOrDefault());

    FindCommissionFeeResponse commissionFeesRes =
        paymentCustomerEndpoint.findCommissionFees(commissionFeeReq);
    model.put("customerTypeFees", commissionFeesRes.getCommissionFees());
    model.put(
        "services", genderTrueServiceTree(new ArrayList(commissionFeesRes.getCommissionFees())));
    model.put("total", commissionFeesRes.getTotal());
    model.put("totalServices", commissionFeesRes.getAll());

    model.put("customerTypeName", searchDataForm.getNameCustomerTypeId());
    model.put("serviceTypes", ServiceType.FULL_SERVICE_TYPES);
    model.put("customerTypes", CustomerType.USER_CUSTOMER_TYPES);
    model.put(
        "paymentChannels",
        new PaymentChannel[] {PaymentChannel.ZOTA, PaymentChannel.SAPO, PaymentChannel.ZOTAHOME});
  }

  @RequestMapping(value = "/customer/specified-commission-fee/update", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + RoleConstants.SALE_DIRECTOR
          + "','"
          + RoleConstants.SALESUPPORT_MANAGER
          + "')")
  public ResponseEntity<?> updateSpecifiedCommissionFee(HttpServletRequest request)
      throws IOException {
    try {
      Long serviceId = Long.parseLong(request.getParameter("serviceId"));
      Long customerId = Long.parseLong(request.getParameter("customerId"));
      Double commission = stringToDouble(request.getParameter("commissionInput"));
      Long commissionFixedAmount = stringToLong(request.getParameter("commissionFixedAmountInput"));
      Double fee = stringToDouble(request.getParameter("feeInput"));
      Long feeFixedAmount = stringToLong(request.getParameter("feeFixedAmountInput"));

      AjaxResponse ajResponse =
          validation.updateSpecifiedCommissionFee(
              commission, commissionFixedAmount, fee, feeFixedAmount);
      if (ajResponse.getCode() == MessageNotify.ERROR_CODE) {
        return ResponseEntity.ok(ajResponse);
      }

      UpdateSpecifiedCommissionFreeRequest updateRequest =
          new UpdateSpecifiedCommissionFreeRequest();
      updateRequest.setServiceId(serviceId);
      updateRequest.setCustomerId(customerId);
      updateRequest.setSpecifiedCommission(commission);
      updateRequest.setSpecifiedCommissionFixedAmount(commissionFixedAmount);
      updateRequest.setSpecifiedFee(fee);
      updateRequest.setSpecifiedFeeFixedAmount(feeFixedAmount);
      UpdateSpecifiedCommissionFreeResponse updateResponse =
          paymentCustomerEndpoint.updateSpecifiedCommissionFee(updateRequest);

      return responseAjax(
          updateResponse.getStatus().getCode(), updateResponse.getStatus().getValue());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);

      return ResponseEntity.ok(new AjaxResponse(MessageNotify.ERROR_CODE, e.getMessage()));
    }
  }

  @RequestMapping(value = "/customer/specified-commission-fee/delete", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + RoleConstants.SALE_DIRECTOR
          + "','"
          + RoleConstants.SALESUPPORT_MANAGER
          + "')")
  public ResponseEntity<?> deleteSpecifiedCommissionFee(HttpServletRequest request)
      throws IOException {
    try {
      Long serviceId = Long.parseLong(request.getParameter("serviceId"));
      Long customerId = Long.parseLong(request.getParameter("customerId"));

      DeleteSpecifiedCommissionFreeRequest deleteRequest =
          new DeleteSpecifiedCommissionFreeRequest();
      deleteRequest.setServiceId(serviceId);
      deleteRequest.setCustomerId(customerId);

      MobiliserResponseType deleteResponse =
          paymentCustomerEndpoint.deleteSpecifiedCommissionFee(deleteRequest);

      return responseAjax(
          deleteResponse.getStatus().getCode(), deleteResponse.getStatus().getValue());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);

      return ResponseEntity.ok(new AjaxResponse(MessageNotify.ERROR_CODE, e.getMessage()));
    }
  }

  @GetMapping(value = "/customer/fee-type/specified/customers")
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + RoleConstants.SALE_DIRECTOR
          + "','"
          + RoleConstants.SALESUPPORT_MANAGER
          + "','"
          + RoleConstants.RECONCILIATION_LEADER
          + "','"
          + RoleConstants.RECONCILIATION
          + "')")
  public String findSpecifiedCommissionFeeCustomer(
      HttpServletRequest request,
      HttpServletResponse response,
      ModelMap map,
      @ModelAttribute SearchDataForm searchDataForm)
      throws RuntimeException {
    int offset = 0;
    int limit = 20;

    if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
      offset = Integer.parseInt(request.getParameter("d-49489-p"));
      if (offset > 0) {
        offset = (offset - 1) * limit;
      }
    }

    List<Customer> customerList = new ArrayList<>();

    try {
      String paramText = StringUtils.trimToEmpty(request.getParameter("search"));
      String paramRange = StringUtils.trimToEmpty(request.getParameter("range"));
      String paramServiceTypeIds = StringUtils.trimToEmpty(request.getParameter("serviceTypeIds"));
      String paramStatus = StringUtils.trimToEmpty(request.getParameter("status"));

      Date[] dates = parseDateRange(paramRange, true);

      FindSpecifiedCommissionFeeCustomerRequest findRequest =
          new FindSpecifiedCommissionFeeCustomerRequest();

      findRequest.setFromDate(dates[0]);
      findRequest.setEndDate(dates[1]);

      if (StringUtil.TRUE.equals(paramStatus)) {
        findRequest.setActive(StringUtil.CHAR_YES);
      } else if (StringUtil.FALSE.equals(paramStatus)) {
        findRequest.setActive(StringUtil.CHAR_NO);
      }

      if (EMPTY.equals(paramServiceTypeIds)) {
        String sServiceType = (String) SessionUtil.getAttribute(SESSION_SERVICE_TYPE);
        if (sServiceType == null) {
          findRequest.setServiceTypes(Collections.singletonList(ServiceType.FUND_IN.name()));
        } else {
          findRequest.setServiceTypes(Collections.singletonList(sServiceType));
        }
      } else {
        SessionUtil.setAttribute(SESSION_SERVICE_TYPE, paramServiceTypeIds);
        findRequest.setServiceTypes(Collections.singletonList(paramServiceTypeIds));
      }

      findRequest.setQuickSearch(paramText);
      findRequest.setOffset(offset);
      findRequest.setLimit(limit);
      FindSpecifiedCommissionFeeCustomerResponse findResponse =
          paymentCustomerEndpoint.findSpecifiedCommissionFeeCustomer(findRequest);

      customerList.addAll(findResponse.getCustomers());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }

    map.put("listCustomer", customerList);
    map.put("pagesize", limit);
    map.put("offset", offset);
    map.put("total", customerList.size());

    map.put("serviceTypes", ServiceType.FULL_SERVICE_TYPES);

    return "/fee_structure/special_customer_list";
  }

  @RequestMapping(value = "/customer/commission-fee/update", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + RoleConstants.SALE_DIRECTOR
          + "','"
          + RoleConstants.SALESUPPORT_MANAGER
          + "')")
  public ResponseEntity<?> updateCommissionFee(HttpServletRequest request) throws IOException {
    try {
      Long serviceId = Long.parseLong(request.getParameter("serviceId"));
      Integer customerTypeId = Integer.parseInt(request.getParameter("customerTypeId"));
      String paymentChannelId = request.getParameter("paymentChannelId");
      Double commission = stringToDouble(request.getParameter("commissionInput"));
      Long commissionFixedAmount = stringToLong(request.getParameter("commissionFixedAmountInput"));
      Double fee = stringToDouble(request.getParameter("feeInput"));
      Long feeFixedAmount = stringToLong(request.getParameter("feeFixedAmountInput"));

      AjaxResponse ajResponse =
          validation.updateSpecifiedCommissionFee(
              commission, commissionFixedAmount, fee, feeFixedAmount);
      if (ajResponse.getCode() == MessageNotify.ERROR_CODE) {
        return ResponseEntity.ok(ajResponse);
      }

      UpdateCommissionFreeRequest updateRequest = new UpdateCommissionFreeRequest();
      updateRequest.setServiceId(serviceId);
      updateRequest.setCustomerTypeId(customerTypeId);
      updateRequest.setPaymentChannelId(paymentChannelId);
      updateRequest.setCommission(commission);
      updateRequest.setCommissionFixedAmount(commissionFixedAmount);
      updateRequest.setFee(fee);
      updateRequest.setFeeFixedAmount(feeFixedAmount);
      UpdateCommissionFreeResponse updateResponse =
          paymentCustomerEndpoint.updateCommissionFee(updateRequest);

      return responseAjax(
          updateResponse.getStatus().getCode(), updateResponse.getStatus().getValue());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);

      return ResponseEntity.ok(new AjaxResponse(MessageNotify.ERROR_CODE, e.getMessage()));
    }
  }

  @RequestMapping(value = "/customer/commission-fee/get", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + RoleConstants.SALE_DIRECTOR
          + "','"
          + RoleConstants.SALESUPPORT_MANAGER
          + "')")
  public ResponseEntity<?> getCommissionFee(HttpServletRequest request) throws IOException {
    try {
      Long serviceId = Long.parseLong(request.getParameter("serviceId"));
      Integer customerTypeId = Integer.parseInt(request.getParameter("customerTypeId"));
      String paymentChannelId = request.getParameter("paymentChannelId");

      GetCommissionFeeRequest getCommissionFeeRequest = new GetCommissionFeeRequest();
      getCommissionFeeRequest.setServiceId(serviceId);
      getCommissionFeeRequest.setCustomerTypeId(customerTypeId);
      getCommissionFeeRequest.setPaymentChannelId(paymentChannelId);
      GetCommissionFeeResponse getResponse =
          paymentCustomerEndpoint.getCommissionFee(getCommissionFeeRequest);

      if (getResponse == null) {
        return ResponseEntity.ok(
            new AjaxResponse(MessageNotify.ERROR_CODE, "No response from Server"));
      }
      if (getResponse.getStatus().getCode() != MessageNotify.SUCCESS_CODE) {
        return ResponseEntity.ok(
            new AjaxResponse(
                getResponse.getStatus().getCode(), getResponse.getStatus().getValue()));
      }

      return ResponseEntity.ok(getResponse);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);

      return ResponseEntity.ok(new AjaxResponse(MessageNotify.ERROR_CODE, e.getMessage()));
    }
  }

  @RequestMapping(value = "/customer/commission-fee/delete", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + RoleConstants.SALE_DIRECTOR
          + "','"
          + RoleConstants.SALESUPPORT_MANAGER
          + "')")
  public ResponseEntity<?> deleteCommissionFee(HttpServletRequest request) throws IOException {
    try {
      Long serviceId = Long.parseLong(request.getParameter("serviceId"));
      Integer customerTypeId = Integer.parseInt(request.getParameter("customerTypeId"));
      String paymentChannelId = request.getParameter("paymentChannelId");

      DeleteCommissionFreeRequest deleteRequest = new DeleteCommissionFreeRequest();
      deleteRequest.setServiceId(serviceId);
      deleteRequest.setCustomerTypeId(customerTypeId);
      deleteRequest.setPaymentChannelId(paymentChannelId);

      MobiliserResponseType deleteResponse =
          paymentCustomerEndpoint.deleteCommissionFee(deleteRequest);

      return responseAjax(
          deleteResponse.getStatus().getCode(), deleteResponse.getStatus().getValue());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);

      return ResponseEntity.ok(new AjaxResponse(MessageNotify.ERROR_CODE, e.getMessage()));
    }
  }

  private Long stringToLong(String input) {
    try {
      return Long.parseLong(input);
    } catch (NumberFormatException e) {
      LOG.error(e.getMessage(), e);
    }

    return 0L;
  }

  private Double stringToDouble(String input) {
    try {
      return Double.parseDouble(input);
    } catch (NumberFormatException e) {
      LOG.error(e.getMessage(), e);
    }

    return 0D;
  }
}
