package br.com.bandao.ConsumerExemple.Service.objectmessage;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.bandao.ConsumerExemple.model.ObjectMessage;
import br.com.bandao.ConsumerExemple.repository.ObjectMessageRepository;

@Service
public class ObjectMessageService {

    private static final Logger logger = LoggerFactory.getLogger(ObjectMessageService.class);

    @Autowired
    private ObjectMessageRepository objectMessageRepository;

    public ObjectMessage createObjectMessage(ObjectMessage objectMessage) {
        objectMessage.setCreateAt(LocalDateTime.now());
        ObjectMessage savedMessage = objectMessageRepository.save(objectMessage);
        logger.info("ObjectMessage criado com sucesso: ID = {}", savedMessage.getId());
        return savedMessage;
    }

    public ObjectMessage update(ObjectMessage objectMessage) {
        Optional<ObjectMessage> existingMessageOpt = objectMessageRepository.findById(objectMessage.getId());
        if (existingMessageOpt.isPresent()) {
            ObjectMessage existingMessage = existingMessageOpt.get();
            boolean isUpdated = false;

            if (!existingMessage.getName().equals(objectMessage.getName())) {
                existingMessage.setName(objectMessage.getName());
                isUpdated = true;
            }

            if (isUpdated) {
                existingMessage.setCreateAt(LocalDateTime.now());
                ObjectMessage updatedMessage = objectMessageRepository.save(existingMessage);
                logger.info("ObjectMessage atualizado com sucesso: ID = {}", updatedMessage.getId());
                return updatedMessage;
            } else {
                logger.info("Nenhuma alteração necessária para ObjectMessage: ID = {}", existingMessage.getId());
                return existingMessage;
            }
        } else {
            throw new RuntimeException("ObjectMessage com ID " + objectMessage.getId() + " não encontrado");
        }
    }
}
