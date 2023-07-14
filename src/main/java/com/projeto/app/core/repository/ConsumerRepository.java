package com.projeto.app.core.repository;

import com.projeto.app.core.entity.Card;
import com.projeto.app.core.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    Optional<Consumer> findByCpf(String cpf);


}
