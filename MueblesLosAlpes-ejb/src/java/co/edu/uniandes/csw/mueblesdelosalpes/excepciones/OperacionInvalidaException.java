/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniandes.csw.mueblesdelosalpes.excepciones;

/**
 * Esta excepción se presenta cuando se viola alguna restricción de ubicidad en el modelo
 * 
 * @author poeta
 */
public class OperacionInvalidaException extends Exception{
    
    /** 
     * Constructor de la excepción.
     * 
     * @param mensaje Mensaje de la excepción
     * 
     */
    public OperacionInvalidaException(String mensaje){
        super(mensaje);
    }
    
}
