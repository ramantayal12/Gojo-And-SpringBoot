package org.gojo.learn.service.trino.datasource;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.gojo.learn.service.trino.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataSourceFactory {

  private final Map<DataSourceType, BaseDataSource> registry;

  @Autowired
  public DataSourceFactory(Set<BaseDataSource> strategies) {
    registry = strategies.stream()
        .collect(
            Collectors.toMap(
                BaseDataSource::getType, strategy -> strategy
            )
        );
  }

  public BaseDataSource getDataSource(DataSourceType dataSourceType) {
    return Optional.ofNullable(registry.get(dataSourceType))
        .orElseThrow(
            () -> new RuntimeException("Implementation of DataSource Not Registered"));
  }
}
