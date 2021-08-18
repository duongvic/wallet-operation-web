package vn.mog.ewallet.operation.web.controller.transfer;

import static vn.mog.ewallet.operation.web.constant.RoleConstants.FINANCE_LEADER;
import static vn.mog.ewallet.operation.web.utils.ValidationUtil.MESAGE_ORDER_FLOW_APPROVE_PROCESS_ERROR;
import static vn.mog.ewallet.operation.web.utils.ValidationUtil.MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS;
import static vn.mog.ewallet.operation.web.utils.ValidationUtil.MESAGE_SUCCESS;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import vn.mog.ewallet.operation.web.constant.SessionConstants;
import vn.mog.ewallet.operation.web.constant.SharedConstants;
import vn.mog.ewallet.operation.web.contract.UserLogin;
import vn.mog.ewallet.operation.web.contract.form.CustomerDataForm;
import vn.mog.ewallet.operation.web.contract.form.TransferDataForm;
import vn.mog.ewallet.operation.web.controller.AbstractController;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.exception.MessageNotify;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindFullCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.beans.CustomerType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.WalletTransferOrderFlowStage;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.FindTransactionsRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.FindTransactionsResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.GetTransactionRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.GetTransactionResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.TxnStatus;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.CreateWalletTransferOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.CreateWalletTransferOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindWalletTransferOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.FindWalletTransferOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.GetWalletTransferOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.GetWalletTransferOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.UpdateWalletTransferOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.UpdateWalletTransferOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.Customer;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.WalletTransferOrder;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.beans.WalletTransferOrderAttachment;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowApproveRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowApproveResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowRejectRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowRejectResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowSubmitProcessRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.OrderFlowSubmitProcessResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.WalletTransferRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.wallet.flow.WalletTransferResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetOTPConfirmRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.GetOTPConfirmResponse;
import vn.mog.framework.common.utils.NumberUtil;
import vn.mog.framework.common.utils.SessionUtil;
import vn.mog.framework.common.utils.Utils;
import vn.mog.framework.contract.base.MobiliserResponseType.Status;

public class AbstractTransferController extends AbstractController {

  public static final String RENDER_PAGE_FUNDIN_SOF_STEP_ONE_REQUEST = "/transfer/fundin_sof/request";
  public static final String RENDER_PAGE_TRANSFER_WALLET_STEP_ONE_REQUEST = "/transfer/transfer_wallet/request";
  static final String RENDER_PAGE_FUNDIN_SOF_STEP_TWO_INITIATE = "/transfer/fundin_sof/initiate";
  static final String RENDER_PAGE_TRANSFER_WALLET_STEP_TWO_INITIATE = "/transfer/transfer_wallet/initiate";
  static final String RENDER_PAGE_FUNDIN_SOF_STEP_THREE_REVIEW = "/transfer/fundin_sof/review";
  static final String RENDER_PAGE_TRANSFER_WALLET_STEP_THREE_REVIEW = "/transfer/transfer_wallet/review";
  static final String RENDER_PAGE_FUNDIN_SOF_STEP_FOUR_APPROVE = "/transfer/fundin_sof/approve";
  static final String RENDER_PAGE_TRANSFER_WALLET_STEP_FOUR_APPROVE = "/transfer/transfer_wallet/approve";
  private static final String FILE_PHIEU_YEU_CAU_NAP_SOF = "/template/BM_AN_01.Phieu_yeu_cau_Nap_SOF.docx";
  private static final String FILE_PHIEU_YEU_CAU_CHUYEN_VI = "/template/BM_AN_02.Phieu_yeu_cau_chuyen_vi.docx";
  private static final String PHIEU_YEU_CAU_NAP_SOF = "BM_AN_01";
  private static final String PHIEU_YEU_CAU_CHUYEN_VI = "BM_AN_02";
  private static final Logger LOG = LogManager.getLogger(AbstractTransferController.class);
  private final String SOURCE_ACCOUNT_RQP = "sourceAccount";
  private final String TARGET_ACCOUNT_RQP = "targetAccount";
  private final String ORDER_STAGE_RQP = "orderStage";
  private final String ORDER_ID_RQP = "orderId";
  private final String AMOUNT_RQP = "amount";
  private final String SERVICE_TYPE_RQP = "serviceType";
  private final String REMARK_RQP = "remark";
  private MessageNotify messageNotify;
  private int messageCode = -1;
  private String messageValue = StringUtils.EMPTY;

  protected void searchTransferTrans(HttpServletRequest request, ModelMap map, List<String> serviceTypes, List<String> serviceCodes) throws FrontEndException {

    String paramSearch = request.getParameter("search");
    String paramDateRange = StringUtils.trimToEmpty(request.getParameter("range"));
    String[] paramTxnStatusIds = request.getParameterValues("txnStatusIds");

    String[] paramSourceMerchant = request.getParameterValues(SOURCE_ACCOUNT_RQP);
    String[] targetMerchant = request.getParameterValues(TARGET_ACCOUNT_RQP);

    Date[] dates = parseDateRange(paramDateRange, false);
    Date fromDate = dates[0];
    Date endDate = dates[1];

    int offset = 0;
    int limit = 20;
    if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
      offset = Integer.parseInt(request.getParameter("d-49489-p"));
      if (offset > 0) {
        offset = (offset - 1) * limit;
      }
    }

    FindTransactionsRequest ftRequest = new FindTransactionsRequest();
    ftRequest.setFromDate(fromDate);
    ftRequest.setEndDate(endDate);
    ftRequest.setTextSearch(paramSearch);
    ftRequest.setOffset(offset);
    ftRequest.setLimit(limit);
    ftRequest.setServiceTypeIds(serviceTypes);
    ftRequest.setServiceCodes(serviceCodes);

    if (paramTxnStatusIds != null && paramTxnStatusIds.length > 0 && StringUtils
        .isNotEmpty(paramTxnStatusIds[0])) {
      ftRequest.setStatusIds(NumberUtil.convertListToInt(paramTxnStatusIds));
    }

    if (paramSourceMerchant != null && paramSourceMerchant.length > 0 && StringUtils
        .isNotEmpty(paramSourceMerchant[0])) {
      ftRequest.setPayerIds(NumberUtil.convertListToLong(paramSourceMerchant));
    }

    if (targetMerchant != null && targetMerchant.length > 0 && StringUtils
        .isNotEmpty(targetMerchant[0])) {
      ftRequest.setPayeeIds(NumberUtil.convertListToLong(targetMerchant));
    }

    FindTransactionsResponse ftRespone = transactionEndpoint.findTransactions(ftRequest);
    List<Customer> sourceMerchants;
    List<Customer> targetMerchants;
    if (serviceTypes.get(0).equals(ServiceType.P2P_TRANSFER.name())) {
      sourceMerchants = getCustomers(CustomerType.ID_CUSTOMER, CustomerType.ID_MERCHANT);
      targetMerchants = getCustomers(CustomerType.ID_CUSTOMER, CustomerType.ID_MERCHANT);
    } else {
      sourceMerchants = getCustomers(CustomerType.ID_POOL, CustomerType.ID_ZOTA);
      targetMerchants = getCustomers(CustomerType.ID_SOF, CustomerType.ID_ZOTA);
    }

    map.put("sourceMerchants", sourceMerchants);
    map.put("targetMerchants", targetMerchants);
    map.put("transferTypes", WALLET_TRANSFER_STYPES);
    map.put("transactions", ftRespone.getTransactions());
    map.put("total", ftRespone.getTotalTxn().intValue());
    map.put("offset", offset);
    map.put("pagesize", limit);
    map.put("amountTransaction", NumberUtil.formatNumber(ftRespone.getTotalNetAmount()));
    map.put("txnStatuses", TxnStatus.TRANSACTION_STATUSES);
  }

  void movementHistoryDetail(HttpServletRequest request, ModelMap model) throws FrontEndException {

    Long txnId = NumberUtil.convertToLong(request.getParameter("txnId"));
    GetTransactionRequest gtRequest = new GetTransactionRequest(txnId);
    GetTransactionResponse gtResponse = transactionEndpoint.getTransaction(gtRequest);

    model.put("transaction", gtResponse.getTransaction());
    model.put("transactionEvents", gtResponse.getTransactionEvents());
  }

  void searchDataTransferRequest(HttpServletRequest request, ModelMap map, String serviceType) throws FrontEndException {
    int offset = 0;
    int limit = 20;
    String quickSearch = StringUtils.trimToEmpty(request.getParameter("search"));
    String paramDateRange = StringUtils.trimToEmpty(request.getParameter("range"));
    String[] paramPayerId = request.getParameterValues(SOURCE_ACCOUNT_RQP);
    String[] paramPayeeId = request.getParameterValues(TARGET_ACCOUNT_RQP);

    String[] status = request.getParameterValues("procces");

    FindWalletTransferOrderRequest fwtRequest = new FindWalletTransferOrderRequest();

    Date[] dates = parseDateRange(paramDateRange, true);
    fwtRequest.setFromDate(dates[0]);
    fwtRequest.setEndDate(dates[1]);

    if (StringUtils.isNumeric(request.getParameter("d-49489-p"))) {
      offset = Integer.parseInt(request.getParameter("d-49489-p"));
      if (offset > 0) {
        offset = (offset - 1) * limit;
      }
    }

    if (paramPayerId != null && paramPayerId.length > 0 && StringUtils
        .isNotEmpty(paramPayerId[0])) {
      fwtRequest.setPayerIds(NumberUtil.convertListToLong(paramPayerId));
    }

    if (paramPayeeId != null && paramPayeeId.length > 0 && StringUtils
        .isNotEmpty(paramPayeeId[0])) {
      fwtRequest.setPayeeIds(NumberUtil.convertListToLong(paramPayeeId));
    }

    if (status != null && status.length > 0 && StringUtils.isNotEmpty(status[0])) {
      fwtRequest.setStages(NumberUtil.convertListToInt(status));
    }

    fwtRequest.setServiceTypes(Collections.singletonList(serviceType));
    fwtRequest.setSearchText(quickSearch);
    fwtRequest.setLimit(limit);
    fwtRequest.setOffset(offset);

    FindWalletTransferOrderResponse fwtResponse = walletEndpoint
        .findWalletTransferOrder(fwtRequest);

    map.put("walletTransferOrders", fwtResponse.getOrders());
    map.put("total", fwtResponse.getTotal().intValue());
    map.put("totalAmount", fwtResponse.getTotalAmount());
    map.put("pagesize", limit);
    map.put("offset", offset);

    List<Customer> sourceMerchants = getCustomers(CustomerType.ID_POOL, CustomerType.ID_ZOTA);
    List<Customer> targetMerchants = getCustomers(CustomerType.ID_SOF, CustomerType.ID_ZOTA);
    map.put("walletTransferStages", WalletTransferOrderFlowStage.WALLET_TRANSFER_STAGES);
    map.put("sourceMerchants", sourceMerchants);
    map.put("targetMerchants", targetMerchants);
  }

  void getDetailTransferRequest(Long orderId, ModelMap model) throws FrontEndException {
    if (orderId > 0) {

      GetWalletTransferOrderRequest gwtRequest = new GetWalletTransferOrderRequest(orderId);
      GetWalletTransferOrderResponse gwtResponse = walletEndpoint
          .getWalletTransferOrder(gwtRequest);

      WalletTransferOrder walletOrder = gwtResponse.getOrder();

      model.put(ORDER_ID_RQP, walletOrder.getId());
      model.put(ORDER_STAGE_RQP,
          WalletTransferOrderFlowStage.WALLET_TRANSFER_STAGES.get(walletOrder.getStage()));
      model.put(SERVICE_TYPE_RQP, walletOrder.getServiceType());
      model.put(AMOUNT_RQP, (walletOrder.getAmount()));
      model.put(REMARK_RQP, walletOrder.getInfo());
      List<WalletTransferOrderAttachment> attachments = gwtResponse.getAttachments();
      if (attachments != null && !attachments.isEmpty()) {
        model.put("attachments", attachments);
        model.put("attachmentName", attachments.get(0).getName());
      }
      model.put(SOURCE_ACCOUNT_RQP, walletOrder.getPayerUsername());
      model.put(TARGET_ACCOUNT_RQP, walletOrder.getPayeeUsername());
      model.put("creatorName", walletOrder.getCreatorUsername());
    }
  }


  void requestTransfer(HttpServletRequest request, ModelMap model) throws FrontEndException {
    // transfer oder , transfer oder attachment
    Long orderId = NumberUtil.convertToLong(request.getParameter(ORDER_ID_RQP));
    if (orderId > 0) {

      GetWalletTransferOrderRequest gwtRequest = new GetWalletTransferOrderRequest(orderId);
      GetWalletTransferOrderResponse gwtResponse = walletEndpoint
          .getWalletTransferOrder(gwtRequest);

      WalletTransferOrder walletOrder = gwtResponse.getOrder();
      Integer stage = walletOrder.getStage();

      if (stage == WalletTransferOrderFlowStage.OPERATION_INIT
          || stage == WalletTransferOrderFlowStage.FINANCE_REJECTED) {

        model.put(ORDER_ID_RQP, walletOrder.getId());
        model.put(REMARK_RQP, walletOrder.getInfo());
        model.put(ORDER_STAGE_RQP, stage);
        List<WalletTransferOrderAttachment> attachments = gwtResponse.getAttachments();
        if (!attachments.isEmpty()) {
          model.put("attachments", attachments);
          model.put("attachmentName", attachments.get(0).getName());
        }
      }
    }
  }


  String requestTransferPost(final TransferDataForm transferData,
      HttpServletRequest request,
      ModelMap model)
      throws FrontEndException, IOException {

    String action = transferData.getAction();
    long orderId = transferData.getOrderId();
    String remark = transferData.getRemark();
    int orderStage = transferData.getOrderStage();
    String serviceType = transferData.getTransferType();

    if (BTN_SAVE.equals(action) || BTN_SUBMIT.equals(action)) {
      if (orderId > 0) {

        WalletTransferOrder walletOrder = new WalletTransferOrder(orderId, remark);
        List<WalletTransferOrderAttachment> attachments = getUploadAttachments(request,
            new WalletTransferOrderAttachment());

        UpdateWalletTransferOrderRequest uwtRequest = new UpdateWalletTransferOrderRequest(
            walletOrder, attachments);
        UpdateWalletTransferOrderResponse uwtResponse = walletEndpoint
            .updateWalletTransferOrder(uwtRequest);

        if (uwtResponse.getStatus().getCode() == MessageNotify.SUCCESS_CODE && BTN_SUBMIT
            .equals(action)) {

          if (orderStage == WalletTransferOrderFlowStage.FINANCE_REJECTED) {
            OrderFlowSubmitProcessRequest ofspRequest = new OrderFlowSubmitProcessRequest(orderId,
                remark);
            OrderFlowSubmitProcessResponse ofspResponse = walletEndpoint
                .orderFlowSubmitProccess(ofspRequest);
            messageCode = ofspResponse.getStatus().getCode();
            messageValue = ofspResponse.getStatus().getValue();

          } else if (orderStage == WalletTransferOrderFlowStage.OPERATION_INIT) {
            OrderFlowApproveRequest ofaRequest = new OrderFlowApproveRequest(orderId, remark);
            OrderFlowApproveResponse ofaResponse = walletEndpoint
                .approveWalletTransferOrder(ofaRequest);
            messageCode = ofaResponse.getStatus().getCode();
            messageValue = ofaResponse.getStatus().getValue();
          } else {
            messageCode = -1;

          }

          if (messageCode == MessageNotify.SUCCESS_CODE) {
            messageNotify = new MessageNotify(MessageNotify.SUCCESS_CODE,
                MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
          } else {
            messageNotify = new MessageNotify(MessageNotify.ERROR_CODE, messageValue);
          }
        } else if (uwtResponse.getStatus().getCode() == MessageNotify.SUCCESS_CODE) {
          messageNotify = new MessageNotify(MessageNotify.SUCCESS_CODE, MESAGE_SUCCESS);

        } else if (uwtResponse.getStatus().getCode() == MessageNotify.ERROR_CODE) {
          rendPageRequestTransfer(model, transferData, uwtResponse.getStatus().getValue());
          return transferData.renderStepOneRequest();
        }

      } else {

        WalletTransferOrder walletOrder = new WalletTransferOrder(serviceType, remark);
        List<WalletTransferOrderAttachment> attachments = getUploadAttachments(request,
            new WalletTransferOrderAttachment());

        CreateWalletTransferOrderRequest cwtRequest = new CreateWalletTransferOrderRequest(
            walletOrder, attachments);
        CreateWalletTransferOrderResponse cwtResponse = walletEndpoint
            .createWalletTransferOrder(cwtRequest);

        if (cwtResponse.getStatus().getCode() == MessageNotify.SUCCESS_CODE && BTN_SUBMIT
            .equals(action)) {

          OrderFlowApproveRequest ofaRequest = new OrderFlowApproveRequest(cwtResponse.getOrderId(),
              remark);
          OrderFlowApproveResponse ofaResponse = walletEndpoint
              .approveWalletTransferOrder(ofaRequest);

          if (ofaResponse.getStatus().getCode() == MessageNotify.SUCCESS_CODE) {
            messageNotify = new MessageNotify(MessageNotify.SUCCESS_CODE,
                MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
          } else {
            messageNotify = new MessageNotify(MessageNotify.ERROR_CODE,
                ofaResponse.getStatus().getValue());
          }
        } else if (cwtResponse.getStatus().getCode() == MessageNotify.SUCCESS_CODE) {
          messageNotify = new MessageNotify(MessageNotify.SUCCESS_CODE, MESAGE_SUCCESS);
        } else {
          rendPageRequestTransfer(model, transferData,
              cwtResponse.getStatus().getValue());
          return transferData.renderStepOneRequest();
        }
      }
    }
    SessionUtil.setAttribute(MessageNotify.SESSION_MESSAGE_NOTIFY, messageNotify);
    return transferData.redirectTransferUri();
  }


  String orderInitiate(HttpServletRequest request, ModelMap model, String serviceType) throws FrontEndException {

    Long orderId = NumberUtil.convertToLong(request.getParameter(ORDER_ID_RQP));

    if (orderId > 0) {
      GetWalletTransferOrderRequest gwtRequest = new GetWalletTransferOrderRequest(orderId);
      GetWalletTransferOrderResponse gwtResponse = walletEndpoint
          .getWalletTransferOrder(gwtRequest);
      WalletTransferOrder walletOrder = gwtResponse.getOrder();

      if (walletOrder.getStage() == WalletTransferOrderFlowStage.FINANCE_READY_TO_VERIFIED
          || walletOrder.getStage() == WalletTransferOrderFlowStage.FINANCE_LEADER_REJECTED) {

        model.put(ORDER_ID_RQP, orderId);
        model.put(ORDER_STAGE_RQP, walletOrder.getStage());
        model.put(AMOUNT_RQP, NumberUtil.formatNumberComma(walletOrder.getAmount()));
        model.put(REMARK_RQP, walletOrder.getInfo());
        model.put(SERVICE_TYPE_RQP, serviceType);
        model.put("payerId", walletOrder.getPayerId());
        model.put("payeeId", walletOrder.getPayeeId());

        List<WalletTransferOrderAttachment> attachments = gwtResponse.getAttachments();
        if (attachments != null && !attachments.isEmpty()) {
          model.put("attachments", attachments);
          model.put("attachmentName", attachments.get(0).getName());
        }

        if (ServiceType.FUND_TRANSFER.name().equals(serviceType) || serviceType
            .equals(StringUtils.EMPTY)) {

          model.put(SOURCE_ACCOUNT_RQP, getCustomers(CustomerType.ID_POOL));
          model.put(TARGET_ACCOUNT_RQP, getCustomers(CustomerType.ID_SOF));

        } else if (ServiceType.WALLET_TRANSFER.name().equals(serviceType)) {

          List<Customer> customers = getCustomers(CustomerType.ID_ZOTA);
          model.put(SOURCE_ACCOUNT_RQP, customers);
          model.put(TARGET_ACCOUNT_RQP, customers);
        }

        return pageStepTwoMovementProcess(serviceType);
      }
    }

    return TransferDataForm.redirectTransferUri(serviceType);
  }

  private String pageStepTwoMovementProcess(String serviceType) {
    return ServiceType.FUND_TRANSFER.name().equals(serviceType) ?
        RENDER_PAGE_FUNDIN_SOF_STEP_TWO_INITIATE :
        RENDER_PAGE_TRANSFER_WALLET_STEP_TWO_INITIATE;
  }


  String orderInitiatePost(HttpServletRequest request, ModelMap model, String serviceType)
      throws FrontEndException {

    String action = request.getParameter("action");

    Long orderId = NumberUtil.convertToLong(request.getParameter(ORDER_ID_RQP));
    int orderStage = NumberUtil.convertToInt(request.getParameter(ORDER_STAGE_RQP));
    String remark = request.getParameter(REMARK_RQP);
    remark = (remark != null) ? remark.trim() : StringUtils.EMPTY;

    if (BTN_REJECT.equals(action)) {

      OrderFlowRejectRequest ofrRequest = new OrderFlowRejectRequest(orderId, remark);
      OrderFlowRejectResponse ofrResponse = walletEndpoint.rejectWalletTransferOrder(ofrRequest);
      if (ofrResponse.getStatus().getCode() == MessageNotify.SUCCESS_CODE) {
        messageNotify = new MessageNotify(MessageNotify.SUCCESS_CODE,
            MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
      } else {
        messageNotify = new MessageNotify(MessageNotify.ERROR_CODE,
            ofrResponse.getStatus().getValue());
      }

    } else if (BTN_SAVE.equals(action) || BTN_SUBMIT.equals(action)) {
      // save or submit

      String typeTransfer = request.getParameter("typeTransfer");
      Long sourceAccount = NumberUtil.convertToLong(request.getParameter(SOURCE_ACCOUNT_RQP));
      Long targetAccount = NumberUtil.convertToLong(request.getParameter(TARGET_ACCOUNT_RQP));
      String amount = StringUtils.trimToEmpty(request.getParameter(AMOUNT_RQP));
      Long amountNumber = NumberUtil.convertToLong(amount.replaceAll(",", StringUtils.EMPTY));

      validation.validateOrderInitiate();// TODO draf

      WalletTransferOrder order = new WalletTransferOrder();
      order.setId(orderId);
      order.setInfo(remark);
      order.setServiceType(serviceType);
      order.setPayerId(sourceAccount);
      order.setPayeeId(targetAccount);
      order.setAmount(amountNumber);

      UpdateWalletTransferOrderRequest uwtRequest = new UpdateWalletTransferOrderRequest(order);
      UpdateWalletTransferOrderResponse uwtResponse = walletEndpoint
          .updateWalletTransferOrder(uwtRequest);

      if (uwtResponse.getStatus().getCode() == MessageNotify.SUCCESS_CODE && BTN_SUBMIT
          .equals(action)) {

        if (orderStage == WalletTransferOrderFlowStage.FINANCE_LEADER_REJECTED) {
          OrderFlowSubmitProcessRequest ofspRequest = new OrderFlowSubmitProcessRequest(orderId,
              remark);
          OrderFlowSubmitProcessResponse ofspResponse = walletEndpoint
              .orderFlowSubmitProccess(ofspRequest);
          messageCode = ofspResponse.getStatus().getCode();
          messageValue = ofspResponse.getStatus().getValue();
        } else if (orderStage == WalletTransferOrderFlowStage.FINANCE_READY_TO_VERIFIED) {
          OrderFlowApproveRequest ofaRequest = new OrderFlowApproveRequest(orderId, remark);
          OrderFlowApproveResponse ofaResponse = walletEndpoint
              .approveWalletTransferOrder(ofaRequest);
          messageCode = ofaResponse.getStatus().getCode();
          messageValue = ofaResponse.getStatus().getValue();
        } else {
          messageCode = -1;
        }

        if (messageCode == MessageNotify.SUCCESS_CODE) {
          messageNotify = new MessageNotify(MessageNotify.SUCCESS_CODE,
              MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
        } else {
          messageNotify = new MessageNotify(MessageNotify.ERROR_CODE, messageValue);
        }
      } else if (uwtResponse.getStatus().getCode() == MessageNotify.SUCCESS_CODE) {
        messageNotify = new MessageNotify(MessageNotify.SUCCESS_CODE,
            MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
      } else {
        messageNotify = new MessageNotify(MessageNotify.ERROR_CODE,
            uwtResponse.getStatus().getValue());
      }
    }
    SessionUtil.setAttribute(MessageNotify.SESSION_MESSAGE_NOTIFY, messageNotify);
    return TransferDataForm.redirectTransferUri(serviceType);
  }


  String orderReview(HttpServletRequest request, ModelMap model, String serviceType) throws FrontEndException {

    Long orderId = NumberUtil.convertToLong(request.getParameter(ORDER_ID_RQP));
    GetWalletTransferOrderRequest gwtRequest = new GetWalletTransferOrderRequest(orderId);
    GetWalletTransferOrderResponse gwtResponse = walletEndpoint.getWalletTransferOrder(gwtRequest);
    WalletTransferOrder walletOrder = gwtResponse.getOrder();
    if ((walletOrder.getStage() == WalletTransferOrderFlowStage.FINANCE_LEADER_READY_TO_REVIEWED
        || walletOrder.getStage() == WalletTransferOrderFlowStage.FINANCE_MANAGER_REJECTED)) {

      model.put(ORDER_ID_RQP, walletOrder.getId());
      model.put(ORDER_STAGE_RQP, walletOrder.getStage());
      model.put(SERVICE_TYPE_RQP, walletOrder.getServiceType());
      model.put(AMOUNT_RQP, (walletOrder.getAmount()));
      model.put(REMARK_RQP, walletOrder.getInfo());
      List<WalletTransferOrderAttachment> attachments = gwtResponse.getAttachments();
      if (attachments != null && !attachments.isEmpty()) {
        model.put("attachments", attachments);
        model.put("attachmentName", attachments.get(0).getName());
      }
      model.put(SOURCE_ACCOUNT_RQP, walletOrder.getPayerUsername());
      model.put(TARGET_ACCOUNT_RQP, walletOrder.getPayeeUsername());
      model.put("creatorName", walletOrder.getCreatorUsername());

      return pageStepThreeMovementProcess(serviceType);
    }

    SessionUtil.setAttribute(MessageNotify.SESSION_MESSAGE_NOTIFY, messageNotify);
    return TransferDataForm.redirectTransferUri(serviceType);
  }

  private String pageStepThreeMovementProcess(String serviceType) {
    return ServiceType.FUND_TRANSFER.name().equals(serviceType) ?
        RENDER_PAGE_FUNDIN_SOF_STEP_THREE_REVIEW :
        RENDER_PAGE_TRANSFER_WALLET_STEP_THREE_REVIEW;
  }


  String orderReviewPost(HttpServletRequest request, ModelMap model, String serviceType) throws FrontEndException {

    Long orderId = NumberUtil.convertToLong(request.getParameter(ORDER_ID_RQP));
    int orderStage = NumberUtil.convertToInt(request.getParameter(ORDER_STAGE_RQP));
    String remark = request.getParameter(REMARK_RQP);

    String action = request.getParameter("action");
    if (BTN_APPROVE.equals(action)) {

      if (orderId > 0
          && orderStage == WalletTransferOrderFlowStage.FINANCE_LEADER_READY_TO_REVIEWED) {
        OrderFlowApproveRequest ofaRequest = new OrderFlowApproveRequest(orderId, remark);
        OrderFlowApproveResponse ofaResponse = walletEndpoint
            .approveWalletTransferOrder(ofaRequest);
        messageCode = ofaResponse.getStatus().getCode();
        messageValue = ofaResponse.getStatus().getValue();
      } else if (orderId > 0
          && orderStage == WalletTransferOrderFlowStage.FINANCE_MANAGER_REJECTED) {
        OrderFlowSubmitProcessRequest ofspRequest = new OrderFlowSubmitProcessRequest(orderId,
            remark);
        OrderFlowSubmitProcessResponse ofspResponse = walletEndpoint
            .orderFlowSubmitProccess(ofspRequest);
        messageCode = ofspResponse.getStatus().getCode();
        messageValue = ofspResponse.getStatus().getValue();
      } else {
        messageCode = -1;
      }

      if (messageCode == MessageNotify.SUCCESS_CODE) {
        messageNotify = new MessageNotify(MessageNotify.SUCCESS_CODE,
            MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
      } else {
        messageNotify = new MessageNotify(MessageNotify.ERROR_CODE, messageValue);
      }

    } else if (BTN_REJECT.equals(action)) {

      messageNotify = rejectWalletTransferProcess(orderId, remark);

    }
    SessionUtil.setAttribute(MessageNotify.SESSION_MESSAGE_NOTIFY, messageNotify);
    return TransferDataForm.redirectTransferUri(serviceType);
  }


  String orderApprove(HttpServletRequest request, ModelMap model, String serviceType) throws FrontEndException {

    Long orderId = NumberUtil.convertToLong(request.getParameter(ORDER_ID_RQP));
    if (orderId > 0) {
      GetWalletTransferOrderRequest gwtRequest = new GetWalletTransferOrderRequest(orderId);
      GetWalletTransferOrderResponse gwtResponse = walletEndpoint
          .getWalletTransferOrder(gwtRequest);
      WalletTransferOrder walletOrder = gwtResponse.getOrder();

      if (walletOrder.getStage()
          == WalletTransferOrderFlowStage.FINANCE_MANAGER_READY_TO_APPROVED) {

        UserLogin userLogin = (UserLogin) SessionUtil
            .getAttribute(SessionConstants.SESSION_ACCOUNT_LOGIN);
        Set<String> roles = userLogin.getRoles();
        boolean hasFinanceLead = roles.contains(FINANCE_LEADER);
        Long amount = walletOrder.getAmount();

        if (hasFinanceLead && (serviceType.equals(ServiceType.FUND_TRANSFER.name())
            && amount < SharedConstants.TRANSFER_FUND_MAX_MONEY
            || serviceType.equals(ServiceType.WALLET_TRANSFER.name())
            && amount < SharedConstants.TRANSFER_WALLET_MAX_MONEY)) {
          // nothing
        } else if (hasFinanceLead && (serviceType.equals(ServiceType.FUND_TRANSFER.name())
            && amount >= SharedConstants.TRANSFER_FUND_MAX_MONEY
            || serviceType.equals(ServiceType.WALLET_TRANSFER.name())
            && amount >= SharedConstants.TRANSFER_WALLET_MAX_MONEY)) {
          return TransferDataForm.redirectTransferUri(serviceType);
        }

        model.put(ORDER_ID_RQP, walletOrder.getId());
        model.put(SERVICE_TYPE_RQP, serviceType);
        model.put(AMOUNT_RQP, amount);
        model.put(REMARK_RQP, walletOrder.getInfo());
        List<WalletTransferOrderAttachment> attachments = gwtResponse.getAttachments();
        if (attachments != null && !attachments.isEmpty()) {
          model.put("attachments", attachments);
          model.put("attachmentName", attachments.get(0).getName());
        }
        model.put(SOURCE_ACCOUNT_RQP, walletOrder.getPayerUsername());
        model.put(TARGET_ACCOUNT_RQP, walletOrder.getPayeeUsername());
        model.put("creatorName", walletOrder.getCreatorUsername());

        return pageStepFourMovementProcess(serviceType);
      }

    }

    SessionUtil.setAttribute(MessageNotify.SESSION_MESSAGE_NOTIFY, messageNotify);
    return TransferDataForm.redirectTransferUri(serviceType);
  }

  private String pageStepFourMovementProcess(String serviceType) {
    return ServiceType.FUND_TRANSFER.name().equals(serviceType) ?
        RENDER_PAGE_FUNDIN_SOF_STEP_FOUR_APPROVE :
        RENDER_PAGE_TRANSFER_WALLET_STEP_FOUR_APPROVE;
  }

  String orderApprovePost(HttpServletRequest request, ModelMap model, String serviceType) throws FrontEndException {

    Long orderId = NumberUtil.convertToLong(request.getParameter(ORDER_ID_RQP));
    String remark = request.getParameter(REMARK_RQP);
    String action = request.getParameter("action");

    if (BTN_APPROVE.equals(action)) {

      WalletTransferOrder wallOrder = new WalletTransferOrder(orderId, remark);
      UpdateWalletTransferOrderRequest uwtRequest = new UpdateWalletTransferOrderRequest(wallOrder);
      walletEndpoint.updateWalletTransferOrder(uwtRequest);

      GetOTPConfirmRequest otpRequest = new GetOTPConfirmRequest(orderId);
      GetOTPConfirmResponse otpResponse = walletEndpoint
          .getWalletTransferOrderOTPConfirm(otpRequest);

      if (otpResponse.getStatus().getCode() == MessageNotify.SUCCESS_CODE) {
        messageNotify = new MessageNotify(MessageNotify.SUCCESS_CODE,
            MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
        model.put(ORDER_ID_RQP, orderId.intValue());
        model.put("perOtp", true);
        model.put("sentPhone", "demo");
        model.put("codeOtp", "1234");
        model.put("waiting", "1234");

        return "/transfer/transfer_otp";
      } else {
        messageNotify = new MessageNotify(MessageNotify.ERROR_CODE,
            otpResponse.getStatus().getValue());
      }

    } else if (BTN_REJECT.equals(action)) {

      messageNotify = rejectWalletTransferProcess(orderId, remark);

    }

    SessionUtil.setAttribute(MessageNotify.SESSION_MESSAGE_NOTIFY, messageNotify);
    return TransferDataForm.redirectTransferUri(serviceType);
  }

  // Giám sát kế toán/ Giám đốc kế toán duyệt giao dịch
  String orderOTPPost(HttpServletRequest request, ModelMap model, String serviceType) throws FrontEndException {

    Long orderId = NumberUtil.convertToLong(request.getParameter(ORDER_ID_RQP));
    String action = request.getParameter("action");
    String otp = request.getParameter("otp");

    if (BTN_CONFIRM_OTP.equals(action) && orderId > 0 && StringUtils.isNotBlank(otp)) {

      WalletTransferRequest confirmOtpRequest = new WalletTransferRequest(orderId, otp);
      WalletTransferResponse confirmOtpResponse = walletEndpoint.orderConfirmOTP(confirmOtpRequest);

      if (confirmOtpResponse.getStatus().getCode() == MessageNotify.SUCCESS_CODE) {
        messageNotify = new MessageNotify(MessageNotify.SUCCESS_CODE,
            MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
      } else if (confirmOtpResponse.getStatus().getCode() == 2062) {
        GetOTPConfirmRequest otpRequest = new GetOTPConfirmRequest(orderId);
        GetOTPConfirmResponse otpResponse = walletEndpoint
            .getWalletTransferOrderOTPConfirm(otpRequest);

        if (otpResponse.getStatus().getCode() == MessageNotify.SUCCESS_CODE) {
          model.put(MessageNotify.codeErr, 1);
          model.put(MessageNotify.mesErr, "Invalid OTP! please re-comfirm OTP");
          model.put(ORDER_ID_RQP, orderId.intValue());
          model.put("perOtp", true);
          model.put("sentPhone", "demo");
          model.put("codeOtp", "1234");
          model.put("waiting", "1234");
          return "/transfer/transfer_otp";
        }
      } else {
        messageNotify = new MessageNotify(MessageNotify.ERROR_CODE,
            confirmOtpResponse.getStatus().getValue());
      }
    } else {
      messageNotify = new MessageNotify(MessageNotify.ERROR_CODE,
          MESAGE_ORDER_FLOW_APPROVE_PROCESS_ERROR);
    }
    SessionUtil.setAttribute(MessageNotify.SESSION_MESSAGE_NOTIFY, messageNotify);
    return TransferDataForm.redirectTransferUri(serviceType);
  }


  ResponseEntity<?> findCustomerByTypeWallet(HttpServletRequest request) throws FrontEndException {

    JSONObject jObject = new JSONObject();
    String typeWallet = request.getParameter("typeWallet");
    if ("fund".equals(typeWallet)) {

      List<Customer> sourceCustomers = getCustomers(CustomerType.ID_POOL);
      List<Customer> desCustomers = getCustomers(CustomerType.ID_SOF);
      try {
        jObject.put("sourceCustomers", Utils.objectToJson(convertCustomers(sourceCustomers)));
        jObject.put("desCustomers", Utils.objectToJson(convertCustomers(desCustomers)));
      } catch (JSONException e) {
        e.printStackTrace();
      }

    } else if ("wallet".equals(typeWallet)) {

      List<Customer> sourceCustomers = getCustomers(CustomerType.ID_ZOTA);
      List<Customer> desCustomers = sourceCustomers;
      try {
        jObject.put("sourceCustomers", Utils.objectToJson(convertCustomers(sourceCustomers)));
        jObject.put("desCustomers", Utils.objectToJson(convertCustomers(desCustomers)));
      } catch (JSONException e) {
        LOG.error("", e);
      }
    }

    return ResponseEntity.ok(jObject.toString());
  }

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

  void getFile(String fileName, HttpServletResponse response) throws IOException {

    try {
      String contentType = StringUtils.EMPTY;
      InputStream inputStream = null;
      String extensionFile = StringUtils.EMPTY;

      if (PHIEU_YEU_CAU_NAP_SOF.equals(fileName)) {
        inputStream = new ClassPathResource(FILE_PHIEU_YEU_CAU_NAP_SOF).getInputStream();
        contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        extensionFile = "docx";
      } else if (PHIEU_YEU_CAU_CHUYEN_VI.equals(fileName)) {
        inputStream = new ClassPathResource(FILE_PHIEU_YEU_CAU_CHUYEN_VI).getInputStream();
        contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        extensionFile = "docx";
      }

      response.addHeader("Content-disposition",
          "attachment;filename=" + fileName + "." + extensionFile);
      response.setContentType(contentType);

      // Copy the stream to the response's output stream.
      IOUtils.copy(inputStream, response.getOutputStream());
      response.flushBuffer();

    } catch (IOException e) {
      LOG.error("Error writing file to output stream. Filename was '{}'" + fileName, e);
      throw new IllegalArgumentException("IOError writing file to output stream");
    }
  }

  private List<CustomerDataForm> convertCustomers(List<Customer> customers) {
    try {
      List<CustomerDataForm> customerDataForms = new ArrayList<>();
      CustomerDataForm customerDataForm;
      for (Customer item : customers) {
        customerDataForm = new CustomerDataForm(item);
        customerDataForms.add(customerDataForm);
      }
      return customerDataForms;
    } catch (Exception e) {
      return Collections.emptyList();
    }
  }

  private MessageNotify rejectWalletTransferProcess(Long orderId, String remark) {
    OrderFlowRejectRequest ofrRequest = new OrderFlowRejectRequest(orderId, remark);
    OrderFlowRejectResponse ofrResponse = walletEndpoint.rejectWalletTransferOrder(ofrRequest);

    if (ofrResponse.getStatus().getCode() == MessageNotify.SUCCESS_CODE) {
      return new MessageNotify(MessageNotify.SUCCESS_CODE,
          MESAGE_ORDER_FLOW_APPROVE_PROCESS_SUCCESS);
    } else {
      return new MessageNotify(MessageNotify.ERROR_CODE, ofrResponse.getStatus().getValue());
    }
  }

  private void rendPageRequestTransfer(ModelMap model, TransferDataForm transferData, String messageValue) {
    model.put(ORDER_ID_RQP, transferData.getOrderId());
    model.put(ORDER_STAGE_RQP, transferData.getOrderStage());
    model.put(REMARK_RQP, transferData.getRemark());
    model.put(MessageNotify.codeErr, MessageNotify.ERROR_CODE);
    model.put(MessageNotify.mesErr, messageValue);
  }

  protected List<Customer> getCustomers(
      Integer... customerTypeIds) {
    try {
      FindFullCustomerRequest customerRequest = new FindFullCustomerRequest();
      customerRequest.setCustomerTypes(Arrays.asList(customerTypeIds));
      return paymentCustomerEndpoint.findCustomers(customerRequest).getCustomers();
    } catch (Exception e) {
      return Collections.emptyList();
    }
  }

}