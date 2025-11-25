package com.roc.proyectofinalapp01.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Simulacion de autenticacion 
        if ("admin".equals(username) && "admin".equals(password)) {
            
            // 1. Si es valido, obtenemos la sesión
            HttpSession session = request.getSession(true);
            
            // 2. Guardamos un objeto de usuario en la sesión
            session.setAttribute("usuarioLogueado", username); 
            
            System.out.println("LoginServlet: 'admin' ha iniciado sesión.");

            // 3. Redirigimos a la página de listado
            response.sendRedirect(request.getContextPath() + "/listar");
            
        } else {
            // 4. Si es invalido, regresamos al login con un mensaje de error
            request.setAttribute("errorLogin", "Usuario o contraseña incorrectos.");
            
            System.out.println("LoginServlet: Intento de login fallido para '" + username + "'.");
            
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}