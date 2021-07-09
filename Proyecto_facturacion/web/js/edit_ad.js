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
            } else if (this.value == "Ciudad") {
                url = 'ws://localhost:8080/Proyecto_facturacion/ciudad';
                console.log("Ciudad");
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
        var obj = JSON.parse(evt.data);
        if(v_selected == "Articulo"){
            console.log("Se recivio un articulo");
            if(obj.nombre === 'NE'){
                info.innerHTML = 'No existe elemento';
            }else{
               document.getElementById("n_nombre").value = obj.nombre;
               document.getElementById("cant").value = obj.cant_stock;
               document.getElementById("desc").value = obj.descripcion;
               document.getElementById("cat").value = obj.id_categoria;
            }
        }else if(v_selected == "Categoria"){
            if(obj.nombre === 'NE'){
                info.innerHTML = 'No existe elemento';
            }else{
                document.getElementById("n_nombre").value = obj.nombre;
                document.getElementById("desc").value = obj.descripcion;
            }
            console.log("Se recivio una categoria");
        }else if(v_selected == "Cliente"){
            if(obj.nombre === 'NE'){
                info.innerHTML = 'No existe elemento';
            }else{
                document.getElementById("n_nombre").value = obj.nombre;
                document.getElementById("descripcion").innerHTML = "Apellido";
                document.getElementById("desc").value = obj.apellido;
                document.getElementById("categoria").innerHTML = "Correo:";
                document.getElementById("cat").value = obj.correo;
                document.getElementById("cantidad").innerHTML = "Direccion:";
                document.getElementById("cant").value = obj.direccion;
            }
            console.log("Se recivio un cliente");
        }else if(v_selected == "Factura"){
            console.log("Se recivio una factura");
        }else if(v_selected == "Metodo_pago"){
            if(obj.tipo === 'NE'){
                info.innerHTML = 'No existe elemento';
            }else{
                document.getElementById("nombre_p").innerHTML = "Tipo:";
                document.getElementById("n_nombre").value = obj.tipo;
            }
            console.log("Se recivio un metodo de pago");
        }else if(v_selected == "Ciudad"){
            if(obj.nombre === 'NE'){
                info.innerHTML = 'No existe elemento';
            }else{
                document.getElementById("n_nombre").value = obj.nombre;
            }
            console.log("Se recivio una ciudad");
        }
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
    