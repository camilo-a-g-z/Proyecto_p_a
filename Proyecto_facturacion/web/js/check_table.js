var ws_table;
function cerrar_ws() {
    window.ws.close();
    ws_table = new WebSocket('ws://localhost:8080/Proyecto_facturacion/modifyDetalle_fac');
    ws_table.onclose = window.onClose();
    ws_table.onopen = window.onOpen();
    setTimeout('edit_table()', 3000);
}
function edit_table() {
    for (var i = 0; i < window.iterator; i++) {
        //se pregunta si el usuario desea elimiar el objeto seleccionado
        if(document.getElementById("e"+i).checked){
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
            id_articulo: ver_id_a(document.getElementById("i_a" + i).innerHTML)+"",
            mensaje: window.arrayOpc[i]+""
        };
        //se envia objeto
        ws_table.send(JSON.stringify(msg));
    }
}
function add_fila(){
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
    textoCelda_5.innerHTML = set_articulo(document.getElementById("input_8").value);
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
    textoCelda_4.innerHTML = get_val_descuento();
    celda.appendChild(textoCelda_4);
    hilera.appendChild(celda);
    //se agrega total a tabla
    var celda = document.createElement("td");
    var textoCelda_2 = document.createElement("label");
    textoCelda_2.setAttribute('id', 't' + window.iterator);
    textoCelda_2.innerHTML = get_val_total();
    celda.appendChild(textoCelda_2);
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
function ver_id_a(art){
    for(var i=0;i<window.arrayArticulos.length;i++){
        if(window.arrayArticulos[i].nombre == art){
            return window.arrayArticulos[i].id_articulo;
            break;
        }
    }
}
function get_precio_id_a(art){
    for(var i=0;i<arrayArticulos.length;i++){
        if(arrayArticulos[i].id_articulo == art){
            return arrayArticulos[i].descripcion;
            break;
        }
    }
}
function get_val_descuento(){
    return val_descuento = (document.getElementById("input_10").value/100)*(document.getElementById("input_9").value* get_precio_id_a(document.getElementById("input_8").value));
}
function get_val_total(){
    return val_total = (document.getElementById("input_9").value * get_precio_id_a(document.getElementById("input_8").value))- get_val_descuento();
}
function get_var() {
    console.log(window.iterator);
}
function set_articulo(art){
    for(var i=0;i<arrayArticulos.length;i++){
        if(arrayArticulos[i].id_articulo == art){
            return arrayArticulos[i].nombre;
            break;
        }
    }
}