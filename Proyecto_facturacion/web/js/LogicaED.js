var arrayArticulos = [];
var iterator = 0;
var arrayOpc = [];
var arrayid = [];
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
        document.getElementById("input_10").value = "";
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