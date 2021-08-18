package vn.mog.framework.common.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpUtil {

  // DEMO -------------------------------------------------------------------
  public static final String RECAPTCHA_VERIF_URL = "https://www.google.com/recaptcha/api/siteverify";
  private static final Logger LOG = LogManager.getLogger(HttpUtil.class);
  // --------------------------------
  private static final String AUTHORIZATION = "Authorization";
  private static final String BEARER_HEADER_PREFIX = "Bearer ";
  public static MultiThreadedHttpConnectionManager connectionManager;
  public static HttpClient client;
  // --------------------------------
  public static RestTemplate restTemplate = null;

  static {

    if (connectionManager == null) {
      connectionManager = new MultiThreadedHttpConnectionManager();
      HttpConnectionManagerParams params = new HttpConnectionManagerParams();
      params.setDefaultMaxConnectionsPerHost(100);
      params.setMaxTotalConnections(5000);
      params.setParameter(HttpConnectionManagerParams.SO_TIMEOUT, 60000);
      params.setParameter(HttpConnectionManagerParams.CONNECTION_TIMEOUT, 60000);
      connectionManager.setParams(params);
    }
    if (client == null) {
      client = new HttpClient(connectionManager);
    }
  }

  static {
    restTemplate = new RestTemplate();
    ObjectMapper lax = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
    jacksonConverter.setObjectMapper(lax);

    List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
    messageConverters.add(jacksonConverter);

    restTemplate.setMessageConverters(messageConverters);
  }

  // DEMO --------------------------------
  final String BASE_REQUEST_URL = "";
  final String ACCESS_TOKEN = "";
  // DEMO ///////////////////////////////////////////////////////////////////
  private String baseRequestURL = "http://127.0.0.1:8080/api/pushToBSC";

  public static HttpServletRequest getCurrentHttpServletRequest() {
    try {
      ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
      HttpServletRequest request = sra.getRequest();

      return request;
    } catch (Exception e) {
      LOG.error("getCurrentHttpServlet", e);
    }
    return null;
  }

  public static String postSOAP(String pUrl, String xmldata, String action) {
    // Get target URL
    try {
      String strURL = pUrl;
      // Get SOAP action
      String strSoapAction = action;
      // Get file to be posted
      // String strXMLFilename = args[2];
      // File input = new File(strXMLFilename);
      // Prepare HTTP post
      PostMethod post = new PostMethod(strURL);
      // Request content will be retrieved directly
      // from the input stream
      RequestEntity entity = new StringRequestEntity(xmldata, "text/xml; charset=UTF-8", "UTF-8");
      post.setRequestEntity(entity);
      // consult documentation for your web service
      post.setRequestHeader("SOAPAction", strSoapAction);
      // Get HTTP client
      HttpClient httpclient = new HttpClient();
      // Execute request
      try {
        int result = httpclient.executeMethod(post);
        // Display status code
        LOG.info("Response status code: " + result);
        // Display response
        LOG.info("Response body: " + post.getResponseBodyAsString());

        return post.getResponseBodyAsString();
      } finally {
        // Release current connection to the connection pool once you
        // are done
        post.releaseConnection();
      }
    } catch (UnsupportedEncodingException e) {
      LOG.error("UnsupportedEncodingException", e);
    } catch (HttpException e) {
      LOG.error("HttpException", e);
    } catch (IOException e) {
      LOG.error("IOException", e);
    }
    return null;
  }

  public static boolean verifyCaptcha(String secret, String response, String remoteip) {
    String url = RECAPTCHA_VERIF_URL;
    final PostMethod method = new PostMethod(url);
    try {
      ObjectMapper mapper = new ObjectMapper();
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

      List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
      urlParameters.add(new NameValuePair("secret", secret));
      urlParameters.add(new NameValuePair("response", response));
      urlParameters.add(new NameValuePair("remoteip", remoteip));

      method.addParameters(urlParameters.toArray(new NameValuePair[urlParameters.size()]));

      int responseCode = client.executeMethod(method);
      String json = null;
      if (responseCode == HttpStatus.SC_OK) {
        json = method.getResponseBodyAsString();
        if (json != null) {
          JSONObject jsonObject = new JSONObject(json);
          return jsonObject.getBoolean("success");
        }
      } else {
        LOG.info("ResponseCode: " + responseCode);
      }

    } catch (Exception e) {
      LOG.error("Exception", e);
    } finally {
      method.releaseConnection();
    }

    return false;
  }

  public static String verify(String gRecaptchaResponse) {
    String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
    String SECRET_KEY = "6LfOZwsUAAAAAD5x3isFxqf1z6NIGv1i_zc6ZMjJ";

    if (gRecaptchaResponse == null || gRecaptchaResponse.length() == 0) {
      return null;
    }

    try {
      URL verifyUrl = new URL(SITE_VERIFY_URL);

      // Mở kết nối (Connection) tới URL trên.
      HttpsURLConnection conn = (HttpsURLConnection) verifyUrl.openConnection();

      // Thêm các thông tin Header vào Request chuẩn bị gửi tới server.
      // Add Request Header
      conn.setRequestMethod("POST");
      conn.setRequestProperty("User-Agent", "Mozilla/5.0");
      conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

      // Dữ liệu sẽ gửi tới server.
      String postParams = "secret=" + SECRET_KEY + "&response=" + gRecaptchaResponse;

      // Send Request
      conn.setDoOutput(true);

      // Lấy luồng đầu ra của kết nối tới server.
      // Ghi dữ liệu vào luồng này, có nghĩa là gửi thông tin đến Server.
      OutputStream outStream = conn.getOutputStream();
      outStream.write(postParams.getBytes());

      outStream.flush();
      outStream.close();

      // Mã trả lời trả về từ Server.
      // Lấy InputStream từ Connection để đọc dữ liệu gửi về từ Server.
      InputStream is = conn.getInputStream();
      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
      StringBuilder sb = new StringBuilder();
      String line = null;
      while ((line = rd.readLine()) != null) {
        sb.append(line + '\n');
      }
      return sb.toString();

    } catch (Exception e) {
      LOG.error("Exception", e);
      return null;
    }
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  protected Object callRequest(String requestURI, Object request, Class clazz) {
    try {
      final String url = BASE_REQUEST_URL + requestURI;
      // --------------
      HttpHeaders headers = new HttpHeaders();
      headers.add(AUTHORIZATION, BEARER_HEADER_PREFIX + this.ACCESS_TOKEN);
      final HttpEntity walletRequest = new HttpEntity(request, headers);
      //
      Object resp = restTemplate.postForObject(url, walletRequest, clazz);
      if (resp != null) {
        return resp;
      }
    } catch (HttpStatusCodeException e) {
      int statusCode = e.getStatusCode().value();
      LOG.error("RESPONE HTTP CODE: " + statusCode);
    } catch (RestClientException ex) {
      ex.printStackTrace();
    }
    return null;
  }

  protected Object postData(Object request) {
    String url = baseRequestURL;
    final PostMethod method = new PostMethod(url);
    method.addRequestHeader("Content-Type", "application/json");
    try {
      ObjectMapper mapper = new ObjectMapper();
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

      final String JSON_STRING = mapper.writeValueAsString(request);
      StringRequestEntity requestEntity = new StringRequestEntity(JSON_STRING, "application/json", "UTF-8");

      method.setRequestEntity(requestEntity);
      method.addRequestHeader("Authorization", "Bearer 442w0sz23z93nnjeve46");
      int response = client.executeMethod(method);
      String json = null;
      if (response == HttpStatus.SC_OK) {
        json = method.getResponseBodyAsString();
        if (json != null) {
          LOG.info("Response: " + json);
        }
      } else {
        LOG.info("ResponseCode: " + response);
      }

    } catch (Exception e) {
      LOG.error("Exception", e);
    } finally {
      method.releaseConnection();
    }
    return null;
  }
}
