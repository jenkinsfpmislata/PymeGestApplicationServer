/*
 * Project: PymeGestApplicationServer
 * Package: com.pymegest.applicationserver.domain
 * File: Puesto.java
 * Date: 10-feb-2014
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
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Miguel María Martínez Echeverría
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "puesto")
public class Puesto implements Serializable{

    @Id
    @Column(name="id_puesto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_puesto;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="descripcion")
    private String descripcion;
    
    @ManyToMany(cascade = {CascadeType.ALL},mappedBy="puestos")
    private Set<Empleado> empleados=new HashSet();
    
    public Puesto(){
        
    }
    
    public Puesto(Integer id_puesto){
        
        this.id_puesto = id_puesto;
    }

    /**
     * @return the id_puesto
     */
    public Integer getId_puesto() {
        return id_puesto;
    }

    /**
     * @param id_puesto the id_puesto to set
     */
    public void setId_puesto(Integer id_puesto) {
        this.id_puesto = id_puesto;
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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the empleados
     */
    public Set<Empleado> getEmpleados() {
        return empleados;
    }

    /**
     * @param empleados the empleados to set
     */
    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
    }
     
}