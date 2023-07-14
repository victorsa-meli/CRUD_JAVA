package com.projeto.app.core.entity;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Consumer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String name;

    @CPF
    @Column
    @NotBlank
    private String cpf;

    @Email
    @Column
    @NotBlank
    private String email;

    @NotBlank
    private String cep;


    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "consumer_id")
    private Set<Card> cardlist;




}
