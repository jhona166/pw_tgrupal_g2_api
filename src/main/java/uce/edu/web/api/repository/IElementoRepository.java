package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.modelo.Elemento;

public interface IElementoRepository {
    //Create
    public void guardarElemento(Elemento elemento);

    public Elemento crearRaiz();
    
    //Read
    public Elemento obtenerElementoPorId(Integer id);
    public List<Elemento> obtenerElementosPorPadreId(Integer elementoId);

    //Update
    public void actualizar(Elemento elemento);

    //Delete, no pide

    //Busqueda
    public List<Elemento> buscarPorNombre(String nombre);
}
