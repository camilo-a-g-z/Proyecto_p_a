var ws_table;
function cerrar_ws() {
    //se cierra antigua conexion
    window.ws.close();
    ws_table = new WebSocket('ws://localhost:8080/Proyecto_facturacion/modifyDetalle_fac');
    ws_table.onclose = window.onClose();
    ws_table.onopen = window.onOpen();
    //se espera correcta conexion con WS para enviar los registros
    setTimeout('edit_table()', 3000);
}
//funcion para crear/modificar/eliminar registros de detalle_fac
function edit_table() {
    for (var i = 0; i < window.iterator; i++) {
        //se pregunta si el usuario desea elimiar el objeto seleccionado
        if (document.getElementById("e" + i).checked) {
            //se cambia opcion a eliminar
            window.arrayOpc[i] = "3";
        }
        var msg = {
            //se crea el objeto para parsearlo y enviarlo a WS
            id_detalle_fac: window.arrayid[i] + "",
            cantidad: document.getElementById("c" + i).value,
            total: document.getElementById("t" + i).innerHTML,
            descuento: document.getElementById("d" + i).value,
            val_descuento: document.getElementById("v_d" + i).innerHTML,
            id_factura: window.id_selected + "",
            id_articulo: ver_id_a(document.getElementById("i_a" + i).innerHTML) + "",
            mensaje: window.arrayOpc[i] + ""
        };
        //se envia objeto
        ws_table.send(JSON.stringify(msg));
    }
    alert("Factura enviada correctamente");
    asignar_divs(document.getElementById("Select").value);
    start_ws(window.v_selected);
}
//funcion para añadir una fila
async function add_fila() {
    //en caso de que no exista la tabla
    if (!document.getElementsByTagName("tbody")[0]) {
        iniciar_tabla();
    }
    if (document.getElementById("input_8").value !== "") {
        //se le da la opcion de crear elemento
        window.arrayOpc.push("2");
        //para evitar problema del lado del servidor en el Decoder se settea un id aelatorio
        window.arrayid.push("1");
        var hilera = document.createElement("tr");
        //se agrega id del articulo
        var celda = document.createElement("td");
        var textoCelda_5 = document.createElement("label");
        textoCelda_5.setAttribute('type', 'text');
        textoCelda_5.setAttribute('id', 'i_a' + window.iterator);
        textoCelda_5.setAttribute('value', "obj.id_articulo");
        textoCelda_5.innerHTML = document.getElementById("input_8").value;
        celda.appendChild(textoCelda_5);
        hilera.appendChild(celda);
        //se agrega cantidad a tabla
        var celda = document.createElement("td");
        var textoCelda = document.createElement("input");
        textoCelda.setAttribute('type', 'text');
        textoCelda.setAttribute('id', 'c' + window.iterator);
        textoCelda.setAttribute('value', document.getElementById("input_9").value);
        celda.appendChild(textoCelda);
        hilera.appendChild(celda);
        //se agrega descuento en porcentaje a la tabla
        var celda = document.createElement("td");
        var textoCelda_3 = document.createElement("input");
        textoCelda_3.setAttribute('type', 'text');
        textoCelda_3.setAttribute('id', 'd' + window.iterator);
        textoCelda_3.setAttribute('value', document.getElementById("input_10").value);
        celda.appendChild(textoCelda_3);
        hilera.appendChild(celda);
        //se agrega valor del descuento
        var celda = document.createElement("td");
        var textoCelda_4 = document.createElement("label");
        textoCelda_4.setAttribute('id', 'v_d' + window.iterator);
        textoCelda_4.innerHTML = Math.round(get_val_descuento());
        celda.appendChild(textoCelda_4);
        hilera.appendChild(celda);
        //se agrega valor unitario
        var celda = document.createElement("td");
        var textoCelda_5 = document.createElement("label");
        textoCelda_5.setAttribute('id', 'v_u' + window.iterator);
        textoCelda_5.innerHTML = get_precio_id_a(document.getElementById("input_8").value);
        celda.appendChild(textoCelda_5);
        hilera.appendChild(celda);
        //se agrega total a tabla
        var celda = document.createElement("td");
        var textoCelda_2 = document.createElement("label");
        textoCelda_2.setAttribute('id', 't' + window.iterator);
        var res = get_val_total();
        textoCelda_2.innerHTML = Math.round(res);
        window.sumar_total(res);
        celda.appendChild(textoCelda_2);
        hilera.appendChild(celda);
        //select de eliminar
        var celda = document.createElement("td");
        var textoCelda_5 = document.createElement("input");
        textoCelda_5.setAttribute('type', 'checkbox');
        textoCelda_5.setAttribute('id', 'e' + window.iterator);
        textoCelda_5.innerHTML = "Eliminar";
        celda.appendChild(textoCelda_5);
        celda.appendChild(document.createTextNode("Eliminar"));
        hilera.appendChild(celda);

        window.iterator++;
        tblBody = document.getElementsByTagName("tbody")[0];
        tabla = document.getElementById("table_d_f");

        body = document.getElementsByTagName("body")[0];
        tblBody.appendChild(hilera);
        // posiciona el <tbody> debajo del elemento <table>
        tabla.appendChild(tblBody);
        // appends <table> into <body>
        body.appendChild(tabla);
    }
}
//funcion para crear encabezado de tabla en caso de que no exista
function iniciar_tabla() {
    //se reinicia el iterador
    window.iterator = 0;
    //Se agrega fecha actual
    const hoy = new Date(Date.now());
    document.getElementById("input_1").value = hoy.toLocaleDateString();
    // Obtener la referencia del elemento body
    var body = document.getElementsByTagName("body")[0];
    // Crea un elemento <table> y un elemento <tbody>
    var tabla = document.createElement("table");
    var tblBody = document.createElement("tbody");
    //se envia id para obtener los articulos relacionados
    //se ponenen titulos a tabla
    var hilera = document.createElement("tr");
    var celda = document.createElement("td");
    var text = document.createElement("label");
    text.innerHTML = "Nombre del articulo";
    celda.appendChild(text);
    hilera.appendChild(celda);
    var celda = document.createElement("td");
    var text = document.createElement("label");
    text.innerHTML = "Cantidad";
    celda.appendChild(text);
    hilera.appendChild(celda);
    var celda = document.createElement("td");
    var text = document.createElement("label");
    text.innerHTML = "Descuento";
    celda.appendChild(text);
    hilera.appendChild(celda);
    var celda = document.createElement("td");
    var text = document.createElement("label");
    text.innerHTML = "Valor del descuento";
    celda.appendChild(text);
    hilera.appendChild(celda);
    var celda = document.createElement("td");
    var text = document.createElement("label");
    text.innerHTML = "Valor unitario";
    celda.appendChild(text);
    hilera.appendChild(celda);
    var celda = document.createElement("td");
    var text = document.createElement("label");
    text.innerHTML = "Total";
    celda.appendChild(text);
    hilera.appendChild(celda);

    tblBody.appendChild(hilera);

    // posiciona el <tbody> debajo del elemento <table>
    tabla.appendChild(tblBody);
    // appends <table> into <body>
    body.appendChild(tabla);
    // modifica el atributo "border" de la tabla y lo fija a "2";
    tabla.setAttribute("border", "2");
    tabla.setAttribute("id", "table_d_f");
}
//segun el nombre se retorna el id del articulo
function ver_id_a(art) {
    for (var i = 0; i < window.arrayArticulos.length; i++) {
        if (window.arrayArticulos[i].nombre == art) {
            return window.arrayArticulos[i].id_articulo;
            break;
        }
    }
}
//segun el id del articulo se retorna el precio
function get_precio_id_a(art) {
    for (var i = 0; i < arrayArticulos.length; i++) {
        if (arrayArticulos[i].nombre == art) {
            return arrayArticulos[i].descripcion;
            break;
        }
    }
}
//segun el id del articulo se retorna el valor del descuento
function get_val_descuento() {
    return val_descuento = (document.getElementById("input_10").value / 100) * (document.getElementById("input_9").value * get_precio_id_a(document.getElementById("input_8").value));
}
//Se calcula el total en base a el articlo, cantidad y descuento
function get_val_total() {
    return val_total = (
            document.getElementById("input_9").value * get_precio_id_a(document.getElementById("input_8").value))
            - get_val_descuento();
}