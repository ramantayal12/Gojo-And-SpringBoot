package org.gojo.learn.service.trino.client;

import org.gojo.learn.service.trino.clients.HttpClientFactory;
import org.gojo.learn.service.trino.dto.TrinoAccessTokenResponseDto;
import org.gojo.learn.service.trino.enums.ClientType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TrinoOkhttpConnector extends ClientConnector<TrinoAccessTokenResponseDto> {

  @Autowired
  public TrinoOkhttpConnector(
      @Value("${data.platform.base.url}") String baseURL,
      @Value("${trino.username}") String username,
      @Value("${trino.password}") String password,
      HttpClientFactory<TrinoAccessTokenResponseDto> httpClientFactory) {
    super(ClientType.OKHTTP, baseURL, username, password, httpClientFactory);
  }

}
