package vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.impl;



import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_PMS_PROFILE_COMPOSITE_SERVICE_API_ENDPOINT;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vn.mog.ewallet.operation.web.exception.FrontEndException;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.ISystemUserEndpoint;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.beans.Customer;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.CreateCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.FindCustomerRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.FindCustomerRoleRequest;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.GeneralResponse;
import vn.mog.ewallet.operation.web.utils.WebUtil;

@SuppressWarnings("unchecked")
@Service
public class SystemUserEndpoint implements ISystemUserEndpoint {

  private static final String PREFIX = "/v1.0";


  private static final String URI_FIND = PREFIX + "/partner/consumer/find";
  private static final String URI_FIND_ALL_ZOTA = PREFIX + "/partner/system-user/check-account-existed";
  private static final String URI_CREATE_ACCOUNT = PREFIX + "/partner/system-user/create/by-zota-account";
  private static final String URI_FIND_CUSTOMER_ROLE_ALL = PREFIX + "/system-user/customer-role/find";


  @Autowired
  private OAuth2RestTemplate restTemplate;


  @Override
  public GeneralResponse<Object> findAccountList(FindCustomerRequest request)
      throws FrontEndException {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<FindCustomerRequest> entityReq = new HttpEntity<>(request, httpHeaders);
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.exchange(
        PLATFORM_BACKEND_PMS_PROFILE_COMPOSITE_SERVICE_API_ENDPOINT + URI_FIND,
        HttpMethod.POST,
        entityReq,
        GeneralResponse.class).getBody();
  }

  @Override
  public GeneralResponse<Object> findAccountFromZota(FindCustomerRequest request)
      throws FrontEndException {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<FindCustomerRequest> entityReq = new HttpEntity<>(request, httpHeaders);
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.exchange(
        PLATFORM_BACKEND_PMS_PROFILE_COMPOSITE_SERVICE_API_ENDPOINT + URI_FIND_ALL_ZOTA,
        HttpMethod.POST,
        entityReq,
        GeneralResponse.class).getBody();
  }



  @Override
  public GeneralResponse<Object> createAccount(CreateCustomerRequest request) throws FrontEndException {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<CreateCustomerRequest> entityReq = new HttpEntity<>(request, httpHeaders);
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.exchange(
        PLATFORM_BACKEND_PMS_PROFILE_COMPOSITE_SERVICE_API_ENDPOINT + URI_CREATE_ACCOUNT,
        HttpMethod.POST,
        entityReq,
        GeneralResponse.class
    ).getBody();
  }

  @Override
  public GeneralResponse<Object> findAllCustomerRole(FindCustomerRoleRequest request, String accessToken)
      throws FrontEndException {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.set("X-Language", "VI");
    httpHeaders.set("Authorization", accessToken);
    HttpEntity<FindCustomerRoleRequest> entityReq = new HttpEntity<>(request, httpHeaders);

    return restTemplate.exchange(
        PLATFORM_BACKEND_PMS_PROFILE_COMPOSITE_SERVICE_API_ENDPOINT + URI_FIND_CUSTOMER_ROLE_ALL,
        HttpMethod.POST,
        entityReq,
        GeneralResponse.class).getBody();
  }

}
