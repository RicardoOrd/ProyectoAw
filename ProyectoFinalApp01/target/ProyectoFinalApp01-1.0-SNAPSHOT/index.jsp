<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dev Summit 2025</title>
        <link rel="stylesheet" href="css/estilos.css">
        <style>
            iframe {
                margin-top: 20px;
                border: 2px solid #1E90FF;
                border-radius: 8px;
                max-width: 100%;
            }
        </style>
    </head>
    <body>

        <%@ include file="fragments/header.jspf" %>
        <%@ include file="fragments/nav.jspf" %>

        <main class="contenedor-principal">
            <section id="inicio" class="seccion-bienvenida">
                <article>
                    <h2>Únete a la comunidad</h2>
                    <p>Tres días de conferencias, talleres y networking con los mejores exponentes de la industria del software.</p>
                    <img src="https://placehold.co/600x200/333/FFF?text=Dev+Summit" alt="Banner del evento" class="img-banner">
                </article>
            </section>

            <section id="registro" class="seccion-formulario">
                <h2>Formulario de Inscripción</h2>
                
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
                        <label for="aceptoTerminos">He leído y acepto los términos y condiciones.</label>
                    </div>
                    
                    <div class="grupo-form">
                        <label for="comentarios">Comentarios:</label>
                        <textarea id="comentarios" name="comentarios" rows="4"></textarea>
                    </div>
                    
                    <button type="submit">Inscribirme Ahora</button>
                </form>
            </section>

            <section id="agenda" class="seccion-extra">
                <article>
                    <h3>Costos de Entrada</h3>
                    <table>
                        <thead>
                            <tr>
                                <th>Boleto</th>
                                <th>Preventa</th>
                                <th>Regular</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Estudiante</td>
                                <td>$600 MXN</td>
                                <td>$900 MXN</td>
                            </tr>
                            <tr>
                                <td>Profesional</td>
                                <td>$1,800 MXN</td>
                                <td>$2,500 MXN</td>
                            </tr>
                        </tbody>
                    </table>
                </article>
            </section>

            <section id="ubicacion">
                <h2>Sede del Evento</h2>
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3762.661640529683!2d-99.16869368561817!3d19.42702058688746!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x85d1ff35f5bd1563%3A0x6c366f0e2de02ff7!2sEl%20%C3%81ngel%20de%20la%20Independencia!5e0!3m2!1ses-419!2smx!4v1645564859345!5m2!1ses-419!2smx" width="600" height="450" style="border:0;" allowfullscreen loading="lazy"></iframe>
            </section>
        </main>

        <%@ include file="fragments/footer.jspf" %>

        <script src="js/app.js"></script>
    </body>
</html>