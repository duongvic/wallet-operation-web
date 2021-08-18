package vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.FindCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.GetAccessTokenRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.*;
import vn.mog.framework.contract.base.KeyValue;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.SummaryBalanceRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.provider.SummaryBalanceResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.transaction.*;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.sofcash.contract.wallet.*;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.CreateBankHistoryRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.CreateBankHistoryResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.FindBankHistoryRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.FindBankHistoryResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.stock.IStockEndpoint;
import vn.mog.framework.security.api.CallerInformation;

import java.util.List;

import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_STOCK_SERVICE_API_ENDPOINT;

@Service
public class StockEndpoint implements IStockEndpoint {

  private static Logger logger = LogManager.getLogger(StockEndpoint.class);

  @Autowired
  OAuth2RestTemplate restTemplate;

  @Value("${platform.backend.sofcash.service.3rd.url.pi-get}")
  private String URI_PI_GET;

  @Value("${platform.backend.sofcash.service.3rd.url.pi-create}")
  private String URI_PI_CREATE;

  @Value("${platform.backend.sofcash.service.3rd.url.pi-update}")
  private String URI_PI_UPDATE;

  @Value("${platform.backend.sofcash.service.3rd.url.pi-delete}")
  private String URI_PI_DELETE;

  @Value("${platform.backend.stock.service.3rd.url.bank-history-find}")
  private String URI_BANK_HISTORY_SEARCH;

  @Value("${platform.backend.stock.service.3rd.url.bank-balance-statistic}")
  private String URI_BANK_BALANCE_STATISTIC_SEARCH;

  @Value("${platform.backend.stock.service.3rd.url.bank-code-list}")
  private String URI_LIST_BANK_CODE;

  @Value("${platform.backend.stock.service.3rd.url.bank-history-create}")
  private String URI_BANK_HISTORY_CREATE;

  @Value("${platform.backend.stock.service.3rd.url.bank-account-find}")
  private String URI_LIST_BANK_ACCOUNT;

  @Value("${platform.backend.stock.service.3rd.url.bank-account-get}")
  private String URI_GET_BANK_ACCOUNT;

  @Value("${platform.backend.stock.service.3rd.url.bank-account-update}")
  private String URI_UPDATE_BANK_ACCOUNT;

  @Value("${platform.backend.stock.service.3rd.url.bank-account-delete}")
  private String URI_DELETE_BANK_ACCOUNT;

  @Override
  public FindBankHistoryResponse findBankHistories(FindBankHistoryRequest request) throws Exception {
    String requestURI = URI_BANK_HISTORY_SEARCH;
    return restTemplate.postForEntity(PLATFORM_BACKEND_STOCK_SERVICE_API_ENDPOINT + requestURI, request, FindBankHistoryResponse.class).getBody();
  }

  @Override
  public BankBalanceStatisticResponse findBankBlanceStatics(BankBalanceStatisticRequest request) throws Exception {
    String requestURI = URI_BANK_BALANCE_STATISTIC_SEARCH;
    return restTemplate.postForEntity(PLATFORM_BACKEND_STOCK_SERVICE_API_ENDPOINT + requestURI, request, BankBalanceStatisticResponse.class).getBody();
  }

  @Override
  public List<KeyValue> getBankCode() throws Exception {
    String requestURI = URI_LIST_BANK_CODE;
    return restTemplate.getForEntity(PLATFORM_BACKEND_STOCK_SERVICE_API_ENDPOINT + requestURI, List.class).getBody();
  }

  @Override
  public FindBankAccountsResponse findBankAccount(FindBankAccountsRequest request) throws Exception {
    String requestURI = URI_LIST_BANK_ACCOUNT;
    return restTemplate.postForEntity(PLATFORM_BACKEND_STOCK_SERVICE_API_ENDPOINT + requestURI, request, FindBankAccountsResponse.class).getBody();
  }

  @Override
  public GetBankHistoryDetailResponse getBankAccount(GetBankHistoryDetailRequest request)
      throws Exception {
    return restTemplate
        .postForEntity(PLATFORM_BACKEND_STOCK_SERVICE_API_ENDPOINT + URI_GET_BANK_ACCOUNT,
            request, GetBankHistoryDetailResponse.class).getBody();
  }

  @Override
  public UpdateBankHistoryResponse updateBankAccount(UpdateBankHistoryRequest request)
      throws Exception {
    return restTemplate
        .postForEntity(PLATFORM_BACKEND_STOCK_SERVICE_API_ENDPOINT + URI_UPDATE_BANK_ACCOUNT,
            request, UpdateBankHistoryResponse.class).getBody();
  }

  @Override
  public DeleteBankHistoryResponse deleteBankAccount(DeleteBankHistoryRequest request)
      throws Exception {

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<DeleteBankHistoryRequest> entityReq = new HttpEntity<>(request, httpHeaders);
    return restTemplate.exchange(
        PLATFORM_BACKEND_STOCK_SERVICE_API_ENDPOINT + URI_DELETE_BANK_ACCOUNT, HttpMethod.DELETE,
        entityReq, DeleteBankHistoryResponse.class).getBody();
  }

  @Override
  public CreateBankHistoryResponse createBankHistory(CreateBankHistoryRequest request)
      throws Exception {
    return restTemplate
        .postForEntity(PLATFORM_BACKEND_STOCK_SERVICE_API_ENDPOINT + URI_BANK_HISTORY_CREATE,
            request, CreateBankHistoryResponse.class).getBody();
  }
}
