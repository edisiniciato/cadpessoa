package com.avaliacaoelotech.pessoaelotech.repository;

import com.avaliacaoelotech.pessoaelotech.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {


    @Query(value = "select p.* from pessoa p where lower(p.nome) = ?1", nativeQuery = true)
    Pessoa findByName(String nome);
}
