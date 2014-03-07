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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.pymegest.applicationserver.dao.UsuarioDAO;
import com.pymegest.applicationserver.domain.BussinesMessage;
import com.pymegest.applicationserver.domain.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private UsuarioDAO usuarioDAO;

    @RequestMapping(value = {"/Session"}, method = RequestMethod.POST)
    public void login(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestBody String json) throws JsonProcessingException {
        try {
            
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            Credentials login = (Credentials) objectMapper.readValue(json, Credentials.class);
            Usuario usuario = usuarioDAO.findByLogin(login.getLogin());
            
            if (usuario != null) {
                
                if (usuario.checkPassword(login.getPassword())) {
                    
                    HttpSession httpSession = httpRequest.getSession();
                    httpSession.setAttribute("usuario", usuario.getLogin());
                    noCache(httpServletResponse);
                    httpServletResponse.setStatus(HttpServletResponse.SC_OK);

                } else {
                    
                    noCache(httpServletResponse);
                    httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }
            } else {
                
                noCache(httpServletResponse);
                httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
            
        } catch (ConstraintViolationException cve) {
            
            List<BussinesMessage> errorList = new ArrayList();
            ObjectMapper jackson = new ObjectMapper();
            
            for (ConstraintViolation constraintViolation : cve.getConstraintViolations()) {
                
                String datos = constraintViolation.getPropertyPath().toString();
                String mensaje = constraintViolation.getMessage();
                BussinesMessage bussinesMessage = new BussinesMessage(datos, mensaje);
                errorList.add(bussinesMessage);
            }
            
            jackson.writeValueAsString(errorList);
            noCache(httpServletResponse);
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            
        } catch (Exception ex) {
            
            noCache(httpServletResponse);
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("text/plain; charset=UTF-8");
            
            try {
                
                noCache(httpServletResponse);
                ex.printStackTrace(httpServletResponse.getWriter());
                
            } catch (Exception ex1) {
                
                noCache(httpServletResponse);
            }
        }
    }

    @RequestMapping(value = {"/Session"}, method = RequestMethod.GET)
    public void recuperarSession(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse) {
        try {

            HttpSession session = httpRequest.getSession();
            String username = (String) session.getAttribute("usuario");
            Usuario usuario = usuarioDAO.findByLogin(username);

            if (usuario != null) {

                ObjectMapper jackson = new ObjectMapper();
                String userJson = jackson.writeValueAsString(usuario);
                noCache(httpServletResponse);
                httpServletResponse.getWriter().println(userJson);
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                httpServletResponse.setContentType("text/plain; charset=UTF-8");

            } else {
                
                noCache(httpServletResponse);
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpServletResponse.setContentType("text/plain; charset=UTF-8");
                
            }
            
            noCache(httpServletResponse);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        } catch (Exception ex) {
            
            noCache(httpServletResponse);
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("text/plain; charset=UTF-8");
            
            try {
                
                noCache(httpServletResponse);
                ex.printStackTrace(httpServletResponse.getWriter());
                
            } catch (Exception ex1) {
                
                noCache(httpServletResponse);
            }
        }
    }

    @RequestMapping(value = {"/Session"}, method = RequestMethod.DELETE)
    public void deleteSession(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse) {
        
        try {
            
            HttpSession session = httpRequest.getSession();
            String username = (String) session.getAttribute("usuario");
            Usuario usuario = usuarioDAO.findByLogin(username);
            
            if (usuario != null) {
                
                session.removeAttribute("usuario");
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                
            } else {
                
                noCache(httpServletResponse);
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpServletResponse.setContentType("text/plain; charset=UTF-8");
            }
            
            noCache(httpServletResponse);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        } catch (Exception ex) {
            
            noCache(httpServletResponse);
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("text/plain; charset=UTF-8");
            
            try {
                
                noCache(httpServletResponse);
                ex.printStackTrace(httpServletResponse.getWriter());
                
            } catch (Exception ex1) {
                
                noCache(httpServletResponse);
            }
        }
    }

    private void noCache(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Cache-Control", "no-cache");
    }
}