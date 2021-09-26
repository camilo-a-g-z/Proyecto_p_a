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
  <script type="text/javascript" src="js/NewUserDates.js"></script>
  <link rel="shortcut icon" href="Recursos/favicon.ico" mce_href="Recursos/favicon.ico" type="image/x-icon" />
  <title>Nuevo usuario</title>
</head>
<body>
    <form id="FormNU" method="post"  action="CreateNU">
        <section class="form-register">
        <h4>Formulario Registro</h4>
        <input class="controls" type="text" name="nombres" id="nombres" placeholder="Ingrese su Nombre" required>
        <input class="controls" type="text" name="apellidos" id="apellidos" placeholder="Ingrese su Apellido" required>
        <input class="controls" type="email" name="correo" id="correo" placeholder="Ingrese su Correo" required>
        <input class="controls" type="text" name="direccion" id="direccion" placeholder="Ingrese su dirección" onkeypress="return valideKey(event);" required>
        <input class="controls" type="text" name=celular" id="celular" placeholder="Ingrese numero de celular" onkeypress="return valideKey(event);" required>
        <input class="controls" type="text" name="documento" id="documento" placeholder="Ingrese nuemro de documento" required>
        <select name="citySelect" class="controls">
            <%while (res.next()){%>
                <option value="id_ciudad"><%= res.getString("nombre") %></option>
            <%}%>
        </select>
        <input class="controls" type="password" name="pass" id="pass" placeholder="Ingrese su Contraseña" required="">    
        <p>Estoy de acuerdo con <a href="#">Terminos y Condiciones</a></p>
        <input class="botons" type="submit" value="Registrar">
        <p><a href="Cliente.jsp">¿Ya tengo Cuenta?</a></p>
      </section>
    </form>

</body>
</html>