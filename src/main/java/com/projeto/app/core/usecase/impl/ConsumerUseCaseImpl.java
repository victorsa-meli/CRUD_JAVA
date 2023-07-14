package com.projeto.app.core.usecase.impl;

import com.projeto.app.core.entity.Consumer;
import com.projeto.app.core.repository.ConsumerRepository;
import com.projeto.app.core.usecase.ConsumerUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ConsumerUseCaseImpl implements ConsumerUseCase {


    private final ConsumerRepository consumerRepository;

    @Override
    public Consumer createConsumer(Consumer consumer) throws Exception {

        Optional<Consumer> consumerCPF = consumerRepository.findByCpf(consumer.getCpf());

        if (consumerCPF.isEmpty()) {

            return consumerRepository.save(consumer);


        }
        throw new Exception("CPF já cadastrado");


    }

    @Override
    public Consumer updateConsumer(Consumer consumer) throws Exception {

        Optional<Consumer> consumerCPF = consumerRepository.findByCpf(consumer.getCpf());


        if (consumerCPF.isEmpty()) {

            throw new Exception("CPF não encontrado!");
        }

        if (!consumerCPF.get().getCpf().equals(consumer.getCpf())) {

            throw new Exception("CPF não pode ser alterado!");
        }

        if (!consumer.getId().equals(consumerCPF.get().getId())) {

            throw new Exception("ID não pode ser alterado!");
        }

        return consumerRepository.save(consumer);
    }

    @Override
    public Consumer deleteConsumer(Consumer consumer) throws Exception {
        Optional<Consumer> object = consumerRepository.findById(consumer.getId());

        if (object.isEmpty()) {

            throw new Exception("ID não encontrado!");
        }

        consumerRepository.delete(consumer);
        return consumer;
    }

    @Override
    public List<Consumer> getallConsumer() throws Exception {

        return consumerRepository.findAll();
    }
}
