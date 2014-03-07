/*
 * Project: PymeGestApplicationServer
 * Package: com.pymegest.applicationserver.api
 * File: EmpleadoController.java
 * Date: 07-feb-2014
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
import com.pymegest.applicationserver.dao.EmpleadoDAO;
import com.pymegest.applicationserver.domain.BussinesMessage;
import com.pymegest.applicationserver.domain.Empleado;
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
public class EmpleadoController {

    @Autowired
    EmpleadoDAO empleadoDAO;

    @RequestMapping(value = {"/Empleado/{idsEmpleados}"}, method = RequestMethod.GET)
    public void read(HttpServletRequest request, HttpServletResponse response, @PathVariable("idsEmpleados") String idsEmpleadosStr) {

        try {

            String[] idsEmpleadosArr = idsEmpleadosStr.split(",");
            List<Empleado> listaEmpleados = new ArrayList();
            for (int i = 0; i < idsEmpleadosArr.length; i++) {
                listaEmpleados.add(empleadoDAO.read(Integer.parseInt(idsEmpleadosArr[i])));
            }

            if (listaEmpleados.isEmpty() == false) {

                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json; chaset=UTF-8");

                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(listaEmpleados);
                response.getWriter().println(json);

            } else {

                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json; chaset=UTF-8");
                
                BussinesMessage mensaje = new BussinesMessage();
                mensaje.setMensaje("La lista de empleados esta vacia.");

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

    @RequestMapping(value = {"/Empleado/{idsEmpleados}"}, method = RequestMethod.DELETE)
    public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("idsEmpleados") String idsEmpleadosStr) {

        try {

            String[] idsEmpleadosArr = idsEmpleadosStr.split(",");
            for (int i = 0; i < idsEmpleadosArr.length; i++) {
                empleadoDAO.delete(Integer.parseInt(idsEmpleadosArr[i]));
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

    @RequestMapping(value = {"/Empleado"}, method = RequestMethod.GET)
    public void findAll(HttpServletRequest request, HttpServletResponse response) {

        try {

            List<Empleado> empleado = empleadoDAO.findAll();


            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json; chaset=UTF-8");

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(empleado);
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

    @RequestMapping(value = {"/Empleado"}, method = RequestMethod.POST)
    public void insert(HttpServletRequest request, HttpServletResponse response, @RequestBody String jsonInput) {

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            Empleado empleado = (Empleado) objectMapper.readValue(jsonInput, Empleado.class);

            if (empleado != null) {

                empleadoDAO.insert(empleado);

                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json; chaset=UTF-8");

                String jsonOutput = objectMapper.writeValueAsString(empleado);
                response.getWriter().println(jsonOutput);
            } else {

                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json; chaset=UTF-8");

                BussinesMessage mensaje = new BussinesMessage();
                mensaje.setMensaje("Imposible insertar un empleado.");

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

    @RequestMapping(value = {"/Empleado/{id_empleado}"}, method = RequestMethod.PUT)
    public void update(HttpServletRequest request, HttpServletResponse response, @PathVariable("id_empleado") int id_empleado, @RequestBody String jsonInput) {

        try {

            Empleado empleadoRead = empleadoDAO.read(id_empleado);

            if (empleadoRead != null) {

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                Empleado empleado = (Empleado) objectMapper.readValue(jsonInput, Empleado.class);

                empleadoDAO.update(empleadoRead);

                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json; chaset=UTF-8");

                String jsonOutput = objectMapper.writeValueAsString(empleado);
                response.getWriter().println(jsonOutput);
                
            }
            else {
                
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json; chaset=UTF-8");
                
                BussinesMessage mensaje = new BussinesMessage();
                mensaje.setMensaje("No se ha encontrado ningun empleado para actualizar.");
                
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