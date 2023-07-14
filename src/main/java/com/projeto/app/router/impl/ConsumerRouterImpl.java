package com.projeto.app.router.impl;

import com.projeto.app.core.entity.Consumer;
import com.projeto.app.core.usecase.impl.ConsumerUseCaseImpl;
import com.projeto.app.router.ConsumerRouter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/consumer")
@AllArgsConstructor
public class ConsumerRouterImpl implements ConsumerRouter {


    private final ConsumerUseCaseImpl consumerUseCase;

    @Override
    @GetMapping
    public ResponseEntity<List<Consumer>> getAllConsumers() throws Exception {
        return ResponseEntity.ok(consumerUseCase.getallConsumer());
    }

    @Override
    @PostMapping
    public ResponseEntity<Consumer> createConsumer(@RequestBody @Valid Consumer consumer) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED).body(consumerUseCase.createConsumer(consumer));

    }

    @Override
    @PutMapping
    public ResponseEntity<Consumer> updateConsumer(@RequestBody Consumer consumer) throws Exception {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(consumerUseCase.updateConsumer(consumer));
    }

    @Override
    @DeleteMapping
    public void deleteConsumer(@RequestBody Consumer consumer) throws Exception {

        consumerUseCase.deleteConsumer(consumer);

    }
}
