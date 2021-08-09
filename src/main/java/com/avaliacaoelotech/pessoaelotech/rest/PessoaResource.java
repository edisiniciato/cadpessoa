package com.avaliacaoelotech.pessoaelotech.rest;

import com.avaliacaoelotech.pessoaelotech.domain.Pessoa;
import com.avaliacaoelotech.pessoaelotech.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa) {
        try {
            return ResponseEntity.ok(pessoaService.save(pessoa));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().header("Erro ao salvar pessoa " + e.getMessage()).body(null);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa, @PathVariable Long id) {
        try {
            System.out.println("ID DO PUT " + id);
            return ResponseEntity.ok(pessoaService.update(pessoa, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().header("Erro ao editar pessoa " + e.getMessage()).body(null);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Pessoa> delete(@PathVariable Long id) {
        try {
            System.out.println("ID DO DELETE " + id);
            pessoaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().header("Erro ao deletar pessoa " + e.getMessage()).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAll() {
        try {
            return ResponseEntity.ok(pessoaService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().header("Erro ao buscar pessoas " + e.getMessage()).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pessoaService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().header("Erro ao buscar pessoa " + e.getMessage()).body(null);
        }
    }
}
