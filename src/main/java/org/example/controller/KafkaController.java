package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.models.Student;
import org.example.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.example.constants.Constants.KAFKA_MY_TOPIC;
import static org.example.constants.Constants.KAFKA_STUDENT_TOPIC;

@RestController
public class KafkaController {

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public KafkaController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/kafka/publishMessage")
    public ResponseEntity<String> publishKafkaMessage(@RequestParam("message") String message) {
        kafkaProducerService.sendMessage(KAFKA_MY_TOPIC, message);
        return ResponseEntity.ok("Accepted");
    }

    @PostMapping("/kafka/publishStudent")
    public ResponseEntity<String> publishStudentInKafka(@RequestBody Student student) throws JsonProcessingException {
        kafkaProducerService.publishStudent(KAFKA_STUDENT_TOPIC, student);
        return ResponseEntity.ok("Accepted Student in Kafka");
    }
}
