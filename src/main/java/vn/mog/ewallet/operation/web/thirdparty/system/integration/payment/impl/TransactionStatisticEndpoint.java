package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.GeneralResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.ITransactionStatisticEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.transaction_statistic.*;
import vn.mog.framework.common.utils.Utils;

import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_DATA_SERVICE_API_ENDPOINT;

@Slf4j
@Service
public class TransactionStatisticEndpoint implements ITransactionStatisticEndpoint {

  @Value("${platform.backend.zpay.data.service.3rd.url.createTransactionStatisticByDate}")
  private String CREATE_TRANSACTION_STATISTIC_BY_DATE;

  @Value("${platform.backend.zpay.data.service.3rd.url.getTransactionStatisticByDate}")
  private String GET_TRANSACTION_STATISTIC_BY_DATE;

  @Value("${platform.backend.zpay.data.service.3rd.url.summaryTransactionStatisticByDate}")
  private String SUMMARY_TRANSACTION_STATISTIC_BY_DATE;

  @Value("${platform.backend.zpay.data.service.3rd.url.createTransactionStatisticByHour}")
  private String CREATE_TRANSACTION_STATISTIC_BY_HOUR;

  @Value("${platform.backend.zpay.data.service.3rd.url.getTransactionStatisticByHour}")
  private String GET_TRANSACTION_STATISTIC_BY_HOUR;

  @Value("${platform.backend.zpay.data.service.3rd.url.summaryTransactionStatisticByHour}")
  private String SUMMARY_TRANSACTION_STATISTIC_BY_HOUR;

  @Autowired
  OAuth2RestTemplate restTemplate;

  @Override
  public GeneralResponse<Object> createTransactionStatisticByDate(CreateTransactionStatisticByDateRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_DATA_SERVICE_API_ENDPOINT + CREATE_TRANSACTION_STATISTIC_BY_DATE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, GeneralResponse.class)
              .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GeneralResponse<Object> getTransactionStatisticByDate(GetTransactionStatisticByDateRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_DATA_SERVICE_API_ENDPOINT + GET_TRANSACTION_STATISTIC_BY_DATE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, GeneralResponse.class)
              .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GeneralResponse<Object> summaryTransactionStatisticByDate(SummaryTransactionStatisticByDateRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_DATA_SERVICE_API_ENDPOINT + SUMMARY_TRANSACTION_STATISTIC_BY_DATE;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, GeneralResponse.class)
              .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GeneralResponse<Object> createTransactionStatisticByHour(CreateTransactionStatisticByHourRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_DATA_SERVICE_API_ENDPOINT + CREATE_TRANSACTION_STATISTIC_BY_HOUR;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, GeneralResponse.class)
              .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GeneralResponse<Object> getTransactionStatisticByHour(GetTransactionStatisticByHourRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_DATA_SERVICE_API_ENDPOINT + GET_TRANSACTION_STATISTIC_BY_HOUR;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, GeneralResponse.class)
              .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public GeneralResponse<Object> summaryTransactionStatisticByHour(SummaryTransactionStatisticByHourRequest request) throws FrontEndException {
    try {
      String endpoint = PLATFORM_BACKEND_DATA_SERVICE_API_ENDPOINT + SUMMARY_TRANSACTION_STATISTIC_BY_HOUR;
      log.info("REQ-URL: {}", endpoint);
      log.info("REQ-BODY: {}", Utils.objectToJson(request));
      return restTemplate.postForEntity(endpoint, request, GeneralResponse.class)
              .getBody();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }
}
