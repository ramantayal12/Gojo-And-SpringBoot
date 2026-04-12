package org.gojo.learn.service.trino.redis.repository;

import static org.gojo.learn.service.trino.constants.Constants.TrinoAccessToken.TRINO_ACCESS_TOKEN_BY_USER_NAMESPACE;
import static org.gojo.learn.service.trino.constants.Constants.TrinoAccessToken.TTL_TIME_UNIT;

import org.gojo.learn.service.trino.entity.CachedAccessTokenEntity;
import org.springframework.stereotype.Component;

@Component
public class TrinoAccessTokenRedisRepository  {

  public CachedAccessTokenEntity getAccessToken(String key) {

    return null;
      //return get(key, CachedAccessTokenEntity.class);

  }

  public void saveAccessToken(String key,
      CachedAccessTokenEntity cachedTrinoAccessTokenEntity,
      int accessTokenTtl) {

//      this.save(key, cachedTrinoAccessTokenEntity,
//          accessTokenTtl, TTL_TIME_UNIT);
//
  }


}
