package org.gojo.learn.service.service;

import org.gojo.learn.service.exception.BaseGojoException;
import org.gojo.learn.service.exception.StudentAlreadyExistsException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
public class RetryKafkaListener {

  int cnt = 0;

  @KafkaListener(
      topics = "retry-topic",
      groupId = "retry-group"
  )
  @RetryableTopic(
      attempts = "3",
      backoff = @Backoff(delay = 1000),
      include = StudentAlreadyExistsException.class
  )
  public void listen(String message) throws BaseGojoException {

    System.out.println("Received message : " + message);
    System.out.println("Count : " + cnt);

    if (cnt < 2) {
      throw new StudentAlreadyExistsException();
    }

  }

}
