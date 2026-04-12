package org.gojo.learn.service.trino.constants;

import java.util.concurrent.TimeUnit;

public class Constants {

  public static class OkhttpTrinoConstants {

    public static String USER_NAME_KEY = "userName";
    public static String PASSWORD_KEY = "password";
    public static String REFRESH_TOKEN_KEY = "refreshToken";
    public static String TOKEN_GRANT_TYPE_KEY = "tokenGrantType";
    public static String TOKEN_GRANT_TYPE_VALUE = "refresh_token";

  }

  public static class TrinoAccessToken {

    public static final String TRINO_ACCESS_TOKEN_BY_USER_NAMESPACE = "trino_access_token_by_user";
    public static final TimeUnit TTL_TIME_UNIT = TimeUnit.HOURS;
    public static final String USER_NAME = "user_name";

  }

  public static class TrinoRefreshToken {

    public static final String TRINO_REFRESH_TOKEN_BY_USER_NAMESPACE = "trino_refresh_token_by_user";
    public static final TimeUnit TTL_TIME_UNIT = TimeUnit.HOURS;
    public static final String USER_NAME = "user_name";
    public static final int TTL_MINUS_FACTOR = 720;

  }

}
