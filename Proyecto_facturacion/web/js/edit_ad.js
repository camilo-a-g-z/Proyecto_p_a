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
            if (!band) {
                ws.close();
            } else {
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

    function onOpen() {
        console.log('Conectado..');
    }
    function onClose() {
        console.log('Desonectado..');
    }
    function onMessage(evt) {
        console.log("recivido");
        var obj = JSON.parse(evt.data);
        if (v_selected == "Articulo") {
            console.log("Se recivio un articulo");
            if (obj.nombre === 'NE') {
                info.innerHTML = 'No existe elemento';
            } else {
                document.getElementById("input_1").value = obj.nombre;
                document.getElementById("input_2").value = obj.cant_stock;
                document.getElementById("input_3").value = obj.descripcion;
                document.getElementById("input_4").value = obj.id_categoria;
            }
        } else if (v_selected == "Categoria") {
            if (obj.nombre === 'NE') {
                info.innerHTML = 'No existe elemento';
            } else {
                document.getElementById("input_1").value = obj.nombre;
                document.getElementById("input_2").value = obj.descripcion;
            }
            console.log("Se recivio una categoria");
        } else if (v_selected == "Cliente") {
            if (obj.nombre === 'NE') {
                info.innerHTML = 'No existe elemento';
            } else {
                document.getElementById("input_1").value = obj.nombre;
                document.getElementById("label_2").innerHTML = "Apellido";
                document.getElementById("input_2").value = obj.apellido;
                document.getElementById("label_3").innerHTML = "Correo:";
                document.getElementById("input_3").value = obj.correo;
                document.getElementById("label_4").innerHTML = "Direccion:";
                document.getElementById("input_4").value = obj.direccion;
                document.getElementById("input_5").value = obj.celular;
                document.getElementById("input_6").value = obj.cedula;
                document.getElementById("input_7").value = obj.id_ciudad;
            }
            console.log("Se recivio un cliente");
        } else if (v_selected == "Factura") {
            console.log("Se recivio una factura");
        } else if (v_selected == "Metodo_pago") {
            if (obj.tipo === 'NE') {
                info.innerHTML = 'No existe elemento';
            } else {
                document.getElementById("label_1").innerHTML = "Tipo:";
                document.getElementById("input_1").value = obj.tipo;
            }
            console.log("Se recivio un metodo de pago");
        } else if (v_selected == "Ciudad") {
            if (obj.nombre === 'NE') {
                info.innerHTML = 'No existe elemento';
            } else {
                document.getElementById("input_1").value = obj.nombre;
            }
            console.log("Se recivio una ciudad");
        }
    }
};
enviar = function () {
    if (nombre.value == "") {
        console.log("No se ingreso nombre:");
    } else {
        ws.send(nombre.value);
    }
}
    