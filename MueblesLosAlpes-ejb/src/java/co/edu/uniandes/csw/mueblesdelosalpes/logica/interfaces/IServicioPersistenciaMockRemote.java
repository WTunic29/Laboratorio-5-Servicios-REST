/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces;

import co.edu.uniandes.csw.mueblesdelosalpes.excepciones.OperacionInvalidaException;
import jakarta.ejb.Remote;

/**
 * Contrato funcional de los servicios de persistencia de manera remota
 * 
 * @author poeta
 */
@Remote
public interface IServicioPersistenciaMockRemote {
    
    public void create(Object obj)
            throws OperacionInvalidaException;

    public boolean update(Object obj);

    public void delete(Object obj)
            throws OperacionInvalidaException;

    public java.util.List findAll(Class c);
    public Object findById(Class c, Object id);
    
}
