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
        <title>Facturacion</title>
    </head>
    <body>
        <h1>Facturacion</h1>
        <table>
            <tr>
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
        <div>
            <label>ID</label>
            <input type="text" id="id_metodo" placeholder="Ingrese id del metodo para testear"/>
        </div>
        <div>
            <button id="boton" name="boton">Enviar</button>
        </div>
        <div id="obtenido"></div>
        <script src="js/codigo.js"></script>
    </body>
</html>