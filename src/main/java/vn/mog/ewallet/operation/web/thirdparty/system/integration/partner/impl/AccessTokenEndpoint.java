package vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.impl;



import static vn.mog.ewallet.operation.web.constant.SharedConstants.PLATFORM_BACKEND_PMS_GET_TOKEN_SERVICE_API_ENDPOINT;

import java.util.Collections;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vn.mog.ewallet.operation.web.exception.FrontEndException;

import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.IAccessTokenEndpoint;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.GeneralResponse;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms.GetAccessTokenRequest;
import vn.mog.ewallet.operation.web.utils.WebUtil;

@SuppressWarnings("unchecked")
@Service
public class AccessTokenEndpoint implements IAccessTokenEndpoint {

  private static final String PREFIX = "/v1";
  private static final String URI_GET_ACCESS_TOKEN_PMS = PREFIX + "/oauth/get/token";





//  @Autowired
//  private OAuth2RestTemplate restTemplate;



  @Override
  public GeneralResponse<Object> getAccessTokenPMS(GetAccessTokenRequest request)
      throws FrontEndException {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<GetAccessTokenRequest> entityReq = new HttpEntity<>(request, httpHeaders);
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.exchange( PLATFORM_BACKEND_PMS_GET_TOKEN_SERVICE_API_ENDPOINT + URI_GET_ACCESS_TOKEN_PMS , HttpMethod.POST,
        entityReq, GeneralResponse.class).getBody();
  }
}
