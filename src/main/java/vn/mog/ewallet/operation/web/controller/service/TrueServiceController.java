package vn.mog.ewallet.operation.web.controller.service;

import static org.apache.commons.lang.StringUtils.EMPTY;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.contract.AjaxResponse;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.controller.service.beans.ProviderOperation;
import vn.mog.ewallet.operation.web.controller.service.beans.ProviderServiceOperation;
import vn.mog.ewallet.operation.web.controller.service.beans.ServiceOperation;
import vn.mog.ewallet.operation.web.controller.service.beans.TransactionRuleMatrix;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.exception.MessageNotify;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.beans.CommissionFee;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.ChangeTrueServiceStatusRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.ChangeTrueServiceStatusResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.CreateTrueServiceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.CreateTrueServiceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.DeleteTrueServiceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.DeleteTrueServiceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.FindTrueServiceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.FindTrueServiceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.GetTrueServiceOperationRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.GetTrueServiceOperationResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.GetTrueServiceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.GetTrueServiceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.GetTrueServiceTransactionRuleMatrixRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.GetTrueServiceTransactionRuleMatrixResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.UpdateTrueServiceAttributeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.UpdateTrueServiceAttributeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.UpdateTrueServiceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.UpdateTrueServiceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.UpdateTrueServiceTransactionRuleMatrixRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.UpdateTrueServiceTransactionRuleMatrixResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.beans.TrueService;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.beans.TrueServiceAttribute;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.beans.TrueServiceOperation;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.TransactionRule;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ProviderCode;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceAttributeType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;
import vn.mog.ewallet.operation.web.utils.ValidationUtil;
import vn.mog.framework.common.utils.NumberUtil;
import vn.mog.framework.common.utils.SessionUtil;
import vn.mog.framework.common.utils.StringUtil;

@Controller
@RequestMapping(value = "/service/service-profile")
public class TrueServiceController extends AbstractController {

  public static final String TRUE_SERVICE_CONTROLLER = "/service/service-profile";
  public static final String TRUE_SERVICE_LIST = TRUE_SERVICE_CONTROLLER + "/list";
  public static final String TRUE_SERVICE_DETAIL = TRUE_SERVICE_CONTROLLER + "/detail";
  public static final String TRUE_SERVICE_OPERATION = TRUE_SERVICE_CONTROLLER + "/operation";
  public static final String SESSION_SERVICE_TYPE = "SESSION_SERVICE_TYPE";
  private static final Logger LOG = LogManager.getLogger(TrueServiceController.class);
  private static String SERVICE_TYPE = "serviceType";
  private static String SERVICE_CODE = "serviceCode";
  private static String SERVICE_ID = "serviceId";

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','STAFF') and !hasAnyRole('CUSTOMERCARE_MANAGER', 'TECH_SUPPORT')")
  public String listServices(HttpServletRequest request, ModelMap model) throws FrontEndException {

    String paramText = StringUtils.trimToEmpty(request.getParameter("search"));
    String paramRange = StringUtils.trimToEmpty(request.getParameter("range"));
    String paramServiceTypeIds = StringUtils.trimToEmpty(request.getParameter("serviceTypeIds"));
    String paramStatus = StringUtils.trimToEmpty(request.getParameter("status"));

    FindTrueServiceRequest ftsRequest = new FindTrueServiceRequest();

    Date[] dates = parseDateRange(paramRange, true);

    ftsRequest.setFromDate(dates[0]);
    ftsRequest.setEndDate(dates[1]);

    if (StringUtil.TRUE.equals(paramStatus)) {
      ftsRequest.setActive(StringUtil.CHAR_YES);
    } else if (StringUtil.FALSE.equals(paramStatus)) {
      ftsRequest.setActive(StringUtil.CHAR_NO);
    }
    ftsRequest.setQuickSearch(paramText);

    if (EMPTY.equals(paramServiceTypeIds)) {
      String sServiceType = (String) SessionUtil.getAttribute(SESSION_SERVICE_TYPE);
      if (sServiceType == null) {
        ftsRequest.setServiceTypes(Collections.singletonList(ServiceType.FUND_IN.name()));
      } else {
        ftsRequest.setServiceTypes(Collections.singletonList(sServiceType));
      }
    } else {
      SessionUtil.setAttribute(SESSION_SERVICE_TYPE, paramServiceTypeIds);
      ftsRequest.setServiceTypes(Collections.singletonList(paramServiceTypeIds));
    }

    FindTrueServiceResponse ftsResponse = trueServiceEndpoint.findTrueServices(ftsRequest);
    model.put("serviceTypes", ServiceType.FULL_SERVICE_TYPES);
    model.put("services", genderTrueServiceTree(ftsResponse.getServices()));
    model.put("totalServices", ftsResponse.getAll());

    return "/service/list";
  }

  /** Ham thay doi trang thai man hinh danh sach */
  @RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "', '"
          + RoleConstants.SALESUPPORT_MANAGER
          + "', 'SALESUPPORT_LEADER')")
  public ResponseEntity<?> changeStatus(HttpServletRequest request) throws FrontEndException {

    try {
      Long serviceId = NumberUtil.convertToLong(request.getParameter(SERVICE_ID));
      String active = StringUtils.trimToEmpty(request.getParameter("active"));

      AjaxResponse ajaxResponse = validation.changeTrueServiceStatus(serviceId, active);
      if (ajaxResponse.getCode() == 1) {
        return ResponseEntity.ok(ajaxResponse);
      }

      if (StringUtil.S_YES.equals(active)) {
        active = StringUtil.S_NO; // Tat chuyen N
      } else {
        active = StringUtil.S_YES;
      }

      ChangeTrueServiceStatusRequest ctsRequest =
          new ChangeTrueServiceStatusRequest(serviceId, active.charAt(0));
      ChangeTrueServiceStatusResponse ctsResponse =
          trueServiceEndpoint.changeTrueServiceStatus(ctsRequest);

      return responseAjax(ctsResponse.getStatus().getCode(), ctsResponse.getStatus().getValue());

    } catch (Exception e) {
      LOG.error("changeStatus", e);
      return ResponseEntity.ok(new AjaxResponse(MessageNotify.ERROR_CODE, e.getMessage()));
    }
  }

  /** Ham them moi danh sach service */
  @RequestMapping(value = "/createTrueService", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize(
      "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','SALESUPPORT_LEADER','SALESUPPORT')")
  public ResponseEntity<?> createTrueService(HttpServletRequest request) throws IOException {

    try {
      String serviceType = request.getParameter(SERVICE_TYPE);

      String serviceCode = StringUtils.trimToEmpty(request.getParameter("serviceCode"));
      String serviceName = StringUtils.trimToEmpty(request.getParameter("serviceName"));
      String serviceShortName = StringUtils.trimToEmpty(request.getParameter("serviceShortName"));
      String customerTypeSupported =
          StringUtils.trimToEmpty(request.getParameter("customerTypeSupported"));
      String parentServiceCode = StringUtils.trimToEmpty(request.getParameter("parentServiceCode"));
      String description = StringUtils.trimToEmpty(request.getParameter("serviceDesc"));
      String price = StringUtils.trimToEmpty(request.getParameter("serviceprice"));
      String parentfee = StringUtils.trimToEmpty(request.getParameter("parentfee"));

      String isActorPayAdd = request.getParameter("isActorPayAdd");

      AjaxResponse ajResponse =
          validation.createTrueService(serviceType, serviceCode, serviceName, isActorPayAdd);
      if (ajResponse.getCode() == 1) {
        return ResponseEntity.ok(ajResponse);
      }

      // bat try catch
      TrueService trueService = new TrueService();
      trueService.setServiceType(ServiceType.valueOf(serviceType));
      trueService.setServiceCode(serviceCode);
      trueService.setServiceName(serviceName);
      trueService.setServiceShortName(serviceShortName);
      trueService.setCustomerTypeSupported(customerTypeSupported);
      trueService.setParentServiceCode(parentServiceCode);
      trueService.setServiceDesc(description);
      trueService.setServicePrices(price);
      trueService.setParentFeeStructureAllowed(StringUtil.S_YES.equals(parentfee));
      trueService.setIsActorPayee(
          StringUtil.S_YES.equals(isActorPayAdd) ? StringUtil.CHAR_YES : StringUtil.CHAR_NO);

      CreateTrueServiceRequest ctsRequest = new CreateTrueServiceRequest(trueService);
      CreateTrueServiceResponse ctsResponse = trueServiceEndpoint.createTrueService(ctsRequest);

      return responseAjax(ctsResponse.getStatus().getCode(), ctsResponse.getStatus().getValue());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.ok(new AjaxResponse(1, e.getMessage()));
    }
  }

  /** Ham cap nhat bieu phi */
  @RequestMapping(value = "/updateTrueService", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize(
      "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "', 'SALESUPPORT_LEADER', 'SALESUPPORT')")
  public ResponseEntity<?> updateTrueService(HttpServletRequest request) throws FrontEndException {

    try {
      String serviceType = request.getParameter(SERVICE_TYPE);
      Long serviceId = NumberUtil.convertToLong(request.getParameter("serviceId"));
      String serviceCode = request.getParameter("serviceCodeEdit");
      String serviceName = request.getParameter("serviceName");
      String serviceShortName = StringUtils.trimToEmpty(request.getParameter("serviceShortName"));
      String customerTypeSupported =
          StringUtils.trimToEmpty(request.getParameter("customerTypeSupported"));
      Long parentServiceId =
          NumberUtil.convertToLong(request.getParameter("parentServiceCodeEdit"));
      String serviceDesc = request.getParameter("serviceDesc");
      String isActorPayEdit = request.getParameter("isActorPayEdit");
      String isParentFeeEdit = request.getParameter("parentfeeEdit");

      String price = StringUtils.trimToEmpty(request.getParameter("serviceprice"));

      AjaxResponse ajaxResponse =
          validation.updateTrueService(
              serviceId, parentServiceId, serviceType, serviceCode, serviceName, isActorPayEdit);
      if (ajaxResponse.getCode() == 1) {
        return ResponseEntity.ok(ajaxResponse);
      }

      UpdateTrueServiceRequest utsRequest = new UpdateTrueServiceRequest();
      utsRequest.setServiceId(serviceId);
      utsRequest.setServiceName(serviceName);
      utsRequest.setServiceShortName(serviceShortName);
      utsRequest.setCustomerTypeSupported(customerTypeSupported);
      utsRequest.setParentServiceId((parentServiceId == 0) ? null : parentServiceId);
      utsRequest.setServiceDesc(serviceDesc);
      utsRequest.setServiceType(serviceType);
      utsRequest.setIsActorPayee(
          StringUtil.S_YES.equals(isActorPayEdit) ? StringUtil.CHAR_YES : StringUtil.CHAR_NO);
      utsRequest.setParentFeeStructureAllowed(
          StringUtil.S_YES.equals(isParentFeeEdit) ? StringUtil.CHAR_YES : StringUtil.CHAR_NO);
      utsRequest.setServicePrices(price);

      UpdateTrueServiceResponse utsResponse = trueServiceEndpoint.updateTrueService(utsRequest);

      return responseAjax(utsResponse.getStatus().getCode(), utsResponse.getStatus().getValue());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.ok(new AjaxResponse(1, e.getMessage()));
    }
  }

  @RequestMapping(value = "/updateTrueServiceAttribute", method = RequestMethod.POST)
  @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "')")
  public String updateTrueServiceAttribute(HttpServletRequest request) throws FrontEndException {

    int numberRow = NumberUtil.convertToInt(request.getParameter("numberRow"));
    Long serviceId = NumberUtil.convertToLong(request.getParameter("serviceId"));
    List<TrueServiceAttribute> serviceAttributes = new ArrayList<>();
    TrueServiceAttribute serviceAttribute;

    for (int i = 0; i < numberRow; i++) {
      String serviceAttributeId = request.getParameter("serviceAttributeId" + i);
      String attributeValue = request.getParameter("attributeValue" + i);
      serviceAttribute = new TrueServiceAttribute(serviceAttributeId, attributeValue);
      serviceAttributes.add(serviceAttribute);
    }

    if (serviceId == 0) {
      SessionUtil.setAttribute(
          MessageNotify.SESSION_MESSAGE_NOTIFY,
          new MessageNotify(MessageNotify.ERROR_CODE, MessageNotify.ERROR_NAME));
      return "redirect:" + TRUE_SERVICE_LIST;
    }

    UpdateTrueServiceAttributeRequest utsaRequest =
        new UpdateTrueServiceAttributeRequest(serviceId, serviceAttributes);
    UpdateTrueServiceAttributeResponse utsaResponse =
        trueServiceEndpoint.updateTrueServiceAttribute(utsaRequest);
    if (utsaResponse.getStatus().getCode() == MessageNotify.SUCCESS_CODE) {
      SessionUtil.setAttribute(
          MessageNotify.SESSION_MESSAGE_NOTIFY,
          new MessageNotify(MessageNotify.SUCCESS_CODE, MessageNotify.SUCCESS_NAME));
      return "redirect:" + TRUE_SERVICE_DETAIL + "/" + serviceId;
    } else {
      SessionUtil.setAttribute(
          MessageNotify.SESSION_MESSAGE_NOTIFY,
          new MessageNotify(
              utsaResponse.getStatus().getCode(), utsaResponse.getStatus().getValue()));
      return "redirect:" + TRUE_SERVICE_DETAIL + "/" + serviceId;
    }
  }

  /** Ham xoa bieu phi man hinh danh sach */
  @RequestMapping(value = "/deleteTrueService", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "', 'SALESUPPORT_LEADER')")
  public ResponseEntity<?> deleteTrueService(HttpServletRequest request) throws FrontEndException {

    try {
      Long serviceId = NumberUtil.convertToLong(request.getParameter(SERVICE_ID));
      if (serviceId == 0L) {
        return ResponseEntity.ok(
            new AjaxResponse(1, validation.notify(ValidationUtil.SERVICE_CODE_NULL)));
      }

      DeleteTrueServiceRequest deleteRequest = new DeleteTrueServiceRequest(serviceId);
      DeleteTrueServiceResponse deleteResponse =
          trueServiceEndpoint.deleteTrueService(deleteRequest);

      return responseAjax(
          deleteResponse.getStatus().getCode(), deleteResponse.getStatus().getValue());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.ok(new AjaxResponse(1, e.getMessage()));
    }
  }

  /** Ham lay danh sach bieu phi cho combobox man hinh them moi */
  @RequestMapping(
      value = "/listFeeAdd",
      method = RequestMethod.POST,
      produces = "text/html;charset=utf-8")
  @PreAuthorize(
      "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "',  'SALESUPPORT_LEADER', 'SALESUPPORT')")
  @ResponseBody
  public String listFeeAdd(HttpServletRequest request) throws FrontEndException {

    JSONArray services = new JSONArray();
    JSONObject service;

    String serviceType = StringUtils.trimToEmpty(request.getParameter(SERVICE_TYPE));
    String serviceLevel = StringUtils.trimToEmpty(request.getParameter("serviceLevel"));

    List<String> serviceTypes = Collections.singletonList(serviceType);

    FindTrueServiceRequest findRequest =
        new FindTrueServiceRequest(serviceTypes, Integer.parseInt(serviceLevel));
    FindTrueServiceResponse findResponse = trueServiceEndpoint.findTrueServices(findRequest);

    List<CommissionFee> resultList = findResponse.getServices();
    if (resultList != null && !resultList.isEmpty()) {
      for (TrueService item : resultList) {
        try {
          service = new JSONObject();
          service.put("id", item.getServiceCode());
          service.put("text", item.getServiceName());
          service.put("level", item.getLevel());
          services.put(service);
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
    }
    return services.toString();
  }

  /** Ham lay danh sach bieu phi cho combobox man hinh sua */
  @RequestMapping(
      value = "/listFeeEdit",
      method = RequestMethod.POST,
      produces = "text/html;charset=utf-8")
  @PreAuthorize(
      "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "',  'SALESUPPORT_LEADER', 'SALESUPPORT')")
  @ResponseBody
  public String listFeeEdit(HttpServletRequest request) throws FrontEndException {

    JSONArray services = new JSONArray();
    JSONObject service;

    String parentServiceName = StringUtils.EMPTY;
    String serviceCode = StringUtils.trimToEmpty(request.getParameter(SERVICE_CODE));
    Long parentServiceId = NumberUtil.convertToLong(request.getParameter("parentServiceId"));
    String serviceType = StringUtils.trimToEmpty(request.getParameter(SERVICE_TYPE));
    String serviceLevel = StringUtils.trimToEmpty(request.getParameter("serviceLevel"));

    FindTrueServiceRequest findRequest = new FindTrueServiceRequest();
    findRequest.setServiceTypes(Collections.singletonList(serviceType));
    findRequest.setLevel(Integer.parseInt(serviceLevel));
    findRequest.setQuickSearch(serviceCode);
    findRequest.setInTree(StringUtil.CHAR_YES);

    FindTrueServiceResponse findResponse = trueServiceEndpoint.findTrueServices(findRequest);
    List<CommissionFee> trueServices = findResponse.getServices();

    if (trueServices != null && !trueServices.isEmpty()) {
      for (TrueService item : trueServices) {
        if (item != null) {
          if (item.getServiceCode() != null && item.getId().equals(parentServiceId)) {
            parentServiceName = item.getServiceName() + " - " + item.getServiceCode();
          }
          try {
            service = new JSONObject();
            service.put("id", item.getId());
            service.put("code", item.getServiceCode());
            service.put("text", item.getServiceName());
            service.put("level", item.getLevel());
            services.put(service);
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }
      }
    }
    return services.toString() + ";" + parentServiceName + ";" + parentServiceId;
  }

  /** Ham view chi tiet service */
  @RequestMapping(value = "/detail/{serviceId}", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "', 'STAFF') and !hasAnyRole('CUSTOMERCARE_MANAGER', 'TECH_SUPPORT')")
  public String detailTrueService(@PathVariable long serviceId, ModelMap model)
      throws FrontEndException {

    GetTrueServiceRequest gtsRequest = new GetTrueServiceRequest(serviceId);
    GetTrueServiceResponse gtsResponse = trueServiceEndpoint.getTrueService(gtsRequest);

    CommissionFee trueService = gtsResponse.getService();

    List<CommissionFee> trueServices = new ArrayList<>();
    trueServices.add(trueService);

    List<String> codeServices = new ArrayList<>();

    if (!gtsResponse.getChildrenServices().isEmpty()) {

      List<CommissionFee> childrenService = gtsResponse.getChildrenServices();
      trueServices.addAll(childrenService);

      for (TrueService item : childrenService) {
        if (item != null) {
          codeServices.add(item.getServiceCode());
        }
      }
      model.put("serviceCodeChildrens", codeServices);
      model.put("serviceChildrens", genderTrueServiceTree(gtsResponse.getChildrenServices()));
    }

    model.put("sysArrays", genderTrueServiceTree(trueServices));
    model.put("service", trueService);

    List<TrueServiceAttribute> attributes = trueService.getAttributes();

    if (attributes.isEmpty()) {
      model.put("numberRow", 0);
    } else {
      model.put("numberRow", attributes.size());
      model.put("serviceAttributes", attributes);
    }

    model.put("serviceAttributeTypes", ServiceAttributeType.SERVICE_ATTRIBUTE_TYPES);

    return "/service/service_detail";
  }

  /** Ham lay danh sach con cua service can xoa */
  @RequestMapping(value = "/listChildrenDelete", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "',  'SALESUPPORT_LEADER')")
  @ResponseBody
  public List<TrueService> listChildrenDelete(HttpServletRequest request) throws FrontEndException {

    List<TrueService> trueServices = new ArrayList<>();
    Long serviceId = NumberUtil.convertToLong(request.getParameter(SERVICE_ID));

    if (serviceId == 0L) {
      return trueServices;
    }

    GetTrueServiceRequest getRequest = new GetTrueServiceRequest(serviceId);
    GetTrueServiceResponse getResponse = trueServiceEndpoint.getTrueService(getRequest);

    trueServices.add(getResponse.getService());

    if (!getResponse.getChildrenServices().isEmpty()) {
      trueServices.addAll((getResponse.getChildrenServices()));
    }

    return trueServices;
  }

  @RequestMapping(value = "/operation", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','FINANCE','SALESUPPORT_LEADER','SALE_DIRECTOR','"
          + RoleConstants.SALESUPPORT_MANAGER
          + "','"
          + RoleConstants.CUSTOMERCARE_MANAGER
          + "', '"
          + RoleConstants.CUSTOMERCARE
          + "')")
  public String serviceOperationes(HttpServletRequest request, ModelMap model)
      throws FrontEndException {
    try {
      String serviceName = request.getParameter("quickSearch");
      String[] trueServiceIds = request.getParameterValues("trueServiceId");
      String[] serviceTypeIds = request.getParameterValues("serviceTypeIds");
      String[] providerCodes = request.getParameterValues("provider");

      FindTrueServiceRequest ftsRequest = new FindTrueServiceRequest();
      FindTrueServiceResponse ftsResponse = trueServiceEndpoint.findTrueServices(ftsRequest);

      GetTrueServiceOperationRequest gtsOperationRequest =
          new GetTrueServiceOperationRequest(serviceName);

      if (trueServiceIds != null
          && trueServiceIds.length > 0
          && StringUtils.isNotEmpty(trueServiceIds[0])) {
        List<Long> serviceIds = new ArrayList<>();
        for (String s : trueServiceIds) {
          serviceIds.add(Long.valueOf(s));
        }
        gtsOperationRequest.setServiceIds(serviceIds);
      }

      if (serviceTypeIds != null
          && serviceTypeIds.length > 0
          && StringUtils.isNotEmpty(serviceTypeIds[0])) {
        gtsOperationRequest.setServiceTypes(Arrays.asList(serviceTypeIds));
      }
      if (providerCodes != null
          && providerCodes.length > 0
          && StringUtils.isNotEmpty(providerCodes[0])) {
        gtsOperationRequest.setProviderCodes(Arrays.asList(providerCodes));
      }

      GetTrueServiceOperationResponse gtsOperationResponse =
          trueServiceEndpoint.getTrueServiceOperation(gtsOperationRequest);

      Map<String, Character> providers = gtsOperationResponse.getProviders();
      Map<String, TrueService> services = gtsOperationResponse.getServices();
      Map<String, TrueServiceOperation> serviceOperations =
          gtsOperationResponse.getServiceOperations();

      List<ServiceOperation> serviceOperationItems = new ArrayList<>();
      List<ProviderOperation> providerOperationItems;

      ServiceOperation serviceOperationTemp;
      ProviderOperation providerOperationTemp;
      ProviderServiceOperation providerSOTemp;
      TrueServiceOperation trueServiceOperationTemp;

      for (Map.Entry<String, TrueService> serviceEntry : services.entrySet()) {
        serviceOperationTemp = new ServiceOperation();
        serviceOperationTemp.setServiceCode(serviceEntry.getKey());
        serviceOperationTemp.setServiceStatus(serviceEntry.getValue().getStatus().toString());
        serviceOperationTemp.setServiceName(serviceEntry.getValue().getServiceName());

        providerOperationItems = new ArrayList<>();

        for (Map.Entry<String, Character> providerEntry : providers.entrySet()) {
          providerOperationTemp = new ProviderOperation();
          providerOperationTemp.setProviderCode(providerEntry.getKey());
          providerOperationTemp.setProviderStatus(providerEntry.getValue().toString());

          trueServiceOperationTemp =
              serviceOperations.get(providerEntry.getKey() + "|" + serviceEntry.getKey());
          if (trueServiceOperationTemp != null) {
            providerSOTemp = new ProviderServiceOperation();
            providerSOTemp.setServiceType(trueServiceOperationTemp.getServiceType());

            providerSOTemp.setProviderServiceCode(
                trueServiceOperationTemp.getProviderServiceCode());
            providerSOTemp.setProviderServiceName(
                trueServiceOperationTemp.getProviderServiceName());
            providerSOTemp.setProviderServiceActive(
                trueServiceOperationTemp.getProviderServiceActive().toString());

            providerSOTemp.setServiceMatchingActive(
                trueServiceOperationTemp.getServiceMatchingActive().toString());

            providerOperationTemp.setProviderServiceOperation(providerSOTemp);
          }
          providerOperationItems.add(providerOperationTemp);
        }

        serviceOperationTemp.setProviderOperations(providerOperationItems);
        serviceOperationItems.add(serviceOperationTemp);
      }

      model.put("serviceOperations", serviceOperationItems);
      model.put("providers", providers);
      model.put("cColumn", providers.size());
      model.put("trueServices", ftsResponse.getServices());
      model.put("serviceTypes", ServiceType.PAYMENT_TYPES);
      model.put("providerTypes", ProviderCode.PAYMENT_PROVIDER_CODES);

    } catch (Exception ex) {
      LOG.error(ex.getMessage(), ex);
    }

    return "/service/service_operation";
  }

  @RequestMapping(value = "/findTransactionRuleMatrix", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "')")
  @ResponseBody
  public List<TransactionRuleMatrix> findTransactionRuleMatrix(HttpServletRequest request)
      throws FrontEndException {

    List<TransactionRuleMatrix> trMatrixList = new ArrayList<>();
    Long serviceId = NumberUtil.convertToLong(request.getParameter(SERVICE_ID));

    if (serviceId == 0L) {
      return trMatrixList;
    }

    GetTrueServiceTransactionRuleMatrixRequest trmRequest =
        new GetTrueServiceTransactionRuleMatrixRequest(serviceId);
    trmRequest.setOffset(0);
    trmRequest.setLimit(20);
    GetTrueServiceTransactionRuleMatrixResponse trmResponse =
        trueServiceEndpoint.getTrueServiceTransactionRuleMatrix(trmRequest);

    List<TransactionRule> transactionRules = trmResponse.getTransactionRule();
    Long transactionRuleMapped = trmResponse.getTransactionRuleMapped();

    for (TransactionRule item : transactionRules) {
      trMatrixList.add(new TransactionRuleMatrix(item, transactionRuleMapped));
    }

    return trMatrixList;
  }

  @RequestMapping(value = "/addTrueServiceMatrix", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize(value = "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "')")
  public ResponseEntity<?> addTrueServiceMatrix(HttpServletRequest request) {
    try {
      String action = request.getParameter("action");
      Long serviceId = NumberUtil.convertToLong(request.getParameter("matrixServiceId"));
      Long tranRuleMatrixId = NumberUtil.convertToLong(request.getParameter("tranRuleMatrixId"));
      AjaxResponse ajResponse = validation.addTrueServiceMatrix(serviceId, tranRuleMatrixId);

      if (ajResponse.getCode() == 1) {
        return ResponseEntity.ok(ajResponse);
      }

      UpdateTrueServiceTransactionRuleMatrixRequest tMatrixRequest =
          new UpdateTrueServiceTransactionRuleMatrixRequest();
      tMatrixRequest.setTrueServiceId(serviceId);
      if (BTN_DELETE.equals(action)) {
        tMatrixRequest.setTransactionRuleId(null);
      } else if (BTN_SUBMIT.equals(action)) {
        tMatrixRequest.setTransactionRuleId(tranRuleMatrixId);
      }

      UpdateTrueServiceTransactionRuleMatrixResponse tMatrixResponse =
          trueServiceEndpoint.updateTrueServiceMatrix(tMatrixRequest);

      return responseAjax(
          tMatrixResponse.getStatus().getCode(), tMatrixResponse.getStatus().getValue());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.ok(new AjaxResponse(1, e.getMessage()));
    }
  }

  @RequestMapping(value = "/ajax/list", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + RoleConstants.SALE_DIRECTOR
          + "','"
          + RoleConstants.SALESUPPORT_MANAGER
          + "')")
  public ResponseEntity<?> listServicesForAjax(HttpServletRequest request, ModelMap model) {
    try {
      String paramServiceTypeIds = StringUtils.trimToEmpty(request.getParameter("serviceTypeIds"));

      FindTrueServiceRequest ftsRequest = new FindTrueServiceRequest();

      if (EMPTY.equals(paramServiceTypeIds)) {
        String sServiceType = (String) SessionUtil.getAttribute(SESSION_SERVICE_TYPE);
        if (sServiceType == null) {
          ftsRequest.setServiceTypes(Collections.singletonList(ServiceType.FUND_IN.name()));
        } else {
          ftsRequest.setServiceTypes(Collections.singletonList(sServiceType));
        }
      } else {
        //      SessionUtil.setAttribute(SESSION_SERVICE_TYPE, paramServiceTypeIds)
        ftsRequest.setServiceTypes(Collections.singletonList(paramServiceTypeIds));
      }

      FindTrueServiceResponse ftsResponse = trueServiceEndpoint.findTrueServices(ftsRequest);

      if (ftsResponse == null) {
        return ResponseEntity.ok(
            new AjaxResponse(MessageNotify.ERROR_CODE, "No response from Server"));
      }
      if (ftsResponse.getStatus().getCode() != MessageNotify.SUCCESS_CODE) {
        return ResponseEntity.ok(
            new AjaxResponse(
                ftsResponse.getStatus().getCode(), ftsResponse.getStatus().getValue()));
      }

      return ResponseEntity.ok(ftsResponse);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);

      return ResponseEntity.ok(new AjaxResponse(MessageNotify.ERROR_CODE, e.getMessage()));
    }
  }
}
