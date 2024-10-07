package com.mballem.demo_spring_rev_jpa.dao;

import com.mballem.demo_spring_rev_jpa.entity.Autor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AutorDao {

    @PersistenceContext
    private EntityManager manager;

    @Transactional(readOnly = false)
    public void save(Autor autor) {
        this.manager.persist(autor);
    }

    @Transactional(readOnly = false)
    public void update(Autor autor) {
        this.manager.merge(autor);
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        this.manager.remove(this.manager.getReference(Autor.class, id));
    }

    @Transactional(readOnly = true)
    public Autor findById(Long id) {
        return this.manager.find(Autor.class, id);
    }

    @Transactional(readOnly = true)
    public List<Autor> findAll() {
        String query = "select a from Autor a";
        return this.manager.createQuery(query, Autor.class).getResultList();
    }

    @Transactional(readOnly = true)
    public List<Autor> findByNomeOrSobrenome(String termo) {
        String query = "select a from Autor a " +
                "where a.nome like :termo OR a.sobrenome like :termo";
        return this.manager.createQuery(query, Autor.class)
                .setParameter("termo", "%" + termo + "%")
                .getResultList();
    }


}
