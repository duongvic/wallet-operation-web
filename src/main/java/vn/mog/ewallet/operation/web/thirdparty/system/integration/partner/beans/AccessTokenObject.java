package vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.beans;

import java.util.List;

public class AccessTokenObject {
  private List<String> authorities;
  private Long cif;
  private String user_name;
  private Boolean verified_email;
  private Integer type;
  private String client_id;
  private Boolean verified_phone;
  private String phone;
  private List<String> scope;
  private Long id;
  private String fullname;
  private Long exp;
  private String jti;
  private String email;

  public List<String> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(List<String> authorities) {
    this.authorities = authorities;
  }

  public Long getCif() {
    return cif;
  }

  public void setCif(Long cif) {
    this.cif = cif;
  }

  public String getUser_name() {
    return user_name;
  }

  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }

  public Boolean getVerified_email() {
    return verified_email;
  }

  public void setVerified_email(Boolean verified_email) {
    this.verified_email = verified_email;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getClient_id() {
    return client_id;
  }

  public void setClient_id(String client_id) {
    this.client_id = client_id;
  }

  public Boolean getVerified_phone() {
    return verified_phone;
  }

  public void setVerified_phone(Boolean verified_phone) {
    this.verified_phone = verified_phone;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public List<String> getScope() {
    return scope;
  }

  public void setScope(List<String> scope) {
    this.scope = scope;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public Long getExp() {
    return exp;
  }

  public void setExp(Long exp) {
    this.exp = exp;
  }

  public String getJti() {
    return jti;
  }

  public void setJti(String jti) {
    this.jti = jti;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "AccessTokenObject{" +
        "authorities=" + authorities +
        ", cif=" + cif +
        ", user_name='" + user_name + '\'' +
        ", verified_email=" + verified_email +
        ", type=" + type +
        ", client_id='" + client_id + '\'' +
        ", verified_phone=" + verified_phone +
        ", phone='" + phone + '\'' +
        ", scope=" + scope +
        ", id=" + id +
        ", fullname='" + fullname + '\'' +
        ", exp=" + exp +
        ", jti='" + jti + '\'' +
        ", email='" + email + '\'' +
        '}';
  }
}
