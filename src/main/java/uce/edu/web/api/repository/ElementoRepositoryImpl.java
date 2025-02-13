package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.modelo.Elemento;


@ApplicationScoped
@Transactional

public class ElementoRepositoryImpl implements IElementoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void guardarElemento(Elemento elemento) {
       this.entityManager.persist(elemento);

    }

    @Override
    public Elemento obtenerElementoPorId(Long id) {
        return entityManager.find(Elemento.class, id);
    }

    @Override
    public List<Elemento> obtenerElementosPorPadreId(Long elementoId) {
        return entityManager.createQuery("SELECT e FROM Elemento e WHERE e.elementoPadre.id = :elementoId", Elemento.class)
        .setParameter("elementoId", elementoId)
        .getResultList();

    }

}
