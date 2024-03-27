package org.example.service;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(topics = "retry-topic")
public class RetractableKafkaConsumer {

  int cnt = 0;

  @KafkaHandler
  @RetryableTopic(
      attempts = "3",
      backoff = @Backoff(delay = 10, maxDelay = 10000),
      autoCreateTopics = "false"
  )
  void retryRefId(String message) throws Exception {
    System.out.println("Retrying refId");
    System.out.println(message);

    if( cnt < 3 ){
      throw new Exception("Retry");
    }
    cnt += 1;
  }

}
