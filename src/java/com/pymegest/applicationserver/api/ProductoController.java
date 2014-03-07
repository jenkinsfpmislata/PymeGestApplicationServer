/*
 * Project: PymeGestApplicationServer
 * Package: com.pymegest.applicationserver.api
 * File: ProductoController.java
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
package com.pymegest.applicationserver.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.pymegest.applicationserver.dao.ProductoDAO;
import com.pymegest.applicationserver.domain.BussinesMessage;
import com.pymegest.applicationserver.domain.Producto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Miguel María Martínez Echeverría
 * @version 1.0
 * @since 1.0
 */
@Controller
public class ProductoController {

    @Autowired
    ProductoDAO productoDAO;

    @RequestMapping(value = {"/Producto/{idsProductos}"}, method = RequestMethod.GET)
    public void read(HttpServletRequest request, HttpServletResponse response, @PathVariable("idsProductos") String idsProductosStr) {

        try {

            String[] idsProductosArr = idsProductosStr.split(",");
            List<Producto> listaProductos = new ArrayList();
            for (int i = 0; i < idsProductosArr.length; i++) {
                listaProductos.add(productoDAO.read(Integer.parseInt(idsProductosArr[i])));
            }

            if (listaProductos.isEmpty() == false) {

                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json; chaset=UTF-8");

                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(listaProductos);
                response.getWriter().println(json);

            } else {

                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json; chaset=UTF-8");

                BussinesMessage mensaje = new BussinesMessage();
                mensaje.setMensaje("La lista de productos esta vacia.");

                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(mensaje);
                response.getWriter().println(json);

            }

        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/plain; charset=UTF-8;");
            try {

                ex.printStackTrace(response.getWriter());

            } catch (IOException ex1) {
            }
        }

    }

    @RequestMapping(value = {"/Producto/{idsProductos}"}, method = RequestMethod.DELETE)
    public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("idsProductos") String idsProductosStr) {

        try {
            String[] idsProductosArr = idsProductosStr.split(",");
            for (int i = 0; i < idsProductosArr.length; i++) {
                productoDAO.delete(Integer.parseInt(idsProductosArr[i]));
            }

            response.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/plain; charset=UTF-8");
            try {

                ex.printStackTrace(response.getWriter());

            } catch (IOException ex1) {
            }
        }

    }

    @RequestMapping(value = {"/Producto"}, method = RequestMethod.GET)
    public void findAll(HttpServletRequest request, HttpServletResponse response) {

        try {

            List<Producto> productos = productoDAO.findAll();


            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json; chaset=UTF-8");

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(productos);
            response.getWriter().println(json);


        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/plain; charset=UTF-8;");
            try {

                ex.printStackTrace(response.getWriter());

            } catch (IOException ex1) {
            }
        }

    }

    @RequestMapping(value = {"/Producto"}, method = RequestMethod.POST)
    public void insert(HttpServletRequest request, HttpServletResponse response, @RequestBody String jsonInput) {

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            Producto producto = (Producto) objectMapper.readValue(jsonInput, Producto.class);

            if (producto != null) {

                productoDAO.insert(producto);

                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json; chaset=UTF-8");

                String jsonOutput = objectMapper.writeValueAsString(producto);
                response.getWriter().println(jsonOutput);
            } else {

                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json; chaset=UTF-8");

                BussinesMessage mensaje = new BussinesMessage();
                mensaje.setMensaje("Imposible insertar un producto.");

                String json = objectMapper.writeValueAsString(mensaje);
                response.getWriter().println(json);

            }

        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/plain; charset=UTF-8;");
            try {

                ex.printStackTrace(response.getWriter());

            } catch (IOException ex1) {
            }
        }

    }

    @RequestMapping(value = {"/Producto/{id_producto}"}, method = RequestMethod.PUT)
    public void update(HttpServletRequest request, HttpServletResponse response, @PathVariable("id_producto") int id_producto, @RequestBody String jsonInput) {

        try {

            Producto productoRead = productoDAO.read(id_producto);
            
            if (productoRead != null) {
                
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                Producto producto = (Producto) objectMapper.readValue(jsonInput, Producto.class);

                productoDAO.update(productoRead);

                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json; chaset=UTF-8");

                String jsonOutput = objectMapper.writeValueAsString(producto);
                response.getWriter().println(jsonOutput);
                
            } else {

                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json; chaset=UTF-8");

                BussinesMessage mensaje = new BussinesMessage();
                mensaje.setMensaje("No se ha encontrado ningun producto para actualizar.");

                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(mensaje);
                response.getWriter().println(json);
            }
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/plain; charset=UTF-8");
            try {

                ex.printStackTrace(response.getWriter());

            } catch (IOException ex1) {
            }
        }
    }
}