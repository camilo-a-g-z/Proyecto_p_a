(function(window, document, JSON){
    'use strict';
    var info = document.getElementById('informacion');
    //direccion del websocket al que se envia y recive la peticion
    var url = 'ws://localhost:8080/Proyecto_facturacion/articulo',
        ws = new WebSocket(url);
    var url_2 = 'ws://localhost:8080/Proyecto_facturacion/modifyArticulo',
        ws_2 = new WebSocket(url);
    var mensajes = document.getElementById('obtenido'),
        nombre =document.getElementById('Nombre');
    var boton = document.getElementById('boton');
    var boton_2 = document.getElementById('boton_2');
    var nombre_2 = document.getElementById('n_nombre'),
        cant = document.getElementById('cant'),
        desc = document.getElementById('desc'),
        cat = document.getElementById('cat');
    //creacion de metodos propios del websocket
    ws.onopen = onOpen;
    ws.onclose = onClose;
    ws.onmessage = onMessage;
    ws_2.onopen = onOpen;
    ws_2.onclose = onClose;
    
    boton_2.addEventListener('click',enviar_2);
    boton.addEventListener('click', enviar);
    
    function onOpen(){
        console.log('Conectado..');
    }
    function onClose(){
        console.log('Desonectado..');
    }
    function enviar(){
        info.innerHTML ='<br>'+ 'Se esta buscando en base de datos'+'<br>';
        ws.send(nombre.value);
    }
    function enviar_2(){
        var msg ={
            nombre:nombre_2.value,
            cant_stock:cant.value,
            descripccion:desc.value,
            id_categoria:cat.value,
            mensaje:'2'
        };
        ws.send(JSON.stringify(msg));
    }
    //funcion que recibe el mensaje por parte del servidor
    function onMessage(evt){
        var obj = JSON.parse(evt.data),
                msg = 'El articulo: '+obj.nombre;
        if(obj.nombre === 'NE'){
            msg = 'No existe elemento';
        }
        mensajes.innerHTML = '</br>'+ msg;
    }
})(window, document, JSON);