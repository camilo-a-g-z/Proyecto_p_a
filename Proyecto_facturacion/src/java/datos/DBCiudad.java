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
            PreparedStatement pstm = cn.getConexion().prepareStatement("insert into ciudad (nombre) "
                    + "values(?)");
            pstm.setString(1, c.getNombre());
            
            pstm.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    
    public String get_last_id(){
        try{
            PreparedStatement pstm_2 = cn.getConexion().prepareStatement("SELECT LAST_INSERT_ID()");
            ResultSet res = pstm_2.executeQuery();
            res.next();
            return res.getString("LAST_INSERT_ID()");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return "";
    }
    
    public void eliminarCiudad(int i) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("delete from ciudad where id_ciudad = "+i);
            pstm.executeUpdate();
    }
    public void modifyCiudad(Ciudad c) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("update ciudad "
                + "set  nombre = ? where id_ciudad = ?");
        pstm.setString(1, c.getNombre());
        pstm.setInt(2, c.getId_ciudad());
        pstm.executeUpdate();
    }
    public String getMensaje() {
        return cn.getMensaje();
    }
}
