package org.gojo.learn.service.controller.kafka;

import static org.gojo.learn.commons.constants.KafkaConstants.KAFKA_STUDENT_TOPIC;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.gojo.learn.service.entity.StudentEntity;
import org.gojo.learn.service.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

  private final KafkaProducerService kafkaProducerService;

  @Autowired
  public KafkaController(KafkaProducerService kafkaProducerService) {
    this.kafkaProducerService = kafkaProducerService;
  }

  @PostMapping("/kafka/publish-message")
  public ResponseEntity<String> publishKafkaMessage(@RequestParam("message") String message,
      @RequestParam("topic") String topic) {
    kafkaProducerService.sendMessage(topic, message);
    return ResponseEntity.ok("Accepted");
  }

  @PostMapping("/kafka/publish-student")
  public ResponseEntity<String> publishStudentInKafka(@RequestBody StudentEntity studentEntity)
      throws JsonProcessingException {
    kafkaProducerService.publishStudent(KAFKA_STUDENT_TOPIC, studentEntity);
    return ResponseEntity.ok("Accepted Student in Kafka");
  }
}
