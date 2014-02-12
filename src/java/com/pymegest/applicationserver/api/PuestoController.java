/*
 * Project: PymeGestApplicationServer
 * Package: com.pymegest.applicationserver.api
 * File: PuestoController.java
 * Date: 11-feb-2014
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
import com.pymegest.applicationserver.dao.PuestoDAO;
import com.pymegest.applicationserver.domain.Puesto;
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
public class PuestoController {
    
    @Autowired
    PuestoDAO puestoDAO;
    
    @RequestMapping(value = {"/Puesto/{idsPuestos}"}, method = RequestMethod.GET)
    public void read(HttpServletRequest request, HttpServletResponse response, @PathVariable("idsPuestos") String idsPuestosStr) {

        try {
            
            String[] idsPuestosArr= idsPuestosStr.split(",");
            List<Puesto> listaPuestos = new ArrayList();
            for (int i=0; i<idsPuestosArr.length; i++) {
                listaPuestos.add(puestoDAO.read(Integer.parseInt(idsPuestosArr[i])));
            }
            
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json; chaset=UTF-8");

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(listaPuestos);
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

    @RequestMapping(value = {"/Puesto/{idsPuestos}"}, method = RequestMethod.DELETE)
    public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("idsPuestos") String idsPuestosStr) {

        try {
            String[] idsPuestosArr = idsPuestosStr.split(",");
            for (int i=0; i<idsPuestosArr.length; i++) {
                puestoDAO.delete(Integer.parseInt(idsPuestosArr[i]));
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

    @RequestMapping(value = {"/Puesto"}, method = RequestMethod.GET)
    public void findAll(HttpServletRequest request, HttpServletResponse response) {

        try {

            List<Puesto> puestos = puestoDAO.findAll();


            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json; chaset=UTF-8");

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(puestos);
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

    @RequestMapping(value = {"/Puesto"}, method = RequestMethod.POST)
    public void insert(HttpServletRequest request, HttpServletResponse response, @RequestBody String jsonInput) {

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            Puesto puesto = (Puesto) objectMapper.readValue(jsonInput, Puesto.class);

            puestoDAO.insert(puesto);

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json; chaset=UTF-8");

            String jsonOutput = objectMapper.writeValueAsString(puesto);
            response.getWriter().println(jsonOutput);

        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/plain; charset=UTF-8;");
            try {

                ex.printStackTrace(response.getWriter());

            } catch (IOException ex1) {
            }
        }

    }

    @RequestMapping(value = {"/Puesto/{id_puesto}"}, method = RequestMethod.PUT)
    public void update(HttpServletRequest request, HttpServletResponse response, @PathVariable("id_puesto") int id_puesto, @RequestBody String jsonInput) {

        try {

            Puesto puestoRead = puestoDAO.read(id_puesto);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            Puesto puesto = (Puesto) objectMapper.readValue(jsonInput, Puesto.class);
            
            puestoDAO.update(puestoRead);

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json; chaset=UTF-8");

            String jsonOutput = objectMapper.writeValueAsString(puesto);
            response.getWriter().println(jsonOutput);

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