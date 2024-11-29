package org.gojo.learn.service.trino.datasource;

import java.util.Properties;
import javax.sql.DataSource;
import org.gojo.learn.service.trino.enums.DataSourceType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public abstract class BaseDataSource {

  public static String ACCESS_TOKEN_KEY = "accessToken";
  public static String SSL_KEY = "SSL";
  public static String SOURCE_KEY = "source";
  public static String TIMEZONE_KEY = "timezone";

  public DataSource getDataSource(
      String accessToken,
      String ssl,
      String source,
      String driverClass,
      String url) {

    SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
    Properties properties = new Properties();
    properties.setProperty(ACCESS_TOKEN_KEY, accessToken);
    properties.setProperty(SSL_KEY, ssl);
    properties.setProperty(SOURCE_KEY, source);

    //Setting the timezone to UTC as any other timezone gets converted to UTC before making any request.
    properties.setProperty(TIMEZONE_KEY, "UTC");
    dataSource.setDriverClassName(driverClass);
    dataSource.setConnectionProperties(properties);
    dataSource.setUrl(url);
    return dataSource;

  }

  abstract public JdbcTemplate getJdbcTemplate();

  abstract public DataSourceType getType();

}
