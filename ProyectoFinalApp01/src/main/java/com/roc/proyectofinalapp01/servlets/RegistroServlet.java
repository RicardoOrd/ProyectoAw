package com.roc.proyectofinalapp01.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate; 


import com.roc.proyectofinalapp01.model.Participante;
import com.roc.proyectofinalapp01.dao.ParticipanteDAO;


@WebServlet(name = "RegistroServlet", urlPatterns = {"/RegistroServlet"})
public class RegistroServlet extends HttpServlet {

    private ParticipanteDAO participanteDAO;

    @Override
    public void init() {
        participanteDAO = new ParticipanteDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");
        String experiencia = request.getParameter("experiencia");
        String[] intereses = request.getParameterValues("intereses");
        String aceptoTerminosStr = request.getParameter("aceptoTerminos");
        String comentarios = request.getParameter("comentarios");

        System.out.println("--- Nuevo Registro Recibido ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Correo: " + email);
        System.out.println("---------------------------------");
        
        
        try {
            LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr); // YYYY-MM-DD
            boolean aceptoTerminos = (aceptoTerminosStr != null && aceptoTerminosStr.equals("si"));

            Participante nuevoParticipante = new Participante();
            nuevoParticipante.setNombre(nombre);
            nuevoParticipante.setEmail(email);
            nuevoParticipante.setFechaNacimiento(fechaNacimiento);
            nuevoParticipante.setExperiencia(experiencia);
            nuevoParticipante.setIntereses(intereses);
            nuevoParticipante.setAceptoTerminos(aceptoTerminos);
            nuevoParticipante.setComentarios(comentarios);

            participanteDAO.insertar(nuevoParticipante);
            
            request.setAttribute("nombreUsuario", nombre);
            request.setAttribute("emailUsuario", email);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("registroExitoso.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            System.err.println("Error al procesar el registro: " + e.getMessage());
            
            request.setAttribute("error", "Hubo un problema al procesar tu registro.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("registroExitoso.jsp");
            dispatcher.forward(request, response);
        }
    }
}