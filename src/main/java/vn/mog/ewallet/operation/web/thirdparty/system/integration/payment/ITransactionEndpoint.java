package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment;


import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.FindTransactionRuleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.FindTransactionRuleResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetTransactionRuleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.GetTransactionRuleResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction.*;


public interface ITransactionEndpoint {

  /****** API Transaction ******/
  GetTransactionResponse getTransaction(GetTransactionRequest request) throws FrontEndException;

  FindTransactionsResponse findTransactions(FindTransactionsRequest request) throws FrontEndException;

  /****** API Transaction Rule ******/
  FindTransactionRuleResponse findTransactionRules(FindTransactionRuleRequest request) throws FrontEndException;

  GetTransactionRuleResponse getTransactionRule(GetTransactionRuleRequest request) throws FrontEndException;

  ExportTransactionLogResponse exportTransaction(ExportTransactionLogRequest request) throws FrontEndException;


  GetStatementOfCustomerResponse statementUser(GetStatementOfCustomerRequest request) throws FrontEndException;

  ExportExcelFileCustomerStatementResponse exportStatementUser(ExportExcelFileCustomerStatementRequest request) throws FrontEndException;

  CreateTransactionReversalOrderResponse createOrderReversal (CreateTransactionReversalOrderRequest request) throws FrontEndException;

  GetTransactionReversalOrderResponse getOrderReversal (GetTransactionReversalOrderRequest request) throws FrontEndException;

  FindTransactionReversalOrderResponse findOrderReversal (FindTransactionReversalOrderRequest request) throws FrontEndException;

  TransactionRevertFlowSubmitResponse submitReversal (TransactionRevertFlowSubmitRequest request) throws FrontEndException;

  TransactionRevertFlowRejectResponse rejectReversal(TransactionRevertFlowRejectRequest request) throws FrontEndException;

  TransactionRevertFlowApproveResponse approveReversal (TransactionRevertFlowApproveRequest request) throws FrontEndException;

  PhoneTopupTransactionOnHoldResponse getPhoneTopupTransactionOnHold(
      PhoneTopupTransactionOnHoldRequest request) throws FrontEndException;

  BillPaymentTransactionOnHoldResponse getBillPaymentTransactionOnHold(
      BillPaymentTransactionOnHoldRequest request) throws FrontEndException;

  PhoneTopupTransactionOnHoldByBatchResponse getPhoneTopupTransactionOnHoldBatch(
          PhoneTopupTransactionOnHoldByBatchRequest request) throws FrontEndException;

  BillPaymentTransactionOnHoldByBatchResponse getBillPaymentTransactionOnHoldBatch(BillPaymentTransactionOnHoldByBatchRequest request) throws FrontEndException;
}
