<%-- 
    Document   : Cliente
    Created on : 2/08/2021, 09:43:46 PM
    Author     : Camilo Garcia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href= "estilos2.css" rel="stylesheet">
        <title>Facturacion web</title>
    </head>
    <body>
        <form id="empleado_data" method="post" action="Cliente">
            <link rel="shortcut icon" href="Recursos/favicon.ico" mce_href="Recursos/favicon.ico" type="image/x-icon" />
            <input type="text" id="Nombre" name="Nombre" placeholder="Escriba el nombre del usuarioo" />
            <input type="text" id="contrasena" name="contrasena" placeholder="Escriba la contraseña" />
            <input type="submit" value="enviar" id="id_boton">
        </form>
        <form id="NC" method="post" action="LoadDatesNU">
            <input type="submit" value="¿Nuevo usuario?">
        </form>
    </body>
</html>