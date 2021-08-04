//funcion que acorde a la eleccion se settean listeners para solo aceptar un tipo de dato
function SetListeners() {
    RemoveListeners();
    RemoveDisable();
    //segun eleccion se settean listeners
    if (window.v_selected === "Articulo") {
        document.getElementById("input_2").setAttribute("onkeypress","return isNumberKey(event)");
        document.getElementById("input_3").setAttribute("onkeypress","return isNumberKeyDecimal(event)");
    } else if (window.v_selected === "Cliente") {
        document.getElementById("input_5").setAttribute("onkeypress","return isNumberKey(event)");
        document.getElementById("input_6").setAttribute("onkeypress","return isNumberKey(event)");
    } else if (window.v_selected === "Factura") {
        document.getElementById("input_1").setAttribute("disabled","");
        document.getElementById("input_2").setAttribute("disabled","");
        document.getElementById("input_3").setAttribute("disabled","");
        document.getElementById("input_4").setAttribute("disabled","");
        document.getElementById("input_5").setAttribute("onkeypress","return isNumberKey(event)");
    }
}
//funcion para eliminar listeners de los inputs
function RemoveListeners(){
    //se pregunta si tiene el listener y encaso de tenerlo se elimina
    if(document.getElementById("Nombre").hasAttribute("onkeypress")){
        document.getElementById("Nombre").removeAttribute("onkeypress");
    } else if(document.getElementById("input_1").hasAttribute("onkeypress")){
        document.getElementById("input_1").removeAttribute("onkeypress");
    } else if(document.getElementById("input_2").hasAttribute("onkeypress")){
        document.getElementById("input_2").removeAttribute("onkeypress");
    }else if(document.getElementById("input_3").hasAttribute("onkeypress")){
        document.getElementById("input_3").removeAttribute("onkeypress");
    } else if(document.getElementById("input_4").hasAttribute("onkeypress")){
        document.getElementById("input_4").removeAttribute("onkeypress");
    }else if(document.getElementById("input_5").hasAttribute("onkeypress")){
        document.getElementById("input_5").removeAttribute("onkeypress");
    } else if(document.getElementById("input_6").hasAttribute("onkeypress")){
        document.getElementById("input_6").removeAttribute("onkeypress");
    }
}
//funcion para eliminar disable
function RemoveDisable(){
    //se pregunta si esta desabilitado y se vueleve a habilitar
    if(document.getElementById("Nombre").hasAttribute("disabled")){
        document.getElementById("Nombre").removeAttribute("disabled");
    } else if(document.getElementById("input_1").hasAttribute("disabled")){
        document.getElementById("input_1").removeAttribute("disabled");
    } else if(document.getElementById("input_2").hasAttribute("disabled")){
        document.getElementById("input_2").removeAttribute("disabled");
    }else if(document.getElementById("input_3").hasAttribute("disabled")){
        document.getElementById("input_3").removeAttribute("disabled");
    } else if(document.getElementById("input_4").hasAttribute("disabled")){
        document.getElementById("input_4").removeAttribute("disabled");
    }else if(document.getElementById("input_5").hasAttribute("disabled")){
        document.getElementById("input_5").removeAttribute("disabled");
    } else if(document.getElementById("input_6").hasAttribute("disabled")){
        document.getElementById("input_6").removeAttribute("disabled");
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