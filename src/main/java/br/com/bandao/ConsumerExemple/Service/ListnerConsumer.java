package br.com.bandao.ConsumerExemple.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ListnerConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ListnerConsumer.class);

    @KafkaListener(topics = "${topic.exemple-kafka}", groupId = "group_id")
    public void consume(String message) throws Exception {
        logger.info(String.format("$$ -> Consumed Message -> %s", message));
        log.info("Message: " + message);
    }
}
