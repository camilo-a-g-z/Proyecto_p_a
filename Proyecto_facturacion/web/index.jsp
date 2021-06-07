<%-- 
    Document   : index
    Created on : 7/06/2021, 10:19:34 AM
    Author     : Camilo Garcia
--%>

<%@page import="java.util.List"%>
<%@page import="logica.Metodo_pago"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% ResultSet metodos = (ResultSet)session.getAttribute("metodos"); %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agenda web</title>
        <script src='validar.jsp' language='JavaScript1.2'></script>
        <link href='diseno.css' rel='stylesheet' type='text/css'>
    </head>
    <body>
        <h1>Contactos</h1>
        <table>
            <tr>
                <th>codigo</th><th>nombre</th><th>apellido</th><th>correo</th><th>Celular</th><th>acciones</th>
            </tr>
                <% while (metodos.next()){ %>
                    <tr>
                        <td><%= metodos.getString("id_metodo_pago") %></td>
                        <td><%= metodos.getString("tipo") %></td>
                    </tr>
                <% }%>
                <tr>
            </tr>
        </table>
    </body>
</html>