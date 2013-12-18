/*
 * Project: PymeGestApplicationServer
 * Package: com.pymegest.applicationserver.dominio
 * File: Usuario.java
 * Date: 17-dic-2013
 * Encoding: UTF-8
 * License: GPL 3.0
 *
 * Copyright(c) PymeGest 2013
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
package com.pymegest.applicationserver.dominio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Juanjo
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
 
    @Id
    @Column(name="id_usuario")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idUsuario;
    
    @Enumerated(EnumType.ORDINAL)
    private TipoCuenta tipoCuenta;

    public Usuario() {
    }
    
    public Usuario(Integer idUsuario, TipoCuenta tipoCuenta) {
        this.idUsuario = idUsuario;
        this.tipoCuenta = tipoCuenta;
    }   

    /**
     * @return the idUsuario
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the tipoCuenta
     */
    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * @param tipoCuenta the tipoCuenta to set
     */
    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }


}