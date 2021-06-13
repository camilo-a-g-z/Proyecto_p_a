(function(window, document, JSON){
    'use strict';
    //direccion del websocket al que se envia y recive la peticion
    var url = 'ws://localhost:8080/Proyecto_facturacion/metodo_pago',
        ws = new WebSocket(url);
    var mensajes = document.getElementById('obtenido');
    var boton = document.getElementById('boton'),
        id_metodo = document.getElementById('id_metodo');
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
        //se crea variable para enviar a websocket
        var msg = {
            id_metodo_pago:id_metodo.value,
            tipo:"contado" 
        };
        //se parsea a un archivo JSON y se envia
        ws.send(JSON.stringify(msg));
    }
    //funcion que recibe el mensaje por parte del servidor
    function onMessage(evt){
        var obj = JSON.parse(evt.data),
                msg = 'El id es: '+obj.id_metodo_pago+' con el tipo: '+obj.tipo;
        mensajes.innerHTML += '</br>'+msg;
    }
})(window, document, JSON);


