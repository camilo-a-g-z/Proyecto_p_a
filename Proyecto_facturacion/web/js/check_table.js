var ws_table;
function cerrar_ws(){
    window.ws.close();
    ws_table = new WebSocket('ws://localhost:8080/Proyecto_facturacion/modifyDetalle_fac');
    ws_table.onclose = window.onClose();
    ws_table.onopen = window.onOpen();
    setTimeout('edit_table()', 3000);
}
function edit_table(){
    for (var i=0;i<1;i++){
        console.log(window.arrayid[i]);
        var msg = {
            id_detalle_fac:window.arrayid[i]+"",
            cantidad:document.getElementById("c"+i).value,
            total:document.getElementById("t"+i).value,
            descuento:document.getElementById("d"+i).value,
            val_descuento:document.getElementById("v_d"+i).value,
            id_factura:window.id_selected+"",
            id_articulo:document.getElementById("i_a"+i).value,
            mensaje:"1"
        };
        ws_table.send(JSON.stringify(msg));
    }
    var input;
}

function get_var(){
    console.log(window.iterator);
}