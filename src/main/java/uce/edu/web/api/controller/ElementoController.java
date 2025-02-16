package uce.edu.web.api.controller;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.repository.modelo.Elemento;
import uce.edu.web.api.service.IElementoService;
import uce.edu.web.api.service.to.ElementoTo;

@Path("elementos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ElementoController {
    @Inject
    private IElementoService service;
    //Create
    @POST
    @Transactional
    public Response crearElemento(Elemento elemento) {
        try{
        service.guardarElemento(elemento);}
        catch(Exception e){
            System.out.println(e.getMessage());
            return Response.status(400)
                           .entity(e.getMessage())
                           .build();
        }
        return Response.ok(elemento)
                       .status(Response.Status.CREATED)
                       .build();

    }

    @PATCH
    @Transactional
    @Path("/{id}/nueva_carpeta")
    public Response crearNuevaCarpetaEnPadre(@PathParam("id")Integer padreId){
        this.service.crearCarpetaNuevaEnPadre(padreId);
        return Response.ok().status(Response.Status.CREATED).build();
    }

    @PATCH
    @Transactional
    @Path("/{id}/upload")
    public Response subirArchivoEnPadre(@PathParam("id") Integer padreId, ElementoTo elemento) {
        try {
            var padre = this.service.buscarElementoPorId(padreId);
            var nuevoElemento = new Elemento();
            byte[] fileData = java.util.Base64.getDecoder().decode(elemento.getDatos());
            nuevoElemento.setNombre(elemento.getNombre());
            nuevoElemento.setElementoPadre(padreId);
            nuevoElemento.setDatos(fileData);
            nuevoElemento.setTipo("ARCHIVO");
            nuevoElemento.setSubElementos(null);
            padre.getSubElementos().add(nuevoElemento);
            this.service.actualizarElemento(padre);
    
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Invalid Base64 file data")
                           .build();
        }
    }

    //Read
    @GET
    @Path("/{id}/subelementos")
    public Response listarSubElementos(@PathParam("id") Integer elementoId) {
        List<Elemento> subElementos = service.buscarElementosPorPadreId(elementoId);
        return Response.ok(subElementos).build();
    }

    @GET
    @Path("/")
    public Response buscarRaiz(){
        var raiz = service.buscarElementoPorId(1);
        System.out.println(raiz);
        return Response.ok().entity(raiz).build();
        
    }

    //Update


    //Delete
}
