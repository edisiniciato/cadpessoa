package com.avaliacaoelotech.pessoaelotech.service;

import com.avaliacaoelotech.pessoaelotech.domain.Pessoa;
import com.avaliacaoelotech.pessoaelotech.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PessoaService implements Serializable {

    @Autowired
    private PessoaRepository repository;

    public List<Pessoa> findAll() {
        return repository.findAll();
    }

    public Pessoa findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrada para o id " + id));
    }

    public Pessoa save(Pessoa pessoa) {
        validarSave(pessoa);
        return repository.save(pessoa);
    }

    public Pessoa update(Pessoa pessoa, Long id) {
        validarSave(pessoa);
        Pessoa find = findById(id);
        find.setNome(pessoa.getNome());
        find.setCpf(pessoa.getCpf());
        find.setDataNascimento(pessoa.getDataNascimento());
        find.setContatos(pessoa.getContatos());
        return repository.save(find);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private void validarSave(Pessoa pessoa) {
        if (pessoa.getDataNascimento().after(new Date())) {
            throw new RuntimeException("A data de nascimento não pode ser uma data futura.");
        }
        if (pessoa.getContatos() == null || pessoa.getContatos().isEmpty()) {
            throw new RuntimeException("A pessoa deve possui ao menos um contato.");
        }
    }
}
