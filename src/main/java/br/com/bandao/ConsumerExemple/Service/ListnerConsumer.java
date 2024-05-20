package br.com.bandao.ConsumerExemple.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bandao.ConsumerExemple.Service.objectmessage.ObjectMessageService;
import br.com.bandao.ConsumerExemple.model.ObjectMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ListnerConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ListnerConsumer.class);

    @Autowired
    private ObjectMessageService objectMessageService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "${topic.exemple-kafka}", groupId = "group_id")
    public void consume(String message) throws Exception {
        logger.info(String.format("$$ -> Consumed Message -> %s", message));
        log.info("Message: " + message);

        ObjectMessage objectMessage = objectMapper.readValue(message, ObjectMessage.class);

        if (objectMessage.getId() == null) {
            objectMessageService.createObjectMessage(objectMessage);
        logger.info("ObjectMessage saved: " + objectMessage);
        } else {
            objectMessageService.update(objectMessage);
        logger.info("ObjectMessage updated: " + objectMessage);
        }
    }
}
