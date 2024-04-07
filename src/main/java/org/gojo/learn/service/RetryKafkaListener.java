package org.gojo.learn.service;

import org.gojo.learn.exception.StudentAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
public class RetryKafkaListener {

  private final KafkaProducerService kafkaProducerService;
  int cnt = 0;

  @Autowired
  public RetryKafkaListener(KafkaProducerService kafkaProducerService) {
    this.kafkaProducerService = kafkaProducerService;
  }

  @KafkaListener(topics = "retry-topic", groupId = "retry-group")
  @RetryableTopic(
      attempts = "3",
      backoff = @Backoff(delay = 1000),
      include = StudentAlreadyExistsException.class
  )
  public void listen(String message) {

    System.out.println("Received message: " + message);

    if (cnt < 4) {
      throw new StudentAlreadyExistsException("Student already exists");
    }

  }

}
