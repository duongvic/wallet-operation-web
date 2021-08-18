package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment;

import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.CreateTransactionRuleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.CreateTransactionRuleResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.RemoveTransactionRuleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.RemoveTransactionRuleResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.TransactionHoldApproveToSuccessRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.TransactionHoldApproveToSuccessResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.UpdateTransactionRuleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.UpdateTransactionRuleResponse;

public interface IPaymentSystemEndpoint {

  /*-- Txn Rule --*/
  CreateTransactionRuleResponse createTransactionRule(CreateTransactionRuleRequest request) throws FrontEndException;

  UpdateTransactionRuleResponse updateTransactionRule(UpdateTransactionRuleRequest request) throws FrontEndException;

  RemoveTransactionRuleResponse removeTransactionRule(RemoveTransactionRuleRequest request) throws FrontEndException;

  TransactionHoldApproveToSuccessResponse changeStatusTxtHold(TransactionHoldApproveToSuccessRequest request) throws FrontEndException;



}
