package com.roc.proyectofinalapp01.servlets;

import com.roc.proyectofinalapp01.dao.ParticipanteDAO;
import com.roc.proyectofinalapp01.model.Participante;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "ListarParticipantesServlet", urlPatterns = {"/listar"})
public class ListarParticipantesServlet extends HttpServlet {

    private ParticipanteDAO participanteDAO;

    @Override
    public void init() {
        participanteDAO = new ParticipanteDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Llamar al DAO para obtener la lista
        List<Participante> listaParticipantes = participanteDAO.obtenerTodos();

        // 2. Guardar la lista en el request (
        request.setAttribute("listaParticipantes", listaParticipantes);

        // 3. Redirigir al JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("listado.jsp");
        dispatcher.forward(request, response);
    }
}