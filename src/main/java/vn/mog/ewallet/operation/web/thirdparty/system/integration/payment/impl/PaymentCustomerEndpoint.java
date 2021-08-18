package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.impl;

import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.exception.MessageNotify;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.PaymentOpServerAPIClient;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.IPaymentCustomerEndpoint;
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
import vn.mog.framework.common.utils.SessionUtil;
import vn.mog.framework.common.utils.Utils;
import vn.mog.framework.contract.base.MobiliserResponseType;

@Slf4j
@Service
public class PaymentCustomerEndpoint implements IPaymentCustomerEndpoint {

  @Value("${platform.backend.payment.service.3rd.url.customer-sendAccountInfoNotification}")
  private String URI_SEND_ACCOUNT_INFO_NOTIFICATION;

  @Value("${platform.backend.payment.service.3rd.url.customer-findCustomers}")
  private String URI_FIND_CUSTOMERS;

  @Value("${platform.backend.payment.service.3rd.url.customer-getCustomer}")
  private String URI_GET_CUSTOMERS;

  @Value("${platform.backend.payment.service.3rd.url.customer-specifiedCommissionFeeCustomer-list}")
  private String URI_FIND_SPECIFIED_COMMISSION_FEE_CUSTOMERS;

  @Value("${platform.backend.payment.service.3rd.url.system-updateSpecifiedCommissionFee}")
  private String UPDATE_SPECIFIED_COMMISSION_FEE;

  @Value("${platform.backend.payment.service.3rd.url.system-deleteSpecifiedCommissionFee}")
  private String DELETE_SPECIFIED_COMMISSION_FEE;

  @Value("${platform.backend.payment.service.3rd.url.system-getCommissionFee}")
  private String URI_GET_COMMISSION_FEE;

  @Value("${platform.backend.payment.service.3rd.url.system-updateCommissionFee}")
  private String URI_UPDATE_COMMISSION_FEE;

  @Value("${platform.backend.payment.service.3rd.url.system-deleteCommissionFee}")
  private String DELETE_COMMISSION_FEE;

  @Value("${platform.backend.payment.service.3rd.url.system-findCommissionFee}")
  private String FIND_COMMISSION_FEE;

  @Value("${platform.backend.payment.service.3rd.url.system-getSpecifiedCommissionFee}")
  private String GET_SPECIFIED_COMMISSION_FEE;

  @Autowired OAuth2RestTemplate restTemplate;

  @Autowired PaymentOpServerAPIClient paymentServerAPI;

  @Override
  public SendUserAccountInforNotificationResponse sendAccountInfoNotification(
      SendUserAccountInforNotificationRequest request) throws FrontEndException {
    try {
      String endpoint =
          PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_SEND_ACCOUNT_INFO_NOTIFICATION;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, SendUserAccountInforNotificationResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GetCustomerResponse getCustomer(GetCustomerRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_GET_CUSTOMERS;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, GetCustomerResponse.class).getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public FindFullCustomerResponse findCustomers(FindFullCustomerRequest request)
      throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_PAYMENT_SERVICE_API_ENDPOINT + URI_FIND_CUSTOMERS;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate
          .postForEntity(endpoint, request, FindFullCustomerResponse.class)
          .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public FindCommissionFeeResponse findCommissionFees(FindCommissionFeeRequest request) {
    FindCommissionFeeResponse response;
    try {
      String endpoint = FIND_COMMISSION_FEE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      response = paymentServerAPI.postForEntity(endpoint, request, FindCommissionFeeResponse.class);
    } catch (Exception e) {
      log.error("findCommissionFee", e);
      response = new FindCommissionFeeResponse();
      SessionUtil.setAttribute(
          MessageNotify.SESSION_MESSAGE_NOTIFY,
          new MessageNotify(MessageNotify.ERROR_CODE, "Fetch Commission Fee is error"));
    }
    return response;
  }

  @Override
  public FindSpecifiedCommissionFeeResponse findSpecifiedCommissionFees(
      FindSpecifiedCommissionFeeRequest request) {
    try {
      String endpoint = GET_SPECIFIED_COMMISSION_FEE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return paymentServerAPI.postForEntity(
          endpoint, request, FindSpecifiedCommissionFeeResponse.class);
    } catch (Exception e) {
      log.error("getSpecifiedCommissionFee", e);
      //      SessionUtil.setAttribute(MessageNotify.SESSION_MESSAGE_NOTIFY, new
      // MessageNotify(MessageNotify.ERROR_CODE, "Fetch Specified Commission Fee is error"));
    }
    return new FindSpecifiedCommissionFeeResponse();
  }

  @Override
  public UpdateSpecifiedCommissionFreeResponse updateSpecifiedCommissionFee(
      UpdateSpecifiedCommissionFreeRequest request) throws FrontEndException {
    UpdateSpecifiedCommissionFreeResponse response;
    try {
      String endpoint = UPDATE_SPECIFIED_COMMISSION_FEE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      response =
          paymentServerAPI.postForEntity(
              endpoint, request, UpdateSpecifiedCommissionFreeResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      response = new UpdateSpecifiedCommissionFreeResponse();
    }
    return response;
  }

  @Override
  public MobiliserResponseType deleteSpecifiedCommissionFee(
      DeleteSpecifiedCommissionFreeRequest request) throws FrontEndException {
    MobiliserResponseType response;
    try {
      String endpoint = DELETE_SPECIFIED_COMMISSION_FEE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      response = paymentServerAPI.postForEntity(endpoint, request, MobiliserResponseType.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      response = new MobiliserResponseType();
    }
    return response;
  }

  @Override
  public FindSpecifiedCommissionFeeCustomerResponse findSpecifiedCommissionFeeCustomer(
      FindSpecifiedCommissionFeeCustomerRequest request) throws FrontEndException {
    FindSpecifiedCommissionFeeCustomerResponse response;
    try {
      String endpoint = URI_FIND_SPECIFIED_COMMISSION_FEE_CUSTOMERS;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      response =
          paymentServerAPI.postForEntity(
              endpoint, request, FindSpecifiedCommissionFeeCustomerResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      response = new FindSpecifiedCommissionFeeCustomerResponse();
    }
    return response;
  }

  @Override
  public GetCommissionFeeResponse getCommissionFee(GetCommissionFeeRequest request)
      throws FrontEndException {
    GetCommissionFeeResponse response;
    try {
      String endpoint = URI_GET_COMMISSION_FEE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      response = paymentServerAPI.postForEntity(endpoint, request, GetCommissionFeeResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      response = new GetCommissionFeeResponse();
    }
    return response;
  }

  @Override
  public UpdateCommissionFreeResponse updateCommissionFee(UpdateCommissionFreeRequest request)
      throws FrontEndException {
    UpdateCommissionFreeResponse response;
    try {
      String endpoint = URI_UPDATE_COMMISSION_FEE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      response =
          paymentServerAPI.postForEntity(endpoint, request, UpdateCommissionFreeResponse.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      response = new UpdateCommissionFreeResponse();
    }
    return response;
  }

  @Override
  public MobiliserResponseType deleteCommissionFee(DeleteCommissionFreeRequest request)
      throws FrontEndException {
    MobiliserResponseType response;
    try {
      String endpoint = DELETE_COMMISSION_FEE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      response = paymentServerAPI.postForEntity(endpoint, request, MobiliserResponseType.class);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      response = new MobiliserResponseType();
    }
    return response;
  }
}
