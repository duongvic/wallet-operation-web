package vn.mog.ewallet.operation.web.thirdparty.system.integration;


import static vn.mog.ewallet.operation.web.constant.SharedConstants.HTTP_CLIENT_CONNECT_TIMEOUT;
import static vn.mog.ewallet.operation.web.constant.SharedConstants.HTTP_CLIENT_SOCKET_TIMEOUT;
import static vn.mog.ewallet.operation.web.constant.SharedConstants.HTTP_DEFAULT_MAX_CONNECTIONS_PER_HOST;
import static vn.mog.ewallet.operation.web.constant.SharedConstants.HTTP_MAX_TOTAL_CONNECTIONS;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import vn.mog.ewallet.operation.web.exception.FrontEndException;


@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class AbstractAPIClient {

  // --------------------------------
  public static final String AUTHORIZATION = "Authorization";
  public static final String BEARER_HEADER_PREFIX = "Bearer ";
  public static final String CONTENT_TYPE = "Content-Type";
  public static final String USER_AGENT = "User-Agent";
  public static final String SESSION_ACCESS_TOKEN = "access_token";
  public final static String SESSION_ACCOUNT_LOGIN = "session_account_login";
  public final static String SESSION_IS_MOBILE = "session_is_mobile";

  public static final String PARAM_ACCESS_TOKEN = "accessToken";
  public static final String PARAM_EXPIRED_TIME = "expiresIn";
  public static final String PARAM_REFRESH_TOKEN = "refreshToken";
  private final static Logger logger = LogManager.getLogger(AbstractAPIClient.class);
  // --------------------------------
  public static MultiThreadedHttpConnectionManager httpConnectionManager;
  public static HttpClient httpClient;

  static {

    if (httpConnectionManager == null) {
      httpConnectionManager = new MultiThreadedHttpConnectionManager();
      HttpConnectionManagerParams params = new HttpConnectionManagerParams();
      params.setDefaultMaxConnectionsPerHost(HTTP_DEFAULT_MAX_CONNECTIONS_PER_HOST);
      params.setMaxTotalConnections(HTTP_MAX_TOTAL_CONNECTIONS);
      params.setParameter(HttpConnectionManagerParams.CONNECTION_TIMEOUT, HTTP_CLIENT_CONNECT_TIMEOUT);
      params.setParameter(HttpConnectionManagerParams.SO_TIMEOUT, HTTP_CLIENT_SOCKET_TIMEOUT);
      httpConnectionManager.setParams(params);
    }
    if (httpClient == null) {
      httpClient = new HttpClient(httpConnectionManager);
    }
  }

  // --------------------------------
  @Autowired
  @Qualifier("apiRestTemplate")
  protected RestTemplate restTemplate;
  // --------------------------------
  private String accessToken;

  // --------------------------------
  protected abstract String getClientID();

  protected abstract String getBaseRequestURL();

  protected abstract String getBearerHeaderPrefix();

  protected abstract boolean isSessionStorage();

  public String getAccessToken() {
    if (isSessionStorage()) {
      try {
        if (getCurrentHttpServletRequest() != null) {
          this.accessToken =
              (String) getCurrentHttpServletRequest().getSession().getAttribute(
                  SESSION_ACCESS_TOKEN);
        }
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
      }
    }
    if (this.accessToken != null && this.accessToken.startsWith(getBearerHeaderPrefix())) {
      this.accessToken = this.accessToken.substring(getBearerHeaderPrefix().length());
    }
    return this.accessToken;
  }

  public void setAccessToken(String accessToken) {
    if (StringUtils.isNotEmpty(accessToken)) {
      this.accessToken = accessToken;
      if (this.accessToken != null && this.accessToken.startsWith(getBearerHeaderPrefix())) {
        this.accessToken = this.accessToken.substring(getBearerHeaderPrefix().length());
      }
      if (isSessionStorage() && StringUtils.isNotEmpty(this.accessToken)) {
        try {
          if (getCurrentHttpServletRequest() != null) {
            getCurrentHttpServletRequest().getSession().setAttribute(SESSION_ACCESS_TOKEN,
                this.accessToken);
          }
        } catch (IllegalStateException e) {
          logger.error(e.getMessage(), e);
        }
      }
    }
  }

  // --------------------------------
  public <T> T callRequest(String requestURI, Object request, Class<T> clazz)
      throws FrontEndException {
    Map<String, String> mapHeader = new HashMap<>();
    logger.debug("--AccessToken--" + getAccessToken());
    if (StringUtils.isNotBlank(getAccessToken())) {
      mapHeader.put(AUTHORIZATION, getBearerHeaderPrefix() + getAccessToken());
    }
    return callRequest(requestURI, mapHeader, request, clazz);
  }

  public <T> T callRequest(String requestURI, Map<String, String> mapHeader, Object request,
      Class<T> clazz) throws FrontEndException {
    final long t1 = System.currentTimeMillis();
    final String url = getBaseRequestURL() + requestURI;
    T resp = null;
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      if (mapHeader != null && !mapHeader.isEmpty()) {
        Iterator<Entry<String, String>> iterator = mapHeader.entrySet().iterator();
        while (iterator.hasNext()) {
          Entry<String, String> entry = iterator.next();
          headers.add(entry.getKey(), entry.getValue());
          logger.debug(entry.getKey() + ":    " + entry.getValue());
        }
      }
      final HttpEntity walletRequest = new HttpEntity(request, headers);
      // --------------
      resp = restTemplate.postForObject(url, walletRequest, clazz);
      if (resp == null) {
        throw new NullPointerException();
      }
      return resp;
    } catch (HttpStatusCodeException e) {
      int statusCode = e.getStatusCode().value();
      logger.error("RESPONE HTTP CODE: " + statusCode, e);
      if (statusCode == 401) {
        throw new FrontEndException(401, "Not Authorized!");
      }
      throw e;
    } catch (RestClientException e) {
      logger.error("RestClientException", e);
      throw e;
    } catch (Exception e) {
      logger.error("Exception", e);
      throw e;
    } finally {
      final long t2 = System.currentTimeMillis();
      // --------------
      logger.info(getClientID().toUpperCase() + ": " + url);
      logger.info("REQ:  " + new Gson().toJson(request));
      logger.info("RESP: " + new Gson().toJson(resp));
      logger.info("Total time taken in ms:    " + (t2 - t1));
      // --------------
    }
  }

  public <T> T exchange(String requestURI, HttpMethod method, Map<String, String> mapHeader,
      Object request, Map<String, String> uriVariables, Class<T> clazz) throws RuntimeException {
    final String url = getBaseRequestURL() + requestURI;
    final long t1 = System.currentTimeMillis();
    try {
      // --------------
      logger.info(getClientID().toUpperCase() + " URL: " + url);
      logger.info("REQ: " + new Gson().toJson(request));
      // --------------
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      if (mapHeader != null && !mapHeader.isEmpty()) {
        Iterator<Entry<String, String>> iterator = mapHeader.entrySet().iterator();
        while (iterator.hasNext()) {
          Entry<String, String> entry = iterator.next();
          headers.add(entry.getKey(), entry.getValue());
          logger.info(entry.getKey() + ":    " + entry.getValue());
        }
      }

      final HttpEntity httpEntity = new HttpEntity(request, headers);

      ResponseEntity<T> resp;
      if (uriVariables != null) {
        resp = restTemplate.exchange(url, method, httpEntity, clazz, uriVariables);
      } else {
        resp = restTemplate.exchange(url, method, httpEntity, clazz);
      }

      if (resp == null) {
        throw new NullPointerException();
      }
      logger.info("RESP:    " + new Gson().toJson(resp));
      if (resp.getStatusCode() != HttpStatus.OK) {
        throw new RuntimeException();
      }
      return resp.getBody();
    } catch (HttpStatusCodeException ex) {
      int statusCode = ex.getStatusCode().value();

      logger.error("RESPONE HTTP CODE: " + statusCode);
      logger.error("RESPONE: " + ex.getResponseBodyAsString());
      logger.error(ex.getMessage(), ex);

      if (statusCode == 401) {
        throw new SecurityException("Not Authorized!");
      }
      throw ex;
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw e;
    } finally {
      final long t2 = System.currentTimeMillis();
      logger.info("Total time taken in ms:    " + (t2 - t1));
      // --------------
    }
  }

  public <T> T getForEntity(String requestURI, Object request,
      Map<String, String> uriVariables, Class<T> clazz) throws Exception {
    Map<String, String> mapHeader = new HashMap<>();
    if (StringUtils.isNotBlank(getAccessToken())) {
      mapHeader.put(AUTHORIZATION, getBearerHeaderPrefix() + getAccessToken());
    }
    return exchange(requestURI, HttpMethod.GET, mapHeader, request, uriVariables, clazz);
  }

  public <T> T getForEntity(String requestURI, Map<String, String> mapHeader, Object request,
      Map<String, String> uriVariables, Class<T> clazz) throws Exception {
    return exchange(requestURI, HttpMethod.GET, mapHeader, request, uriVariables, clazz);
  }

  public <T> T postForEntity(String requestURI, Object request, Class<T> clazz) throws RuntimeException {
    Map<String, String> mapHeader = new HashMap<>();
    Map<String, String> uriVariables = null;
    String accessToken = getAccessToken();
    if (StringUtils.isNotBlank(accessToken)) {
      mapHeader.put(AUTHORIZATION, getBearerHeaderPrefix() + accessToken);
    }
    return exchange(requestURI, HttpMethod.POST, mapHeader, request, uriVariables, clazz);
  }

  public <T> T postForEntity(String requestURI, Object request,
      Map<String, String> uriVariables, Class<T> clazz) throws Exception {
    Map<String, String> mapHeader = new HashMap<>();
    if (StringUtils.isNotBlank(getAccessToken())) {
      mapHeader.put(AUTHORIZATION, getBearerHeaderPrefix() + getAccessToken());
    }
    return exchange(requestURI, HttpMethod.POST, mapHeader, request, uriVariables, clazz);
  }

  public <T> T postForEntity(String requestURI, Map<String, String> mapHeader, Object request,
      Map<String, String> uriVariables, Class<T> clazz) throws Exception {
    return exchange(requestURI, HttpMethod.POST, mapHeader, request, uriVariables, clazz);
  }

  public <T> T putForEntity(String requestURI, Map<String, String> mapHeader, Object request,
      Map<String, String> uriVariables, Class<T> clazz) throws Exception {
    return exchange(requestURI, HttpMethod.PUT, mapHeader, request, uriVariables, clazz);
  }

  public <T> T putForEntity(String requestURI, Object request, Class<T> clazz) throws RuntimeException {
    Map<String, String> mapHeader = new HashMap<>();
    Map<String, String> uriVariables = null;
    String accessToken = getAccessToken();
    if (StringUtils.isNotBlank(accessToken)) {
      mapHeader.put(AUTHORIZATION, getBearerHeaderPrefix() + accessToken);
    }
    return exchange(requestURI, HttpMethod.PUT, mapHeader, request, uriVariables, clazz);
  }

  public <T> T deleteForEntity(String requestURI, Map<String, String> mapHeader, Object request,
      Map<String, String> uriVariables, Class<T> clazz) throws Exception {
    return exchange(requestURI, HttpMethod.DELETE, mapHeader, request, uriVariables, clazz);
  }

  public <T> T deleteForEntity(String requestURI, Map<String, String> uriVariables, Class<T> clazz) throws RuntimeException {
    Map<String, String> mapHeader = new HashMap<>();
    String accessToken = getAccessToken();
    if (StringUtils.isNotBlank(accessToken)) {
      mapHeader.put(AUTHORIZATION, getBearerHeaderPrefix() + accessToken);
    }
    return exchange(requestURI, HttpMethod.DELETE, mapHeader, null, uriVariables, clazz);
  }

  private HttpServletRequest getCurrentHttpServletRequest() {
    try {
      ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
      return sra.getRequest();
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return null;
  }


}
