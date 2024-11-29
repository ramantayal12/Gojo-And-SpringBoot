package org.gojo.learn.service.trino.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "trino")
public class TrinoConfig {

  private String driverClass;
  private String host;
  private String port;
  private String ssl;
  private String source;

}