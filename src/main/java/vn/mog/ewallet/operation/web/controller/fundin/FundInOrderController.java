package vn.mog.ewallet.operation.web.controller.fundin;

import static vn.mog.ewallet.operation.web.constant.RoleConstants.ADMIN_OPERATION;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.AGGREATOR;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.BILLER;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.CUSTOMER;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.CUSTOMERCARE;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.CUSTOMERCARE_MANAGER;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.FA_MANAGER;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.FINANCE;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.FINANCESUPPORT_LEADER;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.FINANCE_LEADER;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.FINANCE_SUPPORT;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.MERCHANT;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.RECONCILIATION;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.RECONCILIATION_LEADER;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.SALESUPPORT;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.SALESUPPORT_MANAGER;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.SALE_ASM;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.SALE_DIRECTOR;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.SALE_EXCUTIVE;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.SALE_RSM;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.SALE_SUPERVISOR;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.TELCO;
import static vn.mog.ewallet.operation.web.utils.ValidationUtil.MESAGE_ERROR;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.exception.ErrorConstant;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.exception.MessageNotify;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.FindBankProfileRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.BankProfile;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.FundOrderChannelType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.FundOrderFlowStageType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.CreateFundOrderAttachmentRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.CreateFundOrderAttachmentResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.CreateFundOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.CreateFundOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindCustomerBankDirectRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindCustomerBankDirectResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindFundOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindFundOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FundInRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FundInResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.GetFundOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.GetFundOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.UpdateFundOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.UpdateFundOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.CustomerBankDirect;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.FundOrder;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.FundOrderAttachment;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.FundOrderFlowApproveRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.FundOrderFlowApproveResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.FundOrderFlowRejectRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.FundOrderFlowRejectResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.FundOrderFlowSubmitProcessRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.FundOrderFlowSubmitProcessResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetOTPConfirmRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetOTPConfirmResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans.Customer;
import vn.mog.framework.common.utils.DateUtil;
import vn.mog.framework.common.utils.NumberUtil;
import vn.mog.framework.common.utils.SessionUtil;
import vn.mog.framework.contract.base.MobiliserResponseType.Status;

@Controller
@RequestMapping(value = "/wallet/fund-in")
public class FundInOrderController extends AbstractController {

  public static final String FUND_IN_ORDER_CONTROLLER = "/wallet/fund-in";
  public static final String FUND_IN_ORDER_LIST = FUND_IN_ORDER_CONTROLLER + "/list";
  public static final String FUND_IN_ORDER_DETAIL = FUND_IN_ORDER_CONTROLLER + "/detail";

  private static final Logger LOG = LogManager.getLogger(FundInOrderController.class);
  private static final String PAGE_EDIT_CASH_ONE_HAND = "/fundin_order/edit_cash_on_hand";
  private static final String PAGE_REQUEST_FUND_IN = "/fundin_order/request_fundin";
  private static final String PAGE_REQUEST_CASH_IN = "/fundin_order/request_cashin";
  private static final String PAGE_VERIFY_CASH_IN = "/fundin_order/verify_cashin";
  private static final String PAGE_VERIFY_REPAYMENT = "/fundin_order/verify_repayment";

  private static final String REDIRECT_FUND_IN_LIST = "redirect:" + FUND_IN_ORDER_LIST;
  private static final String REPAYMENT = "repayment";

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @PreAuthorize(
      value =
          "hasAnyRole('"
              + ADMIN_OPERATION
              + "','"
              + MERCHANT
              + "','"
              + CUSTOMER
              + "','"
              + TELCO
              + "','"
              + AGGREATOR
              + "','"
              + BILLER
              + "','"
              + FINANCE_LEADER
              + "','"
              + FA_MANAGER
              + "','"
              + FINANCE
              + "','"
              + FINANCESUPPORT_LEADER
              + "', '"
              + FINANCE_SUPPORT
              + "', '"
              + RECONCILIATION
              + "','"
              + RECONCILIATION_LEADER
              + "','"
              + CUSTOMERCARE_MANAGER
              + "','"
              + SALE_DIRECTOR
              + "','"
              + SALE_EXCUTIVE
              + "','"
              + SALE_SUPERVISOR
              + "','"
              + SALE_ASM
              + "','"
              + SALE_RSM
              + "', '"
              + SALESUPPORT_MANAGER
              + "' , '"
              + SALESUPPORT
              + "','"
              + CUSTOMERCARE_MANAGER
              + "','"
              + CUSTOMERCARE
              + "')")
  public String search(HttpServletRequest request, ModelMap model) throws FrontEndException {

    FindFundOrderRequest ffoRequest = new FindFundOrderRequest();

    String paramText = StringUtils.trimToEmpty(request.getParameter("quickSearch"));
    String paramDateRange = StringUtils.trimToEmpty(request.getParameter("range"));
    String[] paramCustomerIds = request.getParameterValues("customerIds");
    String[] paramFundOrderFlowStages = request.getParameterValues("stage");
    String[] paramChannels = request.getParameterValues("channel");
    String isDept = request.getParameter("isDept");

    List<Long> customerIds = null;
    List customerList = null;
    List<String> rolesOfCaller = getRolesOfCaller();
    if (paramCustomerIds != null) {
      customerIds = NumberUtil.convertListToLong(paramCustomerIds);
    } else {
      customerList = getListSaleByParent(rolesOfCaller);
    }

    // If Caller is Sale, show all Caller's agents' transactions
    // Get list Agents' Id to show Transaction
    // Sale doesn't have own wallet, so no need to show Sale's transactions
    if (!rolesOfCaller.contains(ADMIN_OPERATION)
        && (customerIds == null || customerIds.isEmpty())
        && customerList != null) {
      customerIds = new ArrayList<>();
      try {
        for (Customer customer : (List<Customer>) customerList) {
          customerIds.add(customer.getId());
        }
      } catch (ClassCastException e) {
        customerIds = null;
      }
    }

    if (customerIds != null) {
      customerIds.remove(null);
    }

    Date[] dates = parseDateRange(paramDateRange, true);

    int offset = 0;
    int limit = 20;
    if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
      offset = Integer.parseInt(request.getParameter("d-49489-p"));
      if (offset > 0) {
        offset = (offset - 1) * limit;
      }
    }

    //    if (paramCustomerIds != null && paramCustomerIds.length > 0 && StringUtils
    //        .isNotEmpty(paramCustomerIds[0])) {
    //      ffoRequest.setCustomerIds(NumberUtil.convertListToLong(paramCustomerIds));
    //    }

    if (paramChannels != null
        && paramChannels.length > 0
        && StringUtils.isNotEmpty(paramChannels[0])) {
      ffoRequest.setOrderChannelIds(Arrays.asList(paramChannels));
    }

    if (paramFundOrderFlowStages != null
        && paramFundOrderFlowStages.length > 0
        && StringUtils.isNotEmpty(paramFundOrderFlowStages[0])) {
      ffoRequest.setStages(NumberUtil.convertListToInt(paramFundOrderFlowStages));
    }

    if (Objects.isNull(isDept)) ffoRequest.setIsDebt(null);
    else if (isDept.equals("true")) ffoRequest.setIsDebt(true);
    else if (isDept.equals("false")) ffoRequest.setIsDebt(false);
    ffoRequest.setFromDate(dates[0]);
    ffoRequest.setEndDate(dates[1]);
    ffoRequest.setCustomerIds(customerIds);
    if (!paramText.isEmpty() && paramText != null) ffoRequest.setSearchText(paramText);
    ffoRequest.setLimit(limit);
    ffoRequest.setOffset(offset);
    ffoRequest.setServiceTypeIds(Collections.singletonList(ServiceType.FUND_IN.name()));

    FindFundOrderResponse ffoResponse = walletEndpoint.findFundOrders(ffoRequest);

    model.put("fundInOrders", ffoResponse.getFundOrders());
    model.put("totalFee", ffoResponse.getFee());
    model.put("totalAmount", ffoResponse.getAmount());
    model.put("pagesize", limit);
    model.put("offset", offset);
    model.put("total", ffoResponse.getCount().intValue());
    model.put("customers", customerList);

    return "/fundin_order/list";
  }

  @RequestMapping(value = "/request-fundin", method = RequestMethod.GET)
  @PreAuthorize(
      value =
          "hasAnyRole('"
              + ADMIN_OPERATION
              + "', '"
              + MERCHANT
              + "','"
              + CUSTOMER
              + "','"
              + SALE_DIRECTOR
              + "','"
              + SALE_EXCUTIVE
              + "','"
              + SALE_SUPERVISOR
              + "','"
              + SALE_ASM
              + "','"
              + SALE_RSM
              + "','"
              + FA_MANAGER
              + "','"
              + FINANCE_LEADER
              + "','"
              + FINANCESUPPORT_LEADER
              + "','"
              + FINANCE_SUPPORT
              + "','"
              + FINANCE
              + "')")
  public String requestFund_In(ModelMap model) throws FrontEndException {

    model.put("listBank", getProfileBanks());
    //    model.put("customers", cacheDataUtil.getCustomersByBiz());

    // link bank
    FindCustomerBankDirectRequest fbdRequest = new FindCustomerBankDirectRequest();
    FindCustomerBankDirectResponse fbdResponse = walletEndpoint.findBankDirects(fbdRequest);

    List<CustomerBankDirect> directBanks = fbdResponse.getBankDirects();
    model.put("existLinkBank", !directBanks.isEmpty());

    return PAGE_REQUEST_FUND_IN;
  }

  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  @PreAuthorize(
      value =
          "hasAnyRole('"
              + ADMIN_OPERATION
              + "','"
              + MERCHANT
              + "','"
              + CUSTOMER
              + "','"
              + TELCO
              + "','"
              + AGGREATOR
              + "','"
              + BILLER
              + "','"
              + FINANCE_LEADER
              + "','"
              + FA_MANAGER
              + "','"
              + FINANCE
              + "','"
              + FINANCESUPPORT_LEADER
              + "',"
              + "'RECONCILIATION','RECONCILIATION_LEADER',"
              + "'CUSTOMERCARE_MANAGER','PROVIDER','"
              + SALESUPPORT
              + "','"
              + SALE_EXCUTIVE
              + "','"
              + SALE_DIRECTOR
              + "' ,'"
              + CUSTOMERCARE
              + "','"
              + CUSTOMERCARE_MANAGER
              + "')")
  public String detail(HttpServletRequest request, ModelMap model) throws FrontEndException {

    Long fundOrderId = NumberUtil.convertToLong(request.getParameter("id"));

    if (fundOrderId > 0) {
      GetFundOrderRequest gfoRequest = new GetFundOrderRequest(fundOrderId);
      GetFundOrderResponse gfoResponse = walletEndpoint.getFundOrder(gfoRequest);

      if (gfoResponse.getStatus().getCode() == 0 && gfoResponse.getFundOrder() != null) {

        model.put("fundInOrders", gfoResponse.getFundOrder());
        model.put("customerCurrentBalance", gfoResponse.getCustomerCurrentBalance());
        model.put("attachments", gfoResponse.getAttachments());

        model.put("quickSearch", request.getParameter("quickSearch"));
        model.put("range", request.getParameter("range"));
        model.put("customerIds", request.getParameterValues("customerIds"));
        model.put("stage", request.getParameterValues("stage"));
        model.put("channel", request.getParameterValues("channel"));
        model.put("numberPage", request.getParameter("d-49520-p"));

        List<FundOrderAttachment> attachments = gfoResponse.getAttachments();
        List<FundOrderAttachment> repaymentAttachmentsOnly = new ArrayList<>();
        if (!Objects.isNull(attachments) && !attachments.isEmpty()) {
          for (FundOrderAttachment fundOrderAttachment : attachments) {
            if (fundOrderAttachment.getContentType().equals(REPAYMENT)) {
              String repaymentDocument =
                  new Base64().encodeToString(fundOrderAttachment.getContent());
              fundOrderAttachment.setImageBase64(repaymentDocument);
              repaymentAttachmentsOnly.add(fundOrderAttachment);
            }
          }
        }
        model.put("documents", repaymentAttachmentsOnly);
        return "/fundin_order/detail";
      }
    }
    return REDIRECT_FUND_IN_LIST;
  }

  @RequestMapping(value = "/bank-tranfer", method = RequestMethod.POST)
  @PreAuthorize(
      value =
          "hasAnyRole('"
              + ADMIN_OPERATION
              + "', '"
              + MERCHANT
              + "','"
              + CUSTOMER
              + "','"
              + SALE_DIRECTOR
              + "','"
              + SALE_EXCUTIVE
              + "','"
              + SALE_SUPERVISOR
              + "','"
              + SALE_ASM
              + "','"
              + SALE_RSM
              + "','"
              + FA_MANAGER
              + "','"
              + FINANCE_LEADER
              + "','"
              + FINANCESUPPORT_LEADER
              + "','"
              + FINANCE_SUPPORT
              + "','"
              + FINANCE
              + "')")
  public String createBankTranfer(HttpServletRequest request, ModelMap model)
      throws FrontEndException, IOException {

    String paramAmount = request.getParameter("amount");
    String bank = request.getParameter("bank");
    String code = request.getParameter("code");
    Long customerId = NumberUtil.convertToLong(request.getParameter("customerId"));
    String action = request.getParameter("action");
    String channel = request.getParameter("channel");

    //    model.put("customers", cacheDataUtil.getCustomersByBiz());

    Long amountBankTranfer =
        NumberUtil.convertToLong(paramAmount.replaceAll(",", StringUtils.EMPTY));
    if (amountBankTranfer == 0L) {
      model.put("amount", paramAmount);
      model.put("customerId", customerId);
      model.put("codeErr", 1);
      model.put("mesErr", "Chọn số tiền để nạp tiền");
      model.put("styleDiv", "bank_tranfer");
      model.put("listBank", getProfileBanks());

      return PAGE_REQUEST_FUND_IN;
    }

    List<FundOrderAttachment> attachments =
        getUploadAttachments(request, new FundOrderAttachment());

    FundOrder fundInOrder = new FundOrder();
    fundInOrder.setOrderChannel(FundOrderChannelType.BANK_TRANSFER.code);
    fundInOrder.setAmount(amountBankTranfer);
    fundInOrder.setBankCode(bank);
    fundInOrder.setCommandCode(code);
    fundInOrder.setPaymentChannelId(channel != null && !channel.isEmpty() ? channel : null);
    fundInOrder.setServiceType(ServiceType.FUND_IN.name());

    CreateFundOrderRequest cfoRequest =
        new CreateFundOrderRequest(fundInOrder, customerId, attachments);
    CreateFundOrderResponse cfoResponse = walletEndpoint.createFundOrder(cfoRequest);

    if (cfoResponse.getStatus().getCode() == 0) {

      if (BTN_SUBMIT.equals(action)) {
        FundOrderFlowApproveRequest fofaRequest =
            new FundOrderFlowApproveRequest(cfoResponse.getFundOrderId());
        FundOrderFlowApproveResponse fofaResponse = walletEndpoint.approveFundOrder(fofaRequest);

        if (fofaResponse.getStatus().getCode() == 0) {
          return REDIRECT_FUND_IN_LIST;
        } else {

          model.put("listBank", getProfileBanks());
          model.put("codeErr", fofaResponse.getStatus().getCode());
          model.put("mesErr", fofaResponse.getStatus().getValue());
          return PAGE_REQUEST_FUND_IN;
        }
      } else {
        return REDIRECT_FUND_IN_LIST;
      }
    } else {
      model.put("styleDiv", "bank_tranfer");
      model.put("amount", paramAmount);
      model.put("customerId", customerId);
      model.put("bank", bank);
      model.put("code", code);
      model.put("codeErr", cfoResponse.getStatus().getCode());
      model.put("mesErr", cfoResponse.getStatus().getValue());
      model.put("listBank", getProfileBanks());

      return PAGE_REQUEST_FUND_IN;
    }
  }

  @RequestMapping(value = "/cash-on-hand", method = RequestMethod.POST)
  @PreAuthorize(
      value =
          "hasAnyRole('"
              + ADMIN_OPERATION
              + "', '"
              + MERCHANT
              + "','"
              + CUSTOMER
              + "','"
              + SALE_DIRECTOR
              + "','"
              + SALE_EXCUTIVE
              + "','"
              + SALE_SUPERVISOR
              + "','"
              + SALE_ASM
              + "','"
              + SALE_RSM
              + "','"
              + FA_MANAGER
              + "','"
              + FINANCE_LEADER
              + "','"
              + FINANCESUPPORT_LEADER
              + "','"
              + FINANCE_SUPPORT
              + "','"
              + FINANCE
              + "')")
  public String createCashOnHand(HttpServletRequest request, ModelMap model)
      throws FrontEndException, IOException {
    return null;
    // DISABLED CASH ON HAND
    //    String amount = request.getParameter("amount");
    //    String remark = request.getParameter("remark");
    //    Long customerId = NumberUtil.convertToLong(request.getParameter("customerId"));
    //    String action = request.getParameter("action");
    //
    ////    model.put("customers", cacheDataUtil.getCustomersByBiz());
    //
    //    Long amountCashOnHand = NumberUtil.convertToLong(amount.replaceAll(",",
    // StringUtils.EMPTY));
    //    if (amountCashOnHand == 0L) {
    //      model.put("amount", amount);
    //      model.put("remark", remark);
    //      model.put("customerId", customerId);
    //      model.put("codeErr", 1);
    //      model.put("mesErr", "Chọn số tiền để nạp tiền");
    //      model.put("styleDiv", "cash_on_hand");
    //      return PAGE_REQUEST_FUND_IN;
    //    }
    //
    //    List<FundOrderAttachment> attachments =
    //        getUploadAttachments(request, new FundOrderAttachment());
    //
    //    FundOrder fundInOrder = new FundOrder();
    //    fundInOrder.setOrderChannel(FundOrderChannelType.CASH_ON_HAND.code);
    //    fundInOrder.setAmount(amountCashOnHand);
    //    fundInOrder.setInfo(remark);
    //    fundInOrder.setServiceType(ServiceType.FUND_IN.name());
    //
    //    CreateFundOrderRequest cfoRequest =
    //        new CreateFundOrderRequest(fundInOrder, customerId, attachments);
    //    CreateFundOrderResponse cfoResponse = walletEndpoint.createFundOrder(cfoRequest);
    //
    //    if (cfoResponse.getStatus().getCode() == 0) {
    //
    //      if (BTN_SUBMIT.equals(action)) {
    //        Long fundOrderId = cfoResponse.getFundOrderId();
    //        FundOrderFlowApproveRequest fofaRequest = new
    // FundOrderFlowApproveRequest(fundOrderId);
    //        FundOrderFlowApproveResponse fofaResponse =
    // walletEndpoint.approveFundOrder(fofaRequest);
    //
    //        if (fofaResponse.getStatus().getCode() == 0) {
    //          return REDIRECT_FUND_IN_LIST;
    //        } else {
    //          model.put("listBank", getProfileBanks());
    //          model.put("codeErr", fofaResponse.getStatus().getCode());
    //          model.put("mesErr", fofaResponse.getStatus().getValue());
    //          return PAGE_REQUEST_FUND_IN;
    //        }
    //      } else {
    //        return REDIRECT_FUND_IN_LIST;
    //      }
    //    } else {
    //      model.put("listBank", getProfileBanks());
    //      model.put("styleDiv", "cash_on_hand");
    //      model.put("amount", amount);
    //      model.put("remark", remark);
    //      model.put("customerId", customerId);
    //      model.put("codeErr", cfoResponse.getStatus().getCode());
    //      model.put("mesErr", cfoResponse.getStatus().getValue());
    //      return PAGE_REQUEST_FUND_IN;
    //    }
  }

  @RequestMapping(value = "/edit-cash-in", method = RequestMethod.GET)
  @PreAuthorize(
      value =
          "hasAnyRole('"
              + ADMIN_OPERATION
              + "', '"
              + MERCHANT
              + "','"
              + CUSTOMER
              + "','"
              + SALE_DIRECTOR
              + "','"
              + SALE_EXCUTIVE
              + "','"
              + SALE_SUPERVISOR
              + "','"
              + SALE_ASM
              + "','"
              + SALE_RSM
              + "','"
              + FA_MANAGER
              + "','"
              + FINANCE_LEADER
              + "','"
              + FINANCESUPPORT_LEADER
              + "','"
              + FINANCE_SUPPORT
              + "','"
              + FINANCE
              + "')")
  public String editCashIn(HttpServletRequest request, ModelMap model) throws FrontEndException {

    Long fundOrderId = NumberUtil.convertToLong(request.getParameter("id"));
    if (fundOrderId > 0) {
      GetFundOrderRequest gfoRequest = new GetFundOrderRequest(fundOrderId);
      GetFundOrderResponse gfoResponse = walletEndpoint.getFundOrder(gfoRequest);

      boolean isFundOrder =
          gfoResponse.getFundOrder() != null
              && gfoResponse.getFundOrder().getStage()
                  == FundOrderFlowStageType.FUND_ORDER_INIT.code;

      if (gfoResponse.getStatus().getCode() == 0 && isFundOrder) {

        model.put("fundInOrders", gfoResponse.getFundOrder());
        model.put("attachments", gfoResponse.getAttachments());
        //        model.put("customers", cacheDataUtil.getCustomersByBiz());
        model.put("listBank", getProfileBanks());

        model.put("quickSearch", request.getParameter("quickSearch"));
        model.put("range", request.getParameter("range"));
        model.put("customerIds", request.getParameterValues("customerIds"));
        model.put("stage", request.getParameterValues("stage"));
        model.put("channel", request.getParameterValues("channel"));
        model.put("numberPage", request.getParameter("d-49520-p"));

        return PAGE_EDIT_CASH_ONE_HAND;
      }
    }
    return REDIRECT_FUND_IN_LIST;
  }

  @RequestMapping(value = "/edit-cash-in", method = RequestMethod.POST)
  @PreAuthorize(
      value =
          "hasAnyRole('"
              + ADMIN_OPERATION
              + "', '"
              + MERCHANT
              + "','"
              + CUSTOMER
              + "','"
              + SALE_DIRECTOR
              + "','"
              + SALE_EXCUTIVE
              + "','"
              + SALE_SUPERVISOR
              + "','"
              + SALE_ASM
              + "','"
              + SALE_RSM
              + "','"
              + FA_MANAGER
              + "','"
              + FINANCE_LEADER
              + "','"
              + FINANCESUPPORT_LEADER
              + "','"
              + FINANCE_SUPPORT
              + "','"
              + FINANCE
              + "')")
  public String editCashInPost(HttpServletRequest request, ModelMap model)
      throws FrontEndException, IOException {

    Long fundOrderId = NumberUtil.convertToLong(request.getParameter("fundOrderId"));
    String amountMoneyParam = StringUtils.trimToEmpty(request.getParameter("amount"));
    String remark = request.getParameter("remark");
    String action = request.getParameter("action");
    Long customerId = NumberUtil.convertToLong(request.getParameter("customerId"));
    List<FundOrderAttachment> attachments =
        getUploadAttachments(request, new FundOrderAttachment());

    GetFundOrderRequest fundOrderRequest = new GetFundOrderRequest(fundOrderId);
    GetFundOrderResponse fundOrderResponse = walletEndpoint.getFundOrder(fundOrderRequest);

    Long amountMoney =
        NumberUtil.convertToLong(amountMoneyParam.replaceAll(",", StringUtils.EMPTY));

    FundOrder fundInOrder = new FundOrder(fundOrderId, amountMoney, remark);

    String fundCodeBank = fundOrderResponse.getFundOrder().getOrderChannel();

    if (fundCodeBank.equals(FundOrderChannelType.BANK_TRANSFER.code)) {
      String bank = request.getParameter("bank");
      String code = request.getParameter("code");

      fundInOrder.setBankCode(bank);
      fundInOrder.setCommandCode(code);
    }

    UpdateFundOrderRequest ufoRequest =
        new UpdateFundOrderRequest(fundInOrder, customerId, attachments);
    UpdateFundOrderResponse ufoResponse = walletEndpoint.updateFundOrder(ufoRequest);

    if (ufoResponse.getStatus().getCode() == 0) {
      if (BTN_SUBMIT.equals(action)) {

        Integer iSuccess;
        String messErr;

        if (fundOrderResponse
            .getFundOrder()
            .getStage()
            .equals(FundOrderFlowStageType.SALE_EXCUTIVE_REJECTED.code)) {

          FundOrderFlowSubmitProcessRequest requestSubmit =
              new FundOrderFlowSubmitProcessRequest(fundOrderId);
          FundOrderFlowSubmitProcessResponse responseSubmit =
              walletEndpoint.submitFundOrder(requestSubmit);

          iSuccess = responseSubmit.getStatus().getCode();
          messErr = responseSubmit.getStatus().getValue();
        } else {
          FundOrderFlowApproveRequest requestSubmit = new FundOrderFlowApproveRequest(fundOrderId);
          FundOrderFlowApproveResponse responseSubmit =
              walletEndpoint.approveFundOrder(requestSubmit);

          iSuccess = responseSubmit.getStatus().getCode();
          messErr = responseSubmit.getStatus().getValue();
        }

        if (iSuccess == 0) {
          return REDIRECT_FUND_IN_LIST;
        } else {
          model.put("codeErr", iSuccess);
          model.put("mesErr", messErr);
          model.put("fundInOrders", fundOrderResponse.getFundOrder());
          model.put("attachments", fundOrderResponse.getAttachments());

          return PAGE_EDIT_CASH_ONE_HAND;
        }
      } else {
        return REDIRECT_FUND_IN_LIST;
      }
    } else {
      model.put("codeErr", ufoResponse.getStatus().getCode());
      model.put("mesErr", ufoResponse.getStatus().getValue());
      model.put("fundInOrders", fundOrderResponse.getFundOrder());
      model.put("attachments", fundOrderResponse.getAttachments());

      //      model.put("customers", cacheDataUtil.getCustomersByBiz());
      model.put("listBank", getProfileBanks());

      return PAGE_EDIT_CASH_ONE_HAND;
    }
  }

  @RequestMapping(value = "/request-cash-in", method = RequestMethod.GET)
  @PreAuthorize(
      value =
          "hasAnyRole('"
              + ADMIN_OPERATION
              + "', '"
              + MERCHANT
              + "','"
              + CUSTOMER
              + "','"
              + SALE_DIRECTOR
              + "','"
              + SALE_EXCUTIVE
              + "','"
              + SALE_SUPERVISOR
              + "','"
              + SALE_ASM
              + "','"
              + SALE_RSM
              + "','"
              + FA_MANAGER
              + "','"
              + FINANCE_LEADER
              + "','"
              + FINANCESUPPORT_LEADER
              + "','"
              + FINANCE_SUPPORT
              + "','"
              + FINANCE
              + "')")
  public String requestCashIn(HttpServletRequest request, ModelMap model) throws FrontEndException {

    Long fundOrderId = NumberUtil.convertToLong(request.getParameter("id"));

    if (fundOrderId > 0) {
      GetFundOrderRequest gfoRequest = new GetFundOrderRequest(fundOrderId);
      GetFundOrderResponse gfoResponse = walletEndpoint.getFundOrder(gfoRequest);
      boolean isRequestCashIn = gfoResponse.getFundOrder() != null;

      if (isRequestCashIn) {
        model.put("fundInOrders", gfoResponse.getFundOrder());
        model.put("customerCurrentBalance", gfoResponse.getCustomerCurrentBalance());
        model.put("attachments", gfoResponse.getAttachments());
        return PAGE_REQUEST_CASH_IN;
      }
    }

    return REDIRECT_FUND_IN_LIST;
  }

  @RequestMapping(value = "/request-cash-in", method = RequestMethod.POST)
  @PreAuthorize(
      value =
          "hasAnyRole('"
              + ADMIN_OPERATION
              + "', '"
              + MERCHANT
              + "','"
              + CUSTOMER
              + "','"
              + SALE_DIRECTOR
              + "','"
              + SALE_EXCUTIVE
              + "','"
              + SALE_SUPERVISOR
              + "','"
              + SALE_ASM
              + "','"
              + SALE_RSM
              + "','"
              + FA_MANAGER
              + "','"
              + FINANCE_LEADER
              + "','"
              + FINANCESUPPORT_LEADER
              + "','"
              + FINANCE_SUPPORT
              + "','"
              + FINANCE
              + "')")
  public String requestCashInPost(HttpServletRequest request, ModelMap model)
      throws FrontEndException, IOException {

    Long fundOrderId = NumberUtil.convertToLong(request.getParameter("id"));
    String remark = request.getParameter("remark");
    String action = request.getParameter("action");

    List<FundOrderAttachment> attachments =
        getUploadAttachments(request, new FundOrderAttachment());

    FundOrder fundInOrder = new FundOrder(fundOrderId, remark);

    UpdateFundOrderRequest updateReq = new UpdateFundOrderRequest(fundInOrder, attachments);
    UpdateFundOrderResponse updateRes = walletEndpoint.updateFundOrder(updateReq);

    if (updateRes.getStatus().getCode() == 0) {
      if (BTN_SUBMIT.equals(action)) {
        GetFundOrderRequest requestGet = new GetFundOrderRequest(fundOrderId);
        GetFundOrderResponse responseGet = walletEndpoint.getFundOrder(requestGet);

        Integer iSuccess;
        String messErr;

        if (responseGet
            .getFundOrder()
            .getStage()
            .equals(FundOrderFlowStageType.FINANCE_SUPPORT_REJECTED.code)) {
          FundOrderFlowSubmitProcessRequest requestSubmit =
              new FundOrderFlowSubmitProcessRequest(fundOrderId);
          FundOrderFlowSubmitProcessResponse responseSubmit =
              walletEndpoint.submitFundOrder(requestSubmit);

          iSuccess = responseSubmit.getStatus().getCode();
          messErr = responseSubmit.getStatus().getValue();
        } else {
          FundOrderFlowApproveRequest requestSubmit = new FundOrderFlowApproveRequest(fundOrderId);
          FundOrderFlowApproveResponse responseSubmit =
              walletEndpoint.approveFundOrder(requestSubmit);

          iSuccess = responseSubmit.getStatus().getCode();
          messErr = responseSubmit.getStatus().getValue();
        }

        if (iSuccess == 0) {
          return REDIRECT_FUND_IN_LIST;
        } else {
          model.put("codeErr", iSuccess);
          model.put("mesErr", messErr);
          model.put("fundInOrders", responseGet.getFundOrder());
          model.put("attachments", responseGet.getAttachments());

          return PAGE_REQUEST_CASH_IN;
        }
      } else if (BTN_REJECT.equals(action)) {
        FundOrderFlowRejectRequest requestReject =
            new FundOrderFlowRejectRequest(fundOrderId, remark);
        FundOrderFlowRejectResponse responseReject = walletEndpoint.rejectFundOrder(requestReject);

        if (responseReject.getStatus().getCode() == 0) {
          return REDIRECT_FUND_IN_LIST;
        } else {
          model.put("codeErr", responseReject.getStatus().getCode());
          model.put("mesErr", responseReject.getStatus().getValue());

          GetFundOrderRequest requestGet = new GetFundOrderRequest(fundOrderId);
          GetFundOrderResponse responseGet = walletEndpoint.getFundOrder(requestGet);

          model.put("fundInOrders", responseGet.getFundOrder());
          model.put("customerCurrentBalance", responseGet.getCustomerCurrentBalance());
          model.put("attachments", responseGet.getAttachments());

          return PAGE_REQUEST_CASH_IN;
        }
      } else {
        return REDIRECT_FUND_IN_LIST;
      }
    } else {
      model.put("codeErr", updateRes.getStatus().getCode());
      model.put("mesErr", updateRes.getStatus().getValue());

      GetFundOrderRequest requestGet = new GetFundOrderRequest(fundOrderId);
      GetFundOrderResponse responseGet = walletEndpoint.getFundOrder(requestGet);

      model.put("fundInOrders", responseGet.getFundOrder());
      model.put("customerCurrentBalance", responseGet.getCustomerCurrentBalance());
      model.put("attachments", responseGet.getAttachments());
      return PAGE_REQUEST_CASH_IN;
    }
  }

  @RequestMapping(value = "/verify-cash-in", method = RequestMethod.GET)
  @PreAuthorize(
      value =
          "hasAnyRole('"
              + ADMIN_OPERATION
              + "','"
              + FINANCE
              + "','"
              + FA_MANAGER
              + "', '"
              + FINANCE_LEADER
              + "', '"
              + FINANCESUPPORT_LEADER
              + "' ,'"
              + FINANCE_SUPPORT
              + "')")
  public String verifyCashIn(HttpServletRequest request, ModelMap model) throws FrontEndException {

    Long fundOrderId = NumberUtil.convertToLong(request.getParameter("id"));

    if (fundOrderId > 0) {
      GetFundOrderRequest gfoRequest = new GetFundOrderRequest(fundOrderId);
      GetFundOrderResponse gfoResponse = walletEndpoint.getFundOrder(gfoRequest);
      FundOrder fundOrder = gfoResponse.getFundOrder();
      Integer stage = fundOrder.getStage();
      boolean isFundOrderValide =
          fundOrder != null
              && (stage.equals(FundOrderFlowStageType.FINANCE_SUPPORT_VERIFY.code)
                  || stage.equals(FundOrderFlowStageType.FINANCE_MANAGER_REJECTED.code)
                  || stage.equals(FundOrderFlowStageType.FINANCE_MAMAGER_APPROVE.code));

      if (gfoResponse.getStatus().getCode() == 0 && isFundOrderValide) {
        model.put("fundInOrders", gfoResponse.getFundOrder());
        model.put("customerCurrentBalance", gfoResponse.getCustomerCurrentBalance());
        model.put("attachments", gfoResponse.getAttachments());
        return PAGE_VERIFY_CASH_IN;
      }
    }

    SessionUtil.setAttribute(
        MessageNotify.SESSION_MESSAGE_NOTIFY,
        new MessageNotify(MessageNotify.ERROR_CODE, validation.notify(MESAGE_ERROR)));
    return REDIRECT_FUND_IN_LIST;
  }

  @RequestMapping(value = "/verify-cash-in", method = RequestMethod.POST)
  @PreAuthorize(
      value =
          "hasAnyRole('"
              + ADMIN_OPERATION
              + "','"
              + FINANCE
              + "','"
              + FA_MANAGER
              + "', '"
              + FINANCE_LEADER
              + "', '"
              + FINANCESUPPORT_LEADER
              + "' ,'"
              + FINANCE_SUPPORT
              + "')")
  public String verifyCashInPost(HttpServletRequest request, ModelMap model)
      throws FrontEndException {

    Long fundOrderId = NumberUtil.convertToLong(request.getParameter("id"));
    String remark = request.getParameter("remark");
    String action = request.getParameter("action");

    if (BTN_APPROVE.equals(action)) {
      GetFundOrderRequest fundOrderRequest = new GetFundOrderRequest(fundOrderId);
      GetFundOrderResponse fundOrderResponse = walletEndpoint.getFundOrder(fundOrderRequest);

      // if stage 6 else stage 4
      if (fundOrderResponse
          .getFundOrder()
          .getStage()
          .equals(FundOrderFlowStageType.FINANCE_MAMAGER_APPROVE.code)) {

        GetOTPConfirmRequest otpConfirmRequest = new GetOTPConfirmRequest(fundOrderId);
        GetOTPConfirmResponse otpConfirmResponse = walletEndpoint.getOTPConfirm(otpConfirmRequest);
        if (otpConfirmResponse.getStatus().getCode() == 0) {
          return "redirect:"
              + FundInOrderController.FUND_IN_ORDER_CONTROLLER
              + "/approve-cash-in?id="
              + fundOrderId;
        } else {
          model.put("id", fundOrderId);
          model.put("codeErr", otpConfirmResponse.getStatus().getCode());
          model.put("mesErr", otpConfirmResponse.getStatus().getValue());
          return "redirect:" + FUND_IN_ORDER_CONTROLLER + "/verify-cash-in";
        }

      } else {

        // nạp chịu
        boolean updateDeptResult = callApiUpdateDept(fundOrderResponse, model, request);
        if (updateDeptResult == false) {
          return PAGE_VERIFY_CASH_IN;
        }

        FundOrderFlowApproveRequest requestSubmit =
            new FundOrderFlowApproveRequest(fundOrderId, remark);
        FundOrderFlowApproveResponse responseSubmit =
            walletEndpoint.approveFundOrder(requestSubmit);

        if (responseSubmit.getStatus().getCode() == 0) {
          return REDIRECT_FUND_IN_LIST;
        } else {
          model.put("codeErr", responseSubmit.getStatus().getCode());
          model.put("mesErr", responseSubmit.getStatus().getValue());

          model.put("fundInOrders", fundOrderResponse.getFundOrder());
          model.put("customerCurrentBalance", fundOrderResponse.getCustomerCurrentBalance());
          model.put("attachments", fundOrderResponse.getAttachments());

          return PAGE_VERIFY_CASH_IN;
        }
      }
    } else if (BTN_REJECT.equals(action)) {
      FundOrderFlowRejectRequest requestReject =
          new FundOrderFlowRejectRequest(fundOrderId, remark);
      FundOrderFlowRejectResponse responseReject = walletEndpoint.rejectFundOrder(requestReject);

      if (responseReject.getStatus().getCode() == 0) {
        return REDIRECT_FUND_IN_LIST;
      } else {
        model.put("codeErr", responseReject.getStatus().getCode());
        model.put("mesErr", responseReject.getStatus().getValue());

        GetFundOrderRequest requestGet = new GetFundOrderRequest(fundOrderId);
        GetFundOrderResponse responseGet = walletEndpoint.getFundOrder(requestGet);

        model.put("fundInOrders", responseGet.getFundOrder());
        model.put("customerCurrentBalance", responseGet.getCustomerCurrentBalance());
        model.put("attachments", responseGet.getAttachments());

        return PAGE_VERIFY_CASH_IN;
      }
    } else {
      model.put("codeErr", 1);
      model.put("mesErr", "Hành động không đúng !");

      GetFundOrderRequest requestGet = new GetFundOrderRequest(fundOrderId);
      GetFundOrderResponse responseGet = walletEndpoint.getFundOrder(requestGet);

      model.put("fundInOrders", responseGet.getFundOrder());
      model.put("customerCurrentBalance", responseGet.getCustomerCurrentBalance());
      model.put("attachments", responseGet.getAttachments());

      return PAGE_VERIFY_CASH_IN;
    }
  }

  @RequestMapping(value = "/approve-cash-in", method = RequestMethod.GET)
  @PreAuthorize(
      value =
          "hasAnyRole('"
              + ADMIN_OPERATION
              + "','"
              + FA_MANAGER
              + "','"
              + FINANCESUPPORT_LEADER
              + "')")
  public String approveCashIn(HttpServletRequest request, ModelMap model) throws FrontEndException {

    Long fundOrderId = NumberUtil.convertToLong(request.getParameter("id"));
    if (fundOrderId > 0) {
      GetFundOrderRequest requestFundin = new GetFundOrderRequest(fundOrderId);
      GetFundOrderResponse responseFundin = walletEndpoint.getFundOrder(requestFundin);

      if (responseFundin.getStatus().getCode() == 0 && responseFundin.getFundOrder() != null) {
        model.put("fundInOrders", responseFundin.getFundOrder());
        model.put("customerCurrentBalance", responseFundin.getCustomerCurrentBalance());
        // model.put("attachments", responseFundin.getAttachments());
        return "/fundin_order/approve_cashin";
      }
    }
    return REDIRECT_FUND_IN_LIST;
  }

  @RequestMapping(value = "/approve-cash-in", method = RequestMethod.POST)
  @PreAuthorize(
      value =
          "hasAnyRole('"
              + ADMIN_OPERATION
              + "','"
              + FA_MANAGER
              + "','"
              + FINANCESUPPORT_LEADER
              + "')")
  public String approveCashInPost(HttpServletRequest request, ModelMap model)
      throws FrontEndException {

    Long fundOrderId = NumberUtil.convertToLong(request.getParameter("id"));
    String otp = request.getParameter("otp");
    String requestId = UUID.randomUUID().toString();

    FundInRequest fundInReq = new FundInRequest(requestId, fundOrderId, otp);
    FundInResponse fundInRes = walletEndpoint.confirmFundInOtp(fundInReq);

    if (fundInRes.getStatus().getCode() == 0) {
      return REDIRECT_FUND_IN_LIST;
    } else {
      model.put("codeErr", fundInRes.getStatus().getCode());
      model.put("mesErr", fundInRes.getStatus().getValue());
      return "/fundin_order/approve_cashin";
    }
  }

  @RequestMapping(value = "/getOtp", method = RequestMethod.POST)
  @ResponseBody
  @PreAuthorize(
      value =
          "hasAnyRole('"
              + ADMIN_OPERATION
              + "','"
              + FINANCE
              + "','"
              + FINANCESUPPORT_LEADER
              + "', '"
              + FA_MANAGER
              + "', '"
              + MERCHANT
              + "','"
              + CUSTOMER
              + "')")
  public GetOTPConfirmResponse getOTPConfirm(HttpServletRequest request) throws FrontEndException {
    Long fundOrderId = NumberUtil.convertToLong(request.getParameter("id"));
    if (fundOrderId > 0) {
      GetOTPConfirmRequest otpRes = new GetOTPConfirmRequest(fundOrderId);
      return walletEndpoint.getOTPConfirm(otpRes);
    }
    GetOTPConfirmResponse response = new GetOTPConfirmResponse();
    response.setStatus(new Status(1, "Error"));
    return response;
  }

  private List<BankProfile> getProfileBanks() {
    try {
      FindBankProfileRequest profileRequest = new FindBankProfileRequest();
      return walletEndpoint.getProfileBanks(profileRequest).getBankProfiles();
    } catch (FrontEndException e) {
      LOG.error(e.getMessage(), e);
      return Collections.emptyList();
    }
  }

  // Nạp chịu
  public boolean callApiUpdateDept(
      GetFundOrderResponse fundOrderResponse, ModelMap model, HttpServletRequest request) {
    try {
      String repaymentDate = request.getParameter("payDate");
      String isDept = request.getParameter("isDept");
      String remark = StringUtils.trimToEmpty(request.getParameter("remark"));

      FundOrder fundOrder = fundOrderResponse.getFundOrder();
      fundOrder.setRepaymentDate(null);
      if (isDept != null && !isDept.isEmpty() && isDept.equals("isDept")) {
        Date repayment = DateUtil.stringToDate(repaymentDate, "dd/MM/yyyy");
        if (repayment != null) {
          fundOrder.setIsDept(true);
          fundOrder.setExpectedRepaymentDate(repayment);
          fundOrder.setInfo(remark);

          UpdateFundOrderRequest ufoRequest = new UpdateFundOrderRequest(fundOrder);
          UpdateFundOrderResponse ufoResponse = walletEndpoint.updateFundOrder(ufoRequest);
          if (ufoResponse.getStatus().getCode() != MessageNotify.SUCCESS_CODE) {
            model.put("codeErr", ufoResponse.getStatus().getCode());
            model.put("mesErr", ufoResponse.getStatus().getValue());

            model.put("fundInOrders", fundOrderResponse.getFundOrder());
            model.put("customerCurrentBalance", fundOrderResponse.getCustomerCurrentBalance());
            model.put("attachments", fundOrderResponse.getAttachments());

            LOG.error(
                "UpdateFundOrderResponse Failed: "
                    + ufoResponse.getStatus().getCode()
                    + " "
                    + ufoResponse.getStatus().getValue());
            return false;
          }
        } else {
          model.put("codeErr", MessageNotify.ERROR_CODE);
          model.put("mesErr", "require.expected.repayment.date");

          model.put("fundInOrders", fundOrderResponse.getFundOrder());
          model.put("customerCurrentBalance", fundOrderResponse.getCustomerCurrentBalance());
          model.put("attachments", fundOrderResponse.getAttachments());
          return false;
        }
      }
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      return false;
    }
    return true;
  }

  @RequestMapping(value = "/verify-repayment/{id}", method = RequestMethod.GET)
  @PreAuthorize(value = "hasAnyRole('" + FA_MANAGER + "')")
  public String verifyRepayment(
      HttpServletRequest request, ModelMap model, @PathVariable("id") Long fundOrderId)
      throws FrontEndException {

    if (fundOrderId > 0) {
      GetFundOrderRequest gfoRequest = new GetFundOrderRequest(fundOrderId);
      GetFundOrderResponse gfoResponse = walletEndpoint.getFundOrder(gfoRequest);
      FundOrder fundOrder = gfoResponse.getFundOrder();
      Integer stage = fundOrder.getStage();
      boolean isFundOrderValide =
          fundOrder != null
              && (stage.equals(FundOrderFlowStageType.FINANCE_MANAGER_REPAYMENT_REJECTED.code)
                  || stage.equals(FundOrderFlowStageType.FINANCE_MANAGER_REPAYMENT_APPROVE.code));

      if (gfoResponse.getStatus().getCode() == 0 && isFundOrderValide) {
        model.put("fundInOrders", gfoResponse.getFundOrder());
        model.put("customerCurrentBalance", gfoResponse.getCustomerCurrentBalance());
        model.put("attachments", gfoResponse.getAttachments());
        return PAGE_VERIFY_REPAYMENT;
      }
    }
    SessionUtil.setAttribute(
        MessageNotify.SESSION_MESSAGE_NOTIFY,
        new MessageNotify(MessageNotify.ERROR_CODE, validation.notify(MESAGE_ERROR)));
    return REDIRECT_FUND_IN_LIST;
  }

  @RequestMapping(value = "/verify-repayment/{id}", method = RequestMethod.POST)
  @PreAuthorize(value = "hasAnyRole('" + FA_MANAGER + "')")
  public String verifyRepaymentByPost(
      HttpServletRequest request, ModelMap model, @PathVariable("id") Long fundOrderId)
      throws FrontEndException {
    try {
      String remark = request.getParameter("remark");
      String action = request.getParameter("action");

      boolean callApiResult = true;
      Integer isSuccess = null;
      String messErr = null;

      GetFundOrderRequest requestGet = new GetFundOrderRequest(fundOrderId);
      GetFundOrderResponse responseGet = walletEndpoint.getFundOrder(requestGet);
      if (responseGet.getStatus().getCode() != MessageNotify.SUCCESS_CODE) {
        LOG.error(
            "GetFundOrderResponse failed: "
                + responseGet.getStatus().getCode()
                + " "
                + responseGet.getStatus().getValue());
      }

      if (action.equals(BTN_APPROVE)) {
        String failedResult =
            doApprove(request, responseGet, model, fundOrderId, callApiResult, isSuccess, messErr);
        if (!failedResult.equals(MessageNotify.SUCCESS_NAME)) return failedResult;
      }

      // stage even to stage odd
      if (action.equals(BTN_REJECT)) {
        FundOrderFlowRejectRequest requestReject =
            new FundOrderFlowRejectRequest(fundOrderId, remark);

        FundOrderFlowRejectResponse responseReject = walletEndpoint.rejectFundOrder(requestReject);
        if (responseReject.getStatus().getCode() != MessageNotify.SUCCESS_CODE) {
          callApiResult = false;
          isSuccess = responseReject.getStatus().getCode();
          messErr = responseReject.getStatus().getValue();
          LOG.error(
              "FundOrderFlowRejectResponse Failed: "
                  + responseReject.getStatus().getCode()
                  + " "
                  + responseReject.getStatus().getValue());
        }
      }

      // stage odd to stage even
      if (action.equals(BTN_SUBMIT)) {
        FundOrderFlowSubmitProcessRequest requestSubmitStage7 =
            new FundOrderFlowSubmitProcessRequest(fundOrderId);

        FundOrderFlowSubmitProcessResponse responseSubmit =
            walletEndpoint.submitFundOrder(requestSubmitStage7);
        if (responseSubmit.getStatus().getCode() != MessageNotify.SUCCESS_CODE) {
          callApiResult = false;
          isSuccess = responseSubmit.getStatus().getCode();
          messErr = responseSubmit.getStatus().getValue();
          LOG.error(
              "FundOrderFlowApproveResponse Failed: "
                  + responseSubmit.getStatus().getCode()
                  + " "
                  + responseSubmit.getStatus().getValue());
        }
      }

      if (callApiResult == true) {
        return REDIRECT_FUND_IN_LIST;
      } else {
        putModeltoView(isSuccess, messErr, responseGet, model);
        return PAGE_VERIFY_REPAYMENT;
      }
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return null;
  }

  public void putModeltoView(
      int errorCode, String messErr, GetFundOrderResponse responseGet, ModelMap model) {
    model.put("codeErr", errorCode);
    model.put("mesErr", messErr);
    model.put("fundInOrders", responseGet.getFundOrder());
    model.put("customerCurrentBalance", responseGet.getCustomerCurrentBalance());
    model.put("attachments", responseGet.getAttachments());
  }

  // stage even to stage even
  public String doApprove(
      HttpServletRequest request,
      GetFundOrderResponse responseGet,
      ModelMap model,
      Long fundOrderId,
      boolean callApiResult,
      Integer isSuccess,
      String messErr)
      throws IOException {

    String remark = request.getParameter("remark");
    String repaymentDate = request.getParameter("payDate");

    FundOrderFlowApproveRequest requestSubmit =
        new FundOrderFlowApproveRequest(fundOrderId, remark);

    // require attachment && repayment date
    List<FundOrderAttachment> attachments =
        getUploadAttachments(request, new FundOrderAttachment());
    Date repayment = DateUtil.stringToDate(repaymentDate, "dd/MM/yyyy");
    if (attachments.isEmpty()) {
      putModeltoView(MessageNotify.ERROR_CODE, "require.document.file", responseGet, model);
      return PAGE_VERIFY_REPAYMENT;
    }

    if (repayment == null) {
      putModeltoView(MessageNotify.ERROR_CODE, "require.repayment.date", responseGet, model);
      return PAGE_VERIFY_REPAYMENT;
    }

    // update attachment
    CreateFundOrderAttachmentRequest attchRequest = new CreateFundOrderAttachmentRequest();
    attachments.get(0).setFundOrderId(fundOrderId);
    attachments.get(0).setContentType(REPAYMENT);
    attchRequest.setAttachment(attachments.get(0));
    CreateFundOrderAttachmentResponse response =
        walletEndpoint.createFundOrderAttachment(attchRequest);
    if (response.getStatus().getCode() != MessageNotify.SUCCESS_CODE) {
      LOG.error(
          "CreateFundOrderAttachmentResponse Failed: "
              + response.getStatus().getCode()
              + " "
              + response.getStatus().getValue());
      putModeltoView(
          response.getStatus().getCode(), response.getStatus().getValue(), responseGet, model);
      return PAGE_VERIFY_REPAYMENT;
    }

    // update repaymented date
    FundOrder fundOrder = responseGet.getFundOrder();
    fundOrder.setRepaymentDate(repayment);

    UpdateFundOrderRequest ufoRequest = new UpdateFundOrderRequest(fundOrder);
    UpdateFundOrderResponse ufoResponse = walletEndpoint.updateFundOrder(ufoRequest);
    if (ufoResponse.getStatus().getCode() != MessageNotify.SUCCESS_CODE) {
      putModeltoView(
          ufoResponse.getStatus().getCode(),
          ufoResponse.getStatus().getValue(),
          responseGet,
          model);
      LOG.error(
          "UpdateFundOrderResponse Failed: "
              + ufoResponse.getStatus().getCode()
              + " "
              + ufoResponse.getStatus().getValue());
      return PAGE_VERIFY_REPAYMENT;
    }

    // approve to stage 10
    FundOrderFlowApproveResponse responseSubmit = walletEndpoint.approveFundOrder(requestSubmit);
    if (responseSubmit.getStatus().getCode() != MessageNotify.SUCCESS_CODE) {
      callApiResult = false;
      isSuccess = responseSubmit.getStatus().getCode();
      messErr = responseSubmit.getStatus().getValue();
      LOG.error(
          "FundOrderFlowApproveResponse Failed: "
              + responseSubmit.getStatus().getCode()
              + " "
              + responseSubmit.getStatus().getValue());
    }
    return MessageNotify.SUCCESS_NAME;
  }
}
