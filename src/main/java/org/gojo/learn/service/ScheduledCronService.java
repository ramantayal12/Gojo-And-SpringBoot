package org.gojo.learn.service;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledCronService {

  Logger logger = LoggerFactory.getLogger(ScheduledCronService.class);

  @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
  public void printMessage() {
    logger.info("Hello Cron");
  }

}
