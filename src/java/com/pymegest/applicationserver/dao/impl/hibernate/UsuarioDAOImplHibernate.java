/*
 * Project: PymeGestApplicationServer
 * Package: com.pymegest.persistence.dao.impl
 * File: UsuarioDAOImplHibernate.java
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
package com.pymegest.applicationserver.dao.impl.hibernate;

import com.pymegest.applicationserver.dao.UsuarioDAO;
import com.pymegest.persistence.dao.impl.GenericDAOImplHibernate;
import com.pymegest.applicationserver.domain.Usuario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;




/**
 * @author Ruben Coll Tovar
 * @version 1.0
 * @since 1.0
 */
public class UsuarioDAOImplHibernate extends GenericDAOImplHibernate<Usuario, Integer> implements UsuarioDAO {

    @Override
    public List<Usuario> findByName(String nombre) {

        if (nombre == null) {

            Session session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("SELECT usuario FROM Usuario usuario");

            List<Usuario> usuarios = query.list();

            return usuarios;

        } else {

            Session session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("SELECT usuario FROM Usuario usuario WHERE nombre LIKE ?");

            query.setString(0, "%" + nombre + "%");

            List<Usuario> usuarios = query.list();

            return usuarios;
            
        }
    }
    
    @Override
    public Usuario findByLogin(String login) {

        if (login == null) {
            
            return null;
        } else {

            Session session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("SELECT usuario FROM Usuario usuario WHERE login = ?");

            query.setString(0,login);

            Usuario usuario = (Usuario) query.uniqueResult();
            
            return usuario;
        }
    }
}
