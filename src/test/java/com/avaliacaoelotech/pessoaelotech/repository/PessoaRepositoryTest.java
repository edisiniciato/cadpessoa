package com.avaliacaoelotech.pessoaelotech.repository;

import com.avaliacaoelotech.pessoaelotech.domain.Pessoa;
import com.avaliacaoelotech.pessoaelotech.domain.PessoaContato;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private PessoaContatoRepository pessoaContatoRepository;

    @Test
    public void save() {
        final Pessoa pessoa = new Pessoa("Bruce", "999999999", new Date());
//        pessoa.setId(1L);
        Pessoa pessoaSave = pessoaRepository.saveAndFlush(pessoa);
        assertThat(pessoaSave.getId()).isNotNull();
        assertThat(pessoaSave.getNome()).isEqualTo("Bruce");
        assertThat(pessoaSave.getCpf()).isEqualTo("999999999");
        assertThat(pessoaSave.getDataNascimento()).isInSameDayAs(new Date());

//        PessoaContato contato = new PessoaContato("Ronaldo", "(44) 99005588", "ronaldo@gmail.com");
//        contato.setPessoa(pessoaSave);
//        PessoaContato pessoaContatoSave = pessoaContatoRepository.saveAndFlush(contato);
//
//        assertThat(pessoaContatoSave.getId()).isNotNull();
//        assertThat(pessoaContatoSave.getPessoa()).isEqualTo(pessoaSave);
//        assertThat(pessoaContatoSave.getNome()).isEqualTo("Ronaldo");
//        assertThat(pessoaContatoSave.getTelefone()).isEqualTo("(44) 99005588");
    }

    @Test
    public void findByNome() {
        Pessoa pessoa = new Pessoa("Jonas da Silva", "999999999", new Date());
        Pessoa pessoaSave = pessoaRepository.saveAndFlush(pessoa);

        Pessoa pessoaFound = pessoaRepository.findByName(pessoaSave.getNome());
        assertThat(pessoaFound.getNome()).isEqualTo(pessoa.getNome());
    }
}