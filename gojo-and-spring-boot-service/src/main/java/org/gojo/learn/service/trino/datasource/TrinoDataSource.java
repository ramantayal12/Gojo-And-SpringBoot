package org.gojo.learn.service.trino.datasource;

import org.gojo.learn.service.trino.config.TrinoConfig;
import org.gojo.learn.service.trino.service.token.TokenFactory;

public abstract class TrinoDataSource extends BaseDataSource {

  protected static final String URL_PREFIX = "jdbc:trino://";
  protected final TrinoConfig trinoConfig;
  protected final String trinoUserName;
  protected final TokenFactory<String> tokenFactory;

  protected TrinoDataSource(
      TokenFactory<String> tokenFactory,
      TrinoConfig trinoConfig,
      String trinoUserName
  ) {
    this.trinoConfig = trinoConfig;
    this.trinoUserName = trinoUserName;
    this.tokenFactory = tokenFactory;
  }

}
