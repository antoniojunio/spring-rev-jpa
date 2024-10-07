package com.mballem.demo_spring_rev_jpa.controller;

import com.mballem.demo_spring_rev_jpa.dao.AutorDao;
import com.mballem.demo_spring_rev_jpa.entity.Autor;
import com.mballem.demo_spring_rev_jpa.entity.InfoAutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("autores")
public class AutorController {

    @Autowired
    private AutorDao dao;

    @PostMapping
    public Autor salvar(@RequestBody Autor autor) {
        dao.save(autor);
        return autor;
    }

    @PutMapping
    public Autor atualizar(@RequestBody Autor autor) {
        dao.update(autor);
        return autor;
    }

    @DeleteMapping("{id}")
    public String remover(@PathVariable Long id) {
        dao.delete(id);
        return "Autor id = " + id + " foi removido com sucesso!";
    }

    @GetMapping("{id}")
    public Autor getById(@PathVariable Long id) {
        return dao.findById(id);
    }

    @GetMapping
    public List<Autor> getAll() {
        return dao.findAll();
    }

    @GetMapping("nomeOrSobrenome")
    public List<Autor> getAutoresByNomeOrSobrenome(@RequestParam String termo) {
        return dao.findByNomeOrSobrenome(termo);
    }

    @GetMapping("total")
    public Long getTotalDeAutores() {
        return dao.getTotalElements();
    }

    @PutMapping("{id}/info")
    public Autor salvarInfoAutor(@PathVariable Long id, @RequestBody InfoAutor infoAutor) {
        return dao.saveInfoAutor(infoAutor, id);
    }
}
