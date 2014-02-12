/*
 * Project: PymeGestApplicationServer
 * Package: com.pymegest.applicationserver.api
 * File: FamiliaController.java
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
import com.pymegest.applicationserver.dao.FamiliaDAO;
import com.pymegest.applicationserver.domain.Familia;
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
public class FamiliaController {

    @Autowired
    FamiliaDAO familiaDAO;

    @RequestMapping(value = {"/Familia/{idsFamilias}"}, method = RequestMethod.GET)
    public void read(HttpServletRequest request, HttpServletResponse response, @PathVariable("idsFamilias") String idsFamiliasStr) {

        try {

            String[] idsFamiliasArr = idsFamiliasStr.split(",");
            List<Familia> listaFamilias = new ArrayList();
            for (int i = 0; i < idsFamiliasArr.length; i++) {
                listaFamilias.add(familiaDAO.read(Integer.parseInt(idsFamiliasArr[i])));
            }

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json; chaset=UTF-8");

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(listaFamilias);
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

    @RequestMapping(value = {"/Familia/{idsFamilias}"}, method = RequestMethod.DELETE)
    public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("idsFamilias") String idsFamiliasStr) {

        try {
            String[] idsFamiliasArr = idsFamiliasStr.split(",");
            for (int i = 0; i < idsFamiliasArr.length; i++) {
                familiaDAO.delete(Integer.parseInt(idsFamiliasArr[i]));
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

    @RequestMapping(value = {"/Familia"}, method = RequestMethod.GET)
    public void findAll(HttpServletRequest request, HttpServletResponse response) {

        try {

            List<Familia> familias = familiaDAO.findAll();


            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json; chaset=UTF-8");

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(familias);
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

    @RequestMapping(value = {"/Familia"}, method = RequestMethod.POST)
    public void insert(HttpServletRequest request, HttpServletResponse response, @RequestBody String jsonInput) {

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            Familia familia = (Familia) objectMapper.readValue(jsonInput, Familia.class);

            familiaDAO.insert(familia);

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json; chaset=UTF-8");

            String jsonOutput = objectMapper.writeValueAsString(familia);
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

    @RequestMapping(value = {"/Familia/{id_familia}"}, method = RequestMethod.PUT)
    public void update(HttpServletRequest request, HttpServletResponse response, @PathVariable("id_familia") int id_familia, @RequestBody String jsonInput) {

        try {

            Familia familiaRead = familiaDAO.read(id_familia);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            Familia familia = (Familia) objectMapper.readValue(jsonInput, Familia.class);

            familiaDAO.update(familiaRead);

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json; chaset=UTF-8");

            String jsonOutput = objectMapper.writeValueAsString(familia);
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