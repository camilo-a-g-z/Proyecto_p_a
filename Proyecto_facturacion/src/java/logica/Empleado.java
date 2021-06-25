package logica;

/**
 *
 * @author Camilo Garcia
 */
public class Empleado {
    private int id_empleado;
    private String nombre;
    private String cedula;
    private int id_ciudad;
    private String password;
    //getters y seters
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getId_empleado() {
        return id_empleado;
    }
    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public int getId_ciudad() {
        return id_ciudad;
    }
    public void setId_ciudad(int id_ciudad) {
        this.id_ciudad = id_ciudad;
    }   
}
