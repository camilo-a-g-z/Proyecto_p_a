package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logica.Ciudad;

/**
 *
 * @author Camilo Garcia
 */
public class DBCiudad {
    DBConexion cn;
    //constructor
    public DBCiudad(){
        cn = new DBConexion();
    }
    public ResultSet getCiudadById(int id) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT id_ciudad, "
                + "nombre "
                + "FROM ciudad "
                + "WHERE id_ciudad = "+id);
        ResultSet res = pstm.executeQuery();
        return res;
    }
    
    public ResultSet getCiudadByNombre(String nombre) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT id_ciudad, "
                + "nombre "
                + "FROM ciudad "
                + "WHERE nombre = "+"\""+nombre+"\"");
        ResultSet res = pstm.executeQuery();
        return res;
    }
    public ResultSet getCiudades() throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT id_ciudad, "
                + "nombre "
                + "FROM ciudad "
                + "ORDER BY nombre ");
        ResultSet res = pstm.executeQuery();
        return res;
    }
    public void insertarCiudad(Ciudad c){
        try{
            PreparedStatement pstm = cn.getConexion().prepareStatement("insert into categoria(nombre) "
                    + "values(?)");
            pstm.setString(1, c.getNombre());
            
            pstm.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }
    }
}
