package logica;

/**
 *
 * @author Camilo Garcia
 */
public class Metodo_pago {
    private int id_metodo_pago;
    private String tipo;
    private String mensaje;

    public Metodo_pago() {
        mensaje="0";
    }
    
    //setters y getters
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public int getId_metodo_pago() {
        return id_metodo_pago;
    }
    public void setId_metodo_pago(int id_metodo_pago) {
        this.id_metodo_pago = id_metodo_pago;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }  
}
