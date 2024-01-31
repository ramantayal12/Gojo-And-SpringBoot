package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.models.Student;
import org.example.serialisation.SerialisationUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static org.example.constants.Constants.*;

/**
 * Run your Spring Boot application and use a tool like Postman or curl to send a POST request to http://localhost:8080/send?message=Hello_Kafka. The message will be sent to the Kafka topic, and the consumer will receive and print the message to the console.
 */
@Service
public class KafkaConsumerService {

    private final StudentRedisService studentRedisService;

    public KafkaConsumerService(StudentRedisService studentRedisService) {
        this.studentRedisService = studentRedisService;
    }

    @KafkaListener(topics = KAFKA_MY_TOPIC, groupId = KAFKA_GROUP_ID)
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }

    /**
     * kafka produced message will disaapear only after it gets consumed till then
     * consumer will try to consume it again and again.
     */
    @KafkaListener(topics = KAFKA_STUDENT_TOPIC, groupId = KAFKA_GROUP_ID)
    public void listenStudent(String serialisedStudent) throws JsonProcessingException {

        // this listener will consume message as student and will write it in db
        Student student = SerialisationUtil.deserialize(serialisedStudent, Student.class);
        studentRedisService.saveUser(student);
    }
}
