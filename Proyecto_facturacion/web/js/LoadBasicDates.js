var wsc;
var arrayCategoria = [];
var arrayCiudad = [];
var arrayMP = [];
//se abre primer ws
wsc = null;
wsc = new WebSocket('ws://localhost:8080/Proyecto_facturacion/categoria');
wsc.onopen = window.onOpen;
wsc.onclose = window.onClose;
//funcion para iniciar carga
async function load(){
    get_categorias();
}
//funcion para traer categorias desde servidor
async function get_categorias() {
    arrayCategoria.pop();
    wsc.onmessage = recivir_art;
    await window.delay(2);
    wsc.send("Todo");
    function recivir_art(evt) {
        var obj = JSON.parse(evt.data);
        //en caso de que no exista el registro
        if (obj.nombre === 'NE') {
            console.log("no existen categorias");
        } else {
            arrayCategoria.push(obj);
        }
    }
    await window.delay(2);
    wsc.close();
    get_articulos();
}
//funcion para traer articulos desde servidor
async function get_articulos() {
    wsc = new WebSocket('ws://localhost:8080/Proyecto_facturacion/articulo');
    wsc.onopen = window.onOpen;
    wsc.onclose = window.onClose;
    window.arrayArticulos.pop();
    wsc.onmessage = recivir_art;
    await window.delay(2);
    wsc.send("Todo");
    function recivir_art(evt) {
        var obj = JSON.parse(evt.data);
        //en caso de que no exista el registro
        if (obj.nombre === 'NE') {
            console.log("no existen elementos");
        } else {
            window.arrayArticulos.push(obj);
        }
    }
    ;
    await delay(2);
    wsc.close();
    get_ciudades();
}
//funcion para traer ciudades desde servidor
async function get_ciudades() {
    wsc = new WebSocket('ws://localhost:8080/Proyecto_facturacion/ciudad');
    wsc.onopen = window.onOpen;
    wsc.onclose = window.onClose;
    arrayCiudad.pop();
    wsc.onmessage = recivir_art;
    await window.delay(2);
    wsc.send("Todo");
    function recivir_art(evt) {
        var obj = JSON.parse(evt.data);
        //en caso de que no exista el registro
        if (obj.nombre === 'NE') {
            console.log("no existen ciudades");
        } else {
            arrayCiudad.push(obj);
        }
    }
    ;
    await window.delay(1);
    wsc.close();
    //se cierra simbolo de carga
    document.getElementsByTagName("body")[0].removeChild(document.getElementById("load"));
}
