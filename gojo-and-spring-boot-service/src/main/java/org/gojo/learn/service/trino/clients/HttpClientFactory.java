package org.gojo.learn.service.trino.clients;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.gojo.learn.service.trino.enums.ClientType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HttpClientFactory<T> {

  private final Map<ClientType, BaseHttpClient<T>> registry;

  @Autowired
  public HttpClientFactory(Set<BaseHttpClient<T>> strategies) {
    registry = strategies.stream()
        .collect(
            Collectors.toMap(
                BaseHttpClient::getType, strategy -> strategy
            )
        );
  }

  public BaseHttpClient<T> getClient(ClientType clientType) {

    return Optional.ofNullable(registry.get(clientType))
        .orElseThrow(
            () -> new RuntimeException("Implementation of Client Not Registered"));

  }

}
