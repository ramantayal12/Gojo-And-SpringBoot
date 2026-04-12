package org.gojo.learn.service.trino.client;


import static org.gojo.learn.service.trino.util.TrinoUtils.getAccessTokenRequestBodyMap;
import static org.gojo.learn.service.trino.util.TrinoUtils.getRefreshTokenRequestBodyMap;

import org.gojo.learn.service.trino.clients.HttpClientFactory;
import org.gojo.learn.service.trino.enums.ClientType;

public abstract class ClientConnector<T> {

  private final ClientType clientType;
  private final String baseURL;
  private final String username;
  private final String password;
  private final HttpClientFactory<T> httpClientFactory;

  public ClientConnector(
      ClientType clientType,
      String baseURL,
      String username,
      String password,
      HttpClientFactory<T> httpClientFactory) {
    this.clientType = clientType;
    this.baseURL = baseURL;
    this.username = username;
    this.password = password;
    this.httpClientFactory = httpClientFactory;
  }

  public T getAccessToken() {
    return httpClientFactory.getClient(clientType).postCall(baseURL,
        getAccessTokenRequestBodyMap(username, password));
  }

  public T getRefreshToken(String refreshToken) {
    return httpClientFactory.getClient(clientType).postCall(baseURL,
        getRefreshTokenRequestBodyMap(username, password, refreshToken));
  }

}
