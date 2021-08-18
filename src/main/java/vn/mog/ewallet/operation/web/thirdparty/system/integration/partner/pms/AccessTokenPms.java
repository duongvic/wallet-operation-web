package vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms;

import java.io.Serializable;

public class AccessTokenPms implements Serializable {

  private static final long serialVersionUID = 1L;

  String access_token;
  String token_type;
  String refresh_token;
  Long expires_in;
  String scope;
  Long id;
  Long cif;
  String email;
  Boolean verified_email;
  String phone;
  Boolean verified_phone;
  String fullname;
  Integer type;
  String jti;
  String language;

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getAccess_token() {
    return access_token;
  }

  public void setAccess_token(String access_token) {
    this.access_token = access_token;
  }

  public String getToken_type() {
    return token_type;
  }

  public void setToken_type(String token_type) {
    this.token_type = token_type;
  }

  public String getRefresh_token() {
    return refresh_token;
  }

  public void setRefresh_token(String refresh_token) {
    this.refresh_token = refresh_token;
  }

  public Long getExpires_in() {
    return expires_in;
  }

  public void setExpires_in(Long expires_in) {
    this.expires_in = expires_in;
  }

  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCif() {
    return cif;
  }

  public void setCif(Long cif) {
    this.cif = cif;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Boolean getVerified_email() {
    return verified_email;
  }

  public void setVerified_email(Boolean verified_email) {
    this.verified_email = verified_email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Boolean getVerified_phone() {
    return verified_phone;
  }

  public void setVerified_phone(Boolean verified_phone) {
    this.verified_phone = verified_phone;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getJti() {
    return jti;
  }

  public void setJti(String jti) {
    this.jti = jti;
  }

  @Override
  public String toString() {
    return "AccessTokenPms{" +
        "access_token='" + access_token + '\'' +
        ", token_type='" + token_type + '\'' +
        ", refresh_token='" + refresh_token + '\'' +
        ", expires_in=" + expires_in +
        ", scope='" + scope + '\'' +
        ", id=" + id +
        ", cif=" + cif +
        ", email='" + email + '\'' +
        ", verified_email=" + verified_email +
        ", phone='" + phone + '\'' +
        ", verified_phone=" + verified_phone +
        ", fullname='" + fullname + '\'' +
        ", type=" + type +
        ", jti='" + jti + '\'' +
        ", language='" + language + '\'' +
        '}';
  }
}
