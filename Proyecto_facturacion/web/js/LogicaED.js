var arrayArticulos = [];
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