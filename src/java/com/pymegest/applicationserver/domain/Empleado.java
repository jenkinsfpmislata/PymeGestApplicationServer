/*
 * Project: PymeGestApplicationServer
 * Package: com.pymegest.applicationserver.domain
 * File: Empleado.java
 * Date: 07-feb-2014
 * Encoding: UTF-8
 * License: GPL 3.0
 *
 * Copyright(c) PymeGest 2014
 * www.pymegest.com
 * admin@pymegest.com
 *
 * This file is part of Pymegest.
 * Pymegest is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * Pymegest is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Pymegest. If not, see <http://www.gnu.org/licenses/>.
 */

package com.pymegest.applicationserver.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Miguel María Martínez Echeverría
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "empleado")
public class Empleado implements Serializable{
    
    @Id
    @Column(name = "id_empleado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_empleado;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "apellidos")
    private String apellidos;
    
    @Column(name = "salario")
    private BigDecimal salario;
    
    @Column(name = "correo_electronico")
    private String correo_electronico;
    
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="empleado_puesto", joinColumns={@JoinColumn(name="id_empleado")}, inverseJoinColumns={@JoinColumn(name="id_puesto")})
    private Set<Puesto> puestos=new HashSet();
    
    public Empleado(){
        
    }
    
    public Empleado(Integer id_empleado) {
        this.id_empleado = id_empleado;
    }
    
    /**
     * @return the id_usuario
     */
    public Integer getId_empleado() {
        return id_empleado;
    }
    
    /**
     * @param id_empleado the id_empleado to set
     */
    public void setId_empleado(Integer id_empleado) {
        this.id_empleado = id_empleado;
    }
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the salario
     */
    public BigDecimal getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    /**
     * @return the correo_electronico
     */
    public String getCorreo_electronico() {
        return correo_electronico;
    }

    /**
     * @param correo_electronico the correo_electronico to set
     */
    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    /**
     * @return the puestos
     */
    public Set<Puesto> getPuestos() {
        return puestos;
    }

    /**
     * @param puestos the puestos to set
     */
    public void setPuestos(Set<Puesto> puestos) {
        this.puestos = puestos;
    }
    
    

}