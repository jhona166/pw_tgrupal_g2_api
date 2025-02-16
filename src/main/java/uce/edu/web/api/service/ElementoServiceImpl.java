package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.IElementoRepository;
import uce.edu.web.api.repository.modelo.Elemento;

@Transactional
@ApplicationScoped
public class ElementoServiceImpl implements IElementoService {

    @Inject
    private IElementoRepository elementoRepository;

    //Create
    @Override
    public void guardarElemento(Elemento elemento) throws Exception {

        if (elemento.getElementoPadre() == null) {
            throw new RuntimeException("El elemento no tiene un padre asignado");

        } else if (elemento.getElementoPadre() != null && elemento.getElementoPadre() != 0) {
            Elemento padre = this.elementoRepository.obtenerElementoPorId(elemento.getElementoPadre());
            padre.getSubElementos().add(elemento);
            this.elementoRepository.actualizar(padre);
            return;
        }

        this.elementoRepository.guardarElemento(elemento);
    }

    @Override
    public void crearCarpetaNuevaEnPadre(Integer idPadre) {
        var padre = this.elementoRepository.obtenerElementoPorId(idPadre);
        var nuevaCarpeta = new Elemento();
        {
            nuevaCarpeta.setNombre("Nueva Carpeta");
            nuevaCarpeta.setTipo("CARPETA");
            nuevaCarpeta.setElementoPadre(idPadre);
        }
        padre.getSubElementos().add(nuevaCarpeta);
        elementoRepository.actualizar(padre);
    }

    //Read
    @Override
    public Elemento buscarElementoPorId(Integer id) {

        //revisar si no existe elemento raiz
        var elemento = this.elementoRepository.obtenerElementoPorId(id);
        if(id ==1 && elemento == null ){
            elemento = this.elementoRepository.crearRaiz();
        }

        return elemento;
    }

    @Override
    public List<Elemento> buscarElementosPorPadreId(Integer elementoId) {
        return this.elementoRepository.obtenerElementosPorPadreId(elementoId);
    }


    //Update
    @Override
    public void actualizarElemento(Elemento elemento) {
        this.elementoRepository.actualizar(elemento);
    }


    //Delete

    

}
