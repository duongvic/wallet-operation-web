package vn.mog.framework.security.api;

import java.security.Principal;
import org.springframework.security.core.Authentication;

public interface ICustomerAware extends Principal {
  static ICustomerAware from(Principal principal) {

    if (principal instanceof Authentication) {
      Object p = ((Authentication) principal).getPrincipal();
      if (p instanceof ICustomerAware) {
        return (ICustomerAware) p;
      }
    }
    if (principal instanceof ICustomerAware) {
      return (ICustomerAware) principal;
    }
    return null;
  }

  Long getId();

  void setId(Long id);

  String getCif();

  String getUsername();

  String getFullname();

  String getEmail();

  String getPhone();

  Integer getType();

  void setCif(String cif);

  void setUsername(String username);

  void setFullname(String fullname);

  void setEmail(String email);

  void setPhone(String phone);

  void setType(Integer type);

  boolean isVerifiedEmail();

  void setVerifiedEmail(boolean verified);

  boolean isVerifiedPhone();

  void setVerifiedPhone(boolean verified);
}
