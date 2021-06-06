package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Camilo Garcia
 */
public class DBArticulo {
    DBConexion cn;
    //constructor
    public DBArticulo(){
        cn = new DBConexion();
    }
    public ResultSet getArticuloById(int id) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT id_articulo, "
                + "nombre, "
                + "cant_stock, "
                + "descripccion, "
                + "id_categoria "
                + "FROM articulo "
                + "WHERE id_articulo = "+id);
        ResultSet res = pstm.executeQuery();
        return res;
    }
    
    public ResultSet getArticuloByNombre(String nombre) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT id_articulo, "
                + "nombre, "
                + "cant_stock, "
                + "descripccion, "
                + "id_categoria "
                + "FROM articulo "
                + "WHERE nombre = "+"\""+nombre+"\"");
        ResultSet res = pstm.executeQuery();
        return res;
    }
    public ResultSet getArticulos() throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT id_articulo, "
                + "nombre, "
                + "cant_stock, "
                + "descripccion, "
                + "id_categoria "
                + "FROM articulo "
                + "ORDER BY nombre ");
        ResultSet res = pstm.executeQuery();
        return res;
    }
}
