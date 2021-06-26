package logica;

/**
 *
 * @author Camilo Garcia
 */
public class Categoria {
    private int id_categoria;
    private String nombre;
    private String descripcion;
    private String mensaje;

    public Categoria() {
    mensaje = "0";
    }
    
    //getters y setters
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public int getId_categoria() {
        return id_categoria;
    }
    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }   
}
