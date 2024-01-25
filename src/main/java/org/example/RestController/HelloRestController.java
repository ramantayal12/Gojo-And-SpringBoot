package org.example.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

  private final String appName;

  @Autowired
  public HelloRestController(@Value("${spring.application.name}") String appName) {
    this.appName = appName;
  }

  @RequestMapping("/hello")
  public String sayHi() {
    return "App Name : " + appName;
  }

}
