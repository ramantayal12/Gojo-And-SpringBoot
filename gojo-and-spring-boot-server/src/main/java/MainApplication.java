import static org.gojo.learn.commons.constants.RedisConstants.CACHE_NAME;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * we’re using @SpringBootApplication as our primary application configuration class. Behind the
 * scenes, that’s equivalent to @Configuration,
 *
 * @EnableAutoConfiguration, and @ComponentScan together. the annotation @EnableCaching to the
 * starter class. It will trigger a post-processor that inspects every Spring bean for the presence
 * of caching annotations on public methods.
 */
@SpringBootApplication( scanBasePackages = {
    "org.gojo.learn.server",
    "org.gojo.learn.service",
    "org.gojo.learn.commons",
    "org.springframework.data"
})
@ComponentScan(basePackages = {
    "org.gojo.learn.server",
    "org.gojo.learn.service",
    "org.gojo.learn.commons"
}
)
@EntityScan(basePackages = {
    "org.gojo.learn.server",
    "org.gojo.learn.service",
    "org.gojo.learn.commons"}
)
@EnableJpaRepositories(basePackages = {
    "org.gojo.learn.server",
    "org.gojo.learn.service",
    "org.gojo.learn.commons"})
@EnableJpaAuditing
@EnableCaching
@EnableScheduling
@EnableMongoRepositories(basePackages = {
    "org.gojo.learn.server",
    "org.gojo.learn.service",
    "org.gojo.learn.commons"})
public class MainApplication {

  public static void main(String[] args) {
    SpringApplication.run(MainApplication.class, args);
  }

  @Bean
  public CacheManager cacheManager() {

    // this method is required for redis caching
    return new ConcurrentMapCacheManager(CACHE_NAME);

  }

}