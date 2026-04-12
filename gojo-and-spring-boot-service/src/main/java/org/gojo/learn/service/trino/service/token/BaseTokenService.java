package org.gojo.learn.service.trino.service.token;

import org.gojo.learn.service.trino.enums.PlatformType;

public abstract class BaseTokenService<T> {

  public abstract T getAccessToken(String key);
  public abstract T getRefreshToken(String key);

  public abstract PlatformType getType();
}
