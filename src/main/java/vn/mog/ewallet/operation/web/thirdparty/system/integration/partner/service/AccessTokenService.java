package vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.service;


import com.google.gson.Gson;
import java.util.Base64;
import vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.beans.AccessTokenObject;

public class AccessTokenService {

  public static String toJson(String accessToken){
    String[] split_string = accessToken.split("\\.");
    String base64EncodedBody = split_string[1];
//    Base64 base64Url = new Base64(true);
    String body = new String(Base64.getDecoder().decode(base64EncodedBody));
    return body;
  }

  public static AccessTokenObject jsonToObject(String body){
    Gson g = new Gson();
    AccessTokenObject accessTokenObject = g.fromJson(body, AccessTokenObject.class);
    return accessTokenObject;
  }

}
