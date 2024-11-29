package org.gojo.learn.service.trino.service.token;

import com.groww.pay.upi.payment.groww_pay_upi_payment_service.trino.enums.PlatformType;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenFactory<T> {

  private final Map<PlatformType, BaseTokenService<T>> registry;

  @Autowired
  public TokenFactory(Set<BaseTokenService<T>> strategies) {
    this.registry = strategies.stream()
        .collect(
            Collectors.toMap(
                BaseTokenService::getType, strategy -> strategy
            )
        );;
  }

  public BaseTokenService<T> getClient(PlatformType type) {

    return Optional.ofNullable(registry.get(type))
        .orElseThrow(
            () -> new RuntimeException("Implementation of Token Service Not Registered"));

  }
}
