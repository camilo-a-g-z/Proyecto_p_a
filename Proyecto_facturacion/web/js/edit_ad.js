(function(window, document, JSON){
    'use strict';
    var url = 'ws://localhost:8080/Proyecto_facturacion/modifyCiudad',
        ws = new WebSocket(url);
    var mensajes = document.getElementById('obtenido'),
        nombre_2 =document.getElementById('Nombre');
    var boton = document.getElementById('boton');
    var info = document.getElementById('informacion');
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
        var msg={
            id_ciudad:'1',
            nombre:nombre_2.value,
            mensaje:"2"
        };
        ws.send(JSON.stringify(msg));
    }
    
    function onMessage(evt){
        var obj = JSON.parse(evt.data),
                msg = 'La ciudad: '+obj.nombre;
        /*if(obj.nombre === 'NE'){
            msg = 'No existe elemento';
        }*/
        mensajes.innerHTML = '</br>'+ msg;
    }
    /*
    //direccion del websocket al que se envia y recive la peticion
    var url_2 = 'ws://localhost:8080/Proyecto_facturacion/modifyArticulo',
        ws_2 = new WebSocket(url);
    
    var boton_2 = document.getElementById('boton_2');
    var nombre_2 = document.getElementById('n_nombre'),
        cant = document.getElementById('cant'),
        desc = document.getElementById('desc'),
        cat = document.getElementById('cat');
    //creacion de metodos propios del websocket
    
    ws_2.onopen = onOpen;
    ws_2.onclose = onClose;
    ws_2.onmessage = onMessage;
    
    boton_2.addEventListener('click',enviar_2);
    
    
    
    function enviar_2(){
        
        var msg ={
            id_articulo : "1",
            nombre : nombre_2.value,
            cant_stock : cant.value,
            descripcion : desc.value,
            id_categoria : "1",
            mensaje : "2"
        };
        ws.send(JSON.stringify(msg));
    }
    //funcion que recibe el mensaje por parte del servidor
    */
})(window, document, JSON);