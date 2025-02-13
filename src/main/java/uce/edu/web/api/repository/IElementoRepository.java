package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.modelo.Elemento;

public interface IElementoRepository {
    public void guardarElemento(Elemento elemento);
    public Elemento obtenerElementoPorId(Long id);
    public List<Elemento> obtenerElementosPorPadreId(Long elementoId);

}
