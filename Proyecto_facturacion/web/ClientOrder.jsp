<%String user = (String) session.getAttribute("id_user"); %>
<html>
    <head>
        <title>Facturacion electronica</title>
        <link rel="shortcut icon" href="favicon.ico" mce_href="favicon.ico" type="image/x-icon" />
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="js/LoadClientDates.js"></script>
        <script type="text/javascript" src="js/LogicaED.js"></script>
        <script type="text/javascript" src="js/check_table.js"></script>
        <script type="text/javascript" src="js/VerifyClientInputs.js"></script>
    </head>
    <body onload="select()">
        <div id="info" style="display:none">
            <label id="id_user"><%out.println(user);%></label>
            <input id="Select"></label>
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

        <div class="loading show" id="load">
            <div class="spin"></div>
        </div>
        <script>
            window.load();
        </script>
        <div  id="div_3">
            <label id="label_1">Fecha de la factura:</label>
            <input type="text" id="input_1" name="input_1" placeholder="DD/MM/AA" disabled="">
        </div>
        <br>
        <div  id="div_4">
            <label id="label_2">Valor del iva:</label>
            <input type="text" id="input_2" name="input_2" disabled="">
        </div>
        <br>
        <div  id="div_5">
            <label id="label_3">Valor subTotal:</label>
            <input type="text" id="input_3" name="input_3" disabled="">
        </div>
        <br>
        <div  id="div_6">
            <label id="label_4">Valor Total:</label>
            <input type="text" id="input_4" name="input_4" disabled="">
        </div>     
        <br>
        <div  id="div_MO">
            <label id="label_MO">Metodo de pago:</label>
            <select name="input_MO" id="input_MO">
                <option value="" selected=""></option>
            </select>
        </div>
        <div  id="div_11">
            Ingresar un nuevo articulo a factura:<br>
            <label id="label_8">Articulo:</label>
            <select name="input_8" id="input_8">
                <option value="" selected=""></option>
            </select>
        </div>
        <br>
        <div  id="div_12">
            <label id="label_9">Cantidad:</label>
            <input type="text" id="input_9" name="input_9" placeholder="Escriba cantidad" onkeypress="return isNumberKeyDecimal(event)"> 
        </div>
        <br>
        <div  id="div_CT">
            <label id="label_CT">Precio:</label>
            <label id="input_CT"></label>
        </div>
        <br>
        <div id="div_13">
            <label id="label_10">Descuento:</label>
            <input type="text" id="input_10" name="input_10" disabled="" value="0">
            <br>
            <button id="boton_3" name="boton_3" onclick="cantVerify()">Agregar</button>
            <button id="boton_4" name="boton_3" onclick="verifyEnviar()">Enviar factura</button>
        </div>
    </body>
</html>
