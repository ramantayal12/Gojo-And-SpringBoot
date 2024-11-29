package org.gojo.learn.service.trino.util;

import static org.gojo.learn.service.trino.constants.Constants.OkhttpTrinoConstants.PASSWORD_KEY;
import static org.gojo.learn.service.trino.constants.Constants.OkhttpTrinoConstants.REFRESH_TOKEN_KEY;
import static org.gojo.learn.service.trino.constants.Constants.OkhttpTrinoConstants.TOKEN_GRANT_TYPE_KEY;
import static org.gojo.learn.service.trino.constants.Constants.OkhttpTrinoConstants.TOKEN_GRANT_TYPE_VALUE;
import static org.gojo.learn.service.trino.constants.Constants.OkhttpTrinoConstants.USER_NAME_KEY;

import org.gojo.learn.service.trino.entity.CachedAccessTokenEntity;
import java.util.Map;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;

public class TrinoUtils {

  @NotNull
  public static Map<String, String> getAccessTokenRequestBodyMap(String userName, String password) {
    return Map.of(
        USER_NAME_KEY, userName,
        PASSWORD_KEY, password);
  }

  @NotNull
  public static Map<String, String> getRefreshTokenRequestBodyMap(
      String userName,
      String password,
      String refreshToken) {
    return Map.of(
        USER_NAME_KEY, userName,
        PASSWORD_KEY, password,
        REFRESH_TOKEN_KEY, refreshToken,
        TOKEN_GRANT_TYPE_KEY, TOKEN_GRANT_TYPE_VALUE);
  }

  public static @NotNull RequestBody getRequestBody(Map<String, String> data) {
    // Build the form body
    FormBody.Builder formBuilder = new FormBody.Builder();
    for (Map.Entry<String, String> entry : data.entrySet()) {
      formBuilder.add(entry.getKey(), entry.getValue());
    }
    return formBuilder.build();
  }

  /**
   *
   * Cached trino access token entity builder
   * @param accessToken
   * @return
   */
  public static CachedAccessTokenEntity getCachedTrinoAccessTokenEntity(String accessToken) {
    return CachedAccessTokenEntity.builder()
        .accessToken(accessToken)
        .build();
  }

}
