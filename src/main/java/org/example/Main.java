package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
  * we’re using @SpringBootApplication as our primary application
  * configuration class. Behind the scenes, that’s equivalent to @Configuration,
  * @EnableAutoConfiguration, and @ComponentScan together.
 */
@SpringBootApplication
public class Main {

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  // random comment for testing github ruleset

}