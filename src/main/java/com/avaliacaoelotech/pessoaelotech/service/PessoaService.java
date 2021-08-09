package com.avaliacaoelotech.pessoaelotech.service;

import com.avaliacaoelotech.pessoaelotech.domain.Pessoa;
import com.avaliacaoelotech.pessoaelotech.domain.PessoaContato;
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
        for (PessoaContato contato : pessoa.getContatos()) {
            contato.setPessoa(pessoa);
        }
        return repository.save(pessoa);
    }

    public Pessoa update(Pessoa pessoa, Long id) {
        Pessoa find = findById(id);

        find.setNome(pessoa.getNome());
        find.setCpf(pessoa.getCpf());
        find.setDataNascimento(pessoa.getDataNascimento());
        return repository.save(find);
    }

    public PessoaContato updateContato(PessoaContato pessoaContato, Long idPessoa, Long idContato) {
        Pessoa pessoa = findById(idPessoa);
        PessoaContato contato = pessoa.getContatos().stream().filter(c -> c.getId().equals(idContato)).findFirst().get();
        contato.setNome(pessoaContato.getNome());
        contato.setEmail(pessoaContato.getEmail());
        contato.setTelefone(pessoaContato.getTelefone());
        repository.save(pessoa);
        return contato;
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

    public PessoaContato addContato(PessoaContato pessoaContato, Long idPessoa) {
        Pessoa find = findById(idPessoa);
        pessoaContato.setPessoa(find);
        find.getContatos().add(pessoaContato);
        return pessoaContato;
    }
}
