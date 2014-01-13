/*
 * Project: PymeGestApplicationServer
 * Package: com.pymegest.applicationserver.domain
 * File: Provincia.java
 * Date: 13-ene-2014
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

import java.util.List;

/**
 * @author Rafa Hernández Novillo
 * @version 1.0
 * @since 1.0
 */
public class Provincia {
    
    private int id;
    private int codigo;
    private int nombre;
    private Pais pais;
    private List municipios;
}