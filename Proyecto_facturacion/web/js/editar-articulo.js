(function(window, document, JSON){
    
    var url = 'ws://localhost:8080/Proyecto_facturacion/articulo',
        ws = new WebSocket(url);
    
    var url = 'ws://localhost:8080/Proyecto_facturacion/modifyArticulo',
        ws1 = new WebSocket(url);
        
    var mensajes = document.getElementById('obtenido');
    var boton_2 = document.getElementById('boton_2');
    var boton_1 = document.getElementById('boton_1');
    var nombre = document.getElementById('Nombre'),
        id = document.getElementById('id'),
        descripcion = document.getElementById('desc'),
        cantstock = document.getElementById('cant'),
        idcategoria = document.getElementById('cat'),
        mensaje = document.getElementById('Eliminar');
        
    boton_1.addEventListener('click', onMessage);
    boton_2.addEventListener('click', eliminar);
    
    ws.onopen = onOpen;
    ws.onclose = onClose;
    ws.onmessage = onMessage;
    
    ws1.onopen = onOpen;
    ws1.onclose = onClose;
    ws1.onmessage = onMessage;
    
    function onOpen(){
        console.log('Conectado..');
    }
    function onClose(){
        console.log('Desonectado..');
    }
    
    function onMessage(evt){
        var obj = JSON.parse(evt.data),
                msg = 'El árticulo: ' +obj.nombre+ 'si existe';
        if(obj.nombre === 'NE'){
            msg = 'No existe el elemento';
        }
        mensajes.innerHTML = '</br>' + msg;
        
        id.value = obj.id_articulo;
        nombre.value = obj.nombre;
        descripcion.value = obj.descripcion;
        cantstock.value = obj.cant_stock;
        idcategoria.value = obj.id_categoria;
        
    }
    
    function eliminar(){
        
        var art ={
            id_articulo: id.value,
            nombre: nombre.value,
            descripcion: descripcion.value,
            cant_stock: cantstock.value,
            id_categoria: idcategoria.value,
            mensaje: mensaje.value
        };
        
        ws1.send(JSON.stringify(art));
    }
    
})(window, document, JSON);

