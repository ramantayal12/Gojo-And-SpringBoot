package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

/**
 * we’re using @SpringBootApplication as our primary application configuration class. Behind the
 * scenes, that’s equivalent to @Configuration,
 *<p></p>
 * @EnableAutoConfiguration, and @ComponentScan together.
 * the annotation @EnableCaching to the starter class. It will trigger a post-processor that
 * inspects every Spring bean for the presence of caching annotations on public methods.
 */
@SpringBootApplication
@EnableCaching
// @EnableSwagger2
public class Main {

  @Bean
  public CacheManager cacheManager() {

    // this method is required for redis caching
    return new ConcurrentMapCacheManager("entities");
  }

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

}