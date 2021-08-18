package vn.mog.ewallet.operation.web.controller.provider;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.contract.AjaxResponse;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.controller.provider.beans.ProviderServiceHelper;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.exception.MessageNotify;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.type.ServiceTypeEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.notification.contract.type.TargetEnum;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.*;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.beans.ProviderService;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.*;
import vn.mog.ewallet.operation.web.utils.WalletTagLib;
import vn.mog.framework.common.utils.NumberUtil;

import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ProviderGroup.PROVIDER_GROUPS;

@Controller
@RequestMapping(value = "/provider/provider-profile")
public class ProviderController extends AbstractController {

  public static final String PROVIDER_CONTROLLER = "/provider/provider-profile";
  public static final String PROVIDER_LIST = PROVIDER_CONTROLLER + "/list";
  public static final String PROVIDER_SERVICE_LIST = PROVIDER_CONTROLLER + "/service/list";
  public static final String PTU_SERVICE_MATCHING = PROVIDER_CONTROLLER + "/ptu-service/matching";
  private static final Logger LOG = LogManager.getLogger(ProviderController.class);

  @Value("#{'${provider.adjust.ranking.list}'.split('\\|')}")
  private Set<String> providers;

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + RoleConstants.TECH_SUPPORT
          + "','"
          + RoleConstants.RECONCILIATION
          + "','"
          + RoleConstants.RECONCILIATION_LEADER
          + "','"
          + RoleConstants.CUSTOMERCARE_MANAGER
          + "','"
          + RoleConstants.CUSTOMERCARE
          + "','SALESUPPORT_MANAGER' , 'SALESUPPORT','FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE')")
  public String search(HttpServletRequest request, ModelMap model) throws FrontEndException {
    try {
      String paramText = request.getParameter("search");
      String[] serviceTypes = request.getParameterValues("serviceTypes");
      String active = request.getParameter("active");
      String providerGroup = request.getParameter("providerGroup");
      String tab = request.getParameter("tab");

      int offset = 0;
      int limit = 20;
      int total = 0;
      if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
        offset = Integer.parseInt(request.getParameter("d-49489-p"));
        if (offset > 0) {
          offset = (offset - 1) * limit;
        }
      }

      FindProviderRequest findReq = new FindProviderRequest();

      if (serviceTypes != null
          && serviceTypes.length > 0
          && !StringUtils.EMPTY.equals(serviceTypes[0])) {
        findReq.setServiceTypes(Arrays.asList(serviceTypes));
      }

      if (StringUtils.isNotEmpty(active)) {
        if (active.equals("1")) {
          findReq.setActive(true);
        } else if (active.equals("0")) {
          findReq.setActive(false);
        }
      }

      if (tab != null && !tab.isEmpty()) {
        findReq.setProviderGroup(tab);
      } else {
        findReq.setProviderGroup(
            providerGroup != null && !providerGroup.isEmpty() ? providerGroup : null);
      }
      findReq.setTextSearch(paramText);
      findReq.setLimit(limit);
      findReq.setOffset(offset);

      FindProviderResponse findRes = providerEndpoint.findProviders(findReq);
      if (findRes.getStatus().getCode() != MessageNotify.SUCCESS_CODE) {
        LOG.error("FindProviderResponse: " + findRes.getStatus().getValue());
      } else {
        total = findRes.getAll().intValue();
        model.put("listServiceTypes", ServiceType.values());
        model.put("listTelcoTypes", TelcoType.values());
        model.put("telCoServiceTypes", TelcoServiceType.values());

        model.put("list", findRes.getProviders());
        model.put("pagesize", limit);
        model.put("offset", offset);
        model.put("total", total);

        model.put("providerCode", request.getParameter("providerCode"));
        model.put("pProService", request.getParameter("pProService"));

        List<ProviderRankingGroup> providerRankingGroups =
            EnumSet.allOf(ProviderRankingGroup.class).stream().collect(Collectors.toList());
        List<ProviderRankingLevel> providerRankingLevels =
            EnumSet.allOf(ProviderRankingLevel.class).stream().collect(Collectors.toList());
        model.put("providerRankingGroups", providerRankingGroups);
        model.put("providerRankingLevels", providerRankingLevels);
        model.put("providerGroups", PROVIDER_GROUPS);

        return "/provider/list";
      }

    } catch (Exception ex) {
      LOG.error(ex.getMessage());
    }
    return "/provider/list";
  }

  @RequestMapping(value = "/changeProviderStatus", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + RoleConstants.SALESUPPORT_LEADER
          + "','SALESUPPORT_MANAGER' , 'SALESUPPORT')")
  public ResponseEntity<?> changeProviderStatus(HttpServletRequest httpRequest)
      throws FrontEndException {

    try {
      Long providerId = NumberUtil.convertToLong(httpRequest.getParameter("providerId"));
      String strActive = httpRequest.getParameter("active");
      boolean active = !Boolean.parseBoolean(strActive);

      ChangeProviderStatusRequest request = new ChangeProviderStatusRequest(providerId, active);
      ChangeProviderStatusResponse responseMpo = providerEndpoint.changeProviderStatus(request);

      return responseAjax(responseMpo.getStatus().getCode(), responseMpo.getStatus().getValue());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.ok(new AjaxResponse(1, e.getMessage()));
    }
  }

  @RequestMapping(value = "/changeProviderServiceStatus", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + RoleConstants.SALESUPPORT_LEADER
          + "','SALESUPPORT_MANAGER' , 'SALESUPPORT')")
  public ResponseEntity<?> changeProviderServiceStatus(HttpServletRequest request)
      throws FrontEndException {
    try {

      Long providerServiceId = NumberUtil.convertToLong(request.getParameter("providerServiceId"));
      String strActive = request.getParameter("active");
      Boolean active = !Boolean.parseBoolean(strActive); // truyen
      // nguoc
      // front-end

      if (providerServiceId > 0) {
        ChangeProviderServiceStatusRequest updateReq =
            new ChangeProviderServiceStatusRequest(providerServiceId, active);
        ChangeProviderServiceStatusResponse updateRes =
            providerEndpoint.changeProviderServiceStatus(updateReq);

        return responseAjax(updateRes.getStatus().getCode(), updateRes.getStatus().getValue());
      } else {
        return ResponseEntity.ok(new AjaxResponse(1, "Dữ liệu truyền bị lỗi, thao tác lại"));
      }

    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.ok(new AjaxResponse(1, e.getMessage()));
    }
  }

  @RequestMapping(value = "/updateProvider", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "', 'SALESUPPORT_LEADER','SALESUPPORT_MANAGER' , 'SALESUPPORT')")
  public ResponseEntity<?> updateProvider(HttpServletRequest request) throws FrontEndException {
    try {
      String providerCode = request.getParameter("providerCode");
      Long providerId = NumberUtil.convertToLong(request.getParameter("providerId"));
      String relationship = request.getParameter("relationship");
      String rankingScoreMin = request.getParameter("rankingScoreMin");
      String rankingScoreMax = request.getParameter("rankingScoreMax");
      String name = request.getParameter("name");
      String customerTypeSupported =
          StringUtils.trimToEmpty(request.getParameter("customerTypeSupported"));
      String listCif = StringUtils.trimToEmpty(request.getParameter("listCif"));
      String blackListCif = StringUtils.trimToEmpty(request.getParameter("blackListCif"));
      String specific = request.getParameter("provider");
      String providerGroup = request.getParameter("providerGroup");
      String providerBizCode = request.getParameter("providerBizCode");
      String rankingGroup = request.getParameter("rankingGroup");
      String rankingLevel = request.getParameter("rankingLevel");

      if (StringUtils.trimToNull(providerCode) == null
          || StringUtils.trimToNull(relationship) == null
          || providerId == 0) {
        return ResponseEntity.ok(new AjaxResponse(1, "Missing Params!"));
      }

      BigInteger valueInput = new BigInteger(relationship);
      BigInteger valueValidate = new BigInteger("20000");
      int i = valueInput.compareTo(valueValidate);
      if (i == 1) {
        return ResponseEntity.ok(new AjaxResponse(1, "Điểm đánh giá phải nhỏ hơn 20.000"));
      }

      if (NumberUtil.convertToLong(relationship) < 0) {
        return ResponseEntity.ok(
            new AjaxResponse(1, "Điểm đánh giá phải lớn hơn hoặc bằng không"));
      }

      GetProviderRequest providerRequest = new GetProviderRequest(providerCode);
      GetProviderResponse getProviderResponse = providerEndpoint.getProvider(providerRequest);

      UpdateProviderRequest updateRequest = new UpdateProviderRequest();
      updateRequest.setProviderId(providerId);
      updateRequest.setName(name);
      updateRequest.setRankingScore(Double.parseDouble(relationship));
      updateRequest.setRankingScoreMin(Double.parseDouble(rankingScoreMin));
      updateRequest.setRankingScoreMax(Double.parseDouble(rankingScoreMax));

      updateRequest.setProviderGroup(
          providerGroup != null && !providerGroup.isEmpty()
              ? providerGroup
              : ProviderGroup.UNDEFINED_05.getCode());
      updateRequest.setProviderBizCode(
          providerBizCode != null && !providerBizCode.isEmpty() ? providerBizCode : null);
      updateRequest.setRankingGroup(
          rankingGroup != null && !rankingGroup.isEmpty() ? Integer.parseInt(rankingGroup) : null);
      updateRequest.setRankingLevel(
          rankingLevel != null && !rankingLevel.isEmpty() ? Integer.parseInt(rankingLevel) : null);

      if (!customerTypeSupported.isEmpty() && customerTypeSupported != null) {
        updateRequest.setCustomerTypeSupported(customerTypeSupported);
      }
      if (specific.equals("true")) {
        updateRequest.setSpecific(true);
        if (!listCif.isEmpty() && listCif != null) {
          updateRequest.setSpecificListCif(listCif);
        }
        updateRequest.setBlackListCif(getProviderResponse.getProvider().getBlackListCif());
      } else {
        updateRequest.setSpecific(false);
        if (!blackListCif.isEmpty() && blackListCif != null) {
          updateRequest.setBlackListCif(blackListCif);
        }
        updateRequest.setSpecificListCif(getProviderResponse.getProvider().getSpecificListCif());
      }

      UpdateProviderResponse updateResponse = providerEndpoint.updateProvider(updateRequest);
      //      UpdateProviderResponse updateResponse = new UpdateProviderResponse();
      cacheDataUtil.resetProviderCaching();
      return responseAjax(
          updateResponse.getStatus().getCode(), updateResponse.getStatus().getValue());
    } catch (Exception e) {
      LOG.error("updateProvider", e);
      return ResponseEntity.ok(new AjaxResponse(1, e.getMessage()));
    }
  }

  @RequestMapping(value = "/getProvider", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "', 'SALESUPPORT_LEADER','SALESUPPORT_MANAGER' , 'SALESUPPORT', 'CUSTOMERCARE_MANAGER', 'CUSTOMERCARE')")
  public GetProviderResponse getProvider(HttpServletRequest request) throws FrontEndException {
    try {
      String providerCode = request.getParameter("providerCode");

      GetProviderRequest providerRequest = new GetProviderRequest(providerCode);
      GetProviderResponse res = providerEndpoint.getProvider(providerRequest);
      return res;
    } catch (Exception e) {
      LOG.error("getProvider", e);
      return null;
    }
  }

  @RequestMapping(value = "/service/list", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + RoleConstants.TECH_SUPPORT
          + "','"
          + RoleConstants.RECONCILIATION
          + "','"
          + RoleConstants.RECONCILIATION_LEADER
          + "','"
          + RoleConstants.CUSTOMERCARE_MANAGER
          + "','"
          + RoleConstants.CUSTOMERCARE
          + "','SALESUPPORT_MANAGER' , 'SALESUPPORT','FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE')")
  public String getServices(ModelMap map) throws FrontEndException {
    try {
      map.put("providers", ProviderCode.PAYMENT_PROVIDER_CODES);
      String[] groups = {"PTU_A", "PTU_B", "PTU_C", "NO_GROUP"};

      List<ProviderServiceHelper> providerServiceHelpers = new ArrayList<>();
      for (String providerCode : providers) {
        ProviderServiceHelper providerServiceHelper = new ProviderServiceHelper();
        GetProviderServicesByProviderCodeRequest request =
            new GetProviderServicesByProviderCodeRequest();
        request.setProviderCode(providerCode);
        GetProviderServicesByProviderCodeResponse response =
            providerEndpoint.getProviderServicesOfProvider(request);
        if (response.getStatus().getCode() == 0) {
          providerServiceHelper.setProviderCode(providerCode);
          providerServiceHelper.setProviderServices(response.getProviderServices());

          boolean hasGroup = false;
          for (String group : groups) {
            if (WalletTagLib.getProviderBizCode(providerCode).contains(group)) {
              hasGroup = true;
              providerServiceHelper.setGroup(group);
              break;
            }
          }
          if (hasGroup == false) {
            providerServiceHelper.setGroup("NO_GROUP");
          }
          providerServiceHelpers.add(providerServiceHelper);
        }
      }

      map.put("providerAndTheirServices", providerServiceHelpers);
      for (String group : groups) {
        List<ProviderServiceHelper> providerServices =
            providerServiceHelpers.stream()
                .filter(element -> group.equals(element.getGroup()))
                .collect(Collectors.toList());
        Collections.sort(
            providerServices,
            (o1, o2) ->
                WalletTagLib.getProviderBizCode(o1.getProviderCode())
                    .compareTo(WalletTagLib.getProviderBizCode(o2.getProviderCode())));
        map.put(group, providerServices);
      }
    } catch (HttpClientErrorException | HttpServerErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/provider_service/list";
  }

  @RequestMapping(value = "/ptu-service/matching", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + RoleConstants.TECH_SUPPORT
          + "','"
          + RoleConstants.RECONCILIATION
          + "','"
          + RoleConstants.RECONCILIATION_LEADER
          + "','"
          + RoleConstants.CUSTOMERCARE_MANAGER
          + "','"
          + RoleConstants.CUSTOMERCARE
          + "','SALESUPPORT_MANAGER' , 'SALESUPPORT','FA_MANAGER','FINANCE_LEADER','FINANCESUPPORT_LEADER','FINANCE_SUPPORT','FINANCE')")
  public String getPtuServices(ModelMap map, HttpServletRequest req) throws FrontEndException {
    try {
      String[] rankingGroupList = req.getParameterValues("rankingGroups");
      String[] serviceCodeList = req.getParameterValues("serviceCodes");

      List<String> serviceCodes =
          Arrays.asList(
              new String[] {
                "06010100", "06010101", "06010103", "06010104", "06010105", "06010107"
              });
      map.put("serviceCodes", serviceCodes);

      List<ProviderRankingGroup> providerRankingGroups =
          EnumSet.allOf(ProviderRankingGroup.class).stream().collect(Collectors.toList());
      map.put("rankingGroups", providerRankingGroups);
      // ------
      GetProviderServicesMatchingWithTrueServiceRequest request =
          new GetProviderServicesMatchingWithTrueServiceRequest();
      request.setServiceCodes(serviceCodes);

      if (rankingGroupList != null
          && rankingGroupList.length > 0
          && StringUtils.isNotEmpty(rankingGroupList[0])) {
        request.setRankingGroupIds(NumberUtil.convertListToInt(rankingGroupList));
      }

      if (serviceCodeList != null
          && serviceCodeList.length > 0
          && StringUtils.isNotEmpty(serviceCodeList[0])) {
        request.setServiceCodes(Arrays.asList(serviceCodeList));
      }

      request.setIncludeInactive(false);
      GetProviderServicesMatchingWithTrueServiceResponse response =
          providerEndpoint.getProviderServicesMatchingWithTrueService(request);
      Map<String, Map<String, List<ProviderService>>> matrixMap = response.getMatrixMap();
      map.put("providerServiceMatrix", matrixMap);
    } catch (HttpClientErrorException | HttpServerErrorException e) {
      LOG.error(e.getResponseBodyAsString(), e);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return "/ptu_service/matching";
  }

  public List<String> getProviderByRankingGroup(String rankingGroup) {
    return Collections.emptyList();
  }
}
