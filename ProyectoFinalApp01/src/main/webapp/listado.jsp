<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Listado de Registros - Dev Summit</title>
        <link rel="stylesheet" href="css/estilos.css">
        
        <style>
            .tabla-registros {
                width: 90%;
                margin: 20px auto;
                border-collapse: collapse;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }
            .tabla-registros th, .tabla-registros td {
                padding: 12px;
                border: 1px solid #ddd;
                text-align: left;
            }
            .tabla-registros th {
                background-color: #1E90FF;
                color: white;
            }
            .tabla-registros tr:nth-child(even) {
                background-color: #f2f2f2;
            }
        </style>
    </head>
    <body>
        
        <%@ include file="fragments/header.jspf" %>
        <%@ include file="fragments/nav.jspf" %>

        <main class="contenedor-principal">
            <section style="width: 100%">
                <h2>Participantes Registrados</h2>
                
                <table class="tabla-registros">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Email</th>
                            <th>Experiencia</th>
                            <th>Intereses</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="p" items="${listaParticipantes}">
                            <tr>
                                <td>${p.id}</td>
                                <td>${p.nombre}</td>
                                <td>${p.email}</td>
                                <td>${p.experiencia}</td>
                                <td>
                                    <ul>
                                        <c:forEach var="interes" items="${p.intereses}">
                                            <li>${interes}</li>
                                        </c:forEach>
                                    </ul>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </section>
        </main>

        <%@ include file="fragments/footer.jspf" %>
        
    </body>
</html>