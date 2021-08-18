package vn.mog.framework.security.impl;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;
import vn.mog.framework.security.api.CallerInformation;
import vn.mog.framework.security.api.ICallerUtils;
import vn.mog.framework.security.api.ICustomerAware;
import vn.mog.framework.security.api.MobiliserWebAuthenticationDetails;

@Service
public class CallerUtilsImpl implements ICallerUtils {

  private static final Log LOGGER = LogFactory.getLog(CallerUtilsImpl.class);

  private static LinkedHashMap<String, Object> principal;

  @Override
  public long getCallerId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null) {
      throw new IllegalArgumentException(
          "No authentication object found in security context. Check configuration?");
    }

    return getActorId(authentication);
  }

  @Override
  public CallerInformation getCallerInformation() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    principal = null;
    if (authentication == null) {
      throw new IllegalArgumentException(
          "No authentication object found in security context. Check configuration?");
    }

    String accessToken = getAccessToken(authentication);

//    Gson gson = new Gson();
//    System.out.println("authentication = " + gson.toJson(authentication));

    Long actorId = Long.valueOf(getActorId(authentication));
    String actorCif = getActorCif(authentication);
    String actorIdentifier = getActorIdentifier(authentication);
    int actorType = getActorType(authentication);
    String msisdn = getMsisdn(authentication);
    String email = getEmail(authentication);

    List<String> actorPrivs = new ArrayList<>();

    for (GrantedAuthority grantedAuth : authentication.getAuthorities()) {
      actorPrivs.add(grantedAuth.getAuthority());
    }

    Object authDetails = authentication.getDetails();
    String userAgent;
    String ip;
    if (authDetails instanceof MobiliserWebAuthenticationDetails) {
      ip = ((MobiliserWebAuthenticationDetails) authDetails).getRemoteAddress();
      userAgent = ((MobiliserWebAuthenticationDetails) authDetails).getUserAgent();
    } else {
      ip = null;
      userAgent = null;
    }

    return new CallerInformation(accessToken, actorId, actorCif, actorType, actorIdentifier,
        actorPrivs, ip, userAgent, msisdn, email);
  }

  private String getMsisdn(Authentication authentication) {
    Object principal = authentication.getPrincipal();
    if (principal instanceof ICustomerAware) {
      return ((ICustomerAware) principal).getPhone();
    }

    Object phone = getOAuth2Details(authentication, "phone");
    if (phone != null) {
      try {
        return (String) phone;
      } catch (ClassCastException e) {
        LOGGER.error(e.getMessage(), e);
      }
    }
    return null;
  }

  private String getEmail(Authentication authentication) {
    Object principal = authentication.getPrincipal();
    if (principal instanceof ICustomerAware) {
      return ((ICustomerAware) principal).getEmail();
    }

    Object email = getOAuth2Details(authentication, "email");
    if (email != null) {
      try {
        return (String) email;
      } catch (ClassCastException e) {
        LOGGER.error(e.getMessage(), e);
      }
    }
    return null;
  }

  @Override
  public boolean hasPrivilege(final String privilege) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null) {
      throw new IllegalArgumentException(
          "No authentication object found in security context. Check configuration?");
    }

    final String prefix = "ROLE_";
    String strPrivilege = privilege.toUpperCase().trim();
    if (!strPrivilege.startsWith(prefix)) {
      strPrivilege = prefix + strPrivilege;
    }

    for (GrantedAuthority grantedAuth : authentication.getAuthorities()) {
      if (StringUtils.equals(strPrivilege, grantedAuth.getAuthority())
          || StringUtils.equals(strPrivilege.replace(prefix, ""), grantedAuth.getAuthority())) {
        return true;
      }
    }

    return false;
  }

  private String getAccessToken(Authentication authentication) {
    Object details = authentication.getDetails();
    if (details instanceof OAuth2AuthenticationDetails) {
      return ((OAuth2AuthenticationDetails) details).getTokenValue();
    }
    return authentication.getCredentials().toString();
  }

  private long getActorId(Authentication authentication) {
    Object principal = authentication.getPrincipal();
    if (principal instanceof ICustomerAware) {
      return ((ICustomerAware) principal).getId();
    }

    Object actorType = getOAuth2Details(authentication, "id");
    if (actorType != null) {
      try {
        return (Integer) actorType;
      } catch (ClassCastException e) {
        LOGGER.error(e.getMessage(), e);
      }
    }

    return 0L;
  }

  private String getActorCif(Authentication authentication) {
    Object principal = authentication.getPrincipal();
    if (principal instanceof ICustomerAware) {
      return ((ICustomerAware) principal).getCif();
    }

    Object actorCif = getOAuth2Details(authentication, "cif");
    if (actorCif != null) {
      return actorCif.toString();
    }

    return null;
  }

  private String getActorIdentifier(Authentication authentication) {
    Object principal = authentication.getPrincipal();
    if (principal instanceof ICustomerAware) {
      return ((ICustomerAware) principal).getUsername();
    }

    Object username = getOAuth2Details(authentication, "username");
    if (username == null) {
      username = getOAuth2Details(authentication, "fullname");
      if (username == null) {
        username = getOAuth2Details(authentication, "phone");
      }
    }
    if (username != null) {
      return username.toString();
    }

    return authentication.getName();
  }

  private int getActorType(Authentication authentication) {
    Object principal = authentication.getPrincipal();
    if (principal instanceof ICustomerAware) {
      return ((ICustomerAware) principal).getType();
    }

    Object actorType = getOAuth2Details(authentication, "type");
    if (actorType != null) {
      try {
        return (Integer) actorType;
      } catch (ClassCastException e) {
        LOGGER.error(e.getMessage(), e);
      }
    }

    return 0;
  }

  @SuppressWarnings("unchecked")
  private static Object getOAuth2Details(Authentication authentication, String attributeName) {

    try {
      if (authentication instanceof OAuth2Authentication) {
        if (principal == null) {
          OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
          LinkedHashMap<String, LinkedHashMap<String, Object>> detailsMap
              = (LinkedHashMap<String, LinkedHashMap<String, Object>>)
              oAuth2Authentication.getUserAuthentication().getDetails();

          principal = detailsMap.get("principal");
        }

        return principal.get(attributeName);
      }
    } catch (Exception e) {
      LOGGER.debug("Can not get Authentication's Principal");
    }

    return null;
  }


}
