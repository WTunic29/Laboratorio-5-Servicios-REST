/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniandes.csw.mueblesdelosalpes.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Modela un vendedor
 * 
 * @author poeta
 */
public class Vendedor {
    
    private long id;
    private String nombres;
    private String apellidos;
    private List<ExperienciaVendedor> experiencia;
    private double salario;
    private double comisionVentas;
    private String perfil;
    private String foto;

    public Vendedor() {
        experiencia = new ArrayList<ExperienciaVendedor>();
    }

    public Vendedor(long id, String nombres, String apellidos,
            List<ExperienciaVendedor> experiencia, double salario,
            double comisionVentas, String perfil, String foto) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.experiencia = experiencia;
        this.salario = salario;
        this.comisionVentas = comisionVentas;
        this.perfil = perfil;
        this.foto = foto;
    }

    public long getIdentificacion() {
        return id;
    }

    public void setIdentificacion(long id) {
        this.id = id;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public double getComisionVentas() {
        return comisionVentas;
    }

    public void setComisionVentas(double comisionVentas) {
        this.comisionVentas = comisionVentas;
    }

    public List<ExperienciaVendedor> getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(List<ExperienciaVendedor> experiencia) {
        this.experiencia = experiencia;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setItemExperiencia(ExperienciaVendedor experiencia) {
        this.experiencia.add(experiencia);
    }
    
}