(function(window, document, JSON){
    'use strict';
    var url = 'ws://localhost:8080/Proyecto_facturacion/metodo_pago',
        ws = new WebSocket(url);
    var mensajes = document.getElementById('obtenido');
    var boton = document.getElementById('boton'),
        id_metodo = document.getElementById('id_metodo');
    
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
        var msg = {
            id_metodo_pago:"1",
            tipo:"contado" 
        };
        ws.send(JSON.stringify(msg));
    }
    function onMessage(evt){
        var obj = JSON.parse(evt.data),
                msg = 'El id es: '+obj.id_metodo_pago+' con el tipo: '+obj.tipo;
        mensajes.innerHTML += '</br>'+msg;
    }
    
})(window, document, JSON);


