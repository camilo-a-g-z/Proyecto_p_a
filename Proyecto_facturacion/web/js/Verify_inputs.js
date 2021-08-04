function SetListeners() {
    if (window.v_selected === "Articulo") {
        
    } else if (window.v_selected === "Categoria") {
        
    } else if (window.v_selected === "Cliente") {
        
    } else if (window.v_selected === "Factura") {
        
    } else if (window.v_selected === "Metodo_pago") {
        
    } else if (window.v_selected === "Ciudad") {
        
    }
}
function RemoveListeners(){
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
function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}
function isNumberKeyDecimal(evt){
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode !== 46 &&(charCode < 48 || charCode > 57)))
        return false;
    return true;
}