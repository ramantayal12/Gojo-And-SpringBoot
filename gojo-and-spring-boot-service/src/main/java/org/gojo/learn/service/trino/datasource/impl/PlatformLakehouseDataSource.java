package org.gojo.learn.service.trino.datasource.impl;


import org.gojo.learn.service.trino.config.TrinoConfig;
import org.gojo.learn.service.trino.datasource.TrinoDataSource;
import org.gojo.learn.service.trino.service.token.TokenFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PlatformLakehouseDataSource extends TrinoDataSource {

  private static final String databaseName = "platform_iceberg";
  private final String url;

  @Autowired
  public PlatformLakehouseDataSource(
      TokenFactory<String> tokenFactory,
      TrinoConfig trinoConfig,
      @Value("${trino.username}") String trinoUserName
  ) {
    super(tokenFactory, trinoConfig, trinoUserName);
    this.url =
        URL_PREFIX + trinoConfig.getHost() + ":" + trinoConfig.getPort() + "/" + databaseName;
  }

  @Override
  public JdbcTemplate getJdbcTemplate() {
    return new JdbcTemplate(getDataSource(
        tokenFactory.getClient(PlatformType.TRINO).getAccessToken(trinoUserName),
        trinoConfig.getSsl(),
        trinoConfig.getSource(),
        trinoConfig.getDriverClass(),
        url
    ));
  }

  @Override
  public DataSourceType getType() {
    return DataSourceType.PLATFORM_LAKEHOUSE;
  }

}
