package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logica.Categoria;

/**
 *
 * @author Camilo Garcia
 */
public class DBCategoria {
    DBConexion cn;
    //constructor
    public DBCategoria(){
        cn = new DBConexion();
    }
    public ResultSet getCategoriaById(int id) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT id_categoria, "
                + "nombre, "
                + "descripcion "
                + "FROM categoria "
                + "WHERE id_categoria = "+id);
        ResultSet res = pstm.executeQuery();
        return res;
    }
    
    public ResultSet getCategoriaByNombre(String nombre) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT id_categoria, "
                + "nombre, "
                + "descripcion "
                + "FROM categoria "
                + "WHERE nombre = "+"\""+nombre+"\"");
        ResultSet res = pstm.executeQuery();
        return res;
    }
    public ResultSet getCategorias() throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT id_categoria, "
                + "nombre, "
                + "descripcion "
                + "FROM categoria "
                + "ORDER BY nombre ");
        ResultSet res = pstm.executeQuery();
        return res;
    }
    public void insertarCategoria(Categoria c){
        try{
            PreparedStatement pstm = cn.getConexion().prepareStatement("insert into categoria(nombre, "
                    + "descripcion) "
                    + "values(?,?)");
            pstm.setString(1, c.getNombre());
            pstm.setString(2, c.getDescripcion());
            
            pstm.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public void eliminarCategoria(int i) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("delete from categoria where id_categoria="+i);
            pstm.executeUpdate();
    }
    public void modifyCategoria(Categoria c) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("update categoria"
                + "set  nombre='"+c.getNombre()+", descripcion='"+c.getDescripcion()+"' where id_categoria="+c.getId_categoria());
            pstm.executeUpdate();
    }
    public String getMensaje() {
        return cn.getMensaje();
    }
}
