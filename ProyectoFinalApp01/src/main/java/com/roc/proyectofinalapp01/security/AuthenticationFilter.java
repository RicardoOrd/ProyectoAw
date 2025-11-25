package com.roc.proyectofinalapp01.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/listar"})
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        // 1. Convertir los objetos request/response a su versión HTTP
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 2. Obtener la sesión actual. 
        HttpSession session = httpRequest.getSession(false); 
        
        boolean isLoggedIn = (session != null && session.getAttribute("usuarioLogueado") != null);

        if (isLoggedIn) {
            System.out.println("Filter: Acceso PERMITIDO a /listar.");
            chain.doFilter(request, response);
        } else {
            System.out.println("Filter: Acceso DENEGADO a /listar. Redirigiendo a login.");
            
            // Añadimos un mensaje de error que 'login.jsp' podrá mostrar
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp?error=Debes+iniciar+sesion+para+ver+esta+pagina");
        }
    }

    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Metodo de inicialización
    }
    
    @Override
    public void destroy() {
        // Metodo de destrucción
    }
}