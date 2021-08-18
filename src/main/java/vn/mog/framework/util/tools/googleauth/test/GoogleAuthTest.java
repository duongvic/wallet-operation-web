package vn.mog.framework.util.tools.googleauth.test;

import vn.mog.framework.util.tools.googleauth.GoogleAuthenticator;
import vn.mog.framework.util.tools.googleauth.GoogleAuthenticatorKey;
import vn.mog.framework.util.tools.googleauth.GoogleAuthenticatorQRGenerator;

public class GoogleAuthTest {
  public static void main(String[] args) {
    final String username = "admin@zo-ta.com";
    GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();
    final GoogleAuthenticatorKey key = googleAuthenticator.createCredentials();
    final String authSecret = key.getKey();
    final String authURL =
        GoogleAuthenticatorQRGenerator.getOtpAuthURL("mytopup.vn", username, authSecret);
    System.out.println(authSecret);
    System.out.println(authURL);
  }
}
