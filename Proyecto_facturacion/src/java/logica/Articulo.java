package logica;

/**
 *
 * @author Camilo Garcia
 */
public class Articulo {
    private int id_articulo;
    private Double nombre;
    private Double cant_stock;
    private String descripcion;
    private int id_categoria;
    //setters y getters
    public int getId_articulo() {
        return id_articulo;
    }
    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }
    public Double getNombre() {
        return nombre;
    }
    public void setNombre(Double nombre) {
        this.nombre = nombre;
    }
    public Double getCant_stock() {
        return cant_stock;
    }
    public void setCant_stock(Double cant_stock) {
        this.cant_stock = cant_stock;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getId_categoria() {
        return id_categoria;
    }
    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
}
