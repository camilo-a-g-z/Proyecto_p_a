package logica;

/**
 *
 * @author Camilo Garcia
 */
public class Factura {
    private int id_factura;
    private String fecha_fac;
    private Double val_iva;
    private Double val_sub_total;
    private Double total;
    private int id_cliente;
    private int id_metodo_pago;
    private String mensaje;

    public Factura() {
        mensaje="0";
    }
    
    //getters y setters
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public int getId_factura() {
        return id_factura;
    }
    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }
    public String getFecha_fac() {
        return fecha_fac;
    }
    public void setFecha_fac(String fecha_fac) {
        this.fecha_fac = fecha_fac;
    }
    public Double getVal_iva() {
        return val_iva;
    }
    public void setVal_iva(Double val_iva) {
        this.val_iva = val_iva;
    }
    public Double getVal_sub_total() {
        return val_sub_total;
    }
    public void setVal_sub_total(Double val_sub_total) {
        this.val_sub_total = val_sub_total;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    public int getId_cliente() {
        return id_cliente;
    }
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    public int getId_metodo_pago() {
        return id_metodo_pago;
    }
    public void setId_metodo_pago(int id_metodo_pago) {
        this.id_metodo_pago = id_metodo_pago;
    } 
}
