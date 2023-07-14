package com.projeto.app.router;

import com.projeto.app.core.entity.Consumer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ConsumerRouter {

    ResponseEntity <List<Consumer>> getAllConsumers() throws Exception;

    ResponseEntity <Consumer> createConsumer(Consumer consumer) throws Exception;

    ResponseEntity <Consumer> updateConsumer(Consumer consumer) throws Exception;

    ResponseEntity<?> deleteConsumer(Consumer consumer) throws Exception;


}
