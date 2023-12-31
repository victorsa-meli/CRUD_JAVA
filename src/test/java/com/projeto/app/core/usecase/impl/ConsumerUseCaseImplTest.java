package com.projeto.app.core.usecase.impl;

import com.projeto.app.core.entity.Consumer;
import com.projeto.app.core.repository.ConsumerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
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

        Optional<Consumer> emptyConsumer = Optional.empty();

        when(consumerRepository.save(consumer)).thenReturn(consumer);
        when(consumerRepository.findByCpf(consumer.getCpf())).thenReturn(emptyConsumer);

        // Call the createConsumer() method
        Consumer createdConsumer = consumerUseCaseImpl.createConsumer(consumer);

        // Assert that the consumer was created successfully
        assertEquals("Mango Joe", createdConsumer.getName());
        assertEquals("691.805.560-78", createdConsumer.getCpf());
        assertEquals("teste@email.com", createdConsumer.getEmail());
    }

    @Test
    public void when_consumerCpf_already_exist() {
        Consumer consumer = new Consumer();
        consumer.setCpf("691.805.560-78");

        when(consumerRepository.findByCpf(consumer.getCpf())).thenReturn(java.util.Optional.of(consumer));

        assertThrows(Exception.class, () -> consumerUseCaseImpl.createConsumer(consumer));


    }


    @Test
    public void when_consumer_updated_success() throws Exception {


        //TODO 1 -CRIAR O OBJETO A SER TESTADO
        Consumer consumerEditado = new Consumer();
        consumerEditado.setName("Mango Joe");
        consumerEditado.setCpf("691.805.560-78");
        consumerEditado.setEmail("emailAtualizado@email.com");
        consumerEditado.setCep("987654321");

        Consumer consumerNoBanco = new Consumer();
        consumerNoBanco.setName("Mango Joe");
        consumerNoBanco.setCpf("691.805.560-78");
        consumerNoBanco.setEmail("teste@email.com");
        consumerNoBanco.setCep("12345678");


        //TODO 2 -MOCAR O QUE É EXTERNO AO METODO  EM QUESTAO
        when(consumerRepository.save(consumerEditado)).thenReturn(consumerEditado);
        when(consumerRepository.findByCpf(consumerEditado.getCpf())).thenReturn(Optional.of(consumerNoBanco));

        //TODO 3- CHAMAR METODO A SER TESTADO
        Consumer consumidorAtualizado = consumerUseCaseImpl.updateConsumer(consumerEditado);


        //TODO 4- ASEGURAR OS RESULTADOS ESPERADOS
        assertEquals("emailAtualizado@email.com", consumidorAtualizado.getEmail());
        assertEquals("987654321", consumidorAtualizado.getCep());


    }


    @Test
    public void when_consumer_updated_already_exist() {

        // Create a consumer
        Consumer consumer = new Consumer();
        consumer.setCpf("691.805.560-78");

        Optional<Consumer> emptyConsumer = Optional.empty();

        when(consumerRepository.findByCpf(consumer.getCpf())).thenReturn(emptyConsumer);

        assertThrows(Exception.class, () -> consumerUseCaseImpl.updateConsumer(consumer));


    }


    @Test
    public void when_delete_consumer_success() throws Exception {

        Consumer consumer = new Consumer();
        consumer.setId(1L);

        Optional<Consumer> foundConsumer = Optional.of(consumer);

        when(consumerRepository.findById(consumer.getId())).thenReturn(foundConsumer);

        consumerUseCaseImpl.deleteConsumer(consumer);

        verify(consumerRepository).findById(1L);

        verify(consumerRepository).delete(consumer);

    }


    @Test
    public void when_delete_consumer_not_found() {

        Consumer consumer = new Consumer();
        when(consumerRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> consumerUseCaseImpl.deleteConsumer(consumer));

    }

    @Test
    public void when_getAll_consumers()  {
        consumerUseCaseImpl.getallConsumer();
        verify(consumerRepository).findAll();
    }

    @Test
    public void testGetRandomCreditCard() {
        String creditCard = ConsumerUseCaseImpl.getRandomCreditCard();
        assertNotNull(creditCard);
        assertEquals(19, creditCard.length());
    }

}