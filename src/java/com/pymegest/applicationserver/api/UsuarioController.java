/*
 * Project: PymeGestApplicationServer
 * Package: com.pymegest.applicationserver.api
 * File: UsuarioController.java
 * Date: 17-dic-2013
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

package com.pymegest.applicationserver.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.pymegest.applicationserver.dao.UsuarioDAO;
import com.pymegest.applicationserver.dominio.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Miguel María Martínez Echeverría
 * @version 1.0
 * @since 1.0
 */
public class UsuarioController {
    
    @Autowired
    UsuarioDAO usuarioDAO;
    
    @RequestMapping(value = {"/Usuario/{idUsuario}"}, method = RequestMethod.GET)
    public void read(HttpServletRequest request, HttpServletResponse response, @PathVariable("idUsuario") int idUsuario) {

        try {

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

    @RequestMapping(value = {"/Usuario/{idUsuario}"}, method = RequestMethod.DELETE)
    public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("idUsuario") int idUsuario) {

        try {

            usuarioDAO.delete(idUsuario);
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

    @RequestMapping(value = {"/Usuario"}, method = RequestMethod.GET)
    public void findAll(HttpServletRequest request, HttpServletResponse response) {

        try {

            List<Usuario> usuarios = usuarioDAO.findAll();


            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json; chaset=UTF-8");

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(usuarios);
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

    @RequestMapping(value = {"/Usuario"}, method = RequestMethod.POST)
    public void insert(HttpServletRequest request, HttpServletResponse response, @RequestBody String jsonInput) {

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            Usuario usuario = (Usuario) objectMapper.readValue(jsonInput, Usuario.class);

            usuarioDAO.insert(usuario);

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json; chaset=UTF-8");

            String jsonOutput = objectMapper.writeValueAsString(usuario);
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

    @RequestMapping(value = {"/Usuario/{idUsuario}"}, method = RequestMethod.PUT)
    public void update(HttpServletRequest request, HttpServletResponse response, @PathVariable("idUsuario") int idUsuario, @RequestBody String jsonInput) {

        try {

            Usuario usuarioRead = usuarioDAO.read(idUsuario);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            Usuario usuario = (Usuario) objectMapper.readValue(jsonInput, Usuario.class);
            
            //MODIFICAMOS EL USUARIO READ CON LOS DATOS DE USUARIO
            
            //usuarioRead.setId(usuario.getId());
            //usuarioRead.set....
            
            usuarioDAO.update(usuarioRead);

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json; chaset=UTF-8");

            String jsonOutput = objectMapper.writeValueAsString(usuario);
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