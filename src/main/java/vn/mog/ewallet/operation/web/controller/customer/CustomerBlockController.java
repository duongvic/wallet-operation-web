package vn.mog.ewallet.operation.web.controller.customer;

import static org.apache.commons.lang.StringUtils.EMPTY;
import static vn.mog.ewallet.operation.web.controller.service.TrueServiceController.SESSION_SERVICE_TYPE;
import static vn.mog.ewallet.operation.web.controller.translog.AbstractTransactionController.USER_CUSTOMER_TYPES;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.contract.AjaxResponse;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.AddBlackListCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.AddBlackListCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindBlackListCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindBlackListCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindProviderByBlockCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindProviderByBlockCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindProviderServicesByBlockCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindProviderServicesByBlockCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindTrueServiceByBlockCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindTrueServiceByBlockCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.RemoveBlackListCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.RemoveBlackListCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.beans.BlockType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.beans.QuickSearchType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.FindProviderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.FindProviderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.GetProviderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.GetProviderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.beans.Provider;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.beans.ProviderService;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.FindTrueServiceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.service.FindTrueServiceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ProviderCode;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.Customer;
import vn.mog.framework.common.utils.NumberUtil;
import vn.mog.framework.common.utils.SessionUtil;

/** Created by duongnh on 12/6/2019. */
@Controller
@RequestMapping(value = "/customer-block")
public class CustomerBlockController extends AbstractController {

  public static final String CUS_BLOCK_CONTROLLER = "/customer-block";
  public static final String CUS_BLOCK_LIST_ALL = CUS_BLOCK_CONTROLLER + "/list-all";
  public static final String CUS_BLOCK_LIST = CUS_BLOCK_CONTROLLER + "/list";
  public static final String CUS_BLOCK_LIST_ADD = CUS_BLOCK_CONTROLLER + "/add";
  public static final String CUS_BLOCK_LIST_DETAIL_ADD_ALL =
      CUS_BLOCK_CONTROLLER + "/list-detail-all";
  public static final String CUS_BLOCK_LIST_DETAIL_ADD = CUS_BLOCK_CONTROLLER + "/list-detail";
  private static final Logger LOG = LogManager.getLogger(CustomerBlockController.class);

  @RequestMapping(value = "/list-all", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "', '"
          + RoleConstants.SALESUPPORT_MANAGER
          + "')")
  public String searchAll(HttpServletRequest request, ModelMap map) throws FrontEndException {
    return search(request, map);
  }

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "', '"
          + RoleConstants.SALESUPPORT_MANAGER
          + "')")
  public String search(HttpServletRequest request, ModelMap map) throws FrontEndException {
    try {
      String quickSearch = StringUtils.trimToEmpty(request.getParameter("quickSearch"));
      if (quickSearch == "") {
        quickSearch = null;
      }

      String param_quickSearchType =
          StringUtils.trimToEmpty(request.getParameter("quickSearchType"));
      String param_blockTypes = request.getParameter("blockTypes");

      String blockType = request.getParameter("blockType");

      String[] paramIdOwnerCustomer = request.getParameterValues("idOwnerCustomerTypes");
      String[] paramProvider = request.getParameterValues("provider");
      String[] paramServiceTypeIds = request.getParameterValues("serviceTypeIds");

      FindBlackListCustomerRequest fwtRequest = new FindBlackListCustomerRequest();

      int offset = 0;
      int limit = 20;
      if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
        offset = Integer.parseInt(request.getParameter("d-49489-p"));
        if (offset > 0) {
          offset = (offset - 1) * limit;
        }
      }

      if (param_blockTypes == null) {
        fwtRequest.setBlockTypes(
            Arrays.asList(
                BlockType.PROVIDER_BLOCK,
                BlockType.PROVIDER_SERVICE_BLOCK,
                BlockType.SERVICE_BLOCK));
      } else if (param_blockTypes.equals("PROVIDER")) {
        fwtRequest.setBlockTypes(
            Arrays.asList(BlockType.PROVIDER_BLOCK, BlockType.PROVIDER_SERVICE_BLOCK));
      } else if (param_blockTypes.equals("SERVICE")) {
        fwtRequest.setBlockTypes(Arrays.asList(BlockType.SERVICE_BLOCK));
      }

      String[] idOwnerCustomerTypes = paramIdOwnerCustomer;
      List<Integer> customerTypeStrings = new ArrayList<>();
      if (paramIdOwnerCustomer != null
          && paramIdOwnerCustomer.length > 0
          && !StringUtils.EMPTY.equals(paramIdOwnerCustomer[0])) {
        for (String customerTypeString : idOwnerCustomerTypes) {
          customerTypeStrings.add(USER_CUSTOMER_TYPES.get(customerTypeString.toUpperCase()));
        }
        fwtRequest.setCustomerTypes((customerTypeStrings));
      }

      if (paramProvider != null
          && paramProvider.length > 0
          && !StringUtils.EMPTY.equals(paramProvider[0])) {
        fwtRequest.setProviderCodes(Arrays.asList(paramProvider));
      }

      if (paramServiceTypeIds != null
          && paramServiceTypeIds.length > 0
          && !StringUtils.EMPTY.equals(paramServiceTypeIds[0])) {
        fwtRequest.setServiceTypes(Arrays.asList(paramServiceTypeIds));
      }

      if (StringUtils.isNotEmpty(param_quickSearchType)) {
        fwtRequest.setQuickSearchType(QuickSearchType.valueOf(param_quickSearchType));
      }

      fwtRequest.setQuickSearch(quickSearch);
      fwtRequest.setLimit(limit);
      fwtRequest.setOffset(offset);

      FindBlackListCustomerResponse fwtResponse = umgrAccountEndpoint.findBlockCustomer(fwtRequest);
      List<Customer> objCus = new ArrayList<>();
      Long totalList = 0L;
      if (fwtResponse != null && fwtResponse.getStatus().getCode() == 0) {
        objCus = fwtResponse.getCustomers();
        totalList = fwtResponse.getTotal();
      }

      map.put("transCancels", objCus);
      map.put("pagesize", limit);
      map.put("offset", offset);
      map.put("total", totalList.intValue());
      map.put("blockTypes", param_blockTypes);
      map.put("quickSearchType", param_quickSearchType);

      map.put("blockType", blockType);

      // list combobox
      map.put("providerTypes", ProviderCode.PAYMENT_PROVIDER_CODES);
      map.put("serviceTypes", ServiceType.FULL_SERVICE_TYPES);
      map.put("listQuickSearchType", QuickSearchType.values());

    } catch (Exception ex) {
      LOG.error(ex.getMessage());
    }

    return "/customer_block/cus_block_list";
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "', '"
          + RoleConstants.SALESUPPORT_MANAGER
          + "')")
  public String add(HttpServletRequest request, ModelMap map) throws FrontEndException {
    try {

      String quickSearch = StringUtils.trimToEmpty(request.getParameter("search"));
      String[] customerIds = request.getParameterValues("customerIds");

      String blockType = request.getParameter("blockType");
      if (blockType == null) {
        blockType = BlockType.SERVICE_BLOCK.name();
      }
      String[] paramProvider = request.getParameterValues("provider");

      map.put("customerIdsBlock", customerIds);

      map.put("blockType", blockType);

      String paramServiceTypeIds = StringUtils.trimToEmpty(request.getParameter("serviceTypeIds"));
      if (blockType.equals(BlockType.SERVICE_BLOCK.name())) {
        FindTrueServiceRequest ftsRequest = new FindTrueServiceRequest();
        ftsRequest.setQuickSearch(quickSearch);
        if (!EMPTY.equals(paramServiceTypeIds)) {
          SessionUtil.setAttribute(SESSION_SERVICE_TYPE, paramServiceTypeIds);
          ftsRequest.setServiceTypes(Collections.singletonList(paramServiceTypeIds));
        } else {
          String sServiceType = (String) SessionUtil.getAttribute(SESSION_SERVICE_TYPE);
          if (sServiceType == null) {
            ftsRequest.setServiceTypes(Collections.singletonList(ServiceType.FUND_IN.name()));
          } else {
            ftsRequest.setServiceTypes(Collections.singletonList(sServiceType));
          }
        }
        FindTrueServiceResponse ftsResponse = trueServiceEndpoint.findTrueServices(ftsRequest);
        map.put("lstTableServices", genderTrueServiceTree(ftsResponse.getServices()));
        map.put("totalServices", ftsResponse.getAll());
      } else {
        map.put("lstTableServices", null);
        map.put("totalServices", 0);
      }

      /*get  list provider service*/
      if (blockType.equals(BlockType.PROVIDER_SERVICE_BLOCK.name())) {
        GetProviderRequest providerRequest = new GetProviderRequest();
        if (paramProvider != null
            && paramProvider.length > 0
            && !StringUtils.EMPTY.equals(paramProvider[0])) {
          providerRequest.setProviderCode(paramProvider[0]);
          GetProviderResponse getProviderResponse = providerEndpoint.getProvider(providerRequest);
          List<ProviderService> lstProviderServices = new ArrayList<>();
          if (getProviderResponse.getStatus().getCode() == 0) {
            Provider tableProvider = getProviderResponse.getProvider();

            lstProviderServices = tableProvider.getServices();
          }
          map.put("lstProviderServices", lstProviderServices);
          map.put("totalProviderService", lstProviderServices.size());
        }
      } else {
        map.put("lstProviderServices", null);
        map.put("totalProviderService", 0);
      }

      /* list combobox*/
      FindProviderRequest findProviderRequest = new FindProviderRequest();
      findProviderRequest.setLimit(-1);
      findProviderRequest.setOffset(-1);
      FindProviderResponse findRes = providerEndpoint.findProviders(findProviderRequest);

      List<String> rolesOfCaller = getRolesOfCaller();
      //      List customerList = getListSaleByParent(rolesOfCaller);

      //      map.put("customers", customerList);
      map.put("providerTypes", findRes.getProviders());
      map.put("totalProvider", findRes.getTotal() == null ? 0 : findRes.getTotal());
      map.put("listBlockTypes", BlockType.TAB_BLOCK_ALL);

      map.put("serviceTypeIds", paramServiceTypeIds);
      map.put("serviceTypes", ServiceType.FULL_SERVICE_TYPES);

      map.put("provider", paramProvider);
    } catch (Exception ex) {
      LOG.error(ex.getMessage(), ex);
    }
    return "/customer_block/cus_block_add";
  }

  @RequestMapping(value = "/add-block", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + RoleConstants.CUSTOMERCARE_MANAGER
          + "', '"
          + RoleConstants.SALESUPPORT_MANAGER
          + "')")
  public ResponseEntity<?> addBlock(HttpServletRequest httpRequest) throws FrontEndException {
    try {
      String paramBlockType = httpRequest.getParameter("blockType");
      String[] paramCustomerCif = httpRequest.getParameterValues("customerCifs[]");
      String[] paramProvider = httpRequest.getParameterValues("providerCodes");
      String[] paramServiceCodes = httpRequest.getParameterValues("serviceCodes");
      String[] paramProviderServiceIds = httpRequest.getParameterValues("providerServiceIds");

      AddBlackListCustomerRequest request = new AddBlackListCustomerRequest();
      request.setBlockType(BlockType.valueOf(paramBlockType));
      if (paramCustomerCif != null
          && paramCustomerCif.length > 0
          && !StringUtils.EMPTY.equals(paramCustomerCif[0])) {
        //        paramCustomerCif = paramCustomerCif[0].split(",");
        request.setCustomerCifs(Arrays.asList(paramCustomerCif));
      }
      if (paramProvider != null
          && paramProvider.length > 0
          && !StringUtils.EMPTY.equals(paramProvider[0])) {
        paramProvider = paramProvider[0].split(",");
        request.setProviderCodes(Arrays.asList(paramProvider));
      }
      if (paramServiceCodes != null
          && paramServiceCodes.length > 0
          && !StringUtils.EMPTY.equals(paramServiceCodes[0])) {
        paramServiceCodes = paramServiceCodes[0].split(",");
        request.setServiceCodes(Arrays.asList(paramServiceCodes));
      }

      if (paramProviderServiceIds != null
          && paramProviderServiceIds.length > 0
          && !StringUtils.EMPTY.equals(paramProviderServiceIds[0])) {
        paramProviderServiceIds = paramProviderServiceIds[0].split(",");
        request.setProviderServiceIds(NumberUtil.convertListToLong(paramProviderServiceIds));
      }

      AddBlackListCustomerResponse responseMpo = umgrAccountEndpoint.addBlock(request);

      return responseAjax(responseMpo.getStatus().getCode(), responseMpo.getStatus().getValue());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.ok(new AjaxResponse(1, e.getMessage()));
    }
  }

  @RequestMapping(value = "/list-detail-all", method = RequestMethod.GET)
  @PreAuthorize(
          "hasAnyRole('"
                  + RoleConstants.ADMIN_OPERATION
                  + "', '"
                  + RoleConstants.SALESUPPORT_MANAGER
                  + "')")
  public String searchAllDetailBlock(HttpServletRequest request, ModelMap map)
      throws FrontEndException {
    return searchDetailBlock(request, map);
  }

  @RequestMapping(value = "/list-detail", method = RequestMethod.GET)
  @PreAuthorize(
          "hasAnyRole('"
                  + RoleConstants.ADMIN_OPERATION
                  + "', '"
                  + RoleConstants.SALESUPPORT_MANAGER
                  + "')")
  public String searchDetailBlock(HttpServletRequest request, ModelMap map)
      throws FrontEndException {
    try {

      String blockType = request.getParameter("blockType");

      String[] paramCustomersCifs = request.getParameterValues("customersCifs");
      String[] paramProvider = request.getParameterValues("provider");
      String[] paramServiceTypeIds = request.getParameterValues("serviceTypeIds");

      FindBlackListCustomerRequest fwtRequest = new FindBlackListCustomerRequest();

      getTableProviderBlock(request, map, paramCustomersCifs);

      getTableProviderServiceBlock(request, map, paramCustomersCifs, paramProvider);

      getTableService(request, map, paramCustomersCifs, paramServiceTypeIds);

      // list combobox
      map.put("providerTypes", ProviderCode.PAYMENT_PROVIDER_CODES);
      map.put("serviceTypes", ServiceType.FULL_SERVICE_TYPES);
      map.put("listQuickSearchType", QuickSearchType.values());

      map.put("customersCifs", paramCustomersCifs);

    } catch (Exception ex) {
      LOG.error(ex.getMessage());
    }

    return "/customer_block/cus_block_detail_list_add";
  }

  private void getTableService(
      HttpServletRequest request,
      ModelMap map,
      String[] paramCustomersCifs,
      String[] paramServiceTypeIds) {
    try {
      String quickSearch = StringUtils.trimToEmpty(request.getParameter("quickSearch"));
      if (quickSearch == "") {
        quickSearch = null;
      }

      int offset = 0;
      int limit = 20;
      if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
        offset = Integer.parseInt(request.getParameter("d-49489-p"));
        if (offset > 0) {
          offset = (offset - 1) * limit;
        }
      }

      FindTrueServiceByBlockCustomerRequest findTrueServiceByBlockCustomerRequest =
          new FindTrueServiceByBlockCustomerRequest();
      findTrueServiceByBlockCustomerRequest.setQuickSearch(quickSearch);
      if (paramServiceTypeIds != null
          && paramServiceTypeIds.length > 0
          && !StringUtils.EMPTY.equals(paramServiceTypeIds[0])) {
        findTrueServiceByBlockCustomerRequest.setServiceTypes(Arrays.asList(paramServiceTypeIds));
      }
      findTrueServiceByBlockCustomerRequest.setServiceCodes(null);
      findTrueServiceByBlockCustomerRequest.setLevel(null);
      findTrueServiceByBlockCustomerRequest.setInTree(null);
      findTrueServiceByBlockCustomerRequest.setActive(null);
      if (paramCustomersCifs != null
          && paramCustomersCifs.length > 0
          && !StringUtils.EMPTY.equals(paramCustomersCifs[0])) {
        findTrueServiceByBlockCustomerRequest.setCustomersCifs((Arrays.asList(paramCustomersCifs)));
      }
      findTrueServiceByBlockCustomerRequest.setLimit(limit);
      findTrueServiceByBlockCustomerRequest.setOffset(offset);

      FindTrueServiceByBlockCustomerResponse findTrueServiceByBlockCustomerResponse =
          umgrAccountEndpoint.findTrueServiceByBlockCustomer(findTrueServiceByBlockCustomerRequest);
      if (findTrueServiceByBlockCustomerResponse.getStatus().getCode() == 0) {
        map.put(
            "lstTableServices",
            genderTrueServiceTree(findTrueServiceByBlockCustomerResponse.getServices()));
        map.put("totalServices", findTrueServiceByBlockCustomerResponse.getTotal());
      } else {
        map.put("lstTableServices", null);
        map.put("totalProviderService", 0);
      }
      map.put("pagesizeTrueService", limit);
      map.put("offset", offset);

    } catch (Exception ex) {
      LOG.error(ex.getMessage());
    }
  }

  private void getTableProviderServiceBlock(
      HttpServletRequest request,
      ModelMap map,
      String[] paramCustomersCifs,
      String[] paramProvider) {
    try {

      int offset = 0;
      int limit = 20;
      if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
        offset = Integer.parseInt(request.getParameter("d-49489-p"));
        if (offset > 0) {
          offset = (offset - 1) * limit;
        }
      }

      FindProviderServicesByBlockCustomerRequest findProviderServicesByBlockCustomerRequest =
          new FindProviderServicesByBlockCustomerRequest();
      if (paramProvider != null
          && paramProvider.length > 0
          && !StringUtils.EMPTY.equals(paramProvider[0])) {
        findProviderServicesByBlockCustomerRequest.setProviderCodes((Arrays.asList(paramProvider)));
      }

      if (paramCustomersCifs != null
          && paramCustomersCifs.length > 0
          && !StringUtils.EMPTY.equals(paramCustomersCifs[0])) {
        findProviderServicesByBlockCustomerRequest.setCustomersCifs(
            (Arrays.asList(paramCustomersCifs)));
      }
      findProviderServicesByBlockCustomerRequest.setActive(null);
      findProviderServicesByBlockCustomerRequest.setLimit(limit);
      findProviderServicesByBlockCustomerRequest.setOffset(offset);

      FindProviderServicesByBlockCustomerResponse findProviderServicesByBlockCustomerResponse =
          umgrAccountEndpoint.findProviderServiceByBlockCustomer(
              findProviderServicesByBlockCustomerRequest);
      if (findProviderServicesByBlockCustomerResponse.getStatus().getCode() == 0) {
        map.put(
            "lstProviderServices",
            findProviderServicesByBlockCustomerResponse.getProviderServices());
        map.put(
            "totalProviderService",
            findProviderServicesByBlockCustomerResponse.getTotal() == null
                ? 0
                : findProviderServicesByBlockCustomerResponse.getTotal().intValue());
      } else {
        map.put(
            "lstProviderServices",
            findProviderServicesByBlockCustomerResponse.getProviderServices());
        map.put("totalProviderService", 0);
      }
      map.put("pagesizeProviderService", limit);
      map.put("offset", offset);

    } catch (Exception ex) {
      LOG.error(ex.getMessage());
    }
  }

  private void getTableProviderBlock(
      HttpServletRequest request, ModelMap map, String[] paramCustomersCifs) {
    try {
      String quickSearch = StringUtils.trimToEmpty(request.getParameter("quickSearch"));
      if (quickSearch == "") {
        quickSearch = null;
      }

      int offset = 0;
      int limit = 20;
      if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
        offset = Integer.parseInt(request.getParameter("d-49489-p"));
        if (offset > 0) {
          offset = (offset - 1) * limit;
        }
      }

      FindProviderByBlockCustomerRequest findProviderByBlockCustomerRequest =
          new FindProviderByBlockCustomerRequest();
      findProviderByBlockCustomerRequest.setTextSearch(quickSearch);
      findProviderByBlockCustomerRequest.setServiceTypes(null);
      if (paramCustomersCifs != null
          && paramCustomersCifs.length > 0
          && !StringUtils.EMPTY.equals(paramCustomersCifs[0])) {
        findProviderByBlockCustomerRequest.setCustomersCifs((Arrays.asList(paramCustomersCifs)));
      }
      findProviderByBlockCustomerRequest.setActive(null);
      findProviderByBlockCustomerRequest.setLimit(limit);
      findProviderByBlockCustomerRequest.setOffset(offset);

      FindProviderByBlockCustomerResponse findProviderByBlockCustomerResponse =
          umgrAccountEndpoint.findProviderByBlockCustomer(findProviderByBlockCustomerRequest);
      if (findProviderByBlockCustomerResponse.getStatus().getCode() == 0) {
        map.put("dataProvider", findProviderByBlockCustomerResponse.getProviders());
        map.put(
            "totalProvider",
            findProviderByBlockCustomerResponse.getTotal() == null
                ? 0
                : findProviderByBlockCustomerResponse.getTotal().intValue());
      } else {
        map.put("providerTypes", findProviderByBlockCustomerResponse.getProviders());
        map.put("totalProvider", 0);
      }
      map.put("pagesizeProvider", limit);
      map.put("offset", offset);

    } catch (Exception ex) {
      LOG.error(ex.getMessage());
    }
  }

  @RequestMapping(value = "/remove-blacklist-provider-service", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize(
      "hasAnyRole('"
          + RoleConstants.ADMIN_OPERATION
          + "','"
          + RoleConstants.CUSTOMERCARE_MANAGER
          + "', '"
          + RoleConstants.SALESUPPORT_MANAGER
          + "')")
  public ResponseEntity<?> removeBlackListProviderServicce(HttpServletRequest httpRequest)
      throws FrontEndException {
    try {
      String[] paramCustomerCif = httpRequest.getParameterValues("customerCifs");
      String paramBlockType = httpRequest.getParameter("blockType");
      String[] paramProvider = httpRequest.getParameterValues("providerCodes");
      String[] paramServiceCodes = httpRequest.getParameterValues("serviceCodes");
      String[] paramProviderServiceIds = httpRequest.getParameterValues("providerServiceIds");

      RemoveBlackListCustomerRequest request = new RemoveBlackListCustomerRequest();
      request.setBlockType(BlockType.valueOf(paramBlockType));
      if (paramCustomerCif != null
          && paramCustomerCif.length > 0
          && !StringUtils.EMPTY.equals(paramCustomerCif[0])) {
        paramCustomerCif = paramCustomerCif[0].split(",");
        request.setCustomerCifs(Arrays.asList(paramCustomerCif));
      }
      if (paramProvider != null
          && paramProvider.length > 0
          && !StringUtils.EMPTY.equals(paramProvider[0])) {
        paramProvider = paramProvider[0].split(",");
        request.setProviderCodes(Arrays.asList(paramProvider));
      }
      if (paramServiceCodes != null
          && paramServiceCodes.length > 0
          && !StringUtils.EMPTY.equals(paramServiceCodes[0])) {
        paramServiceCodes = paramServiceCodes[0].split(",");
        request.setServiceCodes(Arrays.asList(paramServiceCodes));
      }

      if (paramProviderServiceIds != null
          && paramProviderServiceIds.length > 0
          && !StringUtils.EMPTY.equals(paramProviderServiceIds[0])) {
        paramProviderServiceIds = paramProviderServiceIds[0].split(",");
        request.setProviderServiceIds(NumberUtil.convertListToLong(paramProviderServiceIds));
      }

      RemoveBlackListCustomerResponse responseMpo =
          umgrAccountEndpoint.removeBlockProviderService(request);

      return responseAjax(responseMpo.getStatus().getCode(), responseMpo.getStatus().getValue());
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return ResponseEntity.ok(new AjaxResponse(1, e.getMessage()));
    }
  }
}
