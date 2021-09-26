<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%ResultSet res = (ResultSet) session.getAttribute("ciudades");%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="CSS/NewUserStyle.css">
  <link rel="shortcut icon" href="Recursos/favicon.ico" mce_href="Recursos/favicon.ico" type="image/x-icon" />
  <title>Nuevo usuario</title>
</head>
<body>
  <section class="form-register">
    <h4>Formulario Registro</h4>
    <input class="controls" type="text" name="nombres" id="nombres" placeholder="Ingrese su Nombre">
    <input class="controls" type="text" name="apellidos" id="apellidos" placeholder="Ingrese su Apellido">
    <input class="controls" type="email" name="correo" id="correo" placeholder="Ingrese su Correo">
    <input class="controls" type="password" name="correo" id="correo" placeholder="Ingrese su Contraseña">
    <select name="citySelect" class="controls">
        <%while (res.next()){%>
            <option value="id_ciudad"><%= res.getString("nombre") %></option>
        <%}%>
    </select>
    <p>Estoy de acuerdo con <a href="#">Terminos y Condiciones</a></p>
    <input class="botons" type="submit" value="Registrar">
    <p><a href="Cliente.jsp">¿Ya tengo Cuenta?</a></p>
  </section>

</body>
</html>