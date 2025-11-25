<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Editar Participante</title>
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>

        <%@ include file="fragments/header.jspf" %>

        <main class="contenedor-principal" style="padding-top: 2rem;">

            <section class="seccion-formulario" style="margin: 2rem auto; max-width: 600px; float: none;">                
                <h2>Editar Datos del Participante</h2>

                <form id="formRegistro">

                    <input type="hidden" id="idParticipante">

                    <div class="grupo-form">
                        <label for="nombre">Nombre Completo:</label>
                        <input type="text" id="nombre" name="nombre" required pattern="[A-Za-z\s]+">
                    </div>

                    <div class="grupo-form">
                        <label for="email">Correo:</label>
                        <input type="email" id="email" name="email" required>
                    </div>

                    <div class="grupo-form">
                        <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                        <input type="date" id="fechaNacimiento" name="fechaNacimiento" required>
                    </div>

                    <div class="grupo-form">
                        <label for="experiencia">Tu Nivel:</label>
                        <select id="experiencia" name="experiencia" required>
                            <option value="Principiante">Principiante</option>
                            <option value="Intermedio">Intermedio</option>
                            <option value="Avanzado">Avanzado</option>
                        </select>
                    </div>

                    <div class="grupo-form">
                        <label for="intereses">Temas de Interés (Ctrl + Clic para varios):</label>
                        <select id="intereses" name="intereses" multiple size="4" required>
                            <option value="web_frontend">Web Frontend</option>
                            <option value="backend_java">Backend con Java</option>
                            <option value="cloud">Cloud & DevOps</option>
                            <option value="ia_ml">IA & Machine Learning</option>
                        </select>
                    </div>

                    <div class="grupo-form">
                        <input type="checkbox" id="aceptoTerminos" name="aceptoTerminos" value="si" required>
                        <label for="aceptoTerminos">Confirmo la actualización de datos.</label>
                    </div>

                    <div class="grupo-form">
                        <label for="comentarios">Comentarios:</label>
                        <textarea id="comentarios" name="comentarios" rows="4"></textarea>
                    </div>

                    <div style="display: flex; gap: 15px; margin-top: 15px;">
                        <button type="submit" style="flex: 1;">Guardar Cambios</button>

                        <button type="button" onclick="window.location.href = 'listado.jsp'" 
                                style="flex: 1; background-color: #6c757d; color: white; border: none; padding: 10px; border-radius: 5px; cursor: pointer;">
                            Cancelar
                        </button>
                    </div>
                </form>
            </section>
        </main>

        <%@ include file="fragments/footer.jspf" %>

        <script src="js/app.js"></script>
    </body>
</html>