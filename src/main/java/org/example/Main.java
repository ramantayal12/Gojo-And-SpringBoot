package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * we’re using @SpringBootApplication as our primary application configuration class. Behind the
 * scenes, that’s equivalent to @Configuration,
 *
 * @EnableAutoConfiguration, and @ComponentScan together.
 */

/**
 * the annotation @EnableCaching to the starter class. It will trigger a post-processor that
 * inspects every Spring bean for the presence of caching annotations on public methods.
 */
@SpringBootApplication
@EnableCaching
// @EnableSwagger2
public class Main {

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

}