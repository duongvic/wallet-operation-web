package vn.mog.framework.util.tools.random;

import java.security.SecureRandom;

public interface IRandomRepository {
  public void clear();

  public SecureRandom getSecureRandom();
}
