var ws;
var url = 'ws://localhost:8080/Proyecto_facturacion/articulo';
select = function(){
    
    var input = document.getElementById('opcion');
    input.addEventListener('input',function(){
		if (this.value == "Articulo") {
         url = 'ws://localhost:8080/Proyecto_facturacion/articulo';
			console.log("Articulo");
		}else if(this.value == "Categoria"){
          url = 'ws://localhost:8080/Proyecto_facturacion/categoria';
          console.log("Categoria");
      }else if(this.value == "Cliente"){
          url = 'ws://localhost:8080/Proyecto_facturacion/cliente';
          console.log("Cliente");
      }else if(this.value == "Factura"){
          url = 'ws://localhost:8080/Proyecto_facturacion/factura';
          console.log("Factura");
      }else if(this.value == "Metodo_pago"){
          url = 'ws://localhost:8080/Proyecto_facturacion/metodo_pago';
          console.log("Metodo de pago");
      }
      procedimiento();
	});
};
procedimiento = function(){
    select();
    'use strict';
    ws = new WebSocket(url);
    var mensajes = document.getElementById('obtenido'),
        nombre_2 =document.getElementById('Nombre');
    var boton = document.getElementById('boton');
    var info = document.getElementById('informacion');
    var boton_2 = document.getElementById('boton_2');
    var nombre_3 = document.getElementById('n_nombre'),
        cant = document.getElementById('cant'),
        desc = document.getElementById('desc'),
        cat = document.getElementById('cat');
    ws.onopen = onOpen;
    ws.onclose = onClose;
    ws.onmessage = onMessage;
    
    boton_2.addEventListener('click', enviar);
    
    function onOpen(){
        console.log('Conectado..');
    }
    function onClose(){
        console.log('Desonectado..');
    }
    function enviar(){
        info.innerHTML ='<br>'+ 'Se esta buscando en base de datos'+'<br>';
        var msg={
            id_detalle_fac:'1',
            cantidad:'15',
            total:'75000',
            descuento:'8.5',
            val_descuento:'9800',
            id_factura:'1',
            id_articulo:'1',
            mensaje:"3"
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
}
    