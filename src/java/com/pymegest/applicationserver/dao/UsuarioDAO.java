/*
 * Project: PymeGestApplicationServer
 * Package: com.pymegest.persistence.dao
 * File: UserDAO.java
 * Date: 13-dic-2013
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

package com.pymegest.applicationserver.dao;

import com.pymegest.persistence.dao.GenericDAO;
import com.pymegest.applicationserver.domain.Usuario;
import java.util.List;



/**
 * @author Ruben Coll Tovar
 * @version 1.0
 * @since 1.0
 */
public interface UsuarioDAO extends GenericDAO<Usuario, Integer>{
       
    public List<Usuario> findByName(String nombre);
    public Usuario findByLogin(String login);
    
}
