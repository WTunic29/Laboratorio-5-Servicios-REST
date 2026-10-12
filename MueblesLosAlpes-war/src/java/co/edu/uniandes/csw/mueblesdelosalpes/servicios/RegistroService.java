/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniandes.csw.mueblesdelosalpes.servicios;

import co.edu.uniandes.csw.mueblesdelosalpes.dto.Usuario;
import co.edu.uniandes.csw.mueblesdelosalpes.excepciones.OperacionInvalidaException;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioRegistroMockLocal;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author poeta
 */
@Path("/Registro")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegistroService {
    
    @EJB
    private IServicioRegistroMockLocal registroEjb;
    
    @GET
    @Path("clientes/")
    public List<Usuario> getClientes(){
        return registroEjb.darClientes();
    }
    
    @POST
    @Path("clientes/")
    public Response registrar(Usuario usuario){
        
        try{
            
            registroEjb.registrar(usuario);
            return Response.status(Response.Status.CREATED).entity(usuario).build();
        
        } catch (OperacionInvalidaException ex){
            
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).type(MediaType.TEXT_PLAIN).build());
        
        }

    }

    @PUT
    @Path("clientes/{login}")
    public Response actualizarCliente(@PathParam("login") String login, Usuario usuario) {

        if (usuario == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("El cuerpo de la solicitud es obligatorio").build();
        }

        usuario.setLogin(login);

        try {
            
            registroEjb.actualizarCliente(usuario);
            return Response.ok(usuario).build();

        } catch (OperacionInvalidaException e) {
            
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
        
    }

    @DELETE
    @Path("clientes/{login}")
    public Response eliminar(@PathParam("login") String login) {

        try {
            
            registroEjb.eliminarCliente(login);
            return Response.noContent().build();

        } catch (OperacionInvalidaException ex) {
            
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).type(MediaType.TEXT_PLAIN).build());
        }
        
    }
    
}
