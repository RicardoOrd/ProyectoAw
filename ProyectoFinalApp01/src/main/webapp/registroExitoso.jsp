<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Registro Completado</title>
        <link rel="stylesheet" href="css/estilos.css"> <style>
            body { font-family: sans-serif; display: flex; flex-direction: column; min-height: 100vh; background-color: #f0f0f0; margin: 0;}
            .mensaje-container { flex: 1; display: flex; justify-content: center; align-items: center; }
            .mensaje { text-align: center; background: white; padding: 40px; border-radius: 10px; box-shadow: 0 0 15px rgba(0,0,0,0.1); max-width: 500px;}
            h1 { color: #2c3e50; }
            .btn-volver { display: inline-block; margin-top: 20px; padding: 10px 20px; background: #1E90FF; color: white; text-decoration: none; border-radius: 5px; }
        </style>
    </head>
    <body>
        <%@ include file="fragments/header.jspf" %>

        <div class="mensaje-container">
            <div class="mensaje">
                <h1>¡Gracias por inscribirte, <span id="nombre-usuario">Participante</span>!</h1>
                
                <p>Hemos guardado tus datos exitosamente.</p>
                <p>Ya eres parte de la comunidad Dev Summit.</p>

                <a href="listado.jsp" class="btn-volver">Ver Lista de Participantes</a>
            </div>
        </div>

        <%@ include file="fragments/footer.jspf" %>

        <script>
            // Lógica del Cliente (DOM):
            // Leemos el parámetro 'nombre' de la URL (ej: registroExitoso.jsp?nombre=Carlos)
            document.addEventListener("DOMContentLoaded", () => {
                const params = new URLSearchParams(window.location.search);
                const nombre = params.get('nombre');
                
                if (nombre) {
                    document.getElementById('nombre-usuario').textContent = nombre;
                }
            });
        </script>
    </body>
</html>