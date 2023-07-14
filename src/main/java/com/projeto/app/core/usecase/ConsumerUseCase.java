package com.projeto.app.core.usecase;

import com.projeto.app.core.entity.Consumer;
import java.util.List;

public interface ConsumerUseCase {



    //Contrato de uso do ConsumerUseCase

    Consumer createConsumer(Consumer consumer) throws Exception;


    Consumer updateConsumer(Consumer consumer) throws Exception;

    void deleteConsumer(Consumer consumer) throws Exception;

    List<Consumer> getallConsumer() throws Exception;


}
