package org.gojo.learn.service.controller.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Trace - Only when I would be "tracing" the code and trying to find one part of a function
 * specifically.
 * @Debug - Information that is diagnostically helpful to people more than just developers (IT,
 * sysadmins, etc.).
 * @Info - Generally useful information to log (service start/stop, configuration assumptions, etc).
 * Info I want to always have available but usually don't care about under normal circumstances.
 * This is my out-of-the-box config level.
 * @Warn - Anything that can potentially cause application oddities, but for which I am
 * automatically recovering. (Such as switching from a primary to backup server, retrying an
 * operation, missing secondary data, etc.)
 * @Error - Any error which is fatal to the operation, but not the service or application (can't
 * open a required file, missing data, etc.). These errors will force user (administrator, or direct
 * user) intervention. These are usually reserved (in my apps) for incorrect connection strings,
 * missing services, etc.
 * @Fatal - Any error that is forcing a shutdown of the service or application to prevent data loss
 * (or further data loss). I reserve these only for the most heinous errors and situations where
 * there is guaranteed to have been data corruption or loss
 */
@RestController
public class LoggingController {

  Logger logger = LoggerFactory.getLogger(LoggingController.class);

  @GetMapping("/logging")
  public String index() {

    logger.trace("A TRACE Message");
    logger.debug("A DEBUG Message");
    logger.info("An INFO Message");
    logger.warn("A WARN Message");
    logger.error("An ERROR Message");

    return "Howdy! Check out the Logs to see the output...";
  }

}
