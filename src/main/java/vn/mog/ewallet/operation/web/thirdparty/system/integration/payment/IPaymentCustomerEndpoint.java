package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment;


import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.DeleteCommissionFreeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.DeleteSpecifiedCommissionFreeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.FindCommissionFeeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.FindCommissionFeeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.FindSpecifiedCommissionFeeCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.FindSpecifiedCommissionFeeCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.FindSpecifiedCommissionFeeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.FindSpecifiedCommissionFeeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.GetCommissionFeeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.GetCommissionFeeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.UpdateCommissionFreeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.UpdateCommissionFreeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.UpdateSpecifiedCommissionFreeRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.commission.fee.UpdateSpecifiedCommissionFreeResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindFullCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.customer.FindFullCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.GetCustomerResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.SendUserAccountInforNotificationRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account.SendUserAccountInforNotificationResponse;
import vn.mog.framework.contract.base.MobiliserResponseType;

public interface IPaymentCustomerEndpoint {


  SendUserAccountInforNotificationResponse sendAccountInfoNotification(
      SendUserAccountInforNotificationRequest request) throws FrontEndException;

  GetCustomerResponse getCustomer(GetCustomerRequest request) throws FrontEndException;

  FindFullCustomerResponse findCustomers(FindFullCustomerRequest request) throws FrontEndException;


  FindCommissionFeeResponse findCommissionFees(FindCommissionFeeRequest request);


  FindSpecifiedCommissionFeeResponse findSpecifiedCommissionFees(
      FindSpecifiedCommissionFeeRequest request);

  UpdateSpecifiedCommissionFreeResponse updateSpecifiedCommissionFee(
      UpdateSpecifiedCommissionFreeRequest request) throws FrontEndException;

  MobiliserResponseType deleteSpecifiedCommissionFee(DeleteSpecifiedCommissionFreeRequest request)
      throws FrontEndException;


  FindSpecifiedCommissionFeeCustomerResponse findSpecifiedCommissionFeeCustomer(
      FindSpecifiedCommissionFeeCustomerRequest request) throws FrontEndException;

  GetCommissionFeeResponse getCommissionFee(GetCommissionFeeRequest request)
      throws FrontEndException;

  UpdateCommissionFreeResponse updateCommissionFee(UpdateCommissionFreeRequest request)
      throws FrontEndException;

  MobiliserResponseType deleteCommissionFee(DeleteCommissionFreeRequest request)
      throws FrontEndException;


}
