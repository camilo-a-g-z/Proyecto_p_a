package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logica.Articulo;

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
    public void insertarArticulo(Articulo a){
        try{
            PreparedStatement pstm = cn.getConexion().prepareStatement("insert into articulo(nombre, "
                    + "cant_stock, "
                    + "descripccion, "
                    + "id_categoria) "
                    + "values(?,?,?,?)");
            pstm.setString(1, a.getNombre());
            pstm.setString(2, String.valueOf(a.getCant_stock()));
            pstm.setString(3, a.getDescripcion());
            pstm.setString(4, String.valueOf(a.getId_categoria()));
            
            
            pstm.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }
    }
}
