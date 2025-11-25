<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro Completado</title>
        
        <style>
            body { font-family: sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; background-color: #f0f0f0; }
            .mensaje { text-align: center; background: white; padding: 30px; border-radius: 10px; box-shadow: 0 0 15px rgba(0,0,0,0.1); }
            h1 { color: #2c3e50; }
            a { display: inline-block; margin-top: 20px; padding: 10px 20px; background: #1E90FF; color: white; text-decoration: none; border-radius: 5px; }
        </style>
    </head>
    <body>
        <div class="mensaje">

            <c:if test="${not empty nombreUsuario}">
                <h1>¡Gracias por inscribirte, ${nombreUsuario}!</h1>
                <p>Hemos guardado tus datos. Recibirás un correo de confirmación en <strong>${emailUsuario}</strong>.</p>
            </c:if>

            <a href="<c:url value='/index.jsp' />">Volver a la página principal</a>
        </div>
    </body>
</html>