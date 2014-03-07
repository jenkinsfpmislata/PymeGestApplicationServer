/*
 * Project: PymeGestApplicationServer
 * Package: com.pymegest.applicationserver.api
 * File: SessionController.java
 * Date: 18-feb-2014
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
import com.pymegest.applicationserver.dao.UsuarioDAO;
import com.pymegest.applicationserver.domain.Usuario;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ruben Coll Tovar
 * @version 1.0
 * @since 1.0
 */
@Controller
public class SessionController {

    @Autowired
    UsuarioDAO usuarioDAO;

    @RequestMapping(value = {"/Session"}, method = RequestMethod.POST)
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse response, @RequestBody String jsonSession) {

        try {

            ObjectMapper objectMapper = new ObjectMapper();

            Credentials credentials = (Credentials) objectMapper.readValue(jsonSession, Credentials.class);

            Usuario usuario = usuarioDAO.findByLogin(credentials.getLogin());

            if (usuario == null) {
                //No existe usuario con login introducido
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json; chaset=UTF-8");

                String jsonOutput = objectMapper.writeValueAsString(null);
                response.getWriter().println(jsonOutput);
            } else {

                boolean existe = usuario.checkPassword(credentials.getPassword());

                if (existe) {

                    response.setStatus(HttpServletResponse.SC_OK);
                    response.setContentType("application/json; chaset=UTF-8");

                    String jsonOutput = objectMapper.writeValueAsString(usuario);
                    response.getWriter().println(jsonOutput);

                    httpServletRequest.getSession(true).setAttribute("idUsuario", usuario.getId_usuario());



                } else {

                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.setContentType("application/json; chaset=UTF-8");

                    String jsonOutput = objectMapper.writeValueAsString(null);
                    response.getWriter().println(jsonOutput);
                }

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

    @RequestMapping(value = {"/Session"}, method = RequestMethod.GET)
    public void read(HttpServletRequest httpServletRequest, HttpServletResponse response) {

        try {

            Integer idUsuario = (Integer) httpServletRequest.getSession(true).getAttribute("idUduario");

            Usuario usuario = usuarioDAO.read(idUsuario);

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json; chaset=UTF-8");

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(usuario);
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

    @RequestMapping(value = {"/Session"}, method = RequestMethod.DELETE)
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse response) {

        try {
           
            httpServletRequest.getSession(true).invalidate();

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
}