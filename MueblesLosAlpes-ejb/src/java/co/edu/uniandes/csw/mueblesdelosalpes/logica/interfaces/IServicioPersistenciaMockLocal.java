/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces;

import co.edu.uniandes.csw.mueblesdelosalpes.excepciones.OperacionInvalidaException;
import jakarta.ejb.Local;

/**
 * Cada operación
    - create(): agrega un objeto a la persistencia simulada.
    - update(): modifica un objeto existente.
    - delete(): elimina un objeto.
    - findAll(): consulta todos los objetos de una clase.
    - findById(): busca un objeto por su identificador.
 * 
 * @author poeta
 */
@Local
public interface IServicioPersistenciaMockLocal {
    
    public void create(Object obj)
            throws OperacionInvalidaException;

    public boolean update(Object obj);

    public void delete(Object obj)
            throws OperacionInvalidaException;

    public java.util.List findAll(Class c);
    public Object findById(Class c, Object id);
    
}
