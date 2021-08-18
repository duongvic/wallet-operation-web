package vn.mog.ewallet.operation.web.controller.epin;


import static vn.mog.ewallet.operation.web.constant.RoleConstants.FA_MANAGER;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.FINANCE;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.FINANCESUPPORT_LEADER;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.FINANCE_LEADER;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.FINANCE_SUPPORT;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.SALESUPPORT_MANAGER;
import static vn.mog.ewallet.operation.web.utils.ValidationUtil.MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS;
import static vn.mog.ewallet.operation.web.utils.ValidationUtil.MESAGE_ORDER_FLOW_REJECT_PROCESS_SUCCESS;
import static vn.mog.ewallet.operation.web.utils.ValidationUtil.MESAGE_SUCCESS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.constant.SessionConstants;
import vn.mog.ewallet.operation.web.constant.SharedConstants;
import vn.mog.ewallet.operation.web.contract.AjaxResponse;
import vn.mog.ewallet.operation.web.contract.UserLogin;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.controller.epin.beans.EpinPurchaseOrderDetailTmp;
import vn.mog.ewallet.operation.web.controller.epin.beans.EpinPurchaseOrderFlowStage;
import vn.mog.ewallet.operation.web.controller.epin.beans.EpinPurchaseOrderStatus;
import vn.mog.ewallet.operation.web.controller.epin.beans.EpinPurchaseOrderSummaryTmp;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.exception.MessageNotify;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.epinstore.contract.card.GetAvailableCardRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.epinstore.contract.card.GetAvailableCardResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.BuyCardOfflineConfirmRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.BuyCardOfflineConfirmResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.CheckEpinPurchaseOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.CheckEpinPurchaseOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.CreateEpinPurchaseOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.CreateEpinPurchaseOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.ExportEpinPurchaseOrderFormRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.ExportEpinPurchaseOrderFormResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.FindEpinPurchaseOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.FindEpinPurchaseOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.GetEpinPurchaseOrderAttachmentRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.GetEpinPurchaseOrderAttachmentRequest.Action;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.GetEpinPurchaseOrderAttachmentResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.GetEpinPurchaseOrderOTPConfirmRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.GetEpinPurchaseOrderOTPConfirmResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.GetEpinPurchaseOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.GetEpinPurchaseOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.UpdateEpinPurchaseOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.UpdateEpinPurchaseOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.beans.EpinPurchaseOrder;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.beans.EpinPurchaseOrderAttachment;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.epo.beans.EpinPurchaseOrderDetail;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowApproveRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowApproveResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowRejectRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowRejectResponse;
import vn.mog.framework.common.utils.NumberUtil;
import vn.mog.framework.common.utils.SessionUtil;


@Controller
@RequestMapping(value = "/service/merchant-po-api")
public class EpinPurchaseOrderAPIController extends AbstractController {

  public static final String EPIN_PO_CONTROLLER = "/service/merchant-po-api";
  public static final String EPIN_PO_LIST = EPIN_PO_CONTROLLER + "/list";
  public static final String EPIN_PO_API = EPIN_PO_CONTROLLER + "/api-document";
  public static final String EPIN_PO_DETAIL = EPIN_PO_CONTROLLER + "/detail";
  private static Logger LOG = LogManager.getLogger(EpinPurchaseOrderAPIController.class);
  private static String MESSAGE_AVAILABLE = "service.exportcard.card.available";
  private static String MESSAGE_NOT_AVAILABLE = "service.exportcard.card.notavailable";
  private static String MESSAGE_NOT_ENOUGH = "service.exportcard.card.notenough";
  private static String MESSAGE_BUY_MPO_NOT_ENOUGH = "alert.controller.merchant-po.buy-mpo.notenough";
  private static String MESSAGE_NOT_ID = "alert.controller.merchant-po.not-id";

  private static String ONLINE_STORE = "ONLINE_STORE";
  private static String OFFLINE_STORE = "OFFLINE_STORE";

  private MessageNotify messageNotify;

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.SALESUPPORT + "', '"
          + SALESUPPORT_MANAGER + "','" + RoleConstants.FA_MANAGER + "','"
          + RoleConstants.FINANCE + "', '" + RoleConstants.FINANCE_LEADER + "', '"
          + RoleConstants.FINANCESUPPORT_LEADER + "', '" + RoleConstants.FINANCE_SUPPORT + "',   '"
          + RoleConstants.MERCHANT + "','" + RoleConstants.CUSTOMER + "','"
          + RoleConstants.RECONCILIATION + "','" + RoleConstants.RECONCILIATION_LEADER + "', '"
          + RoleConstants.CUSTOMERCARE+"','"+RoleConstants.CUSTOMERCARE_MANAGER+"')")
  public String epinPoList(HttpServletRequest request, ModelMap model) throws FrontEndException {

    UserLogin userLogin = (UserLogin) SessionUtil
        .getAttribute(SessionConstants.SESSION_ACCOUNT_LOGIN);
    long balanceCurrentUser = userLogin.getBalance();
    if (balanceCurrentUser < SharedConstants.CUSTOMER_MAINTENANCE_BALANCE) {
      model.put("checkMerchantExportCard", false);
    } else {
      model.put("checkMerchantExportCard", true);
    }
    model.put("minBalance", NumberUtil.formatNumber(SharedConstants.CUSTOMER_MAINTENANCE_BALANCE));

    String paramText = request.getParameter("quickSearch");
    String paramDateRange = request.getParameter("range");
    String[] paramStatusIds = request.getParameterValues("statusIds");
    String[] paramCustomerIds = request.getParameterValues("customerIds");

    int limit = 20;
    int offset = 0;
    String page = request.getParameter("d-49520-p");
    if (page == null) {
      page = request.getParameter("d-49489-p");
    }
    if (page != null && page.length() > 0) {
      Integer p = Integer.parseInt(page);
      offset = limit * (p - 1);
    }

    FindEpinPurchaseOrderRequest findRequest = new FindEpinPurchaseOrderRequest();

    Date[] dates = parseDateRange(paramDateRange, true);
    findRequest.setFromDate(dates[0]);
    findRequest.setToDate(dates[1]);

    if (paramCustomerIds != null && paramCustomerIds.length > 0 && StringUtils
        .isNotEmpty(paramCustomerIds[0])) {
      findRequest.setCustomerIds(NumberUtil.convertListToLong(paramCustomerIds));
    }

    if (paramStatusIds != null && paramStatusIds.length > 0 && StringUtils
        .isNotEmpty(paramStatusIds[0])) {
      findRequest.setStatusIds(Arrays.asList(paramStatusIds));
    }

    findRequest.setQuickSearch(paramText);
    findRequest.setLimit(limit);
    findRequest.setOffset(offset);

    List storeTypes = new ArrayList();
    storeTypes.add(ONLINE_STORE);
    findRequest.setStoreTypes(storeTypes);

    FindEpinPurchaseOrderResponse findResponse = epinEndpoint.findEpinPOs(findRequest);

    model.put("listStatus", EpinPurchaseOrderStatus.values());
    model.put("purchaseOrders", findResponse.getPurchaseOrders());
    model.put("pagesize", limit);
    model.put("offset", offset);
    model.put("total", findResponse.getCount().intValue());
    model.put("totalOfCards", findResponse.getQuantity().intValue());
    model.put("totalOfCapitalValue", findResponse.getCapitalValue().intValue());
    model.put("totalOfMoney", findResponse.getAmount().intValue());

    model.put("customers", cacheDataUtil.getAccountMerchantByBiz());
    model.put("listStatus", EpinPurchaseOrderStatus.values());

    return "/merchant_manager_api/list";
  }

  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.SALESUPPORT + "','"
          + SALESUPPORT_MANAGER + "','"
          + RoleConstants.SALE_DIRECTOR + "','" + RoleConstants.SALE_EXCUTIVE + "','"
          + RoleConstants.MERCHANT
          + "','" + RoleConstants.CUSTOMER
          + "','" + RoleConstants.CUSTOMERCARE + "','" + RoleConstants.CUSTOMERCARE_MANAGER + "','"
          + RoleConstants.RECONCILIATION + "','" + RoleConstants.RECONCILIATION_LEADER + "' ,'"
          + FA_MANAGER + "','"+FINANCE_LEADER+"','"+FINANCESUPPORT_LEADER+"','"+FINANCE_SUPPORT+"','"+FINANCE+"')")
  public String epinPoDetail(HttpServletRequest request, ModelMap model) throws FrontEndException {

    String quickSearch = StringUtils.trimToEmpty(request.getParameter("quickSearch"));
    String dateRange = StringUtils.trimToEmpty(request.getParameter("range"));
    String[] status = request.getParameterValues("status");
    String[] customerIds = request.getParameterValues("customerIds");
    String page = StringUtils.trimToEmpty(request.getParameter("d-49520-p"));

    String poCode = StringUtils.trimToEmpty(request.getParameter("poCode"));

    Long poMerchantId = NumberUtil.convertToLong(request.getParameter("poMerchantId"));
    if (poMerchantId > 0 || poCode.length() > 0) {
      GetEpinPurchaseOrderRequest epinRequest = new GetEpinPurchaseOrderRequest(poMerchantId,
          poCode, true);
      GetEpinPurchaseOrderResponse epinResponse = epinEndpoint.getEpinPO(epinRequest);
      model.put("merchantPO", epinResponse.getPurchaseOrder());
    } else {
      return "redirect:" + EPIN_PO_LIST;
    }

    model.put("quickSearch", quickSearch);
    model.put("range", dateRange);
    model.put("numberPage", page);
    model.put("status", status);
    model.put("customerIds", customerIds);

    return "/merchant_manager_api/mpo_detail_page";
  }

  /**
   * redirect page, create MPO
   */
  @RequestMapping(value = "/request-po", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.MERCHANT + "','" + RoleConstants.CUSTOMER + "')")
  public String requestPO(HttpServletRequest request) throws FrontEndException {

    return "/merchant_manager_api/mpo_stage_request";
  }

  @RequestMapping(value = "/request-po", method = RequestMethod.POST)
  @PreAuthorize("hasAnyRole('" + RoleConstants.MERCHANT + "','" + RoleConstants.CUSTOMER + "')")
  public String requestSubmitMPO(HttpServletRequest request, ModelMap model)
      throws FrontEndException {

    long totalQuantity = 0L;
    long totalMoney = 0L;
    long balance = 0L;
    long totalDiscount = 0L;
    long totalFeeAmount = 0L;

    String messageNotAvaliable = validation.notify(MESSAGE_NOT_AVAILABLE);
    String messageNotEnough = validation.notify(MESSAGE_NOT_ENOUGH);

    Integer totalTelco = NumberUtil.convertToInt(request.getParameter("totalTelco"));
    List<EpinPurchaseOrderDetail> purchaseOrderDetails = new ArrayList<>();
    List<EpinPurchaseOrderDetailTmp> listPOdetailTmp = new ArrayList<>();

    GetAvailableCardResponse responseAvailable = epinEndpoint
        .getAvailableCard(new GetAvailableCardRequest());
    Boolean availableStatus = true;
    for (int i = 1; i < totalTelco + 1; i++) {
      EpinPurchaseOrderDetail epinPOrderDetail = new EpinPurchaseOrderDetail();
      EpinPurchaseOrderDetailTmp epinPOrderDetailTmp = new EpinPurchaseOrderDetailTmp();

      String cardType = request.getParameter("telco_" + i);
      Integer faceValue = NumberUtil.convertToInt(request.getParameter("value_" + i));
      Integer quantity = NumberUtil.convertToInt(request.getParameter("quantity_" + i));
      String status = request.getParameter("status_" + i);

      epinPOrderDetail.setCardType(cardType);
      epinPOrderDetail.setFaceValue(faceValue);
      epinPOrderDetail.setQuantity(quantity);

      epinPOrderDetailTmp.setCardType(cardType);
      epinPOrderDetailTmp.setFaceValue(faceValue);
      epinPOrderDetailTmp.setQuantity(quantity);

      if (messageNotAvaliable.equals(status)) {
        availableStatus = false;
      } else {
        Map<Integer, Long> mcardType = responseAvailable.getAvailableCard().get(cardType);
        Long quantityCard = mcardType.get(faceValue);
        if (quantity > quantityCard) {
          availableStatus = false;
          status = messageNotEnough;
        }
      }

      epinPOrderDetailTmp.setStatus(status);

      totalQuantity += quantity;
      totalMoney += (long) quantity * faceValue;
      purchaseOrderDetails.add(epinPOrderDetail);
      listPOdetailTmp.add(epinPOrderDetailTmp);
    }

    CheckEpinPurchaseOrderRequest checkEpinRequest = new CheckEpinPurchaseOrderRequest(
        purchaseOrderDetails);
    CheckEpinPurchaseOrderResponse checkEpinResponse = epinEndpoint
        .checkEpinPurchaseOrder(checkEpinRequest);
    if (checkEpinResponse != null) {
      balance = checkEpinResponse.getBalance();
      totalDiscount = checkEpinResponse.getTotalDiscountAmount();
      totalFeeAmount = checkEpinResponse.getTotalFeeAmount();
    }

    EpinPurchaseOrderDetailTmp.order(listPOdetailTmp);

    model.put("totalQuantity", NumberUtil.formatNumber(totalQuantity));
    model.put("totalMoney", NumberUtil.formatNumber(totalMoney) + " VND");
    model.put("balance", NumberUtil.formatNumber(balance) + " VND");
    model.put("totalCommmision", NumberUtil.formatNumber(totalDiscount) + " VND");
    model.put("totalPayable",
        NumberUtil.formatNumber(totalMoney + totalFeeAmount - totalDiscount) + " VND");
    model.put("totalFeeAmount", totalFeeAmount + " VND");
    model.put("currentpay", balance - totalMoney);
    model.put("listPOdetail", listPOdetailTmp);
    model.put("pageRequest", "create");

    // old: totalMoney + totalFeeAmount - totalDiscount - balance < 0
    if (totalMoney + totalFeeAmount - balance < 0
        && balance > SharedConstants.CUSTOMER_MAINTENANCE_BALANCE) {
      model.put("disabledNext", "");
    } else {
      model.put("disabledNext", "");
      model.put("codeErr", 1);
      model.put("mesErr", validation.notify(MESSAGE_BUY_MPO_NOT_ENOUGH));
    }
    if (!availableStatus) {
      model.put("disabledNext", "");
    }
    return "/merchant_manager_api/mpo_stage_request_checkorder";
  }

  /**
   * Redirect page, edit mpo
   */
  @RequestMapping(value = "/edit-po", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.MERCHANT + "','" + RoleConstants.CUSTOMER + "')")
  public String editPO(HttpServletRequest request, ModelMap model) throws FrontEndException {

    Long epinId = NumberUtil.convertToLong(request.getParameter("id"));
    if (epinId == 0) {
      epinId = NumberUtil.convertToLong(request.getParameter("poMerchantId"));
    }

    if (epinId > 0) {
      GetEpinPurchaseOrderRequest epoRequest = new GetEpinPurchaseOrderRequest(epinId);
      GetEpinPurchaseOrderResponse epoResponse = epinEndpoint.getEpinPO(epoRequest);
      if (epoResponse.getStatus().getCode() == 0) {

        List<EpinPurchaseOrderDetail> epinDetails = epoResponse.getPurchaseOrder()
            .getPurchaseOrderDetails();
        List<EpinPurchaseOrderDetailTmp> epinDetailTmps = sumaryEpinInfo(epinDetails, null);

        model.put("purchaseOrder", epoResponse.getPurchaseOrder());
        model.put("purchaseOrderDetails", epinDetailTmps);
        model.put("poMerchantId", epinId);

        return "/merchant_manager_api/edit_po";
      }
    }
    return "redirect:" + EPIN_PO_LIST;
  }

  @RequestMapping(value = "/edit-po", method = RequestMethod.POST)
  @PreAuthorize("hasAnyRole('" + RoleConstants.MERCHANT + "','" + RoleConstants.CUSTOMER + "')")
  public String editConfirmPO(HttpServletRequest request, HttpServletResponse response,
      ModelMap model) throws FrontEndException {

    String messageNotAvaliable = validation.notify(MESSAGE_NOT_AVAILABLE);
    String messageNotEnough = validation.notify(MESSAGE_NOT_ENOUGH);

    Long epinId = NumberUtil.convertToLong(request.getParameter("id"));
    if (epinId == 0) {
      epinId = NumberUtil.convertToLong(request.getParameter("poMerchantId"));
    }
    if (epinId > 0) {
      long totalQuantity = 0L;
      long totalMoney = 0L;
      long balance = 0L;
      long totalDiscount = 0L;
      long totalFeeAmount = 0L;

      Boolean availableStatus = true;

      Integer totalTelco = NumberUtil.convertToInt(request.getParameter("totalTelco"));
      List<EpinPurchaseOrderDetail> epinDetails = new ArrayList<>();
      List<EpinPurchaseOrderDetailTmp> epinDetailTmps = new ArrayList<>();

      GetAvailableCardResponse responseAvailable = epinEndpoint
          .getAvailableCard(new GetAvailableCardRequest());
      for (int i = 1; i < totalTelco + 1; i++) {
        EpinPurchaseOrderDetail epinDetail = new EpinPurchaseOrderDetail();
        EpinPurchaseOrderDetailTmp epinDetailTmp = new EpinPurchaseOrderDetailTmp();

        String cardType = request.getParameter("telco_" + i);
        Integer faceValue = NumberUtil.convertToInt(request.getParameter("value_" + i));
        Integer quantity = NumberUtil.convertToInt(request.getParameter("quantity_" + i));
        String status = request.getParameter("status_" + i);

        epinDetail.setCardType(cardType);
        epinDetail.setFaceValue(faceValue);
        epinDetail.setQuantity(quantity);

        epinDetailTmp.setCardType(cardType);
        epinDetailTmp.setFaceValue(faceValue);
        epinDetailTmp.setQuantity(quantity);

        if (messageNotAvaliable.equals(status)) {
          availableStatus = false;
        } else {
          Map<Integer, Long> mcardType = responseAvailable.getAvailableCard().get(cardType);
          Long quantityCard = mcardType.get(faceValue);
          if (quantity > quantityCard) {
            availableStatus = false;
            status = messageNotEnough;
          }
        }

        epinDetailTmp.setStatus(status);

        totalQuantity += quantity;
        totalMoney += (long) quantity * faceValue;
        epinDetails.add(epinDetail);
        epinDetailTmps.add(epinDetailTmp);
      }

      CheckEpinPurchaseOrderRequest checkEpinRequest = new CheckEpinPurchaseOrderRequest(
          epinDetails);
      CheckEpinPurchaseOrderResponse checkEpinResponse = epinEndpoint
          .checkEpinPurchaseOrder(checkEpinRequest);
      if (checkEpinResponse != null) {
        balance = checkEpinResponse.getBalance();
        totalDiscount = checkEpinResponse.getTotalDiscountAmount();
        totalFeeAmount = checkEpinResponse.getTotalFeeAmount();
      }

      EpinPurchaseOrderDetailTmp.order(epinDetailTmps);

      // param of sumary page
      model.put("totalQuantity", NumberUtil.formatNumber(totalQuantity));
      model.put("totalMoney", NumberUtil.formatNumber(totalMoney) + " VND");
      model.put("balance", NumberUtil.formatNumber(balance) + " VND");
      model.put("totalCommmision", NumberUtil.formatNumber(totalDiscount) + " VND");
      model.put("totalPayable",
          NumberUtil.formatNumber(totalMoney + totalFeeAmount - totalDiscount) + " VND");
      model.put("totalFeeAmount", totalFeeAmount + " VND");
      model.put("currentpay", balance - totalMoney);
      model.put("listPOdetail", epinDetailTmps);
      model.put("pageRequest", "edit");
      model.put("poMerchantId", epinId);
      model.put("poCode", request.getParameter("poCode"));
      if (totalMoney + totalFeeAmount - totalDiscount - balance < 0) {
        model.put("disabledNext", "");
      } else {
        model.put("disabledNext", "");
        model.put("codeErr", 1);
        model.put("mesErr", validation.notify(MESSAGE_BUY_MPO_NOT_ENOUGH));
      }
      if (!availableStatus) {
        model.put("disabledNext", "");
      }

      return "/merchant_manager_api/mpo_stage_request_checkorder";
    } else {
      return "redirect:" + EPIN_PO_LIST;
    }
  }

  @RequestMapping(value = "/epin-checkorder", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.MERCHANT + "','" + RoleConstants.CUSTOMER + "')")
  public String epinCheckOrder(HttpServletRequest request, ModelMap model)
      throws FrontEndException {

    Long epinId = NumberUtil.convertToLong(request.getParameter("epinId"));
    if (epinId > 0) {

      GetEpinPurchaseOrderRequest epoRequest = new GetEpinPurchaseOrderRequest(epinId, true);
      GetEpinPurchaseOrderResponse epoResponse = epinEndpoint.getEpinPO(epoRequest);
      if (epoResponse.getStatus().getCode() == 0) {

        List<EpinPurchaseOrderDetail> epinDetails = epoResponse.getPurchaseOrder()
            .getPurchaseOrderDetails();
        List<EpinPurchaseOrderDetailTmp> epinDetailTmps = sumaryEpinInfo(epinDetails, null);
        EpinPurchaseOrder epinPo = epoResponse.getPurchaseOrder();

        long totalMoney = epinPo.getTotalValue();
        long totalDiscount = epinPo.getTotalCommission();
        long totalFeeAmount = epinPo.getTotalFee();

        model.put("purchaseOrder", epinPo);
        model.put("totalQuantity", NumberUtil.formatNumber(epinPo.getTotalQuantity()));
        model.put("totalMoney", NumberUtil.formatNumber(totalMoney));
        model.put("totalCommmision", NumberUtil.formatNumber(epinPo.getTotalCommission()));
        model.put("totalPayable",
            NumberUtil.formatNumber(totalMoney + totalFeeAmount - totalDiscount));
        model.put("totalFeeAmount", NumberUtil.formatNumber(totalFeeAmount));

        model.put("poCode", epinPo.getPoCode());
        model.put("listPOdetail", epinDetailTmps);

        model.put("pageRequest", "edit");
        model.put("poMerchantId", epinId);

        return "/merchant_manager_api/mpo_stage_request_checkorder";
      }
    }

    return "redirect:list";
  }

  @RequestMapping(value = "/epin-checkorder", method = RequestMethod.POST)
  @PreAuthorize("hasAnyRole('" + RoleConstants.MERCHANT + "','" + RoleConstants.CUSTOMER + "')")
  public String epinCheckorderPost(HttpServletRequest request, ModelMap model)
      throws FrontEndException {

    String messageNotAvaliable = validation.notify(MESSAGE_NOT_AVAILABLE);
    String messageNotEnough = validation.notify(MESSAGE_NOT_ENOUGH);

    Long totalQuantity = 0L;
    Long totalMoney = 0L;
    Integer totalTelco = NumberUtil.convertToInt(request.getParameter("totalTelco"));
    String buttonAction = request.getParameter("action");

    List<EpinPurchaseOrderDetail> epinDetails = new ArrayList<>();
    List<EpinPurchaseOrderDetailTmp> epinDetailTmps = new ArrayList<>();

    Boolean availableStatus = true;

    for (int i = 1; i < totalTelco + 1; i++) {
      EpinPurchaseOrderDetail epinDetail = new EpinPurchaseOrderDetail();
      EpinPurchaseOrderDetailTmp epinDetailTmp = new EpinPurchaseOrderDetailTmp();

      String cardType = request.getParameter("telco_" + i);
      Integer faceValue = NumberUtil.convertToInt(request.getParameter("value_" + i));
      Integer quantity = NumberUtil.convertToInt(request.getParameter("quantity_" + i));
      String status = request.getParameter("status_" + i);

      epinDetailTmp.setCardType(cardType);
      epinDetailTmp.setFaceValue(faceValue);
      epinDetailTmp.setQuantity(quantity);

      if (messageNotAvaliable.equals(status) || messageNotEnough.equals(status)) {
        availableStatus = false;
      }

      epinDetailTmp.setStatus(status);
      epinDetailTmps.add(epinDetailTmp);

      epinDetail.setCardType(cardType);
      epinDetail.setFaceValue(faceValue);
      epinDetail.setQuantity(quantity);
      epinDetails.add(epinDetail);

      totalQuantity += quantity;
      Integer money = faceValue * quantity;
      totalMoney += money;
    }

    String pageRequest = request.getParameter("pageRequest");
    if (StringUtils.isEmpty(pageRequest)) {
      return "redirect:" + EPIN_PO_LIST;
    }

    if (pageRequest.equals("create")) {
      EpinPurchaseOrder epin = new EpinPurchaseOrder(epinDetails, totalMoney, totalQuantity);
      CreateEpinPurchaseOrderRequest cEpinRequest = new CreateEpinPurchaseOrderRequest(epin);

      if (buttonAction.equals("next")) {
        cEpinRequest.setAction(CreateEpinPurchaseOrderRequest.Action.NEXT);
      } else {
        cEpinRequest.setAction(CreateEpinPurchaseOrderRequest.Action.SAVE);
      }
      CreateEpinPurchaseOrderResponse cEpinResponse = epinEndpoint.createEpinPO(cEpinRequest);
      int responseCreateCode = cEpinResponse.getStatus().getCode();
      model.put("mPoCreate", cEpinRequest);

      if (responseCreateCode == 0) {
        if (buttonAction.equals("next")) {
          messageNotify = new MessageNotify(MessageNotify.SUCCESS_CODE,
              MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
        } else {
          messageNotify = new MessageNotify(MessageNotify.SUCCESS_CODE, MESAGE_SUCCESS);
        }
        SessionUtil.setAttribute(MessageNotify.SESSION_MESSAGE_NOTIFY, messageNotify);
        return "redirect:" + EPIN_PO_LIST;
      } else {
        model.put("codeErr", responseCreateCode);
        model.put("mesErr", validation
            .notify("error.code." + responseCreateCode, cEpinResponse.getStatus().getValue()));

        long balance = 0L;
        long totalDiscount = 0L;
        long totalFeeAmount = 0L;

        CheckEpinPurchaseOrderRequest checkEpinRequest = new CheckEpinPurchaseOrderRequest(
            epinDetails);
        CheckEpinPurchaseOrderResponse checkEpinResponse = epinEndpoint
            .checkEpinPurchaseOrder(checkEpinRequest);
        if (checkEpinResponse != null) {
          balance = checkEpinResponse.getBalance();
          totalDiscount = checkEpinResponse.getTotalDiscountAmount();
          totalFeeAmount = checkEpinResponse.getTotalFeeAmount();
        }

        model.put("totalQuantity", NumberUtil.formatNumber(totalQuantity));
        model.put("totalMoney", NumberUtil.formatNumber(totalMoney) + " VND");
        model.put("balance", NumberUtil.formatNumber(balance) + " VND");
        model.put("totalCommmision", NumberUtil.formatNumber(totalDiscount) + " VND");
        model.put("totalPayable",
            NumberUtil.formatNumber(totalMoney + totalFeeAmount - totalDiscount) + " VND");
        model.put("totalFeeAmount", totalFeeAmount + " VND");
        model.put("currentpay", balance - totalMoney);
        model.put("listPOdetail", epinDetailTmps);
        model.put("pageRequest", "create");

        if (!availableStatus) {
          model.put("disabledNext", "");
        }

        return "/merchant_manager_api/mpo_stage_request_checkorder";
      }

    } else if (pageRequest.equals("edit")) {
      long epinId = NumberUtil.convertToLong(request.getParameter("poMerchantId"));

      EpinPurchaseOrder epin = new EpinPurchaseOrder(epinId, totalMoney, totalQuantity,
          epinDetails);
      UpdateEpinPurchaseOrderRequest updateEpinRequest = new UpdateEpinPurchaseOrderRequest(epin);

      if (buttonAction.equals("next")) {
        updateEpinRequest.setAction(UpdateEpinPurchaseOrderRequest.Action.NEXT);
      } else {
        updateEpinRequest.setAction(UpdateEpinPurchaseOrderRequest.Action.SAVE);
      }
      UpdateEpinPurchaseOrderResponse updateEpinResponse = epinEndpoint
          .updateEpinPO(updateEpinRequest);
      model.put("mPoCreate", updateEpinRequest);

      int responseCode = updateEpinResponse.getStatus().getCode();

      if (responseCode == 0) {
        if (buttonAction.equals("next")) {
          messageNotify = new MessageNotify(MessageNotify.SUCCESS_CODE,
              MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
        } else {
          messageNotify = new MessageNotify(MessageNotify.SUCCESS_CODE, MESAGE_SUCCESS);
        }
        SessionUtil.setAttribute(MessageNotify.SESSION_MESSAGE_NOTIFY, messageNotify);
        return "redirect:" + EPIN_PO_LIST;

      } else {
        model.put("codeErr", responseCode);
        model.put("mesErr", validation
            .notify("error.code." + responseCode, updateEpinResponse.getStatus().getValue()));

        long balance = 0L;
        long totalDiscount = 0L;
        long totalFeeAmount = 0L;

        CheckEpinPurchaseOrderRequest checkEpinReq = new CheckEpinPurchaseOrderRequest(epinDetails);
        CheckEpinPurchaseOrderResponse checkEpinRes = epinEndpoint
            .checkEpinPurchaseOrder(checkEpinReq);
        if (checkEpinRes != null) {
          balance = checkEpinRes.getBalance();
          totalDiscount = checkEpinRes.getTotalDiscountAmount();
          totalFeeAmount = checkEpinRes.getTotalFeeAmount();
        }

        // param of sumary page
        model.put("totalQuantity", NumberUtil.formatNumber(totalQuantity));
        model.put("totalMoney", NumberUtil.formatNumber(totalMoney) + " VND");
        model.put("balance", NumberUtil.formatNumber(balance) + " VND");
        model.put("totalCommmision", NumberUtil.formatNumber(totalDiscount) + " VND");
        model.put("totalPayable",
            NumberUtil.formatNumber(totalMoney + totalFeeAmount - totalDiscount) + " VND");
        model.put("totalFeeAmount", totalFeeAmount + " VND");
        model.put("currentpay", balance - totalMoney);
        model.put("listPOdetail", epinDetailTmps);
        model.put("pageRequest", "edit");
        model.put("poMerchantId", epinId);
        model.put("poCode", request.getParameter("poCode"));

        if (!availableStatus) {
          model.put("disabledNext", "");
        }

        return "/merchant_manager_api/mpo_stage_request_checkorder";
      }
    }
    return "redirect:" + EPIN_PO_LIST;
  }

  @RequestMapping(value = "/epin-export", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.MERCHANT + "','" + RoleConstants.CUSTOMER + "')")
  public String epinExportAllowed(HttpServletRequest request, ModelMap model)
      throws FrontEndException {

    Long epinId = NumberUtil.convertToLong(request.getParameter("epinId"));
    if (epinId > 0) {

      GetEpinPurchaseOrderRequest ePinRequest = new GetEpinPurchaseOrderRequest(epinId, true);
      GetEpinPurchaseOrderResponse ePinResponse = epinEndpoint.getEpinPO(ePinRequest);
      EpinPurchaseOrder epin = ePinResponse.getPurchaseOrder();
      if (epin != null && epin.getStage()
          .equals(EpinPurchaseOrderFlowStage.EPIN_EXPORT_ALLOWED.code)) {

        List<EpinPurchaseOrderDetail> epinDetails = epin.getPurchaseOrderDetails();
        List<EpinPurchaseOrderDetailTmp> epinDetailTmps = sumaryEpinInfo(epinDetails, null);

        boolean checkEpinAvaliable = checkEpinAvaliable(epinDetailTmps);

        long balance = 0L;
        long totalDiscount = 0L;
        long totalFeeAmount = 0L;
        long totalMoney = epin.getTotalValue();

        CheckEpinPurchaseOrderRequest checkEpinReq = new CheckEpinPurchaseOrderRequest(epinDetails);
        CheckEpinPurchaseOrderResponse checkEpinRes = epinEndpoint
            .checkEpinPurchaseOrder(checkEpinReq);
        if (checkEpinRes != null) {
          balance = checkEpinRes.getBalance();
          totalDiscount = checkEpinRes.getTotalDiscountAmount();
          totalFeeAmount = checkEpinRes.getTotalFeeAmount();
        }

        model.put("totalQuantity", NumberUtil.formatNumber(epin.getTotalQuantity()));
        model.put("totalMoney", NumberUtil.formatNumber(totalMoney) + " VND");
        model.put("balance", NumberUtil.formatNumber(balance) + " VND");
        model.put("totalCommmision", NumberUtil.formatNumber(totalDiscount) + " VND");
        model.put("totalPayable",
            NumberUtil.formatNumber(totalMoney + totalFeeAmount - totalDiscount) + " VND");
        model.put("totalFeeAmount", totalFeeAmount + " VND");
        model.put("currentpay", balance - totalMoney);
        model.put("listPOdetail", epinDetailTmps);
        model.put("pageRequest", "create");

        model.put("epinId", epinId);

        if (totalMoney + totalFeeAmount - balance < 0
            && balance > SharedConstants.CUSTOMER_MAINTENANCE_BALANCE) {
          model.put("disabledNext", "");
        } else {
          model.put("disabledNext", "");
          model.put("codeErr", 1);
          model.put("mesErr", validation.notify(MESSAGE_BUY_MPO_NOT_ENOUGH));
        }

        if (!checkEpinAvaliable) {
          model.put("disabledNext", "");
        }
      }
      return "/merchant_manager_api/mpo_stage_export";
    }

    return "redirect:list";
  }

  @RequestMapping(value = "/epin-export", method = RequestMethod.POST)
  @PreAuthorize("hasAnyRole('" + RoleConstants.MERCHANT + "','" + RoleConstants.CUSTOMER + "')")
  public String epinExportAllowedPost(HttpServletRequest request, ModelMap model)
      throws FrontEndException {

    Long epinId = NumberUtil.convertToLong(request.getParameter("epinId"));
    String action = request.getParameter("action");
    if (epinId > 0) {
      GetEpinPurchaseOrderRequest epinRequest = new GetEpinPurchaseOrderRequest(epinId, false);
      GetEpinPurchaseOrderResponse epinResponse = epinEndpoint.getEpinPO(epinRequest);
      EpinPurchaseOrder epin = epinResponse.getPurchaseOrder();
      if (epin != null && epin.getStage()
          .equals(EpinPurchaseOrderFlowStage.EPIN_EXPORT_ALLOWED.code)) {
        if (BTN_EXPORT.equals(action)) {

          GetEpinPurchaseOrderOTPConfirmRequest geoRequest = new GetEpinPurchaseOrderOTPConfirmRequest(
              epinId);
          GetEpinPurchaseOrderOTPConfirmResponse gepResonse = epinEndpoint
              .getOTPConfirm(geoRequest);
          if (gepResonse.getStatus().getCode() == 0) {
            model.put("id", epinId);
            return "redirect:request-otp";
          }
        } else if (BTN_REJECT.equals(action)) {

          OrderFlowRejectRequest ofrRequest = new OrderFlowRejectRequest(epinId);
          OrderFlowRejectResponse ofrResponse = epinEndpoint
              .epinPurchaseOrderWorkflowReject(ofrRequest);
          if (ofrResponse.getStatus().getCode() == 0) {
            messageNotify = new MessageNotify(MessageNotify.SUCCESS_CODE,
                MESAGE_ORDER_FLOW_REJECT_PROCESS_SUCCESS);
          } else {
            messageNotify = new MessageNotify(MessageNotify.ERROR_CODE,
                ofrResponse.getStatus().getValue());
          }
          SessionUtil.setAttribute(MessageNotify.SESSION_MESSAGE_NOTIFY, messageNotify);
        }
      }
    }

    return "redirect:list";
  }

  @RequestMapping(value = "/request-otp", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.MERCHANT + "','" + RoleConstants.CUSTOMER + "')")
  public String requestOTP() throws FrontEndException {

    return "/merchant_manager_api/mpo_stage_export_otp";
  }

  @RequestMapping(value = "/request-otp", method = RequestMethod.POST)
  @PreAuthorize("hasAnyRole('" + RoleConstants.MERCHANT + "','" + RoleConstants.CUSTOMER + "')")
  public String requestOTPsubmit(HttpServletRequest request, ModelMap model)
      throws FrontEndException {

    String otp = request.getParameter("otp");
    Long epinId = NumberUtil.convertToLong(request.getParameter("id"));

    if (StringUtils.isNotBlank(otp) && epinId > 0) {
      BuyCardOfflineConfirmRequest confirmReq = new BuyCardOfflineConfirmRequest(epinId, otp);
      BuyCardOfflineConfirmResponse confirmRes = epinEndpoint.buyCardOfflineConfirm(confirmReq);
      if (confirmRes.getStatus().getCode() == 0) {
        model.put("id", epinId);
        return "redirect:" + EPIN_PO_CONTROLLER + "/export-mpo";
      } else {
        model.put("codeErr", confirmRes.getStatus().getCode());
        model.put("mesErr", confirmRes.getStatus().getValue());
        model.put("id", epinId);
        return "/merchant_manager_api/mpo_stage_export_otp";
      }
    }

    return "/merchant_manager_api/mpo_stage_export_otp";
  }

  @RequestMapping(value = "/epin-approve", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.SALESUPPORT + "','"
          + SALESUPPORT_MANAGER + "')")
  public String epinApprove(HttpServletRequest request, ModelMap model) throws FrontEndException {

    Long epinId = NumberUtil.convertToLong(request.getParameter("epinId"));
    if (epinId > 0) {

      GetEpinPurchaseOrderRequest gEpinRequest = new GetEpinPurchaseOrderRequest(epinId, true);
      GetEpinPurchaseOrderResponse gEpinResponse = epinEndpoint.getEpinPO(gEpinRequest);
      EpinPurchaseOrder ePin = gEpinResponse.getPurchaseOrder();

      if (ePin != null && ePin.getStage()
          .equals(EpinPurchaseOrderFlowStage.SALESUPPORT_READY_TO_VERIFY.code)) {

        List<EpinPurchaseOrderDetail> epinDetails = ePin.getPurchaseOrderDetails();
        List<EpinPurchaseOrderDetailTmp> epinDetailTmps
            = sumaryEpinInfo(epinDetails, ePin.getMerchantCif());

        boolean checkEpinAvaliable = checkEpinAvaliable(epinDetailTmps);
        if (!checkEpinAvaliable) {
          model.put("disabledNext", "");
        }
        long totalDiscount = 0L;
        long totalFeeAmount = 0L;
        long totalMoney = ePin.getTotalValue();

        CheckEpinPurchaseOrderRequest checkEpinReq = new CheckEpinPurchaseOrderRequest(epinDetails);
        CheckEpinPurchaseOrderResponse checkEpinRes = epinEndpoint
            .checkEpinPurchaseOrder(checkEpinReq);
        if (checkEpinRes != null) {
          totalDiscount = checkEpinRes.getTotalDiscountAmount();
          totalFeeAmount = checkEpinRes.getTotalFeeAmount();
        }

        model.put("totalQuantity", NumberUtil.formatNumber(ePin.getTotalQuantity()));
        model.put("totalMoney", NumberUtil.formatNumber(totalMoney) + " VND");
        model.put("totalCommmision", NumberUtil.formatNumber(ePin.getTotalCommission()) + " VND");
        model.put("totalPayable",
            NumberUtil.formatNumber(totalMoney + totalFeeAmount - totalDiscount) + " VND");
        model.put("totalFeeAmount", totalFeeAmount + " VND");
        model.put("listPOdetail", epinDetailTmps);
        model.put("epinId", epinId);

        return "/merchant_manager_api/mpo_stage_approve";
      }
    }

    return "redirect:" + EPIN_PO_LIST;
  }

  @RequestMapping(value = "/epin-approve", method = RequestMethod.POST)
  @PreAuthorize(
      "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.SALESUPPORT + "','"
          + SALESUPPORT_MANAGER + "')")
  public String epinApprovePost(HttpServletRequest request, ModelMap model) throws Exception {

    Long epinId = NumberUtil.convertToLong(request.getParameter("epinId"));
    String remark = request.getParameter("remark");
    String action = request.getParameter("action");
    if (epinId > 0) {

      if (BTN_APPROVE.equals(action)) {

        OrderFlowApproveRequest ofaRequest = new OrderFlowApproveRequest(epinId, remark);
        OrderFlowApproveResponse ofaResponse = epinEndpoint
            .epinPurchaseOrderWorkflowApporve(ofaRequest);

        if (ofaResponse.getStatus().getCode() == 0) {
          messageNotify = new MessageNotify(MessageNotify.SUCCESS_CODE,
              MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
        } else {
          messageNotify = new MessageNotify(MessageNotify.ERROR_CODE,
              ofaResponse.getStatus().getValue());
        }

      } else if (BTN_REJECT.equals(action)) {

        OrderFlowRejectRequest ofrRrequest = new OrderFlowRejectRequest(epinId, remark);
        OrderFlowRejectResponse ofrResponse = epinEndpoint
            .epinPurchaseOrderWorkflowReject(ofrRrequest);

        if (ofrResponse.getStatus().getCode() == 0) {
          messageNotify = new MessageNotify(MessageNotify.SUCCESS_CODE,
              MESAGE_ORDER_FLOW_REJECT_PROCESS_SUCCESS);
        } else {
          messageNotify = new MessageNotify(MessageNotify.ERROR_CODE,
              ofrResponse.getStatus().getValue());
        }
      }

      SessionUtil.setAttribute(MessageNotify.SESSION_MESSAGE_NOTIFY, messageNotify);
    }
    return "redirect:" + EPIN_PO_LIST;
  }

  @RequestMapping(value = "/update-request", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.MERCHANT + "','" + RoleConstants.CUSTOMER + "')")
  public String updateRequestGetPO() throws FrontEndException {
    return "redirect:" + EPIN_PO_LIST;
  }

  @RequestMapping(value = "/update-request", method = RequestMethod.POST)
  @PreAuthorize("hasAnyRole('" + RoleConstants.MERCHANT + "','" + RoleConstants.CUSTOMER + "')")
  public String updateRequestPO(HttpServletRequest request, ModelMap model)
      throws FrontEndException {

    String pageRequest = request.getParameter("pageRequest");
    if (pageRequest == null || StringUtils.EMPTY.equals(pageRequest)) {
      return "redirect:" + EPIN_PO_LIST;
    }

    Long totalMoney = 0L;
    String poCode = request.getParameter("poCode");
    Integer totalTelco = NumberUtil.convertToInt(request.getParameter("totalTelco"));
    Long epinId = NumberUtil.convertToLong(request.getParameter("poMerchantId"));

    List<EpinPurchaseOrderDetailTmp> epinDetailTmps = new ArrayList<>();
    for (int i = 1; i < totalTelco + 1; i++) {
      EpinPurchaseOrderDetailTmp epinDetailTmp = new EpinPurchaseOrderDetailTmp();

      String cardType = request.getParameter("telco_" + i);
      Integer faceValue = NumberUtil.convertToInt(request.getParameter("value_" + i));
      Integer quantity = NumberUtil.convertToInt(request.getParameter("quantity_" + i));
      String status = request.getParameter("status_" + i);

      epinDetailTmp.setCardType(cardType);
      epinDetailTmp.setFaceValue(faceValue);
      epinDetailTmp.setQuantity(quantity);
      epinDetailTmp.setStatus(status);

      totalMoney += faceValue * quantity;
      epinDetailTmps.add(epinDetailTmp);
    }

    EpinPurchaseOrderDetailTmp.order(epinDetailTmps);

    model.put("purchaseOrderDetails", epinDetailTmps);

    if (pageRequest.equals("create")) {
      model.put("totalTelco", totalTelco);
      model.put("totalValue", totalMoney);
      return "/merchant_manager_api/mpo_stage_request";

    } else if (pageRequest.equals("edit")) {

      model.put("poCode", poCode);
      model.put("poMerchantId", epinId);
      model.put("totalTelco", totalTelco);
      model.put("totalValue", totalMoney);
      return "/merchant_manager_api/edit_po";
    }
    return "redirect:" + EPIN_PO_LIST;
  }

  @RequestMapping(value = "/export-mpo", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.MERCHANT + "','" + RoleConstants.CUSTOMER + "')")
  public String exportMPO() throws FrontEndException {

    return "/merchant_manager_api/mpo_stage_export_download";
  }

  /**
   * Tải file về
   */
  @RequestMapping(value = "/export-mpo", method = RequestMethod.POST)
  @PreAuthorize("hasAnyRole('" + RoleConstants.MERCHANT + "','" + RoleConstants.CUSTOMER + "')")
  public String exportMPOsubmit(HttpServletRequest request, HttpServletResponse response,
      ModelMap model) throws FrontEndException {

    Long epinId = NumberUtil.convertToLong(request.getParameter("poMerchantId"));

    if (epinId > 0) {
      GetEpinPurchaseOrderAttachmentRequest attachmentReq = new GetEpinPurchaseOrderAttachmentRequest(
          epinId, Action.GET_FILE);
      GetEpinPurchaseOrderAttachmentResponse attachmentRes = epinEndpoint
          .getMPOAttachment(attachmentReq);

      if (attachmentRes.getStatus().getCode() == 0) {
        EpinPurchaseOrderAttachment attachment = attachmentRes.getAttachment();
        exportAttach(response, attachment);
      } else {
        model.put("codeErr", attachmentRes.getStatus().getCode());
        model.put("mesErr", attachmentRes.getStatus().getValue());
        model.put("id", epinId);
      }
    } else {
      model.put("codeErr", 1);
      model.put("mesErr", validation.notify(MESSAGE_NOT_ID));
      return "/merchant_manager_api/list";
    }
    return "/merchant_manager_api/mpo_stage_export_download";
  }

  @RequestMapping(value = "/export-epin", method = RequestMethod.GET)
  @PreAuthorize("hasAnyRole('" + RoleConstants.MERCHANT + "','" + RoleConstants.CUSTOMER + "')")
  public void exportEpin(HttpServletRequest request, HttpServletResponse response)
      throws FrontEndException {
    Long epinId = NumberUtil.convertToLong(request.getParameter("poMerchantId"));
    if (epinId > 0) {
      GetEpinPurchaseOrderAttachmentRequest atRequest = new GetEpinPurchaseOrderAttachmentRequest(
          epinId, Action.GET_FILE);
      GetEpinPurchaseOrderAttachmentResponse attachmentResponse = epinEndpoint
          .getMPOAttachment(atRequest);

      if (attachmentResponse.getStatus().getCode() == 0) {
        EpinPurchaseOrderAttachment attachment = attachmentResponse.getAttachment();
        exportAttach(response, attachment);
      }
    }
  }

  @RequestMapping(value = "/export-po-new", method = RequestMethod.GET)
  public void exportCreateNewEpin(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    Long epinId = NumberUtil.convertToLong(request.getParameter("poMerchantId"));
    try {
      if (epinId > 0) {
        ExportEpinPurchaseOrderFormRequest atRequest = new ExportEpinPurchaseOrderFormRequest(
            epinId);
        ExportEpinPurchaseOrderFormResponse attachmentResponse = epinEndpoint
            .exportEpoNewCreate(atRequest);

        if (attachmentResponse.getStatus().getCode() == 0) {
          response.setContentType("application/vnd.ms-excel");
          response.setHeader("Content-Disposition",
              "attachment; filename=" + attachmentResponse.getName());
          FileCopyUtils.copy(attachmentResponse.getContent(), response.getOutputStream());
          response.flushBuffer();
        }
      }
    } catch (Exception ex) {
      LOG.error(ex.getMessage());
    }
  }

  @RequestMapping(value = "/getOtp", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize("hasAnyRole('" + RoleConstants.MERCHANT + "','" + RoleConstants.CUSTOMER + "')")
  public ResponseEntity<?> getOtp(HttpServletRequest request) throws FrontEndException {

    Long epinId = NumberUtil.convertToLong(request.getParameter("poMerchantId"));
    if (epinId > 0) {
      GetEpinPurchaseOrderOTPConfirmRequest geoRequest = new GetEpinPurchaseOrderOTPConfirmRequest(
          epinId);
      GetEpinPurchaseOrderOTPConfirmResponse gepResonse = epinEndpoint.getOTPConfirm(geoRequest);
      return ResponseEntity.ok(new AjaxResponse(gepResonse.getStatus().getCode(),
          gepResonse.getStatus().getValue()));
    } else {
      return ResponseEntity.ok(new AjaxResponse(1, validation.notify(MESSAGE_NOT_ID)));
    }
  }

  @RequestMapping(value = "/resendPass", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize("hasAnyRole('" + RoleConstants.MERCHANT + "','" + RoleConstants.CUSTOMER + "')")
  public ResponseEntity<?> resendPass(HttpServletRequest httpRequest) throws FrontEndException {
    Long epinId = NumberUtil.convertToLong(httpRequest.getParameter("poMerchantId"));
    if (epinId > 0) {
      GetEpinPurchaseOrderAttachmentRequest request = new GetEpinPurchaseOrderAttachmentRequest(
          epinId, Action.GET_PASSWORD);
      GetEpinPurchaseOrderAttachmentResponse response = epinEndpoint.getMPOAttachment(request);
      return ResponseEntity
          .ok(new AjaxResponse(response.getStatus().getCode(), response.getStatus().getValue()));
    } else {
      return ResponseEntity.ok(new AjaxResponse(1, validation.notify(MESSAGE_NOT_ID)));
    }
  }

  @RequestMapping(value = "/getSumary", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize(
      "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + SALESUPPORT_MANAGER + "' ,'"
          + RoleConstants.SALE_DIRECTOR + "','"
          + RoleConstants.SALE_EXCUTIVE + "','" + RoleConstants.MERCHANT + "','"
          + RoleConstants.CUSTOMER
          + "')")
  public EpinPurchaseOrderSummaryTmp getSumary(HttpServletRequest request)
      throws FrontEndException {

    Long epinId = NumberUtil.convertToLong(request.getParameter("poMerchantId"));
    if (epinId > 0) {
      long totalQuantity = 0L;
      long totalMoney = 0L;

      String messageNotAvaliable = validation.notify(MESSAGE_NOT_AVAILABLE);
      String messageAvaliable = validation.notify(MESSAGE_AVAILABLE);
      String messageNotEnough = validation.notify(MESSAGE_NOT_ENOUGH);

      GetEpinPurchaseOrderRequest epinRequest = new GetEpinPurchaseOrderRequest(epinId);
      GetEpinPurchaseOrderResponse epinResponse = epinEndpoint.getEpinPO(epinRequest);

      List<EpinPurchaseOrderDetail> epinDetails = epinResponse.getPurchaseOrder()
          .getPurchaseOrderDetails();
      List<EpinPurchaseOrderDetailTmp> epinDetailTmps = new ArrayList<>();

      GetAvailableCardResponse responseAvailable = epinEndpoint
          .getAvailableCard(new GetAvailableCardRequest());

      CheckEpinPurchaseOrderRequest checkBalanceReq = new CheckEpinPurchaseOrderRequest(
          epinDetails);
      CheckEpinPurchaseOrderResponse checkBalanceRes = epinEndpoint
          .checkEpinPurchaseOrder(checkBalanceReq);

      for (EpinPurchaseOrderDetail item : epinDetails) {
        EpinPurchaseOrderDetailTmp epinDetailTmp = new EpinPurchaseOrderDetailTmp();
        epinDetailTmp.setCardType(item.getCardType());
        epinDetailTmp.setFaceValue(item.getFaceValue());
        epinDetailTmp.setQuantity(item.getQuantity());

        Long quantity = (long) item.getQuantity();
        totalQuantity += quantity;
        totalMoney += quantity * item.getFaceValue();

        if (responseAvailable.getAvailableCard() != null) {
          Map<String, Map<Integer, Long>> availableCard = responseAvailable.getAvailableCard();
          if (availableCard.get(item.getCardType()) != null) {
            Map<Integer, Long> cardType = availableCard.get(item.getCardType());
            if (cardType.get(item.getFaceValue()) != null) {
              Long quantityCard = cardType.get(item.getFaceValue());
              if (quantityCard >= quantity) {
                epinDetailTmp.setStatus(messageAvaliable);
              } else {
                epinDetailTmp.setStatus(messageNotEnough);
              }
            } else {
              epinDetailTmp.setStatus(messageNotAvaliable);
            }
          } else {
            epinDetailTmp.setStatus(messageNotAvaliable);
          }
        } else {
          epinDetailTmp.setStatus(messageNotAvaliable);
        }

        epinDetailTmps.add(epinDetailTmp);
      }

      EpinPurchaseOrderDetailTmp.order(epinDetailTmps);

      EpinPurchaseOrderSummaryTmp epinSummaryTmp = new EpinPurchaseOrderSummaryTmp();
      epinSummaryTmp.setPurchaseOrderDetails(epinDetailTmps);
      epinSummaryTmp.setBalance(checkBalanceRes.getBalance());
      epinSummaryTmp.setTotalDiscountAmount(checkBalanceRes.getTotalDiscountAmount());
      epinSummaryTmp.setTotalFeeAmount(checkBalanceRes.getTotalFeeAmount());
      epinSummaryTmp.setTotalQuantity(totalQuantity);
      epinSummaryTmp.setTotalMoney(totalMoney);

      return epinSummaryTmp;
    } else {
      return null;
    }
  }

  @RequestMapping(value = "/getDetail", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize(
      "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + SALESUPPORT_MANAGER + "' ,'"
          + RoleConstants.SALE_DIRECTOR + "','"
          + RoleConstants.SALE_EXCUTIVE + "','" + RoleConstants.MERCHANT + "','"
          + RoleConstants.CUSTOMER
          + "')")
  public GetEpinPurchaseOrderResponse getDetail(HttpServletRequest request)
      throws FrontEndException {
    Long epinId = NumberUtil.convertToLong(request.getParameter("poMerchantId"));
    if (epinId > 0) {
      return epinEndpoint.getEpinPO(new GetEpinPurchaseOrderRequest(epinId));
    } else {
      return null;
    }
  }

  @RequestMapping(value = "/getAvailabelCard", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize("hasAnyRole('" + RoleConstants.MERCHANT + "','" + RoleConstants.CUSTOMER + "')")
  public ResponseEntity<?> getAvailabelCard(HttpServletRequest request) throws FrontEndException {
    String cardType = request.getParameter("cardtype");
    List<String> listCard = Collections.singletonList(cardType);

    GetAvailableCardRequest cardReq = new GetAvailableCardRequest(listCard);
    GetAvailableCardResponse cardRes = epinEndpoint.getAvailableCard(cardReq);

    Map<Integer, Long> listAvailabel = null;

    if (cardRes.getAvailableCard() != null) {
      listAvailabel = cardRes.getAvailableCard().get(cardType);
    }

    return ResponseEntity.ok(listAvailabel);
  }

  @RequestMapping(value = "/api-document", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + SALESUPPORT_MANAGER + "','"
          + RoleConstants.SALE_DIRECTOR + "','"
          + RoleConstants.SALE_EXCUTIVE + "','" + RoleConstants.MERCHANT + "','"
          + RoleConstants.CUSTOMER
          + "')")
  public String apiDocument() throws FrontEndException {
    return "/merchant_manager_api/api_document";
  }

  private List<EpinPurchaseOrderDetailTmp>
  sumaryEpinInfo(List<EpinPurchaseOrderDetail> epinDetails,
      String payerCif) {
    List<EpinPurchaseOrderDetailTmp> epinDetailTmps = new ArrayList<>();
    String messageNotAvaliable = validation.notify(MESSAGE_NOT_AVAILABLE);
    String messageAvaliable = validation.notify(MESSAGE_AVAILABLE);
    String messageNotEnough = validation.notify(MESSAGE_NOT_ENOUGH);

    GetAvailableCardRequest requestAvailable = new GetAvailableCardRequest();
    requestAvailable.setPayerCif(payerCif);
    GetAvailableCardResponse responseAvailable = epinEndpoint.getAvailableCard(requestAvailable);

    for (EpinPurchaseOrderDetail item : epinDetails) {
      long cardStock = 0L;
      EpinPurchaseOrderDetailTmp epinDetailTmp = new EpinPurchaseOrderDetailTmp();
      epinDetailTmp.setCardType(item.getCardType());
      epinDetailTmp.setFaceValue(item.getFaceValue());
      epinDetailTmp.setQuantity(item.getQuantity());

      if (responseAvailable.getAvailableCard() != null) {
        Map<String, Map<Integer, Long>> availableCard = responseAvailable.getAvailableCard();

        if (availableCard.get(item.getCardType()) != null) {

          Map<Integer, Long> cardType = availableCard.get(item.getCardType());

          if (cardType.get(item.getFaceValue()) != null) {
            Long quantity = cardType.get(item.getFaceValue());
            cardStock += quantity;

            if (quantity >= item.getQuantity()) {
              epinDetailTmp.setStatus(messageAvaliable);
            } else {
              epinDetailTmp.setStatus(messageNotEnough);
            }
          } else {
            epinDetailTmp.setStatus(messageNotAvaliable);
          }
        } else {
          epinDetailTmp.setStatus(messageNotAvaliable);
        }
      } else {
        epinDetailTmp.setStatus(messageNotAvaliable);
      }

      epinDetailTmp.setCardStock(cardStock);

      epinDetailTmps.add(epinDetailTmp);
    }

    EpinPurchaseOrderDetailTmp.order(epinDetailTmps);

    return epinDetailTmps;
  }

  private boolean checkEpinAvaliable(List<EpinPurchaseOrderDetailTmp> epinDetailTmps) {
    String messageNotAvaliable = validation.notify(MESSAGE_NOT_AVAILABLE);
    String messageNotEnough = validation.notify(MESSAGE_NOT_ENOUGH);
    for (EpinPurchaseOrderDetailTmp item : epinDetailTmps) {
      if (messageNotEnough.equals(item.getStatus()) || messageNotAvaliable
          .equals(item.getStatus())) {
        return false;
      }
    }
    return true;
  }

  private void exportAttach(HttpServletResponse response, EpinPurchaseOrderAttachment attachment) {
    try {
      response.setContentType("application/vnd.ms-excel");
      response.setHeader("Content-Disposition", "attachment; filename=" + attachment.getName());
      FileCopyUtils.copy(attachment.getContent(), response.getOutputStream());
      // response.getOutputStream().flush();
      // response.getOutputStream().close();
      response.flushBuffer();
    } catch (IOException e) {
      LOG.error("Error writing file to output stream. Filename was '{}'" + attachment.getName(), e);
      throw new IllegalArgumentException("IOError writing file to output stream");
    }
  }

}
