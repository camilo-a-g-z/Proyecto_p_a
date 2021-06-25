(function(window, document, JSON){
    'use strict';
    var info = document.getElementById('informacion');
    //direccion del websocket al que se envia y recive la peticion
    var url = 'ws://localhost:8080/Proyecto_facturacion/articulo',
        ws = new WebSocket(url);
    var mensajes = document.getElementById('obtenido'),
        nombre =document.getElementById('Nombre');
    var boton = document.getElementById('boton');
    //creacion de metodos propios del websocket
    ws.onopen = onOpen;
    ws.onclose = onClose;
    ws.onmessage = onMessage;
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