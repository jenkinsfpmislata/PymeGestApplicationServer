/*
 * Project: PymeGestApplicationServer
 * Package: com.pymegest.applicationserver.dao.impl.hibernate
 * File: ProductoDAOImplHibernate.java
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

package com.pymegest.applicationserver.dao.impl.hibernate;

import com.pymegest.applicationserver.dao.ProductoDAO;
import com.pymegest.applicationserver.domain.Producto;
import com.pymegest.persistence.dao.impl.GenericDAOImplHibernate;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @author Miguel María Martínez Echeverría
 * @version 1.0
 * @since 1.0
 */
public class ProductoDAOImplHibernate extends GenericDAOImplHibernate<Producto, Integer> implements ProductoDAO{

    @Override
    public List<Producto> findByName(String nombre) {
        
        if (nombre == null) {

            Session session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("SELECT producto FROM Producto producto");

            List<Producto> producto = query.list();

            return producto;

        } else {

            Session session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("SELECT producto FROM Producto producto WHERE nombre LIKE ?");

            query.setString(0, "%" + nombre + "%");

            List<Producto> producto = query.list();

            return producto;
            
        }
    }

}