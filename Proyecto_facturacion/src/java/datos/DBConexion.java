package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Camilo Garcia
 */
public class DBConexion {
    static String bd = "facturacion";
    static String login = "root";
    static String password = "u_admin";
    static String url = "jdbc:mysql://localhost:3306/"+bd;
    static String mensaje = "";
    
    Connection conexion = null;
    
    public DBConexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(url, login, password);
            if (conexion!=null){
                    System.out.println("Conexión a base de datos "+bd+" OK");
            }
        }catch(SQLException e){
                mensaje = e.getMessage();
        }catch(ClassNotFoundException e){
                mensaje = e.getMessage();
        }
    }
    /**
     * Metodo para retornar mensajes de control
     * @return 
     */
    public static String getMensaje() {
        return mensaje;
    }
    /**
     * Metodo para setear mensajes de control
     * @param mensaje 
     */
    public static void setMensaje(String mensaje) {
        DBConexion.mensaje = mensaje;
    }
    /**
     * metodo que retorna la conexion a la bd
     * @return 
     */
    public Connection getConexion(){
        return conexion;
    }
    /**
     * metodo que desconecta la base de datos
     */
    public void desconectar(){
        conexion = null;
    }
}
