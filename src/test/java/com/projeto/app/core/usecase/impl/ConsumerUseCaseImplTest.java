package com.projeto.app.core.usecase.impl;

import com.projeto.app.core.entity.Consumer;
import com.projeto.app.core.repository.ConsumerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ConsumerUseCaseImplTest {
    @Mock
    ConsumerRepository consumerRepository;

    @InjectMocks
    ConsumerUseCaseImpl consumerUseCaseImpl;


    @BeforeEach
    public void setUp() {
        this.consumerRepository = Mockito.mock(ConsumerRepository.class);
        this.consumerUseCaseImpl = new ConsumerUseCaseImpl(consumerRepository);
    }



    @Test
    public void test_createConsumer() throws Exception {
        // Create a consumer
        Consumer consumer = new Consumer();
        consumer.setName("Mango Joe");
        consumer.setCpf("691.805.560-78");
        consumer.setEmail("teste@email.com");
        consumer.setCep("12345678");

        when(consumerRepository.save(consumer)).thenReturn(consumer);

        // Call the createConsumer() method
        Consumer createdConsumer = consumerUseCaseImpl.createConsumer(consumer);

        // Assert that the consumer was created successfully
        assertEquals("Mango Joe", createdConsumer.getName());
        assertEquals("691.805.560-78", createdConsumer.getCpf());
    }

    @Test
    public void when_consumerCpf_exist() throws Exception {
        // Create a consumer
        Consumer consumer = new Consumer();
        consumer.setName("Mango Joe");
        consumer.setCpf("691.805.560-78");
        consumer.setEmail("teste@email.com");
        consumer.setCep("12345678");

        when(consumerRepository.save(consumer)).thenReturn(consumer);
        when(consumerRepository.findByCpf(consumer.getCpf())).thenReturn(java.util.Optional.of(consumer));

        assertThrows(Exception.class, () -> {
            consumerUseCaseImpl.createConsumer(consumer);
        });


    }



}