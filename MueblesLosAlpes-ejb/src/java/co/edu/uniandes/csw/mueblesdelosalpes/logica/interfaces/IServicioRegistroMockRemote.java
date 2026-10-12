/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces;

import co.edu.uniandes.csw.mueblesdelosalpes.dto.Usuario;
import co.edu.uniandes.csw.mueblesdelosalpes.excepciones.OperacionInvalidaException;
import java.util.List;
import jakarta.ejb.Remote;

/**
 *
 * @author poeta
 */
@Remote
public interface IServicioRegistroMockRemote {
    
    public void registrar(Usuario u)
            throws OperacionInvalidaException;

    public void eliminarCliente(String login)
            throws OperacionInvalidaException;

    public List<Usuario> darClientes();

    void actualizarCliente(Usuario u)
            throws OperacionInvalidaException;


}
