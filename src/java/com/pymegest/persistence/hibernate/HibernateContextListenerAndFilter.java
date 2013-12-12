/*
 * Project: PymeGestApplicationServer
 * Package: com.pymegest.persistence.hibernate
 * File: HibernateContextListenerAndFilter.java
 * Date: 12-dic-2013
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
package com.pymegest.persistence.hibernate;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Rafa Hernández
 * @version 1.0
 * @since 1.0
 */
public class HibernateContextListenerAndFilter implements Filter, ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        HibernateUtil.buildSessionFactory();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HibernateUtil.openSessionAndBindToThread();
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateUtil.closeSessionFactory();
    }
}