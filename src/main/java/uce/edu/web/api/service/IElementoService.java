package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.modelo.Elemento;

public interface IElementoService {
    
    //Create
    public void guardarElemento(Elemento elemento) throws Exception;
    public void crearCarpetaNuevaEnPadre(Integer idPadre);

    
    //Read
    public Elemento buscarElementoPorId (Integer id);
    public List<Elemento> buscarElementosPorPadreId(Integer elementoId);
    
    //Update
    public void actualizarElemento(Elemento elemento);

    //Delete

    
}
