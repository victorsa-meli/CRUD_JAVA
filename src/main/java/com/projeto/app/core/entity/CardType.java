package com.projeto.app.core.entity;


import lombok.Getter;

@Getter
public enum CardType {

    ALIMENTACAO(1), REFEICAO(2);

    final int cardCode;
    CardType(int cardCode){
        this.cardCode = cardCode;
    }

}
