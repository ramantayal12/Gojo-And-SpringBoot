package org.gojo.learn.service.trino.redis.repository;

import static org.gojo.learn.service.trino.constants.Constants.TrinoRefreshToken.TRINO_REFRESH_TOKEN_BY_USER_NAMESPACE;
import static org.gojo.learn.service.trino.constants.Constants.TrinoRefreshToken.TTL_MINUS_FACTOR;
import static org.gojo.learn.service.trino.constants.Constants.TrinoRefreshToken.TTL_TIME_UNIT;
import static org.gojo.learn.service.trino.constants.Constants.TrinoRefreshToken.USER_NAME;

import org.gojo.learn.service.trino.entity.CachedRefreshTokenEntity;
import io.micrometer.core.instrument.MeterRegistry;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrinoRefreshTokenRedisRepository {


  public CachedRefreshTokenEntity getTrinoRefreshToken(String key) {

    return null;
      //return get(key, CachedRefreshTokenEntity.class);

  }

  public void saveRefreshToken(
      String key,
      CachedRefreshTokenEntity cachedTrinoRefreshTokenEntity,
      int refreshTokenTtl
  ) {

//      this.save(key, cachedTrinoRefreshTokenEntity,
//          refreshTokenTtl, TTL_TIME_UNIT);
  }

}
