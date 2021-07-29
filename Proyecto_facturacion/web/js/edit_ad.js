var arrayid = [];
var arrayOpc = [];
var arrayArticulos = [];
var ws;
var url;
var v_selected;
var band = true;
var nombre;
var o_selected;
var iterator = 0;
var id_selected = "0";
// Obtener la referencia del elemento body
var body;
// Crea un elemento <table> y un elemento <tbody>
var tabla;
var tblBody;
//funcion para confirmar conexion con ws
function onOpen() {
    console.log('Conectado..');
}
//funcion para confirmar cierrre de sesion con ws
function onClose() {
    console.log('Desonectado..');
}
//funcion que controla visivilidad de los divs
select = function () {
    var input;
    input = document.getElementById("Select");
    input.addEventListener('input', function () {
        let seleccionado = this.value;
        asignar_divs(seleccionado);
    });
    input = document.getElementById('opcion');
    input.addEventListener('input', function () {
        v_selected = this.value;
        start_ws(v_selected);
    });
};
//metodo para ajustar segun la seleccion, los divs y el procedimiento
function asignar_divs (evt){
    if (evt == "Editar") {
            o_selected = "1";
            edit_divs();
            document.getElementById("div_1").style.display = "inline";
            document.getElementById("div_2").style.display = "inline";
            document.getElementById("div_10").style.display = "inline";
            document.getElementById("boton_2").innerHTML = "Editar";
        } else if (evt == "Eliminar") {
            o_selected = "3";
            edit_divs();
            document.getElementById("div_1").style.display = "inline";
            document.getElementById("div_2").style.display = "inline";
            document.getElementById("div_10").style.display = "inline";
            document.getElementById("boton_2").innerHTML = "Eliminar";
        } else if (evt == "Crear") {
            o_selected = "2";
            edit_divs();
            document.getElementById("div_1").style.display = "none";
            document.getElementById("div_2").style.display = "none";
            document.getElementById("div_10").style.display = "inline";
            document.getElementById("boton_2").innerHTML = "Crear";
        } else {
            document.getElementById("div_1").style.display = "none";
            document.getElementById("div_2").style.display = "none";
            document.getElementById("div_10").style.display = "none";
        }
}
//metodo que inicia WS segun requerimiento ademas de variables para correcto funcionamiento
function start_ws(evt){
    if (evt == "") {
            console.log("no hace conexion");
        } else {
            if (!band) {
                ws.close();
            } else {
                band = false;
            }
            if (evt == "Factura") {
                url = 'ws://localhost:8080/Proyecto_facturacion/factura';
            }
            ws = null;
            ws = new WebSocket('ws://localhost:8080/Proyecto_facturacion/articulo');
            ws.onopen = onOpen;
            ws.onclose = onClose;
            setTimeout('get_articulos()', 1500);
            //se selecciona url a conectar
            selecionar_url();
            edit_divs();
            //se enlaza el websocket a la url elegida para posteriormente emplearlo
            procedimiento();
        }
}
//metodo que elige el tipo de url para el websocket (url solo valida para realizar consultas)
selecionar_url = function () {
    if (v_selected == "Articulo") {
        url = 'ws://localhost:8080/Proyecto_facturacion/articulo';
    } else if (v_selected == "Categoria") {
        url = 'ws://localhost:8080/Proyecto_facturacion/categoria';
    } else if (v_selected == "Cliente") {
        url = 'ws://localhost:8080/Proyecto_facturacion/cliente';
    } else if (v_selected == "Factura") {
        url = 'ws://localhost:8080/Proyecto_facturacion/factura';
    } else if (v_selected == "Metodo_pago") {
        url = 'ws://localhost:8080/Proyecto_facturacion/metodo_pago';
    } else if (v_selected == "Ciudad") {
        url = 'ws://localhost:8080/Proyecto_facturacion/ciudad';
    }
};
//metodo que elige el tipo de url para el websocket (url solo valida para editar)
selecionar_url_modify = function () {
    if (v_selected == "Articulo") {
        url = 'ws://localhost:8080/Proyecto_facturacion/modifyArticulo';
    } else if (v_selected == "Categoria") {
        url = 'ws://localhost:8080/Proyecto_facturacion/modifyCategoria';
    } else if (v_selected == "Cliente") {
        url = 'ws://localhost:8080/Proyecto_facturacion/modifyCliente';
    } else if (v_selected == "Factura") {
        url = 'ws://localhost:8080/Proyecto_facturacion/modifyFactura';
    } else if (v_selected == "Metodo_pago") {
        url = 'ws://localhost:8080/Proyecto_facturacion/modifyMP';
    } else if (v_selected == "Ciudad") {
        url = 'ws://localhost:8080/Proyecto_facturacion/modifyCiudad';
    }
};
//funcion para manejar resultados obtenidos del websocket
async function procedimiento() {
    await delay(3);
    if (document.getElementById("input_8").length <= 1) {
        set_articulo_div();
    }
    ws = null;
    ws = new WebSocket(url);
    ws.onopen = onOpen;
    ws.onclose = onClose;
    ws.onmessage = onMessage;
    nombre = document.getElementById('Nombre');
    var info = document.getElementById('informacion');
    //se verifica si elemento esta vacio de lo contrario se procede segun el tipo de onjeto
    function onMessage(evt) {
        var obj = JSON.parse(evt.data);
        if (v_selected == "Articulo") {
            if (obj.nombre === 'NE') {
                info.innerHTML = 'No existe elemento';
            } else {
                id_selected = obj.id_articulo + "";
                document.getElementById("input_1").value = obj.nombre;
                document.getElementById("input_2").value = obj.cant_stock;
                document.getElementById("input_3").value = obj.descripcion;
                document.getElementById("input_4").value = obj.id_categoria;
            }
        } else if (v_selected == "Categoria") {
            if (obj.nombre === 'NE') {
                info.innerHTML = 'No existe elemento';
            } else {
                id_selected = obj.id_categoria + "";
                document.getElementById("input_1").value = obj.nombre;
                document.getElementById("input_2").value = obj.descripcion;
            }
            console.log("Se recivio una categoria");
        } else if (v_selected == "Cliente") {
            if (obj.nombre === 'NE') {
                info.innerHTML = 'No existe elemento';
            } else {
                id_selected = obj.id_cliente + "";
                document.getElementById("input_1").value = obj.nombre;
                document.getElementById("input_2").value = obj.apellido;
                document.getElementById("input_3").value = obj.correo;
                document.getElementById("input_4").value = obj.direccion;
                document.getElementById("input_5").value = obj.celular;
                document.getElementById("input_6").value = obj.cedula;
                document.getElementById("input_7").value = obj.id_ciudad;
            }
            console.log("Se recivio un cliente");
        } else if (v_selected === "Factura") {
            if (obj.fecha_fac === "NE") {
                info.innerHTML = "No existe elemento";
            } else {
                id_selected = obj.id_factura + "";
                document.getElementById("input_1").value = obj.fecha_fac;
                document.getElementById("input_2").value = obj.val_iva;
                document.getElementById("input_3").value = obj.val_sub_total;
                document.getElementById("input_4").value = obj.total;
                document.getElementById("input_5").value = obj.id_cliente;
                document.getElementById("input_6").value = obj.id_metodo_pago;
                ws.close();
                ws = null;
                ws = new WebSocket('ws://localhost:8080/Proyecto_facturacion/detalle_fac');
                ws.onopen = onOpen;
                ws.onclose = onClose;
                setTimeout('create_tabla()', 3000);
            }
            console.log("Se recivio una factura");
        } else if (v_selected == "Metodo_pago") {
            if (obj.tipo === 'NE') {
                info.innerHTML = 'No existe elemento';
            } else {
                id_selected = obj.id_cliente + "";
                document.getElementById("input_1").value = obj.tipo;
            }
            console.log("Se recivio un metodo de pago");
        } else if (v_selected == "Ciudad") {
            if (obj.nombre === 'NE') {
                info.innerHTML = 'No existe elemento';
            } else {
                id_selected = obj.id_ciudad + "";
                document.getElementById("input_1").value = obj.nombre;
            }
        }
    }
}
;
//funcion para poner los articulos a escoger
function set_articulo_div() {
    selected = document.getElementById("input_8");
    for (var i = 0; i < arrayArticulos.length; i++) {
        var option = document.createElement("option");
        option.value = arrayArticulos[i].nombre;
        option.text = arrayArticulos[i].nombre;
        selected.appendChild(option);
    }
}
//fucion para enviar consulta a servidor
enviar = function () {
    if (nombre.value == "") {
        alert("No se ingreso nombre");
    } else {
        ws.send(nombre.value);
    }
};
//funcion para cerrar de manera segura la conexion
close_conexion = function () {
    ws.close();
    selecionar_url_modify();
    ws = null;
    ws = new WebSocket(url);
    ws.onopen = onOpen;
    ws.onclose = onClose;
    //espera 3 segundos para permitir el correcto cierre y redirige
    setTimeout('enviar_edit()', 3000);
};
//funcion para empaquetar, parsear a JSON y enviar a el servidor el objeto
async function enviar_edit() {
    ws.onmessage = get_id;
    if (v_selected == "Articulo") {
        var msg = {
            id_articulo: id_selected,
            nombre: document.getElementById("input_1").value,
            cant_stock: document.getElementById("input_2").value,
            descripcion: document.getElementById("input_3").value,
            id_categoria: document.getElementById("input_4").value,
            mensaje: o_selected
        };
        ws.send(JSON.stringify(msg));
    } else if (v_selected == "Categoria") {
        var msg = {
            id_categoria: id_selected,
            nombre: document.getElementById("input_1").value,
            descripcion: document.getElementById("input_2").value,
            mensaje: o_selected
        };
        ws.send(JSON.stringify(msg));
    } else if (v_selected == "Cliente") {
        var msg = {
            id_cliente: id_selected,
            nombre: document.getElementById("input_1").value,
            apellido: document.getElementById("input_2").value,
            correo: document.getElementById("input_3").value,
            direccion: document.getElementById("input_4").value,
            celular: document.getElementById("input_5").value,
            cedula: document.getElementById("input_6").value,
            id_ciudad: document.getElementById("input_7").value,
            password: "1234",
            mensaje: o_selected
        };
        ws.send(JSON.stringify(msg));
    } else if (v_selected == "Factura") {
        if(o_selected != "3"){
            //se cierra antigua conexion
            ws.close();
            //se crea nueva conexion para añadir factura
            ws = null;
            ws = new WebSocket('ws://localhost:8080/Proyecto_facturacion/modifyFactura');
            ws.onopen = onOpen;
            ws.onclose = onClose;
            ws.onmessage = get_id;
            //se espera 2 segundos para permitir correcta conexion con WebSocket
            await delay(2);
            //se crea el objeto a enviar
            var msg = {
                id_factura: id_selected,
                fecha_fac: document.getElementById("input_1").value,
                val_iva: document.getElementById("input_2").value,
                val_sub_total: document.getElementById("input_3").value,
                total: document.getElementById("input_4").value,
                id_cliente: document.getElementById("input_5").value,
                id_metodo_pago: document.getElementById("input_6").value,
                mensaje: o_selected
            };
            ws.send(JSON.stringify(msg));
            //se espera a recivir el id de la factura recien registrada en DB
            //se procede a guardar registros de la factura
            setTimeout('window.cerrar_ws()', 3000);
        }else{
            //se cierra antigua conexion
            ws.close();
            ws_table = new WebSocket('ws://localhost:8080/Proyecto_facturacion/modifyDetalle_fac');
            ws_table.onclose = onClose();
            ws_table.onopen = onOpen();
            //se espera correcta conexion con WS para enviar los registros
            setTimeout('delete_factura()', 3000);
        }
    } else if (v_selected == "Metodo_pago") {
        var msg = {
            id_metodo_pago: id_selected,
            tipo: document.getElementById("input_1").value,
            mensaje: o_selected
        };
        ws.send(JSON.stringify(msg));
    } else if (v_selected == "Ciudad") {
        var msg = {
            id_ciudad: id_selected,
            nombre: document.getElementById("input_1").value,
            mensaje: o_selected
        };
        ws.send(JSON.stringify(msg));
    }
    function get_id(evt) {
        //se recive el id del ultimo registro enviado
        id_selected = evt.data;
    }
    await delay(2);
    if(v_selected != "Factura"){
        asignar_divs(document.getElementById("Select").value);
        start_ws(v_selected);   
    }
}
function delete_factura(){
    //se crea el objeto para parsearlo y enviarlo a WS
    var msg = {
        //como se van a eliminar todos los registros de una factura solo importa enviar los registros de esa factura
        id_detalle_fac: 0 + "",
        cantidad: 0 + "",
        total: 0 + "",
        descuento: 0 + "",
        val_descuento: 0 + "",
        id_factura: id_selected + "",
        id_articulo: 0 + "",
        mensaje: 4+ ""
    };
    //se envia objeto
    ws_table.send(JSON.stringify(msg));
}
//funcion que acorde a la opcion elegida muestra u oculta los divs
edit_divs = function () {
    if (!!document.getElementById("table_d_f")) {
        document.getElementsByTagName("body")[0].removeChild(document.getElementById("table_d_f"));
    }
    for (var i = 1; i <= 7; i++) {
        document.getElementById("input_" + i).value = "";
    }
    //se limpia campo de pregunta
    document.getElementById("Nombre").value = "";
    //se esconden primero todos los divs
    hide_divs();
    if (v_selected == "Articulo") {
        document.getElementById("label_1").innerHTML = "Nombre:";
        document.getElementById("label_2").innerHTML = "Cantidad en stock:";
        document.getElementById("label_3").innerHTML = "Descripcion:";
        document.getElementById("label_4").innerHTML = "ID de la categoria:";
        document.getElementById("div_3").style.display = "inline";
        document.getElementById("div_4").style.display = "inline";
        document.getElementById("div_5").style.display = "inline";
        document.getElementById("div_6").style.display = "inline";

    } else if (v_selected == "Categoria") {
        document.getElementById("label_1").innerHTML = "Nombre:";
        document.getElementById("label_2").innerHTML = "Descripcion:";
        document.getElementById("div_3").style.display = "inline";
        document.getElementById("div_4").style.display = "inline";
    } else if (v_selected == "Cliente") {
        document.getElementById("label_1").innerHTML = "Nombre:";
        document.getElementById("label_2").innerHTML = "Apellido:";
        document.getElementById("label_3").innerHTML = "Correo:";
        document.getElementById("label_4").innerHTML = "Direccion:";
        document.getElementById("label_5").innerHTML = "Celular:";
        document.getElementById("label_6").innerHTML = "Cedula:";
        document.getElementById("label_7").innerHTML = "ID ciudad:";
        document.getElementById("div_3").style.display = "inline";
        document.getElementById("div_4").style.display = "inline";
        document.getElementById("div_5").style.display = "inline";
        document.getElementById("div_6").style.display = "inline";
        document.getElementById("div_7").style.display = "inline";
        document.getElementById("div_8").style.display = "inline";
        document.getElementById("div_9").style.display = "inline";
    } else if (v_selected == "Factura") {
        document.getElementById("label_1").innerHTML = "Fecha de la factura:";
        document.getElementById("label_2").innerHTML = "Valor de IVA:";
        document.getElementById("label_3").innerHTML = "Valor subTotal:";
        document.getElementById("label_4").innerHTML = "Total:";
        document.getElementById("label_5").innerHTML = "ID del cliente:";
        document.getElementById("label_6").innerHTML = "ID del metodo de pago:";
        document.getElementById("div_3").style.display = "inline";
        document.getElementById("div_4").style.display = "inline";
        document.getElementById("div_5").style.display = "inline";
        document.getElementById("div_6").style.display = "inline";
        document.getElementById("div_7").style.display = "inline";
        document.getElementById("div_8").style.display = "inline";
        document.getElementById("div_11").style.display = "inline";
        document.getElementById("div_12").style.display = "inline";
        document.getElementById("div_13").style.display = "inline";
    } else if (v_selected == "Metodo_pago") {
        document.getElementById("label_1").innerHTML = "Tipo:";
        document.getElementById("div_3").style.display = "inline";
    } else if (v_selected == "Ciudad") {
        document.getElementById("label_1").innerHTML = "Nombre:";
        document.getElementById("div_3").style.display = "inline";
    }
};
//funcion para esconder divs de edicion
hide_divs = function () {
    document.getElementById("div_3").style.display = "none";
    document.getElementById("div_4").style.display = "none";
    document.getElementById("div_5").style.display = "none";
    document.getElementById("div_6").style.display = "none";
    document.getElementById("div_7").style.display = "none";
    document.getElementById("div_8").style.display = "none";
    document.getElementById("div_9").style.display = "none";
    document.getElementById("div_13").style.display = "none";
    document.getElementById("div_12").style.display = "none";
    document.getElementById("div_11").style.display = "none";

};
//funcion para crear la tabla de productos de una factura
create_tabla = function () {
    ws.onmessage = seetear;
    // Obtener la referencia del elemento body
    var body = document.getElementsByTagName("body")[0];
    // Crea un elemento <table> y un elemento <tbody>
    var tabla = document.createElement("table");
    var tblBody = document.createElement("tbody");
    //se envia id para obtener los articulos relacionados
    ws.send(id_selected + "");
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

    function seetear(evt) {
        var obj = JSON.parse(evt.data);
        //en caso de que no exista el registro
        if (obj.total == "0") {
            console.log("No existe ningun registro");
        } else {
            //se agrega el id del objeto a arraylist
            arrayid.push(obj.id_detalle_fac);
            //se agrega opcion de modificar
            arrayOpc.push("1");
            var hilera = document.createElement("tr");
            //se agrega id del articulo
            var celda = document.createElement("td");
            var textoCelda_5 = document.createElement("label");
            textoCelda_5.setAttribute('type', 'text');
            textoCelda_5.setAttribute('id', 'i_a' + iterator);
            textoCelda_5.setAttribute('value', obj.id_articulo);
            textoCelda_5.innerHTML = set_articulo(obj.id_articulo);
            celda.appendChild(textoCelda_5);
            hilera.appendChild(celda);
            //se agrega cantidad a tabla
            var celda = document.createElement("td");
            var textoCelda = document.createElement("input");
            textoCelda.setAttribute('type', 'text');
            textoCelda.setAttribute('id', 'c' + iterator);
            textoCelda.setAttribute('value', obj.cantidad);
            celda.appendChild(textoCelda);
            hilera.appendChild(celda);
            //se agrega descuento en porcentaje a la tabla
            var celda = document.createElement("td");
            var textoCelda_3 = document.createElement("input");
            textoCelda_3.setAttribute('type', 'text');
            textoCelda_3.setAttribute('id', 'd' + iterator);
            textoCelda_3.setAttribute('value', Math.round(obj.descuento));
            celda.appendChild(textoCelda_3);
            hilera.appendChild(celda);
            //se agrega valor del descuento
            var celda = document.createElement("td");
            var textoCelda_4 = document.createElement("label");
            textoCelda_4.setAttribute('id', 'v_d' + iterator);
            textoCelda_4.innerHTML = obj.val_descuento;
            celda.appendChild(textoCelda_4);
            hilera.appendChild(celda);
            //se agrega valor unitario
            var celda = document.createElement("td");
            var textoCelda_5 = document.createElement("label");
            textoCelda_5.setAttribute('id', 'v_u' + iterator);
            textoCelda_5.innerHTML = set_val_uni_articulo(obj.id_articulo);
            celda.appendChild(textoCelda_5);
            hilera.appendChild(celda);
            //se agrega total a tabla
            var celda = document.createElement("td");
            var textoCelda_2 = document.createElement("label");
            textoCelda_2.setAttribute('id', 't' + iterator);
            textoCelda_2.innerHTML = obj.total;
            celda.appendChild(textoCelda_2);
            hilera.appendChild(celda);
            //select de eliminar
            var celda = document.createElement("td");
            var textoCelda_5 = document.createElement("input");
            textoCelda_5.setAttribute('type', 'checkbox');
            textoCelda_5.setAttribute('id', 'e' + iterator);
            textoCelda_5.innerHTML = "Eliminar";
            celda.appendChild(textoCelda_5);
            celda.appendChild(document.createTextNode("Eliminar"));
            hilera.appendChild(celda);

            iterator++;
            tblBody.appendChild(hilera);
        }
        // posiciona el <tbody> debajo del elemento <table>
        tabla.appendChild(tblBody);
        // appends <table> into <body>
        body.appendChild(tabla);
        // modifica el atributo "border" de la tabla y lo fija a "2";
        tabla.setAttribute("border", "2");
        tabla.setAttribute("id", "table_d_f");
    }
};
//funcion para traer todos los articulos
async function get_articulos() {
    arrayArticulos.pop();
    ws.onmessage = recivir_art;

    ws.send("Todo");
    function recivir_art(evt) {
        var obj = JSON.parse(evt.data);
        //en caso de que no exista el registro
        if (obj.nombre === 'NE') {
            console.log("no existen elementos");
        } else {
            arrayArticulos.push(obj);
        }
    }
    ;
    await delay(1);
    ws.close();
}
function delay(n) {
    return new Promise(function (resolve) {
        setTimeout(resolve, n * 1000);
    });
}
function set_articulo(art) {
    for (var i = 0; i < arrayArticulos.length; i++) {
        if (arrayArticulos[i].id_articulo == art) {
            return arrayArticulos[i].nombre;
            break;
        }
    }
}
function set_val_uni_articulo(art) {
    for (var i = 0; i < arrayArticulos.length; i++) {
        if (arrayArticulos[i].id_articulo == art) {
            return arrayArticulos[i].descripcion;
            break;
        }
    }
}
function sumar_total(add) {
    //se traen los labels donde estan los valores
    //en caso de que se cree una nueva factura se settean valores a cero
    if (document.getElementById("input_3").value == "") {
        sub_total = 0;
    } else {
        sub_total = parseFloat(document.getElementById("input_3").value);
    }
    if (document.getElementById("input_2").value == "") {
        val_iva = 0;
    } else {
        val_iva = parseFloat(document.getElementById("input_2").value);
    }
    //se agrega cantidad a sub_total
    sub_total += add;
    document.getElementById("input_3").value = sub_total;
    //se agrega cantidad de IVA
    val_iva = sub_total * 0.19;
    document.getElementById("input_2").value = val_iva;
    //se agrega cantidad total de la factura;
    document.getElementById("input_4").value = sub_total + val_iva;
}