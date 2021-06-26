package logica;

/**
 * @author Camilo Garcia
 */
public class Cliente {
    private int id_cliente;
    private String nombre;
    private String apellido;
    private String correo;
    private String direccion;
    private Double celular;
    private String cedula;
    private int id_ciudad;
    private String password;
    //setters y getters

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getId_cliente() {
        return id_cliente;
    }
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Double getCelular() {
        return celular;
    }
    public void setCelular(Double celular) {
        this.celular = celular;
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
