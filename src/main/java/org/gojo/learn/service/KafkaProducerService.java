package org.gojo.learn.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.gojo.learn.entity.StudentEntity;
import org.gojo.learn.serialisation.SerialisationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    public void publishStudent(String topic, StudentEntity studentEntity) throws JsonProcessingException {
        kafkaTemplate.send(topic, SerialisationUtil.serialize(studentEntity));
    }
}
