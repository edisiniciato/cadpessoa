package com.avaliacaoelotech.pessoaelotech.repository;

import com.avaliacaoelotech.pessoaelotech.domain.PessoaContato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaContatoRepository extends JpaRepository<PessoaContato, Long> {

}
