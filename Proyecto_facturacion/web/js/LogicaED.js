var arrayArticulos = [];
var iterator = 0;
var arrayOpc = [];
var arrayid = [];
var ws;
var id_client;
var id_selected;
function open_factura_conexion() {
    id_client = parseInt(document.getElementById("id_user").innerHTML);
    console.log(id_client);
    if(id_client !== 'null\n'){
        //se inicia simbolo de carga
        document.getElementById("load").removeAttribute("style");
        ws = null;
        ws = new WebSocket('ws://localhost:8080/Proyecto_facturacion/modifyFactura');
        ws.onopen = onOpen;
        ws.onclose = onClose;
        //espera 3 segundos para permitir el correcto cierre y redirige
        setTimeout('sendBill()', 3000);
    }else{
        alert("Error de red intente ingresando nuevamente");
    }
};
function sendBill(){
    ws.onmessage = get_id;
    var msg = {
        id_factura: "0",
        fecha_fac: document.getElementById("input_1").value,
        val_iva: document.getElementById("input_2").value,
        val_sub_total: document.getElementById("input_3").value,
        total: document.getElementById("input_4").value,
        id_cliente: id_client+"",
        id_metodo_pago: set_MP_by_nombre(document.getElementById("input_MO").value)+"",
        mensaje: "2"
    };
    ws.send(JSON.stringify(msg));
    //se espera a recivir el id de la factura recien registrada en DB
    //se procede a guardar registros de la factura
    setTimeout('window.cerrar_ws()', 3000);
}
//funcion para almacenar el id de la factura recien creada
function get_id(evt) {
        //se recive el id del ultimo registro enviado
        id_selected = evt.data;
}
//se limpia campo para nueva factura
function asignar_divs(evt){
    document.getElementById("input_2").value = "";
    document.getElementById("input_3").value = "";
    document.getElementById("input_4").value = "";
    document.getElementById("input_9").value = "";
}
function start_ws(evt){
    
}
//funcion para buscar segun el nombre del metodo de pago el id de este
function set_MP_by_nombre(cat){
    for (var i = 0; i < window.arrayMP.length; i++) {
        if (window.arrayMP[i].tipo === cat) {
            return window.arrayMP[i].id_metodo_pago;
            break;
        }
    }
}
//funcion para agregar un retraso en un tiempo n segundos
function delay(n) {
    return new Promise(function (resolve) {
        setTimeout(resolve, n * 1000);
    });
}
//funcion para confirmar conexion con ws
function onOpen() {
    console.log('Conectado..');
}
//funcion para confirmar cierrre de sesion con ws
function onClose() {
    console.log('Desonectado..');
}
//funcion para poner los articulos a escoger
function set_articulo_div() {
    clean_input_8();
    selected = document.getElementById("input_8");
    for (var i = 0; i < arrayArticulos.length; i++) {
        var option = document.createElement("option");
        option.value = arrayArticulos[i].nombre;
        option.text = arrayArticulos[i].nombre;
        selected.appendChild(option);
    }
}
//funcion para poner los metodos de pago a escoger
function set_MP_div() {
    clean_input_MO();
    selected = document.getElementById("input_MO");
    for (var i = 0; i < window.arrayMP.length; i++) {
        var option = document.createElement("option");
        option.value = window.arrayMP[i].tipo;
        option.text = window.arrayMP[i].tipo;
        selected.appendChild(option);
    }
}
//funcion para poner las categorias a escoger
function set_categoria_div() {
    clean_input_MO();
    selected = document.getElementById("input_MO");
    for (var i = 0; i < window.arrayCategoria.length; i++) {
        var option = document.createElement("option");
        option.value = window.arrayCategoria[i].nombre;
        option.text = window.arrayCategoria[i].nombre;
        selected.appendChild(option);
    }
}
//funcion para lipiar select o input 8
function clean_input_8(){
  for(var i = document.getElementById("input_8").options.length;i>=0;i--){
    document.getElementById("input_8").remove(i);
  }
}
//funcion para lipiar select o input MO
function clean_input_MO(){
  for(var i = document.getElementById("input_MO").options.length;i>=0;i--){
    document.getElementById("input_MO").remove(i);
  }
}
// funcion para controlar eventos
function select (){
    input = document.getElementById('input_8');
    input.addEventListener('input', function () {
        //Se obtiene precio
        let valor = get_precio_id_a(document.getElementById("input_8").value);
        //Se envia a label
        document.getElementById("input_CT").innerHTML = valor;
        //se limpian otros labels
        document.getElementById("input_9").value = "";
        document.getElementById("input_10").value = "0";
    });
}
//funcion para agregar total
function sumar_total(add) {
    //se traen los labels donde estan los valores
    //en caso de que se cree una nueva factura se settean valores a cero
    if (document.getElementById("input_3").value === "") {
        sub_total = 0;
    } else {
        sub_total = parseFloat(document.getElementById("input_3").value);
    }
    if (document.getElementById("input_2").value === "") {
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
//funcion para generar factura
function generarFacura(){
    const $elementoParaConvertir = document.body; // <-- Aquí puedes elegir cualquier elemento del DOM
    html2pdf()
        .set({
            margin: 1,
            filename: 'documento.pdf',
        image: {
            type: 'jpeg',
            quality: 0.98
        },
        html2canvas: {
            scale: 3, // A mayor escala, mejores gráficos, pero más peso
            letterRendering: true,
        },
        jsPDF: {
            unit: "in",
            format: "a3",
            orientation: 'portrait' // landscape o portrait
        }
    })
    .from($elementoParaConvertir)
    .save()
    .catch(err => console.log(err));
}