/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniandes.csw.mueblesdelosalpes.persistencia.mock;

import co.edu.uniandes.csw.mueblesdelosalpes.dto.ExperienciaVendedor;
import co.edu.uniandes.csw.mueblesdelosalpes.dto.Mueble;
import co.edu.uniandes.csw.mueblesdelosalpes.dto.RegistroVenta;
import co.edu.uniandes.csw.mueblesdelosalpes.dto.TipoMueble;
import co.edu.uniandes.csw.mueblesdelosalpes.dto.TipoUsuario;
import co.edu.uniandes.csw.mueblesdelosalpes.dto.Usuario;
import co.edu.uniandes.csw.mueblesdelosalpes.dto.Vendedor;
import co.edu.uniandes.csw.mueblesdelosalpes.excepciones.OperacionInvalidaException;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioPersistenciaMockLocal;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioPersistenciaMockRemote;

import jakarta.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author poeta
 */
@Stateless
public class ServicioPersistenciaMock implements IServicioPersistenciaMockRemote, 
        IServicioPersistenciaMockLocal{
    
    private static ArrayList<Vendedor> vendedores;
    private static ArrayList<Mueble> muebles;
    private static ArrayList<Usuario> usuarios;
    private static ArrayList<RegistroVenta> registrosVentas;
    
    private long siguienteIdVendedor() {
        return vendedores.stream().mapToLong(Vendedor::getIdentificacion).max().orElse(0L) + 1;
    }

    private long siguienteReferenciaMueble() {
        return muebles.stream().mapToLong(Mueble::getReferencia).max().orElse(0L) + 1;
    }


    public ServicioPersistenciaMock() {
        
        if (vendedores == null) {
            
            vendedores = new ArrayList<>();
            muebles = new ArrayList<>();
            usuarios = new ArrayList<>();
            registrosVentas = new ArrayList<>();

            ArrayList<ExperienciaVendedor> experiencia = new ArrayList<>();

            experiencia.add(new ExperienciaVendedor(1L, "Banco de los Alpes", "Cajero",
                    "Se desempeñó en diferentes áreas administrativas", 1998));

            vendedores.add(new Vendedor(1L, "Carlos Antonio", "Gomez Rodriguez",
                    new ArrayList<>(experiencia), 900000, 80000,
                    "Técnico en auditoría y costos", "vendedor1"));

            experiencia.clear();
            
            experiencia.add(new ExperienciaVendedor(2L, "Marketplace de los Alpes", "Asesor de ventas",
                    "Se desempeñó como consultor y asesor en área de ventas", 2006));

            vendedores.add(new Vendedor(2L, "Claudia", "Sanchez Guerrero",
                    new ArrayList<>(experiencia), 950000, 85000,
                    "Comunicadora social", "vendedor2"));

            experiencia.clear();
            
            experiencia.add(new ExperienciaVendedor(3L, "Seguros de los Alpes", "Vendedor",
                    "Se desempeñó como vendedora e impulsadora", 2010));

            vendedores.add(new Vendedor(3L, "Angela Patricia", "Montoya Zanabria",
                    new ArrayList<>(experiencia), 1200000, 135000,
                    "Técnico en Gestión de mercadeo", "vendedor2"));

            experiencia.clear();
            
            experiencia.add(new ExperienciaVendedor(4L, "Autopartes de los Alpes", "Director de producción",
                    "Se desempeñó como director en el área de producción", 2009));

            vendedores.add(new Vendedor(4L, "Juan Pablo", "Escobar Vélez",
                    new ArrayList<>(experiencia), 1000000, 100000,
                    "Técnico en métodos de producción", "vendedor1"));

            muebles.add(new Mueble(1L, "Silla clásica", "Una confortable silla con estilo del siglo XIX.",
                    TipoMueble.Interior, 45, "sillaClasica", 123));

            muebles.add(new Mueble(2L, "Sillón new wave", "Innovador y cómodo.",
                    TipoMueble.Interior, 60, "newWave", 5655));

            muebles.add(new Mueble(3L, "Silla moderna", "Lo último en la moda de interiores.",
                    TipoMueble.Interior, 50, "sillaModerna", 5464));

            muebles.add(new Mueble(4L, "Mesa de jardín", "Una bella mesa para comidas al aire libre.",
                    TipoMueble.Exterior, 100, "mesaJardin", 4568));

            muebles.add(new Mueble(5L, "Orange games", "Una hermosa silla con un toque moderno y elegante.",
                    TipoMueble.Interior, 70, "sillaNaranja", 1345));

            muebles.add(new Mueble(6L, "Cama king", "Una hermosa cama hecha en cedro para dos personas.",
                    TipoMueble.Interior, 50, "bed", 63358));

            muebles.add(new Mueble(7L, "Silla Neoclásica", "Una bella silla con un estilo neoclásico.",
                    TipoMueble.Exterior, 65, "neoClasica", 678));

            muebles.add(new Mueble(8L, "Camarote junior", "Con diseño moderno.",
                    TipoMueble.Interior, 85, "camarote", 56565));

            usuarios.add(new Usuario("admin", "adminadmin", TipoUsuario.Administrador));

            usuarios.add(new Usuario("client", "clientclient", TipoUsuario.Cliente));
            
        }
        
    }

    @Override
    public void create(Object obj) throws OperacionInvalidaException {
        
        if (obj instanceof Vendedor) {
            
            Vendedor vendedor = (Vendedor) obj;
            
            vendedor.setIdentificacion(siguienteIdVendedor());
            vendedores.add(vendedor);

        } else if (obj instanceof Mueble) {
            
            Mueble mueble = (Mueble) obj;
            
            mueble.setReferencia(siguienteReferenciaMueble());
            muebles.add(mueble);

        } else if (obj instanceof Usuario) {
            Usuario usuario = (Usuario) obj;

            for (Usuario existente : usuarios) {
                
                if (existente.getLogin().equals(usuario.getLogin())) {
                    throw new OperacionInvalidaException("El usuario '" + usuario.getLogin() + "' ya ha sido registrado en el sistema");
                }

                if (usuario.getDocumento() != 0 && existente.getDocumento() == usuario.getDocumento() 
                        && existente.getTipoDocumento() != null && existente.getTipoDocumento().equals(usuario.getTipoDocumento())) {
                    
                    throw new OperacionInvalidaException("El usuario con documento '" + usuario.getDocumento() + "' ya ha sido registrado en el sistema");
                }
                
            }

            usuarios.add(usuario);

        } else if (obj instanceof RegistroVenta) {
            registrosVentas.add((RegistroVenta) obj);
        }
        
    }

    @Override
    public boolean update(Object obj) {

        if (obj == null) {
            return false;
        }

        if (obj instanceof Vendedor) {

            Vendedor editar = (Vendedor) obj;

            for (int i = 0; i < vendedores.size(); i++) {
                
                if (vendedores.get(i).getIdentificacion() == editar.getIdentificacion()) {
                    
                    vendedores.set(i, editar);
                    return true;
                    
                }
                
            }

        } else if (obj instanceof Mueble) {

            Mueble editar = (Mueble) obj;

            for (int i = 0; i < muebles.size(); i++) {
                
                if (muebles.get(i).getReferencia() == editar.getReferencia()) {
                   
                    muebles.set(i, editar);
                    return true;
                    
                }
                
            }

        } else if (obj instanceof Usuario) {

            Usuario editar = (Usuario) obj;

            if (editar.getLogin() == null) {
                return false;
            }

            for (int i = 0; i < usuarios.size(); i++) {

                Usuario actual = usuarios.get(i);

                if (actual.getLogin() != null && actual.getLogin().equals(editar.getLogin())) {
                    
                    usuarios.set(i, editar);
                    return true;
                
                }
            
            }
        }

        return false;
    }

    @Override
    public void delete(Object obj) throws OperacionInvalidaException {
        
        if (obj instanceof Vendedor) {
            
            Vendedor eliminar = (Vendedor) obj;
            
            vendedores.removeIf(v -> v.getIdentificacion() == eliminar.getIdentificacion());

        } else if (obj instanceof Mueble) {
            
            Mueble eliminar = (Mueble) obj;
            
            muebles.removeIf(m -> m.getReferencia() == eliminar.getReferencia());

        } else if (obj instanceof Usuario) {
            
            Usuario eliminar = (Usuario) obj;

            if (eliminar == null || eliminar.getLogin() == null) {
                return;
            }

            for (RegistroVenta venta : registrosVentas) {
                
                if (venta.getComprador() != null && venta.getComprador().getLogin().equals(eliminar.getLogin())) {
                    throw new OperacionInvalidaException("El usuario ha realizado compras y no puede " + "ser eliminado del sistema.");
                }
                
            }

            usuarios.removeIf(u -> u.getLogin().equals(eliminar.getLogin()));
            
        }
        
    }

    @Override
    public List findAll(Class c) {
        
        if (c.equals(Mueble.class)) {
            return muebles;
        } else if (c.equals(Vendedor.class)) {
            return vendedores;
        } else if (c.equals(Usuario.class)) {
            return usuarios;
        } else if (c.equals(RegistroVenta.class)) {
            return registrosVentas;
        }

        return null;
    }

    @Override
    public Object findById(Class c, Object id) {
        
        if (id == null) {
            return null;
        }

        if (c.equals(Vendedor.class)) {
            
            for (Vendedor vendedor : vendedores) {
                
                if (vendedor.getIdentificacion() == Long.parseLong(id.toString())) {
                    return vendedor;
                }
                
            }

        } else if (c.equals(Mueble.class)) {
            
            for (Mueble mueble : muebles) {
                
                if (mueble.getReferencia() == Long.parseLong(id.toString())) {
                    return mueble;
                }
                
            }

        } else if (c.equals(Usuario.class)) {
            
            for (Usuario usuario : usuarios) {
                
                if (usuario.getLogin().equals(id.toString())) {
                    return usuario;
                }
                
            }
            
        }

        return null;
    }
    
}
