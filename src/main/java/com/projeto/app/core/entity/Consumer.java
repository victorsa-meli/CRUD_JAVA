package com.projeto.app.core.entity;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import javax.persistence.*;
import javax.validation.constraints.Email;
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
    @NotNull
    private String name;

    @CPF
    @Column
    @NotNull
    private String cpf;

    @Email
    @Column
    @NotNull
    private String email;


    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "consumer_id")
    private Set<Card> cardlist;




}
