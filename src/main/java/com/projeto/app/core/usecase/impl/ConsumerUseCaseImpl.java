package com.projeto.app.core.usecase.impl;

import com.projeto.app.core.entity.Card;
import com.projeto.app.core.entity.CardType;
import com.projeto.app.core.entity.Consumer;
import com.projeto.app.core.repository.ConsumerRepository;
import com.projeto.app.core.usecase.ConsumerUseCase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@AllArgsConstructor
public class ConsumerUseCaseImpl implements ConsumerUseCase {


    private final ConsumerRepository consumerRepository;

    @Override
    public Consumer createConsumer(Consumer consumer) throws Exception {

        Optional<Consumer> consumerCPF = consumerRepository.findByCpf(consumer.getCpf());


        if (consumerCPF.isEmpty()) {

            Set<Card> cardList = new HashSet<>();
            Card cardAlimentacao = new Card();
            Card cardRefeicao = new Card();

            cardAlimentacao.setNumeroCartao(getRandomCreditCard());
            cardAlimentacao.setCardType(CardType.ALIMENTACAO);
            cardAlimentacao.setSaldo(0.0);

            cardRefeicao.setNumeroCartao(getRandomCreditCard());
            cardRefeicao.setCardType(CardType.REFEICAO);
            cardRefeicao.setSaldo(0.0);

            cardList.add(cardAlimentacao);
            cardList.add(cardRefeicao);

            consumer.setCardlist(cardList);

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

        consumerCPF.get().setCardlist(consumer.getCardlist());


        return consumerRepository.save(consumerCPF.get());
    }

    @Override
    public void deleteConsumer(Consumer consumer) throws Exception {
        Optional<Consumer> object = consumerRepository.findById(consumer.getId());

        if (object.isEmpty()) {

            throw new Exception("ID não encontrado!");
        }

        consumerRepository.delete(consumer);

    }

    @Override
    public List<Consumer> getallConsumer() throws Exception {

        return consumerRepository.findAll();
    }

    public static String getRandomCreditCard() {
        Random r = new Random();
        double number = r.nextDouble();

        String creditCard = String.format("%.16f", number).substring(2);

        // Format the credit card number
        String formattedCreditCard = String.format("%s-%s-%s-%s",
                creditCard.substring(0, 4),
                creditCard.substring(4, 8),
                creditCard.substring(8, 12),
                creditCard.substring(12));

        return formattedCreditCard;
    }

}