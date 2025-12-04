<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Listado de Participantes</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
    <h1>Participantes Registrados</h1>
    
    <a href="index.jsp" class="btn">Nuevo Registro</a>

    <table border="1" id="tabla-participantes">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Email</th>
                <th>Experiencia</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody id="tabla-body">
            </tbody>
    </table>

    <script src="js/app.js"></script>
</body>
</html>