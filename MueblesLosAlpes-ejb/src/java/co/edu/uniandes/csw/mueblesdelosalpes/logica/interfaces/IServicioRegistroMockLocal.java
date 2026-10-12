/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces;

import co.edu.uniandes.csw.mueblesdelosalpes.dto.Usuario;
import co.edu.uniandes.csw.mueblesdelosalpes.excepciones.OperacionInvalidaException;

import java.util.List;
import jakarta.ejb.Local;

/**
 * Contrato funcional de los servicios de registro de usuario
 * 
 * @author poeta
 */
@Local
public interface IServicioRegistroMockLocal {
    
    public void registrar(Usuario u)
            throws OperacionInvalidaException;
    
    public void eliminarCliente(String login)
            throws OperacionInvalidaException;
    
    public List<Usuario> darClientes();
    
    public void actualizarCliente(Usuario u)
            throws OperacionInvalidaException;
        
}
