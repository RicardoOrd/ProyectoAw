<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login - Dev Summit</title>
        <link rel="stylesheet" href="css/estilos.css">
        
        <style>
            .seccion-formulario {
                max-width: 500px;
                margin: 20px auto;
                padding: 20px;
            }
        </style>
    </head>
    <body>
        
        <%@ include file="fragments/header.jspf" %>
        <%@ include file="fragments/nav.jspf" %>

        <main class="contenedor-principal">
            <section id="login" class="seccion-formulario">
                <h2>Iniciar Sesión</h2>
                <p>Usa "admin" / "admin" para entrar (simulación).</p>
                
                <%-- Mostramos un mensaje de error si el filtro nos redirige --%>
                <c:if test="${not empty param.error}">
                    <p style="color: red; font-weight: bold;">
                        Error: ${param.error}
                    </p>
                </c:if>
                
                <%-- Mostramos un mensaje si el login falla --%>
                <c:if test="${not empty errorLogin}">
                    <p style="color: red; font-weight: bold;">
                        Error: ${errorLogin}
                    </p>
                </c:if>

                <form action="<c:url value='/login' />" method="POST">
                    <div class="grupo-form">
                        <label for="username">Usuario:</label>
                        <input type="text" id="username" name="username" required>
                    </div>
                    <div class="grupo-form">
                        <label for="password">Contraseña:</label>
                        <input type="password" id="password" name="password" required>
                    </div>
                    <button type="submit">Entrar</button>
                </form>
            </section>
        </main>

        <%@ include file="fragments/footer.jspf" %>
        
    </body>
</html>