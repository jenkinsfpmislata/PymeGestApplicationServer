/*
 * Project: PymeGestApplicationServer
 * Package: com.pymegest.persistence.dao.impl
 * File: GenericDAOImplHibernate.java
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
package com.pymegest.persistence.dao.impl;

import com.pymegest.persistence.dao.GenericDAO;
import com.pymegest.persistence.hibernate.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author Ruben Coll Tovar
 * @version 1.0
 * @since 1.0
 */
public class GenericDAOImplHibernate<T, ID extends Serializable> implements GenericDAO<T, ID> {

    protected SessionFactory sessionFactory;

    public GenericDAOImplHibernate() {

        this.sessionFactory = HibernateUtil.getSessionFactory();

    }

    @Override
    public T read(ID id) {

        Session session = sessionFactory.getCurrentSession();

        T t = (T) session.get(getEntityClass(), id);

        return t;
    }

    @Override
    public void insert(T t) {

        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        session.save(t);

        session.getTransaction().commit();

    }

    @Override
    public void update(T t) {

        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        session.update(t);

        session.getTransaction().commit();

    }

    @Override
    public void delete(ID id) {

        Session session = sessionFactory.getCurrentSession();

        T t = (T) session.get(getEntityClass(), id);

        session.beginTransaction();

        session.delete(t);

        session.getTransaction().commit();
    }

    @Override
    public List<T> findAll() {

        Session session = sessionFactory.getCurrentSession(); //Abrimos la sesion   

        Query query = session.createQuery("SELECT t FROM " + getEntityClass().getName() + " t");

        List<T> tList = query.list();

        return tList;
    }

    private Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
