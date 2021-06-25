package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logica.Empleado;

/**
 *
 * @author Camilo Garcia
 */
public class DBEmpleado {
    DBConexion cn;
    //constructor
    public DBEmpleado(){
        cn = new DBConexion();
    }
    public ResultSet getEmpleadoById(int id) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT id_empleado, "
                + "nombre, "
                + "cedula, "
                + "id_ciudad,"
                + "password "
                + "FROM empleado "
                + "WHERE id_empleado = "+id);
        ResultSet res = pstm.executeQuery();
        return res;
    }
    
    public ResultSet geEmpleadoByNombre(String nombre) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT id_empleado, "
                + "nombre, "
                + "cedula, "
                + "id_ciudad,"
                + "password "
                + "FROM empleado "
                + "WHERE nombre = "+"\""+nombre+"\"");
        ResultSet res = pstm.executeQuery();
        return res;
    }
    public ResultSet getEmpleados() throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT id_empleado, "
                + "nombre, "
                + "cedula, "
                + "id_ciudad,"
                + "password "
                + "FROM empleado "
                + "ORDER BY nombre ");
        ResultSet res = pstm.executeQuery();
        return res;
    }
    public void insertarEmpleado(Empleado e){
        try{
            PreparedStatement pstm = cn.getConexion().prepareStatement("insert into empleado (nombre, "
                    + "cedula, "
                    + "id_ciudad,"
                    + "password) "
                    + "values(?,?,?,4)");
            pstm.setString(1, e.getNombre());
            pstm.setString(2, e.getCedula());
            pstm.setString(3, String.valueOf(e.getId_ciudad()));
            pstm.setString(4, e.getPassword());
            
            pstm.executeUpdate();
        }catch (SQLException err){
            System.out.println(err);
        }
    }
    public String getMensaje() {
        return cn.getMensaje();
    }
}
