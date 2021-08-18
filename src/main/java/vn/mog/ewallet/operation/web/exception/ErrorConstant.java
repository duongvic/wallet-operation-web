package vn.mog.ewallet.operation.web.exception;

public enum ErrorConstant {
  SUCCESS("PX00000", "Success"),
  INVALID_REQUEST("PX00099", "Invalid request"),

  REQUIRED_NAME_ERROR("PX00001", "Require name"),
  REQUIRED_AGE_ERROR("PX00002", "Require age"),
  REQUIRED_ADDRESS_ERROR("PX00003", "Require address"),
  REQUIRED_PARAMS_ERROR("PX000012", "Require params"),

  REQUIRED_MSISDN("PX10001", "required msisdn"),
  REQUIRED_CUSTOMER_TYPE("PX10002", "required customer type"),
  REQUIRED_EMAIL("PX10003", "required email"),

  DUPLICATED_MSISDN_ERROR("PX00020", "Duplicated Msisdn"),
  CREDENTIAL_NOT_EXISTED("PX00030", "Credential not existed"),

  PROVIDER_NOT_AVAILABLE_ERROR("PX000100", "Provider not available"),
  PROVIDER_ERROR("PX000101", "Provider error"),
  GENERAL_ERROR("PX99999", "General error");

  private String code;
  private String message;

  ErrorConstant(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String code() {
    return code;
  }

  public String message() {
    return message;
  }
}
