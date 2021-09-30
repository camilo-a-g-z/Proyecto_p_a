<%@page import="logica.Cliente"%>
<%String user = (String) session.getAttribute("id_user"); 
  Cliente cli = (Cliente) session.getAttribute("user");%>
<html>
    <head>
        <title>Facturacion electronica</title>
        <link rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico" type="image/x-icon" />
        <link href="CSS/estilos4.css" rel="stylesheet"/>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="js/LoadClientDates.js"></script>
        <script type="text/javascript" src="js/LogicaED.js"></script>
        <script type="text/javascript" src="js/check_table.js"></script>
        <script type="text/javascript" src="js/VerifyClientInputs.js"></script>
        <script src="js/html2pdf.bundle.min.js"></script>
    </head>
    <body onload="select()">
        <div id="info" style="display:none">
            <label id="id_user"><%out.println(user);%></label>
            <input id="Select">
            <form id="formBack" action="RedirectUser" method="post">
                <input id="id_cliente" name="id_cliente" type="text" value="<%out.println(user);%>">
                <input id="btm_submit" type="submit">
            </form>
        </div>
        <!--Se carga primero todos los datos antes de empezar la pagina-->
        <style>
            .loading {
                position: fixed;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background-color: white;
                display: flex;
                justify-content: center;
                align-items: center;
                z-index: 9999;
                transition: 1s all;
                opacity: 0;
            }
            .loading.show {
                opacity: 1;
            }
            .loading .spin {
                border: 3px solid hsla(185, 100%, 62%, 0.2);
                border-top-color: #3cefff;
                border-radius: 50%;
                width: 3em;
                height: 3em;
                animation: spin 1s linear infinite;
            }
            @keyframes spin {
                to {
                    transform: rotate(360deg);
                }
            }            
        </style>
        <div id="user_info">
            <label class="principal">Nombre: <%out.println(cli.getNombre());%></label>
            <br>
            <label class="principal">Apellido: <%out.println(cli.getApellido());%></label>
            <br>
            <label class="principal">Cedula: <%out.println(cli.getCedula());%></label>
            <br>
            <label class="principal">Celular: <%out.println(cli.getCelular());%></label>
            <br>
            <label class="principal">Dirección: <%out.println(cli.getDireccion());%></label>
            <br>
            <label class="principal">Correo: <%out.println(cli.getCorreo());%></label>
            <br>
        </div>
        <div class="loading show" id="load">
            <div class="spin"></div>
        </div>
        <script>
            window.load();
        </script>
        
        <div  id="div_3">
            <label class="principal" id="label_1">Fecha de la factura:</label>
            <input type="text" id="input_1" name="input_1" placeholder="DD/MM/AA" disabled="">
        </div>
        <br>
        <div  id="div_4">
            <label class="principal" id="label_2">Valor del iva:</label>
            <input type="text" id="input_2" name="input_2" disabled="">
        </div>
        <br>
        <div  id="div_5">
            <label class="principal" id="label_3">Valor subTotal:</label>
            <input type="text" id="input_3" name="input_3" disabled="">
        </div>
        <br>
        <div  id="div_6">
            <label class="principal" id="label_4">Valor Total:</label>
            <input type="text" id="input_4" name="input_4" disabled="">
        </div>     
        <br>
        <div  id="div_MO">
            <label class="principal" id="label_MO">Metodo de pago:</label>
            <select name="input_MO" id="input_MO">
                <option value="" selected=""></option>
            </select>
        </div>
        <div  id="div_11">
            Ingresar un nuevo articulo a factura:<br> </div>
            <label class="principal" id="label_8">Articulo:</label>
            <select name="input_8" id="input_8">
                <option value="" selected=""></option>
            </select>
        <div  id="div_11" id="info_div">
            Informacion del producto: 
            <label class="principal" id="info_producto"></label>
        </div>
        <br>
        <div  id="div_12">
            <label class="principal" id="label_9">Cantidad:</label>
            <input type="text" id="input_9" name="input_9" placeholder="Escriba cantidad" onkeypress="return isNumberKeyDecimal(event)"> 
        </div>
        <br>
        <div  id="div_CT">
            <label class="principal" id="label_CT">Precio:</label>
            <label class="principal" id="input_CT"></label>
        </div>
        <br>
        <div id="div_13">
            <label class="principal" id="label_10">Descuento:</label>
            <input type="text" id="input_10" name="input_10" disabled="" value="0">
            <br>
            <button id="boton_3" name="boton_3" onclick="cantVerify()">Agregar</button>
            <button id="boton_4" name="boton_3" onclick="verifyEnviar()">Enviar factura</button>
        </div>
    </body>
</html>
