package logica;

/**
 *
 * @author Camilo Garcia
 */
public class Ciudad {
    private int id_ciudad;
    private String nombre;
    private String mensaje;

    public Ciudad() {
        mensaje = "0";
    }
    
    //getters y setters
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public int getId_ciudad() {
        return id_ciudad;
    }
    public void setId_ciudad(int id_ciudad) {
        this.id_ciudad = id_ciudad;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nobre) {
        this.nombre = nobre;
    }
}
