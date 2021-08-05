//funcion que acorde a la eleccion se settean listeners para solo aceptar un tipo de dato
function SetListeners() {
    RemoveListeners();
    RemoveDisable();
    //segun eleccion se settean listeners
    if (window.v_selected === "Articulo") {
        document.getElementById("input_2").setAttribute("onkeypress","return isNumberKey(event)");
        document.getElementById("input_3").setAttribute("onkeypress","return isNumberKeyDecimal(event)");
    } else if (window.v_selected === "Cliente") {
        document.getElementById("Nombre").setAttribute("onkeypress","return isNumberKey(event)");
        document.getElementById("input_5").setAttribute("onkeypress","return isNumberKey(event)");
        document.getElementById("input_6").setAttribute("onkeypress","return isNumberKey(event)");
    } else if (window.v_selected === "Factura") {
        document.getElementById("Nombre").setAttribute("onkeypress","return isNumberKey(event)");
        document.getElementById("input_1").setAttribute("disabled","");
        document.getElementById("input_2").setAttribute("disabled","");
        document.getElementById("input_3").setAttribute("disabled","");
        document.getElementById("input_4").setAttribute("disabled","");
        document.getElementById("input_5").setAttribute("onkeypress","return isNumberKey(event)");
    }
}
//funcion que verifica si los campos de cantidad y descuento estan llenos
function veryfyAddFila(){
    //en caso de que no haya llenado se le notifica al usuario
    if(document.getElementById("input_9").value === "" || document.getElementById("input_10").value === ""){
        alert("Digite cantidad y descuento");
    }else{
        window.add_fila();
    }
}
//funcion para verificar que los campos en cada caso de envio esten completos
function verifyClose_conexion(){
    let band = true;
    if (window.v_selected === "Articulo") {
        if(document.getElementById("input_1").value === "" || 
                document.getElementById("input_2").value === "" || 
                document.getElementById("input_3").value === "" ){
            alert("Ingrese todos los datos de ARTICULO.");
            band = false;
        }
    } else if (window.v_selected === "Categoria") {
        if(document.getElementById("input_1").value === "" || document.getElementById("input_2").value === ""){
            alert("Ingrese todos los datos de CATEGORIA.");
            band = false;
        }
    } else if (window.v_selected === "Cliente") {
        if(document.getElementById("input_1").value === "" ||
                document.getElementById("input_2").value === "" ||
                document.getElementById("input_3").value === "" ||
                document.getElementById("input_4").value === "" ||
                document.getElementById("input_5").value === "" ||
                document.getElementById("input_6").value === ""){
            alert("Ingrese todos los datos de CLIENTE.");
            band = false;
        }
    } else if (window.v_selected === "Factura") {
        if(document.getElementById("input_5").value === "" ){
            alert("Ingrese todos los datos de Factura.");
            band = false;
        }
    } else if (window.v_selected === "Metodo_pago" || window.v_selected === "Ciudad") {
        if(document.getElementById("input_1").value === "" ){
            alert("Ingrese todos los datos de Factura.");
            band = false;
        }
    }
    if(band){
        window.close_conexion();
    }
}
//funcion para eliminar listeners de los inputs
function RemoveListeners(){
    //se pregunta si tiene el listener y encaso de tenerlo se elimina
    for(let i = 1;i<=6;i++){
        if(document.getElementById("input_"+i).hasAttribute("onkeypress")){
            document.getElementById("input_"+i).removeAttribute("onkeypress");
        }
    }
    if(document.getElementById("Nombre").hasAttribute("onkeypress")){
        document.getElementById("Nombre").removeAttribute("onkeypress");
    }
}
//funcion para eliminar disable
function RemoveDisable(){
    //se pregunta si esta desabilitado y se vueleve a habilitar
    if(document.getElementById("Nombre").hasAttribute("disabled")){
        document.getElementById("Nombre").removeAttribute("disabled");
    }
    for(let i = 1;i<=6;i++){
        if(document.getElementById("input_"+i).hasAttribute("disabled")){
            document.getElementById("input_"+i).removeAttribute("disabled");
        }
    }
}
//funcion para limitar a solo ingreso de numeros en input HTML
function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}
////funcion para limitar a solo ingreso de numeros decimales en input HTML
function isNumberKeyDecimal(evt){
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode !== 46 &&(charCode < 48 || charCode > 57)))
        return false;
    return true;
}