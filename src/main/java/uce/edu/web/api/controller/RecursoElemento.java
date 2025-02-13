package uce.edu.web.api.controller;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.repository.IElementoRepository;
import uce.edu.web.api.repository.modelo.Elemento;

@Path("elementos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class RecursoElemento {
    @Inject
    private IElementoRepository repositorio;
    
    @POST
    @Transactional
    public Response crearElemento(Elemento elemento) {
        repositorio.guardarElemento(elemento);
        return Response.ok(elemento).status(Response.Status.CREATED).build();

    }
    @GET
    @Path("{id}/subelementos")
    public Response listarSubElementos(@PathParam("id") Long elementoId) {
        List<Elemento> subElementos = repositorio.obtenerElementosPorPadreId(elementoId);
        return Response.ok(subElementos).build();
    }
}
