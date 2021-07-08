var ws;
var url;
var v_selected;
var band = true;
var nombre;
select = function () {
    var input = document.getElementById('opcion');
    input.addEventListener('input', function () {
        v_selected = this.value;
        if (this.value == "") {
            console.log("no hace conexion");
        } else {
            if(!band){
                ws.close();
            }else{
                band = false;
            }
            if (this.value == "Articulo") {
                url = 'ws://localhost:8080/Proyecto_facturacion/articulo';
                console.log("Articulo");
            } else if (this.value == "Categoria") {
                url = 'ws://localhost:8080/Proyecto_facturacion/categoria';
                console.log("Categoria");
            } else if (this.value == "Cliente") {
                url = 'ws://localhost:8080/Proyecto_facturacion/cliente';
                console.log("Cliente");
            } else if (this.value == "Factura") {
                url = 'ws://localhost:8080/Proyecto_facturacion/factura';
                console.log("Factura");
            } else if (this.value == "Metodo_pago") {
                url = 'ws://localhost:8080/Proyecto_facturacion/metodo_pago';
                console.log("Metodo de pago");
            }
            procedimiento();
        }
    });
};
procedimiento = function () {
    ws = null;
    ws = new WebSocket(url);
    ws.onopen = onOpen;
    ws.onclose = onClose;
    ws.onmessage = onMessage;
    
    var mensajes = document.getElementById('obtenido');
            nombre = document.getElementById('Nombre');
    var boton = document.getElementById('boton');
    var info = document.getElementById('informacion');
    var boton_2 = document.getElementById('boton');
    var nombre_3 = document.getElementById('n_nombre'),
            cant = document.getElementById('cant'),
            desc = document.getElementById('desc'),
            cat = document.getElementById('cat');
    
    function onOpen() {
        console.log('Conectado..');
    }
    function onClose() {
        console.log('Desonectado..');
    }
    //boton_2.addEventListener('click', enviar);
    
    
    function onMessage(evt) {
        console.log("recivido");
        //var obj = JSON.parse(evt.data);
        
       // ws.close();
        /*if(obj.nombre === 'NE'){
         msg = 'No existe elemento';
         }*/
        //mensajes.innerHTML = '</br>' + msg;
    }
};
enviar = function(){
        //info.innerHTML += '<br>' + 'Se esta buscando en base de datos' + '<br>';
        if(nombre.value == ""){
            console.log("No se ingreso nombre:");
        }else{
            ws.send(nombre.value);
            
        }
    }
    