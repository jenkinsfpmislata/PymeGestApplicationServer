/*
 * Project: PymeGestApplicationServer
 * Package: com.pymegest.applicationserver.domain
 * File: Familia.java
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import org.hibernate.annotations.IndexColumn;

/**
 * @author Miguel María Martínez Echeverría
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "familia")
public class Familia implements Serializable {

    @Id
    @Column(name = "id_familia")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_familia;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_producto")
    @IndexColumn(name = "idx")
    private List<Producto> productos = new ArrayList();;

    public Familia() {
    }

    public Familia(Integer id_familia) {

        this.id_familia = id_familia;
    }

    public Integer getId_familia() {
        return id_familia;
    }

    public void setId_familia(Integer id_familia) {
        this.id_familia = id_familia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}