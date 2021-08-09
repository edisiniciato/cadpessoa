package com.avaliacaoelotech.pessoaelotech.repository;

import com.avaliacaoelotech.pessoaelotech.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {


    @Query(value = "select p from Pessoa p where p.nome = :nome")
    Pessoa searchByName(@Param("nome") String nome);
}
