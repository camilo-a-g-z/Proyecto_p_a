package logica;

/**
 *
 * @author Camilo Garcia
 */
public class Detalle_fac {
    private int id_detalle_fac;
    private int cantidad;
    private Double total;
    private float descuento;
    private Double val_descuento;
    private int id_factura;
    private int id_articulo;
    private String mensaje;

    public Detalle_fac() {
        mensaje="0";
    }
    
    //setters y getters
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public int getId_detalle_fac() {
        return id_detalle_fac;
    }
    public void setId_detalle_fac(int id_detalle_fac) {
        this.id_detalle_fac = id_detalle_fac;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    public float getDescuento() {
        return descuento;
    }
    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }
    public Double getVal_descuento() {
        return val_descuento;
    }
    public void setVal_descuento(Double val_descuento) {
        this.val_descuento = val_descuento;
    }
    public int getId_factura() {
        return id_factura;
    }
    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }
    public int getId_articulo() {
        return id_articulo;
    }
    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }
}
