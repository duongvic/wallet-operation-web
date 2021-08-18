package vn.mog.ewallet.operation.web.thirdparty.system.integration.payment.contract.system.account;

public class GetGoogleAuthenticatorInfoResponse extends GetGoogleAuthenticatorInfoResponseType {

  private String secretKey;
  private String url;

  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}
