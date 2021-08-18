package vn.mog.ewallet.operation.web.thirdparty.system.integration.partner.pms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeneralResponse<T> {
  private String code;
  private String msg;
  private T data;


  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }
  public String getMsg() {
    return msg;
  }
  public void setMsg(String msg) {
    this.msg = msg;
  }
  public T getData() {
    return data;
  }
  public void setData(T data) {
    this.data = data;
  }
  
}
