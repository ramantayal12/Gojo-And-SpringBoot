package org.gojo.learn.controller;

import org.gojo.learn.service.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/starting")
public class HelloRestController {

  private final String appName;
  private final IdGenerator idGenerator;

  /**
   * @Value will pick value for key "spring.application.name" from application.properties file
   */
  @Autowired
  public HelloRestController(@Value("${spring.application.name}") String appName,
      IdGenerator idGenerator) {
    this.appName = appName;
    this.idGenerator = idGenerator;
  }

  @GetMapping("/hello")
  public String sayHi() {
    return "App Name : " + appName;
  }

  /**
   * @RequestParam is key-value in postman
   * @PathVariable is pathParam that we have to specify in path
   */
  @GetMapping("/getUniqueWorkflowId")
  public String getRandomIdUsingSecureRandom(@RequestParam("prefix") String prefix) {

    return idGenerator.generateId(prefix);

  }

}
