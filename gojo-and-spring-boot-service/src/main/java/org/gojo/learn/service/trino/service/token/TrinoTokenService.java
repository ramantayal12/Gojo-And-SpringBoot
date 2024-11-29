package org.gojo.learn.service.trino.service.token;

import static org.gojo.learn.service.trino.util.TrinoUtils.getCachedTrinoAccessTokenEntity;

import org.gojo.learn.service.trino.client.TrinoOkhttpConnector;
import org.gojo.learn.service.trino.dto.TrinoAccessTokenResponseDto;
import org.gojo.learn.service.trino.entity.CachedAccessTokenEntity;
import org.gojo.learn.service.trino.entity.CachedRefreshTokenEntity;
import org.gojo.learn.service.trino.enums.PlatformType;
import org.gojo.learn.service.trino.redis.repository.TrinoAccessTokenRedisRepository;
import org.gojo.learn.service.trino.redis.repository.TrinoRefreshTokenRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrinoTokenService extends BaseTokenService<String>{

  private final TrinoAccessTokenRedisRepository trinoAccessTokenRedisRepository;
  private final TrinoRefreshTokenRedisRepository trinoRefreshTokenRedisRepository;

  private final TrinoOkhttpConnector clientConnector;

  private final static int refreshTokenTtl = 2;
  private final static int accessTokenTtl = 1;

  @Autowired
  public TrinoTokenService(
      TrinoAccessTokenRedisRepository trinoAccessTokenRedisRepository,
      TrinoRefreshTokenRedisRepository trinoRefreshTokenRedisRepository,
      TrinoOkhttpConnector clientConnector) {
    this.trinoAccessTokenRedisRepository = trinoAccessTokenRedisRepository;
    this.trinoRefreshTokenRedisRepository = trinoRefreshTokenRedisRepository;
    this.clientConnector = clientConnector;
  }

  @Override
  public String getAccessToken(String key) {

    CachedAccessTokenEntity cachedAccessTokenEntity = trinoAccessTokenRedisRepository.getAccessToken(
        key);
    if( cachedAccessTokenEntity != null ) return cachedAccessTokenEntity.getAccessToken();

    TrinoAccessTokenResponseDto trinoAccessTokenResponseDto = clientConnector.getAccessToken();
    String accessToken = trinoAccessTokenResponseDto.getAccessToken();


    trinoAccessTokenRedisRepository.saveAccessToken(key,
        getCachedTrinoAccessTokenEntity(accessToken), accessTokenTtl);
    return accessToken;

  }

  @Override
  public String getRefreshToken(String key) {

    CachedRefreshTokenEntity cachedRefreshTokenEntity = trinoRefreshTokenRedisRepository.getTrinoRefreshToken(
        key);
    if( cachedRefreshTokenEntity != null ) return cachedRefreshTokenEntity.getRefreshToken();

    TrinoAccessTokenResponseDto trinoAccessTokenResponseDto = clientConnector.getAccessToken();
    String refreshToken = trinoAccessTokenResponseDto.getRefreshToken();

    trinoRefreshTokenRedisRepository.saveRefreshToken(key,
        CachedRefreshTokenEntity.builder()
            .refreshToken(refreshToken)
            .build(), refreshTokenTtl);
    return refreshToken;

  }

  @Override
  public PlatformType getType() {
    return PlatformType.TRINO;
  }
}
