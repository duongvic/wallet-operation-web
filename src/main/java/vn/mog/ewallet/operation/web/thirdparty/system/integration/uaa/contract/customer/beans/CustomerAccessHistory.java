package vn.mog.ewallet.operation.web.thirdparty.system.integration.uaa.contract.customer.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

public class CustomerAccessHistory {

  private Long id;

  private Long customerId;

  private Date loginDate;

  private Date logoutDate;

  private String ip;

  private String location;

  private String userAgent;

  private String channel;

  private String authenticationRefId;

  private String deviceOs;

  private String deviceName;

  private String stie;

  public String getDeviceOs() {
    return deviceOs == null ? StringUtils.EMPTY : deviceOs;
  }

  public void setDeviceOs(String deviceOs) {
    this.deviceOs = deviceOs;
  }

  public String getDeviceName() {
    return deviceName == null ? StringUtils.EMPTY : deviceName.toLowerCase();
  }

  public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
  }

  public String getStie() {
    return stie == null ? StringUtils.EMPTY : stie;
  }

  public void setStie(String stie) {
    this.stie = stie;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public Date getLoginDate() {
    return loginDate;
  }

  public void setLoginDate(Date loginDate) {
    this.loginDate = loginDate;
  }

  public Date getLogoutDate() {
    return logoutDate;
  }

  public void setLogoutDate(Date logoutDate) {
    this.logoutDate = logoutDate;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public String getAuthenticationRefId() {
    return authenticationRefId;
  }

  public void setAuthenticationRefId(String authenticationRefId) {
    this.authenticationRefId = authenticationRefId;
  }

  @JsonProperty("formatLoginDate")
  public String formatLoginDate() {
    return DateFormatUtils.format(this.loginDate, "dd/MM/yyyy - HH:mm:ss");
  }


}
