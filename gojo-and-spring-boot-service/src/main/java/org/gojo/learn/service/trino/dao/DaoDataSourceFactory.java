package org.gojo.learn.service.trino.dao;

import org.gojo.learn.service.trino.enums.DataSourceType;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DaoDataSourceFactory {

  private final Map<DataSourceType, BaseDataSourceSourceDao> registry;

  @Autowired
  public DaoDataSourceFactory(Set<BaseDataSourceSourceDao> strategies) {
    registry = strategies.stream()
        .collect(
            Collectors.toMap(
                BaseDataSourceSourceDao::getType, strategy -> strategy
            )
        );
  }

  public BaseDataSourceSourceDao getDao(DataSourceType dataSourceType){
    return Optional.ofNullable(registry.get(dataSourceType))
        .orElseThrow(
            () -> new RuntimeException("Implementation of Dao Not Registered"));
  }
}
