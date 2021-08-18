package vn.mog.ewallet.operation.web.controller.translog;


import static vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.TransactionReversalOrderFlowStage.STATES_REVERSAL_TYPES;
import static vn.mog.framework.common.utils.NumberUtil.convertListToInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.jexl2.UnifiedJEXL.Exception;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.mog.ewallet.operation.web.constant.RoleConstants;
import vn.mog.ewallet.operation.web.contract.form.SearchDataForm;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.ITransactionEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.type.ServiceType;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.CreateTransactionReversalOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.CreateTransactionReversalOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.FindTransactionReversalOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.FindTransactionReversalOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.GetTransactionReversalOrderRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.GetTransactionReversalOrderResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.TransactionRevertFlowApproveRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.TransactionRevertFlowApproveResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.TransactionRevertFlowRejectRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.TransactionRevertFlowRejectResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.beans.TransactionCancel;
import vn.mog.framework.common.utils.NumberUtil;


@Controller
@RequestMapping(value = "/service/reversal-history")
public class TransactionReversalController extends AbstractTransactionController {

  public static final String TRANS_REVERSAL_CONTROLLER = "/service/reversal-history";
  public static final String TRANS_REVERSAL_LIST = TRANS_REVERSAL_CONTROLLER + "/list";
  public static final String TRANS_REVERSAL_DETAIL = TRANS_REVERSAL_CONTROLLER + "/detail";

  public static final String TRANS_REVERSAL_LIST_TXN_REQUEST =
      TRANS_REVERSAL_CONTROLLER + "/list-reversal-TxnRequest";

  public static final String TRANS_REVERSAL_SUBMIT = TRANS_REVERSAL_CONTROLLER + "/request/submit";
  private static final Logger logger = LogManager.getLogger(TransactionLogController.class);

  @Autowired
  ITransactionEndpoint transactionEndpoint;

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @PreAuthorize(
      "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.CUSTOMERCARE_MANAGER
          + "','"
          + RoleConstants.CUSTOMERCARE + "', '" + RoleConstants.FINANCE + "' ,'"
          + RoleConstants.FINANCE_SUPPORT + "' , '" + RoleConstants.FINANCESUPPORT_LEADER + "', '"
          + RoleConstants.FINANCE_LEADER + "' , '"+RoleConstants.RECONCILIATION_LEADER+"' , '"+RoleConstants.RECONCILIATION+"')")
  public String search(HttpServletRequest httpRequest,HttpServletResponse response, ModelMap map,
      @ModelAttribute SearchDataForm searchDataForm) throws FrontEndException {
    try {
//      FindTransactionReversalOrderRequest findReversalRequest = new FindTransactionReversalOrderRequest();
//      int limit = 20;
//      int offset = 0;
//      String page = httpRequest.getParameter("d-49520-p");
//      if (page == null) {
//        page = httpRequest.getParameter("d-49489-p");
//      }
//      if (page != null && page.length() > 0) {
//        Integer p = Integer.parseInt(page);
//        offset = limit * (p - 1);
//      }
//
//      String paramQuickSearch = httpRequest.getParameter("quickSearch");
//      String paramDateRange = httpRequest.getParameter("range");
//      String[] paramServiceTypeIds = httpRequest.getParameterValues("serviceTypeIds");
//      String[] paramStatusIds = httpRequest.getParameterValues("status");
//
//      findReversalRequest.setTextSearch(paramQuickSearch);
//
//      Date[] dates = parseDateRange(paramDateRange, true);
//      findReversalRequest.setFromDate(dates[0]);
//      findReversalRequest.setEndDate(dates[1]);
//
//      if (paramServiceTypeIds != null && paramServiceTypeIds.length > 0 && StringUtils
//          .isNotEmpty(paramServiceTypeIds[0])) {
//        findReversalRequest.setServiceTypeIds(Arrays.asList(paramServiceTypeIds));
//      }
//
//      if (paramStatusIds != null && paramStatusIds.length > 0 && StringUtils
//          .isNotEmpty(paramStatusIds[0])) {
//        List<Integer> statusList = new ArrayList<>();
//        statusList.add(Integer.parseInt(String.valueOf(paramStatusIds)));
//        findReversalRequest.setStageIds(statusList);
//      }
//
//      findReversalRequest.setLimit(limit);
//      findReversalRequest.setOffset(offset);
//
//      FindTransactionReversalOrderResponse response = transactionEndpoint
//          .findOrderReversal(findReversalRequest);
//
//      map.put("transCancels", response.getTransactionReversalOrders());
//      map.put("total", Integer.valueOf(Math.toIntExact(response.getTotalTxn())));
//      map.put("pagesize", limit);
//      map.put("offset", offset);
//      map.put("statuses", TransactionCancel.Status.list());
//      map.put("processes", TransactionCancel.Process.list());
//      map.put("serviceTypes", ServiceType.PAYMENT_TYPES);
//      map.put("totalRequestAmount", response.getTotalRequestAmount());
//      map.put("totalNetAmount", response.getTotalNetAmount());


      if (searchDataForm.getServiceTypeIds() == null || searchDataForm.getServiceTypeIds().isEmpty()) {
        List<String> stringListService = new ArrayList<>();
        stringListService.add(String.valueOf(ServiceType.TXN_REVERSAL));
        searchDataForm.setServiceTypeIds(stringListService);
      }
      searchCommonTrans(httpRequest, response, map, searchDataForm);
      map.put("serviceTypes", ServiceType.WALLET_SERVICE_TYPES);
    } catch (Exception ex) {
      logger.error(ex.getMessage());
    }

      return "/transaction_reversal/transaction-reversal";
    }

    @RequestMapping(value = "/list-reversal-TxnRequest", method = RequestMethod.GET)
    @PreAuthorize(
        "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','" + RoleConstants.CUSTOMERCARE_MANAGER
            + "','"
            + RoleConstants.CUSTOMERCARE + "', '" + RoleConstants.FINANCE + "' ,'"
            + RoleConstants.FINANCE_SUPPORT + "' , '" + RoleConstants.FINANCESUPPORT_LEADER + "', '"
            + RoleConstants.FINANCE_LEADER + "' , '"+RoleConstants.RECONCILIATION_LEADER+"' , '"+RoleConstants.RECONCILIATION+"')")
    public String searchReversalTxnRequest (HttpServletRequest httpRequest, ModelMap map)
      throws FrontEndException {
      try {
        FindTransactionReversalOrderRequest findReversalRequest = new FindTransactionReversalOrderRequest();

        int offset = 0;
        int limit = 20;

        if (StringUtils.isNumeric(httpRequest.getParameter("d-49489-p"))) {
          offset = Integer.parseInt(httpRequest.getParameter("d-49489-p"));
          if (offset > 0) {
            offset = (offset - 1) * limit;
          }
        }

        String paramQuickSearch = httpRequest.getParameter("quickSearch");
        String paramDateRange = httpRequest.getParameter("range");
        String[] paramServiceTypeIds = httpRequest.getParameterValues("serviceTypeIds");
        String[] paramStatesIds = httpRequest.getParameterValues("states");

        findReversalRequest.setTextSearch(paramQuickSearch);

        Date[] dates = parseDateRange(paramDateRange, true);
        findReversalRequest.setFromDate(dates[0]);
        findReversalRequest.setEndDate(dates[1]);

        if (paramServiceTypeIds != null && paramServiceTypeIds.length > 0 && StringUtils
            .isNotEmpty(paramServiceTypeIds[0])) {
          findReversalRequest.setServiceTypeIds(Arrays.asList(paramServiceTypeIds));
        }

        if (paramStatesIds != null && paramStatesIds.length > 0 && StringUtils
            .isNotEmpty(paramStatesIds[0])) {
          findReversalRequest.setStageIds(convertListToInt(paramStatesIds));
        }
        findReversalRequest.setLimit(limit);
        findReversalRequest.setOffset(offset);

        FindTransactionReversalOrderResponse response = transactionEndpoint
            .findOrderReversal(findReversalRequest);

        map.put("transCancels", response.getTransactionReversalOrders());
        map.put("total", Integer.valueOf(Math.toIntExact(response.getTotalTxn())));
        map.put("pagesize", limit);
        map.put("offset", offset);
        map.put("lstCbStates", STATES_REVERSAL_TYPES);
        map.put("processes", TransactionCancel.Process.list());
        map.put("serviceTypes", ServiceType.PAYMENT_TYPES);
        map.put("totalRequestAmount", response.getTotalRequestAmount());
        map.put("totalNetAmount", response.getTotalNetAmount());
//        map.put("numberPage", 'd-49489-p');
      } catch (Exception ex) {
        logger.error(ex.getMessage());
      }

      return "/transaction_reversal/transaction-reversal-txn-request";
    }

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    @PreAuthorize(
        "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "','"
            + RoleConstants.CUSTOMERCARE_MANAGER + "','"
            + RoleConstants.CUSTOMERCARE + "')")
    public String innitReversal (HttpServletRequest httpRequest, ModelMap map)
      throws FrontEndException {
      detailCommonTrans(httpRequest, map);
      return "/transaction_reversal/transaction-reversal-request";
    }

    @RequestMapping(value = "/request/verify", method = RequestMethod.POST)
    @PreAuthorize(
        "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "', '" + RoleConstants.CUSTOMERCARE
            + "', '"
            + RoleConstants.CUSTOMERCARE_MANAGER + "')")
    public String requestCancelSend (HttpServletRequest httpRequest, ModelMap map)
      throws FrontEndException {
      Long txnId = NumberUtil.convertToLong(httpRequest.getParameter("transactionId"));
      String remark = httpRequest.getParameter("remark");

      CreateTransactionReversalOrderRequest reversalOrderRequest = new CreateTransactionReversalOrderRequest();
      reversalOrderRequest.setTxnId(txnId);
      reversalOrderRequest.setRemark(remark);
      try {
        CreateTransactionReversalOrderResponse response = transactionEndpoint
            .createOrderReversal(reversalOrderRequest);
      } catch (Exception ex) {
        logger.error(ex.getMessage());
      }
      return searchReversalTxnRequest(httpRequest, map);
    }

    @RequestMapping(value = "/request/approve", method = RequestMethod.GET)
    @PreAuthorize(
        "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "', '" + RoleConstants.FA_MANAGER + "', '"
            + RoleConstants.FINANCE_LEADER + "' , '" + RoleConstants.FINANCESUPPORT_LEADER + "', '"
            + RoleConstants.FINANCE_SUPPORT + "','" + RoleConstants.FINANCE + "' )")
    public String approve (HttpServletRequest httpRequest, ModelMap map)
      throws FrontEndException {
      try {
        Long txnId = NumberUtil.convertToLong(httpRequest.getParameter("txnReversalId"));
        GetTransactionReversalOrderRequest request = new GetTransactionReversalOrderRequest();
        request.setOrderId(txnId);

        GetTransactionReversalOrderResponse response = transactionEndpoint
            .getOrderReversal(request);
        if (response != null && response.getStatus().getCode() == 0) {
          map.put("tranReversal", response.getTransactionReversalOrder());
        }
      } catch (Exception ex) {
        logger.error(ex.getMessage());
      }
      return "/transaction_reversal/reversal_verify";
    }

    @RequestMapping(value = "/request/submit", method = RequestMethod.POST)
    @PreAuthorize(
        "hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "', '" + RoleConstants.FA_MANAGER + "', '"
            + RoleConstants.FINANCE_LEADER + "' , '" + RoleConstants.FINANCESUPPORT_LEADER + "', '"
            + RoleConstants.FINANCE_SUPPORT + "','" + RoleConstants.FINANCE + "' )")
    public String requestCancelSendOTP (HttpServletRequest httpRequest, ModelMap map)
      throws java.lang.Exception {
      String action = httpRequest.getParameter("action");
      Long txnId = NumberUtil.convertToLong(httpRequest.getParameter("txnReversalId"));
      String remark = httpRequest.getParameter("remark");
      try {
        if (action.equals("reject")) {
          TransactionRevertFlowRejectRequest rejectRequest = new TransactionRevertFlowRejectRequest();
          rejectRequest.setOrderId(txnId);
          rejectRequest.setRemark(remark);

          TransactionRevertFlowRejectResponse rejectResponse = transactionEndpoint
              .rejectReversal(rejectRequest);
          if (rejectResponse.getStatus().getCode() == 0) {
            return searchReversalTxnRequest(httpRequest, map);
          }

        } else if (action.equals("approve")) {
          TransactionRevertFlowApproveRequest approveRequest = new TransactionRevertFlowApproveRequest();
          approveRequest.setOrderId(txnId);
          approveRequest.setRemark(remark);

          TransactionRevertFlowApproveResponse approveResponse = transactionEndpoint
              .approveReversal(approveRequest);
          if (approveResponse.getStatus().getCode() == 0) {
            return searchReversalTxnRequest(httpRequest, map);
          }

        }
      } catch (Exception ex) {
        logger.error(ex.getMessage());
      }
      return searchReversalTxnRequest(httpRequest, map);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('" + RoleConstants.ADMIN_OPERATION + "')")
    public String detail (HttpServletRequest httpRequest, ModelMap map) throws FrontEndException {
     try {
       Long oderId = NumberUtil.convertToLong(httpRequest.getParameter("txnReversalId"));
       GetTransactionReversalOrderRequest request = new GetTransactionReversalOrderRequest();
       request.setOrderId((oderId));

       GetTransactionReversalOrderResponse response = transactionEndpoint.getOrderReversal(request);
       if (response.getStatus().getCode() != 0) {
         return searchReversalTxnRequest(httpRequest, map);
       }
       map.put("tranReversal", response.getTransactionReversalOrder());
     }catch (java.lang.Exception ex){
       logger.error(ex.getMessage());
     }
      return "/transaction_reversal/transaction-reversal-detail";
    }

  }
