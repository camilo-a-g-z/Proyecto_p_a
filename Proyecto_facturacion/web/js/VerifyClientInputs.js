//funcion para limitar a solo ingreso de numeros en input HTML
function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}
//funcion para limitar a solo ingreso de numeros decimales en input HTML
function isNumberKeyDecimal(evt){
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode !== 46 &&(charCode < 48 || charCode > 57)))
        return false;
    return true;
}
//funcion para agregar o quitarvalor en caso de eliminar
function deleteItem(id){
    //se obtiene valor del label
    let vt = document.getElementById("t"+id).innerHTML;
    //se le resta
    window.sumar_total(vt*-1);
    //ahora se oculta label
    document.getElementById("tr"+id).setAttribute("style","display:none");
}
//funcion para verificar que campo de cantidad sea diferente a cero
function cantVerify(){
    let cant = document.getElementById("input_9");
    if(cant.value === "" || cant.value === "0"){
        alert("Ingrese una cantidad valida");
    }else{
        window.add_fila();
    }
}
function verifyEnviar(){
    if(!document.getElementById("table_d_f")){
        alert("Ingrese articulos.");
    }else{
       window.open_factura_conexion();
    }
}