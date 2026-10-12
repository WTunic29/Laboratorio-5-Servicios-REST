/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniandes.csw.mueblesdelosalpes.servicios;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Config de la app REST
 * 
 *  - @ApplicationPath("/webresources") establece la ruta base de la API.
 *  - Extends Application registra la configuración de Jakarta REST.
 *  - Como RegistroService tiene @Path("/Registro"), la ruta resultante incluirá /webresources/Registro
 * 
 * @author poeta
 */
@ApplicationPath("/webresources")
public class RestApplication extends Application{
}
