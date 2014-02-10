/*
 * Project: PymeGestApplicationServer
 * Package: com.pymegest.applicationserver.domain
 * File: Producto.java
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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Miguel María Martínez Echeverría
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "producto")
public class Producto implements Serializable{
    
    @Id
    @Column(name = "id_producto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_producto;
    
    @Column(name = "codigo_producto")
    private String codigo_producto;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "familia_producto")
    private String familia_producto;
    
    public Producto() {
        
    }
    
    public Producto(Integer id_producto) {
        
        this.id_producto = id_producto;        
    }

    /**
     * @return the id_producto
     */
    public Integer getId_producto() {
        return id_producto;
    }

    /**
     * @param id_producto the id_producto to set
     */
    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    /**
     * @return the codigo_producto
     */
    public String getCodigo_producto() {
        return codigo_producto;
    }

    /**
     * @param codigo_producto the codigo_producto to set
     */
    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
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
     * @return the familia_producto
     */
    public String getFamilia_producto() {
        return familia_producto;
    }

    /**
     * @param familia_producto the familia_producto to set
     */
    public void setFamilia_producto(String familia_producto) {
        this.familia_producto = familia_producto;
    }
    
}