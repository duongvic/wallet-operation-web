package vn.mog.ewallet.operation.web.contract;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import vn.mog.ewallet.operation.web.exception.MessageNotify;
import vn.mog.framework.contract.base.MobiliserResponseType;

public class GenericResponse {
  private String message;
  private int code;

  public GenericResponse(final String message) {
    super();
    this.message = message;
  }

  public GenericResponse(final int code, final String message) {
    super();
    this.message = message;
    this.code = code;
  }

  public GenericResponse(final Object response) {
    if (response == null) {
      this.code = MessageNotify.ERROR_CODE;
      this.message = MessageNotify.ERROR_NAME;
    } else {
      MobiliserResponseType mobResponse = (MobiliserResponseType) response;
      this.code = mobResponse.getStatus().getCode();
      this.message = mobResponse.getStatus().getValue();
    }
  }

  public GenericResponse(List<ObjectError> allErrors, int code) {
    this.code = code;
    String temp = allErrors.stream().map(e -> {
      if (e instanceof FieldError) {
        return "{\"field\":\"" + ((FieldError) e).getField() + "\",\"defaultMessage\":\"" + e.getDefaultMessage() + "\"}";
      } else {
        return "{\"object\":\"" + e.getObjectName() + "\",\"defaultMessage\":\"" + e.getDefaultMessage() + "\"}";
      }
    }).collect(Collectors.joining(","));
    this.message = "[" + temp + "]";
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public void setCode(final int code) {
    this.code = code;
  }

}