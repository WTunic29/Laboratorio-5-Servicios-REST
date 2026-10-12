/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniandes.csw.mueblesdelosalpes.logica.ejb;

import co.edu.uniandes.csw.mueblesdelosalpes.dto.Usuario;
import co.edu.uniandes.csw.mueblesdelosalpes.excepciones.OperacionInvalidaException;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioPersistenciaMockLocal;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioRegistroMockLocal;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioRegistroMockRemote;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 * Use @EJB para que GlassFish inyecte la persistencia local.
 * Eliminé la creación manual new ServicioPersistenciaMock(), 
 * porque en un contenedor EJB la dependencia debe gestionarse mediante inyección.
 * 
 * @author poeta
 */
@Stateless
public class ServicioRegistroMock implements IServicioRegistroMockRemote,
        IServicioRegistroMockLocal{
    
    @EJB
    private IServicioPersistenciaMockLocal persistencia;
    
    public ServicioRegistroMock(){
    }
    
    @Override
    public void registrar(Usuario u) throws OperacionInvalidaException{
        
        if(u == null){
            throw new OperacionInvalidaException("El usuario no puede ser nulo");
        }
        
        if(u.getDocumento() == 0){
            throw new OperacionInvalidaException("El número de documento no es válido");
        }
        
        persistencia.create(u);
    }
    
    @Override
    public void eliminarCliente(String login) throws OperacionInvalidaException {

        Usuario usuario = (Usuario) persistencia.findById(Usuario.class, login);

        if (usuario == null) {
            throw new OperacionInvalidaException("No se encontró el usuario que se desea eliminar");
        }

        persistencia.delete(usuario);
    }
    
    @Override
    public List<Usuario> darClientes() {
        return persistencia.findAll(Usuario.class);
    }

    /**
     *
     * @param u
     * @throws OperacionInvalidaException
     */
    @Override
    public void actualizarCliente(Usuario u) throws OperacionInvalidaException {

        if (u == null) {
            throw new OperacionInvalidaException("El usuario no puede ser nulo");
        }

        if (u.getLogin() == null || u.getLogin().trim().isEmpty()) {
            throw new OperacionInvalidaException("El login del usuario es obligatorio");
        }

        boolean actualizado = persistencia.update(u);

        if (!actualizado) {
            throw new OperacionInvalidaException("No se encontró el usuario que se desea actualizar");
        }
        
    }

}
