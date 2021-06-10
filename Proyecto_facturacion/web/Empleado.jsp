<%-- 
    Document   : Empleado
    Created on : 10/06/2021, 11:36:25 AM
    Author     : Camilo Garcia
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Facturacion web</title>
    </head>
    <body>
        <form id="empleado_data" method="post" action="Empleado">
            <label for="Nombre_">Nombre:*</label>
            <input type="text" id="Nombre" name="Nombre" placeholder="Escriba el nombre del empleado" />
            <label for="Contrasena_">Contraseña:*</label>
            <input type="text" id="contrasena" name="contrasena" placeholder="Escriba la contraseña" />
            <input type="submit" value="enviar" id="id_boton">
        </form>
    </body>
</html>
