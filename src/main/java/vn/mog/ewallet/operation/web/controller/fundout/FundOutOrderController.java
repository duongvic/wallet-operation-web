package vn.mog.ewallet.operation.web.controller.fundout;

import static vn.mog.ewallet.operation.web.constant.RoleConstants.ADMIN_OPERATION;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.CUSTOMER;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.FA_MANAGER;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.FINANCE;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.FINANCESUPPORT_LEADER;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.FINANCE_LEADER;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.FINANCE_SUPPORT;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.MERCHANT;
import static vn.mog.ewallet.operation.web.constant.RoleConstants.SALE_DIRECTOR;
import static vn.mog.ewallet.operation.web.utils.ValidationUtil.DATA_INVALIDATE;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.constant.SessionConstants;
import vn.mog.ewallet.operation.web.contract.UserLogin;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.exception.MessageNotify;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.FindBankProfileRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.FindBankRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.Bank;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.BankProfile;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.FundOrderChannelType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.FundOrderFlowStageType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.CreateFundOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.CreateFundOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindCustomerBankDirectRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindCustomerBankDirectResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindFundOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindFundOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FundOutRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FundOutResponse;
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
import vn.mog.framework.common.utils.DateUtil;
import vn.mog.framework.common.utils.NumberUtil;
import vn.mog.framework.common.utils.SessionUtil;
import vn.mog.framework.contract.base.MobiliserResponseType.Status;

@Controller
@RequestMapping(value = "/wallet/fund-out")
public class FundOutOrderController extends AbstractController {

  public static final String FUND_OUT_ORDER_CONTROLLER = "/wallet/fund-out";
  public static final String FUND_OUT_ORDER_LIST = FUND_OUT_ORDER_CONTROLLER + "/list";
  public static final String FUND_OUT_ORDER_REQUEST =
      FUND_OUT_ORDER_CONTROLLER + "/request-fundout";

  private static final Logger LOG = LogManager.getLogger(FundOutOrderController.class);
  private static final String PAGE_EDIT_CASH_ONE_HAND = "/fundout_order/edit_cash_on_hand";
  private static final String PAGE_REQUEST_FUND_OUT = "/fundout_order/request_fundout";
  private static final String PAGE_REQUEST_CASH_OUT = "/fundout_order/request_cashout";
  private static final String PAGE_VERIFY_CASH_OUT = "/fundout_order/verify_cashout";
  private static final String PAGE_APPROVE_CASH_OUT_OTP = "/fundout_order/approve_cashout_otp";

  private static final String REDIRECT_FUND_OUT_LIST = "redirect:" + FUND_OUT_ORDER_LIST;
  private static final String REDIRECT_FUND_OUT_ORDER_REQUEST =
      "redirect:" + FUND_OUT_ORDER_REQUEST;

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
              + FA_MANAGER
              + "','"
              + FINANCE
              + "','"
              + FINANCESUPPORT_LEADER
              + "','"
              + RoleConstants.RECONCILIATION
              + "','"
              + RoleConstants.RECONCILIATION_LEADER
              + "','"
              + RoleConstants.CUSTOMERCARE_MANAGER
              + "','"
              + RoleConstants.PROVIDER
              + "','"
              + RoleConstants.SALE_ASM
              + "','"
              + RoleConstants.SALE_EXCUTIVE
              + "','"
              + SALE_DIRECTOR
              + "')")
  public String search(HttpServletRequest request, ModelMap model) throws FrontEndException {

    String paramText = StringUtils.trimToEmpty(request.getParameter("quickSearch"));
    String paramDateRange = StringUtils.trimToEmpty(request.getParameter("range"));
    String[] paramCustomerIds = request.getParameterValues("customerIds");
    String[] paramFlowStages = request.getParameterValues("stage");
    String[] paramChannels = request.getParameterValues("channel");

    FindFundOrderRequest ffoRequest = new FindFundOrderRequest();

    Date[] dates = parseDateRange(paramDateRange, true);
    ffoRequest.setFromDate(dates[0]);
    ffoRequest.setEndDate(dates[1]);

    int offset = 0;
    int limit = 20;

    if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
      offset = Integer.parseInt(request.getParameter("d-49489-p"));
      if (offset > 0) {
        offset = (offset - 1) * limit;
      }
    }

    if (paramCustomerIds != null
        && paramCustomerIds.length > 0
        && StringUtils.isNotEmpty(paramCustomerIds[0])) {
      ffoRequest.setCustomerIds(NumberUtil.convertListToLong(paramCustomerIds));
    }

    if (paramChannels != null
        && paramChannels.length > 0
        && StringUtils.isNotEmpty(paramChannels[0])) {
      ffoRequest.setOrderChannelIds(Arrays.asList(paramChannels));
    }

    if (paramFlowStages != null
        && paramFlowStages.length > 0
        && StringUtils.isNotEmpty(paramFlowStages[0])) {
      ffoRequest.setStages(NumberUtil.convertListToInt(paramFlowStages));
    }

    ffoRequest.setSearchText(paramText);
    ffoRequest.setLimit(limit);
    ffoRequest.setOffset(offset);
    ffoRequest.setServiceTypeIds(Collections.singletonList(ServiceType.FUND_OUT.name()));

    FindFundOrderResponse ffoResponse = walletEndpoint.findFundOrders(ffoRequest);

    model.put("fundOutOrders", ffoResponse.getFundOrders());
    model.put("totalFee", ffoResponse.getFee());
    model.put("totalAmount", ffoResponse.getAmount());

    model.put("pagesize", limit);
    model.put("offset", offset);
    model.put("total", ffoResponse.getCount().intValue());
    //    model.put("customers", cacheDataUtil.getCustomersByBiz());

    return "/fundout_order/list";
  }

  @RequestMapping(value = "/request-fundout", method = RequestMethod.GET)
  @PreAuthorize(
      value =
          "hasAnyRole('"
              + ADMIN_OPERATION
              + "','"
              + MERCHANT
              + "','"
              + CUSTOMER
              + "','"
              + SALE_DIRECTOR
              + "','"
              + RoleConstants.SALE_ASM
              + "','"
              + RoleConstants.SALE_EXCUTIVE
              + "','"
              + FA_MANAGER
              + "','"
              + FINANCE_LEADER
              + "', '"
              + FINANCESUPPORT_LEADER
              + "', '"
              + FINANCE_SUPPORT
              + "' ,'"
              + FINANCE
              + "')")
  public String requestFundOut(HttpServletRequest request, ModelMap model)
      throws FrontEndException {

    putParameterToResponseFundOutOrder(request, model);

    String styleDiv = (String) SessionUtil.getAttribute("styleDiv");
    if (styleDiv != null) {
      model.put("styleDiv", "linked_bank");
    }

    model.put("timeRequest", DateUtil.dateToString("hh:MM:ss dd/MM/yyyy", new Date()));
    model.put("stepLinkBank", "request");

    return PAGE_REQUEST_FUND_OUT;
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
              + FA_MANAGER
              + "','"
              + FINANCE
              + "','"
              + FINANCESUPPORT_LEADER
              + "',"
              + "'RECONCILIATION','"
              + RoleConstants.RECONCILIATION_LEADER
              + "','"
              + RoleConstants.CUSTOMERCARE_MANAGER
              + "','"
              + RoleConstants.PROVIDER
              + "','"
              + RoleConstants.SALE_ASM
              + "','"
              + RoleConstants.SALE_EXCUTIVE
              + "','"
              + SALE_DIRECTOR
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
        return "/fundout_order/detail";
      }
    }
    return REDIRECT_FUND_OUT_LIST;
  }

  @RequestMapping(value = "/bank-tranfer", method = RequestMethod.POST)
  @PreAuthorize(
      value =
          "hasAnyRole('"
              + ADMIN_OPERATION
              + "','"
              + MERCHANT
              + "','"
              + CUSTOMER
              + "','"
              + SALE_DIRECTOR
              + "','"
              + RoleConstants.SALE_ASM
              + "','"
              + RoleConstants.SALE_EXCUTIVE
              + "','"
              + FA_MANAGER
              + "','"
              + FINANCE_LEADER
              + "', '"
              + FINANCESUPPORT_LEADER
              + "', '"
              + FINANCE_SUPPORT
              + "' ,'"
              + FINANCE
              + "')")
  public String createBankTranfer(HttpServletRequest request, ModelMap model)
      throws FrontEndException {

    String amount = request.getParameter("amount");
    String bank = request.getParameter("bank");
    String number = request.getParameter("number");
    String account = request.getParameter("account");
    String branch = request.getParameter("branch");
    Long customerId = NumberUtil.convertToLong(request.getParameter("customerId"));
    String action = request.getParameter("action");

    Long amountL = NumberUtil.convertToLong(amount.replaceAll(",", StringUtils.EMPTY));

    if (amountL == 0L) {
      model.put("amount", amount);
      model.put("customerId", customerId);
      model.put("codeErr", 1);
      model.put("mesErr", "Chọn số tiền để nạp tiền");
      model.put("styleDiv", "bank_tranfer");

      model.put("listBank", getFullBanks());
      //      model.put("customers", cacheDataUtil.getCustomersByBiz());

      return PAGE_REQUEST_FUND_OUT;
    }

    FundOrder fundOutOrder = new FundOrder();
    fundOutOrder.setOrderChannel(FundOrderChannelType.BANK_TRANSFER.code);
    fundOutOrder.setAmount(amountL);
    fundOutOrder.setBankCode(bank);
    fundOutOrder.setBankAccountName(account);
    fundOutOrder.setBankAccountNumber(number);
    fundOutOrder.setBankBranch(branch);
    fundOutOrder.setServiceType(ServiceType.FUND_OUT.name());

    CreateFundOrderRequest cfoRequest = new CreateFundOrderRequest(fundOutOrder, customerId);
    CreateFundOrderResponse cfoResponse = walletEndpoint.createFundOrder(cfoRequest);

    if (cfoResponse.getStatus().getCode() == 0) {

      if (BTN_SUBMIT.equals(action)) {

        FundOrderFlowApproveRequest fofaRequest =
            new FundOrderFlowApproveRequest(cfoResponse.getFundOrderId());
        FundOrderFlowApproveResponse fofaResponse = walletEndpoint.approveFundOrder(fofaRequest);

        if (fofaResponse.getStatus().getCode() == 0) {
          return REDIRECT_FUND_OUT_LIST;
        } else {
          model.put("codeErr", fofaResponse.getStatus().getCode());
          model.put("mesErr", fofaResponse.getStatus().getValue());
          return PAGE_REQUEST_FUND_OUT;
        }
      } else {
        return REDIRECT_FUND_OUT_LIST;
      }
    } else {

      model.put("listBank", getFullBanks());
      //      model.put("customers", cacheDataUtil.getCustomersByBiz());

      model.put("bank", bank);
      model.put("account", account);
      model.put("number", number);
      model.put("branch", branch);
      model.put("styleDiv", "bank_tranfer");
      model.put("amount", amount);
      model.put("customerId", customerId);
      model.put("codeErr", cfoResponse.getStatus().getCode());
      model.put("mesErr", cfoResponse.getStatus().getValue());
      return PAGE_REQUEST_FUND_OUT;
    }
  }

  @RequestMapping(value = "/cash-on-hand", method = RequestMethod.POST)
  @PreAuthorize(
      value =
          "hasAnyRole('"
              + ADMIN_OPERATION
              + "','"
              + MERCHANT
              + "','"
              + CUSTOMER
              + "','"
              + SALE_DIRECTOR
              + "','"
              + RoleConstants.SALE_ASM
              + "','"
              + RoleConstants.SALE_EXCUTIVE
              + "','"
              + FA_MANAGER
              + "','"
              + FINANCE_LEADER
              + "', '"
              + FINANCESUPPORT_LEADER
              + "', '"
              + FINANCE_SUPPORT
              + "' ,'"
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
    //    model.put("listBank", getFullBanks());
    ////    model.put("customers", cacheDataUtil.getCustomersByBiz());
    //
    //    Long amountL = NumberUtil.convertToLong(amount.replaceAll(",", StringUtils.EMPTY));
    //    if (amountL == 0L) {
    //      model.put("amount", amount);
    //      model.put("remark", remark);
    //      model.put("customerId", customerId);
    //      model.put("codeErr", 1);
    //      model.put("mesErr", "Chọn số tiền để rút tiền");
    //
    //      model.put("styleDiv", "cash_on_hand");
    //
    //      return PAGE_REQUEST_FUND_OUT;
    //    }
    //
    //    List<FundOrderAttachment> attachments = getUploadAttachments(request,
    //        new FundOrderAttachment());
    //
    //    FundOrder fundOutOrder = new FundOrder();
    //    fundOutOrder.setOrderChannel(FundOrderChannelType.CASH_ON_HAND.code);
    //    fundOutOrder.setAmount(amountL);
    //    fundOutOrder.setInfo(remark);
    //    fundOutOrder.setServiceType(ServiceType.FUND_OUT.name());
    //
    //    CreateFundOrderRequest cfoRequest = new CreateFundOrderRequest(fundOutOrder, customerId,
    //        attachments);
    //    CreateFundOrderResponse cfoResponse = walletEndpoint.createFundOrder(cfoRequest);
    //
    //    if (cfoResponse.getStatus().getCode() == 0) {
    //
    //      if (BTN_SUBMIT.equals(action)) {
    //        FundOrderFlowApproveRequest fofaRequest = new FundOrderFlowApproveRequest(
    //            cfoResponse.getFundOrderId());
    //        FundOrderFlowApproveResponse fofaResponse =
    // walletEndpoint.approveFundOrder(fofaRequest);
    //
    //        if (fofaResponse.getStatus().getCode() == 0) {
    //          return REDIRECT_FUND_OUT_LIST;
    //        } else {
    //          model.put("codeErr", fofaResponse.getStatus().getCode());
    //          model.put("mesErr", fofaResponse.getStatus().getValue());
    //          return PAGE_REQUEST_FUND_OUT;
    //        }
    //      } else {
    //        return REDIRECT_FUND_OUT_LIST;
    //      }
    //    } else {
    //      model.put("styleDiv", "cash_on_hand");
    //      model.put("amount", amount);
    //      model.put("remark", remark);
    //      model.put("customerId", customerId);
    //      model.put("codeErr", cfoResponse.getStatus().getCode());
    //      model.put("mesErr", cfoResponse.getStatus().getValue());
    //      return PAGE_REQUEST_FUND_OUT;
    //    }
  }

  @RequestMapping(value = "/edit-cash-out", method = RequestMethod.GET)
  @PreAuthorize(
      value =
          "hasAnyRole('"
              + ADMIN_OPERATION
              + "','"
              + MERCHANT
              + "','"
              + CUSTOMER
              + "','"
              + SALE_DIRECTOR
              + "','"
              + RoleConstants.SALE_ASM
              + "','"
              + RoleConstants.SALE_EXCUTIVE
              + "','"
              + FA_MANAGER
              + "','"
              + FINANCE_LEADER
              + "', '"
              + FINANCESUPPORT_LEADER
              + "', '"
              + FINANCE_SUPPORT
              + "' ,'"
              + FINANCE
              + "')")
  public String editCashOut(HttpServletRequest request, ModelMap model) throws FrontEndException {

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
        model.put("listBank", getFullBanks());

        return PAGE_EDIT_CASH_ONE_HAND;
      }
    }
    return REDIRECT_FUND_OUT_LIST;
  }

  @RequestMapping(value = "/edit-cash-out", method = RequestMethod.POST)
  @PreAuthorize(
      value =
          "hasAnyRole('"
              + ADMIN_OPERATION
              + "','"
              + MERCHANT
              + "','"
              + CUSTOMER
              + "','"
              + SALE_DIRECTOR
              + "','"
              + RoleConstants.SALE_ASM
              + "','"
              + RoleConstants.SALE_EXCUTIVE
              + "','"
              + FA_MANAGER
              + "','"
              + FINANCE_LEADER
              + "', '"
              + FINANCESUPPORT_LEADER
              + "', '"
              + FINANCE_SUPPORT
              + "' ,'"
              + FINANCE
              + "')")
  public String editCashOutPost(HttpServletRequest request, ModelMap model)
      throws FrontEndException, IOException {

    Long fundOrderId = NumberUtil.convertToLong(request.getParameter("id"));
    String amount = request.getParameter("amount").replaceAll(",", StringUtils.EMPTY);
    String remark = request.getParameter("remark");
    String action = request.getParameter("action");
    Long customerId = NumberUtil.convertToLong(request.getParameter("customerId"));

    GetFundOrderRequest gfoRequest = new GetFundOrderRequest(fundOrderId);
    GetFundOrderResponse gfoResponse = walletEndpoint.getFundOrder(gfoRequest);

    List<FundOrderAttachment> attachments =
        getUploadAttachments(request, new FundOrderAttachment());

    FundOrder fundOrder = new FundOrder();
    fundOrder.setId(fundOrderId);
    fundOrder.setAmount(NumberUtil.convertToLong(amount));
    fundOrder.setInfo(remark);

    String codeBank = FundOrderChannelType.BANK_TRANSFER.code;
    String fundCodeBank = gfoResponse.getFundOrder().getOrderChannel();

    if (fundCodeBank.equals(codeBank)) {
      String bank = request.getParameter("bank");
      String number = request.getParameter("number");
      String account = request.getParameter("account");
      String branch = request.getParameter("branch");

      fundOrder.setBankCode(bank);
      fundOrder.setBankAccountName(account);
      fundOrder.setBankAccountNumber(number);
      fundOrder.setBankBranch(branch);
    }

    UpdateFundOrderRequest ufoRequest =
        new UpdateFundOrderRequest(fundOrder, customerId, attachments);
    UpdateFundOrderResponse ufoResponse = walletEndpoint.updateFundOrder(ufoRequest);

    if (ufoResponse.getStatus().getCode() == 0) {
      if (BTN_SUBMIT.equals(action)) {

        Integer iSuccess;
        String messErr;

        if (gfoResponse
            .getFundOrder()
            .getStage()
            .equals(FundOrderFlowStageType.SALE_EXCUTIVE_REJECTED.code)) {
          FundOrderFlowSubmitProcessRequest ffspRequest =
              new FundOrderFlowSubmitProcessRequest(fundOrderId);
          FundOrderFlowSubmitProcessResponse ffspResponse =
              walletEndpoint.submitFundOrder(ffspRequest);

          iSuccess = ffspResponse.getStatus().getCode();
          messErr = ffspResponse.getStatus().getValue();
        } else {
          FundOrderFlowApproveRequest fofaRequest = new FundOrderFlowApproveRequest(fundOrderId);
          FundOrderFlowApproveResponse fofaResponse = walletEndpoint.approveFundOrder(fofaRequest);

          iSuccess = fofaResponse.getStatus().getCode();
          messErr = fofaResponse.getStatus().getValue();
        }

        if (iSuccess == 0) {
          return REDIRECT_FUND_OUT_LIST;
        } else {
          model.put("codeErr", iSuccess);
          model.put("mesErr", messErr);
          model.put("fundInOrders", gfoResponse.getFundOrder());
          model.put("attachments", gfoResponse.getAttachments());

          return PAGE_EDIT_CASH_ONE_HAND;
        }
      } else {
        return REDIRECT_FUND_OUT_LIST;
      }
    } else {
      model.put("attachments", gfoResponse.getAttachments());
      model.put("fundInOrders", gfoResponse.getFundOrder());
      model.put("codeErr", ufoResponse.getStatus().getCode());
      model.put("mesErr", ufoResponse.getStatus().getValue());

      return PAGE_EDIT_CASH_ONE_HAND;
    }
  }

  @RequestMapping(value = "/request-cash-out", method = RequestMethod.GET)
  @PreAuthorize(
      value =
          "hasAnyRole('"
              + ADMIN_OPERATION
              + "','"
              + MERCHANT
              + "','"
              + CUSTOMER
              + "','"
              + SALE_DIRECTOR
              + "','"
              + RoleConstants.SALE_ASM
              + "','"
              + RoleConstants.SALE_EXCUTIVE
              + "','"
              + FA_MANAGER
              + "','"
              + FINANCE_LEADER
              + "', '"
              + FINANCESUPPORT_LEADER
              + "', '"
              + FINANCE_SUPPORT
              + "' ,'"
              + FINANCE
              + "')")
  public String requestCashOut(HttpServletRequest request, ModelMap model)
      throws FrontEndException {

    Long fundOrderId = NumberUtil.convertToLong(request.getParameter("id"));
    if (fundOrderId > 0) {
      GetFundOrderRequest gfoRequest = new GetFundOrderRequest(fundOrderId);
      GetFundOrderResponse gfoResponse = walletEndpoint.getFundOrder(gfoRequest);

      if (gfoResponse.getStatus().getCode() == 0 && gfoResponse.getFundOrder() != null) {
        model.put("fundInOrders", gfoResponse.getFundOrder());
        model.put("customerCurrentBalance", gfoResponse.getCustomerCurrentBalance());
        model.put("attachments", gfoResponse.getAttachments());
        return PAGE_REQUEST_CASH_OUT;
      }
    }
    return REDIRECT_FUND_OUT_LIST;
  }

  @RequestMapping(value = "/request-cash-out", method = RequestMethod.POST)
  @PreAuthorize(
      value =
          "hasAnyRole('"
              + ADMIN_OPERATION
              + "','"
              + MERCHANT
              + "','"
              + CUSTOMER
              + "','"
              + SALE_DIRECTOR
              + "','"
              + RoleConstants.SALE_ASM
              + "','"
              + RoleConstants.SALE_EXCUTIVE
              + "','"
              + FA_MANAGER
              + "','"
              + FINANCE_LEADER
              + "', '"
              + FINANCESUPPORT_LEADER
              + "', '"
              + FINANCE_SUPPORT
              + "' ,'"
              + FINANCE
              + "')")
  public String requestCashOutPost(HttpServletRequest request, ModelMap model)
      throws FrontEndException, IOException {

    Long fundOrderId = NumberUtil.convertToLong(request.getParameter("id"));
    String remark = request.getParameter("remark");
    String action = request.getParameter("action");

    List<FundOrderAttachment> attachments =
        getUploadAttachments(request, new FundOrderAttachment());

    FundOrder fundOrder = new FundOrder(fundOrderId, remark);

    UpdateFundOrderRequest ufoRequest = new UpdateFundOrderRequest(fundOrder, attachments);
    UpdateFundOrderResponse ufoResponse = walletEndpoint.updateFundOrder(ufoRequest);

    if (ufoResponse.getStatus().getCode() == 0) {
      if (BTN_SUBMIT.equals(action)) {
        GetFundOrderRequest gfoRequest = new GetFundOrderRequest(fundOrderId);
        GetFundOrderResponse gfoResponse = walletEndpoint.getFundOrder(gfoRequest);

        Integer iSuccess;
        String messErr;

        if (gfoResponse
            .getFundOrder()
            .getStage()
            .equals(FundOrderFlowStageType.FINANCE_SUPPORT_REJECTED.code)) {
          FundOrderFlowSubmitProcessRequest ffspRequest =
              new FundOrderFlowSubmitProcessRequest(fundOrderId);
          FundOrderFlowSubmitProcessResponse ffspResponse =
              walletEndpoint.submitFundOrder(ffspRequest);

          iSuccess = ffspResponse.getStatus().getCode();
          messErr = ffspResponse.getStatus().getValue();
        } else {
          FundOrderFlowApproveRequest fofaRequest = new FundOrderFlowApproveRequest(fundOrderId);
          FundOrderFlowApproveResponse fofaResponse = walletEndpoint.approveFundOrder(fofaRequest);

          iSuccess = fofaResponse.getStatus().getCode();
          messErr = fofaResponse.getStatus().getValue();
        }

        if (iSuccess == 0) {
          return REDIRECT_FUND_OUT_LIST;
        } else {

          model.put("codeErr", iSuccess);
          model.put("mesErr", messErr);
          model.put("fundInOrders", gfoResponse.getFundOrder());
          model.put("attachments", gfoResponse.getAttachments());

          return PAGE_REQUEST_CASH_OUT;
        }
      } else if (BTN_REJECT.equals(action)) {
        FundOrderFlowRejectRequest folRejecRequest =
            new FundOrderFlowRejectRequest(fundOrderId, remark);
        FundOrderFlowRejectResponse folRejectResponse =
            walletEndpoint.rejectFundOrder(folRejecRequest);

        if (folRejectResponse.getStatus().getCode() == 0) {
          return REDIRECT_FUND_OUT_LIST;
        } else {
          GetFundOrderRequest gfoRequest = new GetFundOrderRequest(fundOrderId);
          GetFundOrderResponse gfoResponse = walletEndpoint.getFundOrder(gfoRequest);

          model.put("codeErr", folRejectResponse.getStatus().getCode());
          model.put("mesErr", folRejectResponse.getStatus().getValue());
          model.put("fundInOrders", gfoResponse.getFundOrder());
          model.put("customerCurrentBalance", gfoResponse.getCustomerCurrentBalance());
          model.put("attachments", gfoResponse.getAttachments());

          return PAGE_REQUEST_CASH_OUT;
        }
      } else {
        return REDIRECT_FUND_OUT_LIST;
      }
    } else {
      GetFundOrderRequest gfoRequest = new GetFundOrderRequest(fundOrderId);
      GetFundOrderResponse gfoResponse = walletEndpoint.getFundOrder(gfoRequest);

      model.put("attachments", gfoResponse.getAttachments());
      model.put("fundInOrders", gfoResponse.getFundOrder());
      model.put("codeErr", ufoResponse.getStatus().getCode());
      model.put("mesErr", ufoResponse.getStatus().getValue());

      return PAGE_REQUEST_CASH_OUT;
    }
  }

  @RequestMapping(value = "/verify-cash-out", method = RequestMethod.GET)
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
  public String verifyCashOut(HttpServletRequest request, ModelMap model) throws FrontEndException {

    Long fundOrderId = NumberUtil.convertToLong(request.getParameter("id"));
    if (fundOrderId > 0) {
      GetFundOrderRequest gfoRequest = new GetFundOrderRequest(fundOrderId);
      GetFundOrderResponse gfoResponse = walletEndpoint.getFundOrder(gfoRequest);

      FundOrder fundOrder = gfoResponse.getFundOrder();

      boolean isFundOrder =
          fundOrder != null
              && (fundOrder.getStage().equals(FundOrderFlowStageType.FINANCE_SUPPORT_VERIFY.code)
                  || fundOrder
                      .getStage()
                      .equals(FundOrderFlowStageType.FINANCE_MAMAGER_APPROVE.code)
                  || fundOrder
                      .getStage()
                      .equals(FundOrderFlowStageType.FINANCE_MANAGER_REJECTED.code));
      if (gfoResponse.getStatus().getCode() == 0 && isFundOrder) {

        model.put("fundInOrders", gfoResponse.getFundOrder());
        model.put("bankCodeSOF", gfoResponse.getBankCodeSOF());
        model.put("customerCurrentBalance", gfoResponse.getCustomerCurrentBalance());
        model.put("attachments", gfoResponse.getAttachments());
        model.put("profileBanks", getProfileBanks());

        return PAGE_VERIFY_CASH_OUT;
      }
    }
    return REDIRECT_FUND_OUT_LIST;
  }

  @RequestMapping(value = "/verify-cash-out", method = RequestMethod.POST)
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
  public String verifyCashOutPost(HttpServletRequest request, ModelMap model)
      throws FrontEndException {

    Long fundOrderId = NumberUtil.convertToLong(request.getParameter("id"));
    String remark = request.getParameter("remark");
    String action = request.getParameter("action");
    String profileBankCode = request.getParameter("profileBankCode");

    String responseValue = null;
    int responseCode = 0;

    if (BTN_APPROVE.equals(action)) {
      GetFundOrderRequest gfoRequest = new GetFundOrderRequest(fundOrderId);
      GetFundOrderResponse gfoResponse = walletEndpoint.getFundOrder(gfoRequest);

      if (gfoResponse
          .getFundOrder()
          .getStage()
          .equals(FundOrderFlowStageType.FINANCE_MAMAGER_APPROVE.code)) {

        GetOTPConfirmRequest otpRequest = new GetOTPConfirmRequest(fundOrderId);
        GetOTPConfirmResponse otpResponse = walletEndpoint.getOTPConfirm(otpRequest);

        if (otpResponse.getStatus().getCode() == 0) {
          return "redirect:" + FUND_OUT_ORDER_CONTROLLER + "/approve-cash-out?id=" + fundOrderId;
        } else {
          model.put("id", fundOrderId);
          model.put("codeErr", otpResponse.getStatus().getCode());
          model.put("mesErr", otpResponse.getStatus().getValue());
          return "redirect:" + FUND_OUT_ORDER_CONTROLLER + "/verify-cash-out";
        }

      } else {

        FundOrder fundOrder = new FundOrder(fundOrderId, remark);

        UpdateFundOrderRequest ufoRequest = new UpdateFundOrderRequest(fundOrder);
        ufoRequest.setBankCodeSOF(profileBankCode);
        UpdateFundOrderResponse ufoResponse = walletEndpoint.updateFundOrder(ufoRequest);

        if (ufoResponse.getStatus().getCode() == 0) {

          FundOrderFlowApproveRequest fofaRequest =
              new FundOrderFlowApproveRequest(fundOrderId, remark);
          FundOrderFlowApproveResponse fofaResponse = walletEndpoint.approveFundOrder(fofaRequest);

          if (fofaResponse.getStatus().getCode() == 0) {
            return REDIRECT_FUND_OUT_LIST;
          } else {
            model.put("codeErr", fofaResponse.getStatus().getCode());
            model.put("mesErr", fofaResponse.getStatus().getValue());

            model.put("fundInOrders", gfoResponse.getFundOrder());
            model.put("customerCurrentBalance", gfoResponse.getCustomerCurrentBalance());
            model.put("attachments", gfoResponse.getAttachments());
            model.put("profileBanks", getProfileBanks());
            return PAGE_VERIFY_CASH_OUT;
          }
        } else {
          responseCode = ufoResponse.getStatus().getCode();
          responseValue = ufoResponse.getStatus().getValue();
        }
      }
    } else if (BTN_REJECT.equals(action)) {
      FundOrderFlowRejectRequest fofrRequest = new FundOrderFlowRejectRequest(fundOrderId, remark);
      FundOrderFlowRejectResponse fofrResponse = walletEndpoint.rejectFundOrder(fofrRequest);

      if (fofrResponse.getStatus().getCode() == 0) {
        return REDIRECT_FUND_OUT_LIST;
      } else {

        GetFundOrderRequest gfoRequest = new GetFundOrderRequest(fundOrderId);
        GetFundOrderResponse gfoResponse = walletEndpoint.getFundOrder(gfoRequest);

        model.put("codeErr", fofrResponse.getStatus().getCode());
        model.put("mesErr", fofrResponse.getStatus().getValue());
        model.put("fundInOrders", gfoResponse.getFundOrder());
        model.put("customerCurrentBalance", gfoResponse.getCustomerCurrentBalance());
        model.put("attachments", gfoResponse.getAttachments());

        return PAGE_VERIFY_CASH_OUT;
      }
    } else {
      GetFundOrderRequest gfoRequest = new GetFundOrderRequest(fundOrderId);
      GetFundOrderResponse gfoResponse = walletEndpoint.getFundOrder(gfoRequest);

      model.put("codeErr", 1);
      model.put("mesErr", "Hành động không đúng !");
      model.put("fundInOrders", gfoResponse.getFundOrder());
      model.put("customerCurrentBalance", gfoResponse.getCustomerCurrentBalance());
      model.put("attachments", gfoResponse.getAttachments());

      return PAGE_VERIFY_CASH_OUT;
    }
    if (responseValue != null) {
      SessionUtil.setAttribute(
          MessageNotify.SESSION_MESSAGE_NOTIFY, new MessageNotify(responseCode, responseValue));
    }
    return REDIRECT_FUND_OUT_LIST;
  }

  @RequestMapping(value = "/approve-cash-out", method = RequestMethod.GET)
  @PreAuthorize(
      value =
          "hasAnyRole('"
              + ADMIN_OPERATION
              + "','"
              + FA_MANAGER
              + "', '"
              + FINANCE_LEADER
              + "', '"
              + FINANCESUPPORT_LEADER
              + "' ,'"
              + FINANCE_SUPPORT
              + "')")
  public String approveCashOut(HttpServletRequest request, ModelMap model)
      throws FrontEndException {

    Long fundOrderId = NumberUtil.convertToLong(request.getParameter("id"));
    if (fundOrderId > 0) {
      GetFundOrderRequest gfoRequest = new GetFundOrderRequest(fundOrderId);
      GetFundOrderResponse gfoResponse = walletEndpoint.getFundOrder(gfoRequest);

      if (gfoResponse.getStatus().getCode() == 0 && gfoResponse.getFundOrder() != null) {
        model.put("fundInOrders", gfoResponse.getFundOrder());
        model.put("customerCurrentBalance", gfoResponse.getCustomerCurrentBalance());
        // model.put("attachments", gfoResponse.getAttachments());
        return PAGE_APPROVE_CASH_OUT_OTP;
      }
    }
    return REDIRECT_FUND_OUT_LIST;
  }

  @RequestMapping(value = "/approve-cash-out", method = RequestMethod.POST)
  @PreAuthorize(
      value =
          "hasAnyRole('"
              + ADMIN_OPERATION
              + "','"
              + FA_MANAGER
              + "', '"
              + FINANCE_LEADER
              + "', '"
              + FINANCESUPPORT_LEADER
              + "' ,'"
              + FINANCE_SUPPORT
              + "')")
  public String approveCashOutPost(HttpServletRequest request, ModelMap model)
      throws FrontEndException {

    Long fundOrderId = NumberUtil.convertToLong(request.getParameter("id"));
    String otp = request.getParameter("otp");

    if (fundOrderId > 0) {
      String requestId = UUID.randomUUID().toString();
      FundOutRequest fundOutRequest = new FundOutRequest(requestId, fundOrderId, otp);
      FundOutResponse fundOutResponse = walletEndpoint.confirmFundOutOtp(fundOutRequest);

      if (fundOutResponse.getStatus().getCode() == 0) {
        return REDIRECT_FUND_OUT_LIST;

      } else {
        model.put("codeErr", fundOutResponse.getStatus().getCode());
        model.put("mesErr", fundOutResponse.getStatus().getValue());
        return PAGE_APPROVE_CASH_OUT_OTP;
      }

    } else {
      model.put("codeErr", 1);
      model.put("mesErr", "Có lỗi, dữ liệu không hợp lệ");
      return PAGE_APPROVE_CASH_OUT_OTP;
    }
  }

  @RequestMapping(value = "/linked-bank", method = RequestMethod.POST)
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','MERCHANT','CUSTOMER')")
  public String linkBankTranfer(HttpServletRequest request, ModelMap model)
      throws FrontEndException {

    String pAmount = request.getParameter("amountLinkBank");
    String bankCode = request.getParameter("bankCode");
    String bankAccountName = request.getParameter("bankAccountName");
    String bankAccountNumber = request.getParameter("bankAccountNumber");
    String bankBranch = request.getParameter("bankBranch");
    String action = request.getParameter("action");

    Long amountOfLinkBank = NumberUtil.convertToLong(pAmount.replaceAll(",", StringUtils.EMPTY));

    UserLogin userLogin =
        (UserLogin) SessionUtil.getAttribute(SessionConstants.SESSION_ACCOUNT_LOGIN);

    String messageValidation =
        validation.isLinkBankTranfer(amountOfLinkBank, userLogin.getBalance());
    if (messageValidation.length() > 0) {

      SessionUtil.setAttribute(
          MessageNotify.SESSION_MESSAGE_NOTIFY,
          new MessageNotify(MessageNotify.ERROR_CODE, messageValidation));
      SessionUtil.setAttribute("styleDiv", "linked_bank");

      return REDIRECT_FUND_OUT_ORDER_REQUEST;
    }

    if (BTN_REQUEST.equals(action)) {

      Long paramFundOrderId = NumberUtil.convertToLong(request.getParameter("fundOrderId"));

      FundOrder fundOrder = new FundOrder();
      fundOrder.setOrderChannel(FundOrderChannelType.LINK_BANK.code);
      fundOrder.setAmount(amountOfLinkBank);
      fundOrder.setBankCode(bankCode);
      fundOrder.setBankAccountName(bankAccountName);
      fundOrder.setBankAccountNumber(bankAccountNumber);
      fundOrder.setBankBranch(bankBranch);
      fundOrder.setServiceType(ServiceType.FUND_OUT.name());

      boolean isResultFundOrder;
      Long isResultFundOrderId;

      int statusCode;
      String statusValue;

      if (paramFundOrderId > 0) {
        fundOrder.setId(paramFundOrderId);

        UpdateFundOrderRequest ufoRequest = new UpdateFundOrderRequest(fundOrder);
        UpdateFundOrderResponse ufoResponse = walletEndpoint.updateFundOrder(ufoRequest);

        isResultFundOrder = ufoResponse.getStatus().getCode() == 0 && paramFundOrderId > 0;
        isResultFundOrderId = paramFundOrderId;

        statusCode = ufoResponse.getStatus().getCode();
        statusValue = ufoResponse.getStatus().getValue();

      } else {

        CreateFundOrderRequest cfoRequest = new CreateFundOrderRequest(fundOrder);
        CreateFundOrderResponse cfoResponse = walletEndpoint.createFundOrder(cfoRequest);

        isResultFundOrder =
            cfoResponse.getStatus().getCode() == 0 && cfoResponse.getFundOrderId() > 0;
        isResultFundOrderId = cfoResponse.getFundOrderId();

        statusCode = cfoResponse.getStatus().getCode();
        statusValue = cfoResponse.getStatus().getValue();
      }

      if (isResultFundOrder) {
        GetOTPConfirmRequest otpConfirmRequest = new GetOTPConfirmRequest(isResultFundOrderId);
        GetOTPConfirmResponse otpConfirmResponse = walletEndpoint.getOTPConfirm(otpConfirmRequest);

        if (otpConfirmResponse.getStatus().getCode() == 0) {

          putParameterToResponseFundOutOrder(request, model);

          model.put("amountLinkBank", pAmount);
          model.put("styleDiv", "linked_bank");
          model.put("fundOrderId", isResultFundOrderId);

          model.put("stepLinkBank", "verify");
          model.put("perOtp", true);
          model.put("sentPhone", "demo");
          model.put("codeOtp", "1234");
          model.put("waiting", "1234");
          return PAGE_REQUEST_FUND_OUT;
        }

      } else {
        SessionUtil.setAttribute(
            MessageNotify.SESSION_MESSAGE_NOTIFY, new MessageNotify(statusCode, statusValue));
        SessionUtil.setAttribute("styleDiv", "linked_bank");
        return REDIRECT_FUND_OUT_ORDER_REQUEST;
      }
    }

    SessionUtil.setAttribute(
        MessageNotify.SESSION_MESSAGE_NOTIFY,
        new MessageNotify(MessageNotify.ERROR_CODE, validation.notify(DATA_INVALIDATE)));
    return REDIRECT_FUND_OUT_LIST;
  }

  @RequestMapping(value = "/linked-bank-otp", method = RequestMethod.POST)
  @PreAuthorize(value = "hasAnyRole('" + ADMIN_OPERATION + "','MERCHANT','CUSTOMER')")
  public String linkBankTransferConfirmOtp(HttpServletRequest request, ModelMap model)
      throws FrontEndException {

    String action = request.getParameter("action");

    if (BTN_SUBMIT.equals(action)) {

      Long fundOrderId = NumberUtil.convertToLong(request.getParameter("fundOrderId"));
      String otp = request.getParameter("otp");

      if (fundOrderId > 0 && StringUtils.isNotEmpty(otp)) {

        FundOutRequest fundOutRequest =
            new FundOutRequest(UUID.randomUUID().toString(), fundOrderId, otp);
        FundOutResponse fundOutResponse = walletEndpoint.confirmFundOutOtp(fundOutRequest);

        int code = fundOutResponse.getStatus().getCode();
        String value = fundOutResponse.getStatus().getValue();

        if (code == 0) {
          SessionUtil.setAttribute(
              MessageNotify.SESSION_MESSAGE_NOTIFY,
              new MessageNotify(MessageNotify.SUCCESS_CODE, MessageNotify.SUCCESS_NAME));
        } else {
          SessionUtil.setAttribute(
              MessageNotify.SESSION_MESSAGE_NOTIFY, new MessageNotify(code, value));
        }

        return REDIRECT_FUND_OUT_LIST;
      }
    } else if (BTN_CANCEL.equals(action)) {

    } else if (BTN_BACK.equals(action)) {

      putParameterToResponseFundOutOrder(request, model);
      model.put("amountLinkBank", request.getParameter("amountLinkBank"));
      model.put("styleDiv", "linked_bank");
      model.put("stepLinkBank", "request");
      model.put("fundOrderId", request.getParameter("fundOrderId"));

      return PAGE_REQUEST_FUND_OUT;
    }

    return REDIRECT_FUND_OUT_LIST;
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
      return walletEndpoint.getOTPConfirm(new GetOTPConfirmRequest(fundOrderId));
    }
    GetOTPConfirmResponse response = new GetOTPConfirmResponse();
    response.setStatus(new Status(MessageNotify.ERROR_CODE, "Error"));
    return response;
  }

  private void putParameterToResponseFundOutOrder(HttpServletRequest httpRequest, ModelMap model)
      throws FrontEndException {

    model.put("listBank", getFullBanks());
    //    model.put("customers", cacheDataUtil.getCustomersByBiz());

    // link bank
    FindCustomerBankDirectRequest fcbdRequest = new FindCustomerBankDirectRequest();
    FindCustomerBankDirectResponse fcbdResponse = walletEndpoint.findBankDirects(fcbdRequest);

    List<CustomerBankDirect> directBanks = fcbdResponse.getBankDirects();
    model.put("directBank", directBanks.isEmpty() ? new CustomerBankDirect() : directBanks.get(0));
    model.put("existLinkBank", !directBanks.isEmpty());

    // link bank tranfer
    model.put(
        "amountLinkBank", StringUtils.trimToEmpty(httpRequest.getParameter("amountLinkBank")));
    model.put("bankCode", StringUtils.trimToEmpty(httpRequest.getParameter("bankCode")));
    model.put(
        "bankAccountName", StringUtils.trimToEmpty(httpRequest.getParameter("bankAccountName")));
    model.put(
        "bankAccountNumber",
        StringUtils.trimToEmpty(httpRequest.getParameter("bankAccountNumber")));
    model.put("bankBranch", StringUtils.trimToEmpty(httpRequest.getParameter("bankBranch")));
    model.put("action", StringUtils.trimToEmpty(httpRequest.getParameter("action")));
  }

  private List<Bank> getFullBanks() {
    try {
      return walletEndpoint.findBanks(new FindBankRequest()).getBanks();
    } catch (FrontEndException e) {
      LOG.error(e.getMessage(), e);
      return Collections.emptyList();
    }
  }

  private List<BankProfile> getProfileBanks() {
    try {
      return walletEndpoint.getProfileBanks(new FindBankProfileRequest()).getBankProfiles();
    } catch (FrontEndException e) {
      LOG.error(e.getMessage(), e);
      return Collections.emptyList();
    }
  }
}
