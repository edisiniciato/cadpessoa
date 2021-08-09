package com.avaliacaoelotech.pessoaelotech.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo nome deve ser informado.")
    private String nome;

    @NotNull(message = "O campo cpf deve ser informado.")
    private String cpf;

    @NotNull(message = "O campo data de nascimento deve ser informado")
    @Column(name = "DATANASCIMENTO")
    private Date dataNascimento;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PessoaContato> contatos;

    public Pessoa() {
        contatos = new ArrayList<>();
    }

    public Pessoa(String nome, String cpf, Date dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.contatos = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<PessoaContato> getContatos() {
        return contatos;
    }

    public void setContatos(List<PessoaContato> contatos) {
        this.contatos = contatos;
    }
}
