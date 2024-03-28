package org.gojo.service;

import org.gojo.dto.StudentDto;
import org.gojo.serialisation.SerialisationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static org.gojo.constants.KafkaConstants.*;

/**
 * Run your Spring Boot application and use a tool like Postman or curl to send a POST request to http://localhost:8080/send?message=Hello_Kafka. The message will be sent to the Kafka topic, and the consumer will receive and print the message to the console.
 */
@Service
public class KafkaConsumerService {

    private final StudentServiceWithCache studentServiceWithCache;

    @Autowired
    public KafkaConsumerService(StudentServiceWithCache studentServiceWithCache) {
        this.studentServiceWithCache = studentServiceWithCache;
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
    public void listenStudent(String serialisedStudent) throws Exception {

        // this listener will consume message as student and will write it in db
        StudentDto studentDto = SerialisationUtil.deserialize(serialisedStudent, StudentDto.class);
        studentServiceWithCache.saveStudent(studentDto);

    }
}
