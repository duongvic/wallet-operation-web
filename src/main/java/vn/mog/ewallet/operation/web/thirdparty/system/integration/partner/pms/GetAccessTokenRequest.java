package vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms;

import java.io.Serializable;

public class GetAccessTokenRequest implements Serializable {

  private static final long serialVersionUID = 1L;
  private String zotaToken;
  private String profile;

  public String getZotaToken() {
    return zotaToken;
  }

  public void setZotaToken(String zotaToken) {
    this.zotaToken = zotaToken;
  }

  public String getProfile() {
    return profile;
  }

  public void setProfile(String profile) {
    this.profile = profile;
  }
}
