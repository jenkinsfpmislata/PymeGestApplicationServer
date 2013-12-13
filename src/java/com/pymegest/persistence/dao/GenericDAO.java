/*
 * Project: PymeGestApplicationServer
 * Package: com.pymegest.persistence.dao
 * File: GenericDAO.java
 * Date: 13-dic-2013
 * Encoding: UTF-8
 * License: H:\2º DAW\Proyecto\PymeGestApplicationServer\GPL 3.0
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
package com.pymegest.persistence.dao;

import java.util.List;

/**
 * @author Ruben Coll Tovar
 * @version 1.0
 * @since 1.0
 */
public interface GenericDAO<T, ID> {

    T read(ID id);
    
    void insert(T t);

    void update(T t);

    void delete(ID id);

    List<T> findAll();
}
