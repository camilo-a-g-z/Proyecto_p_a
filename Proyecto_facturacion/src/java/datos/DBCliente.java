package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Camilo Garcia
 */
public class DBCliente {
    DBConexion cn;
    //constructor
    public DBCliente(){
        cn = new DBConexion();
    }
    public ResultSet getClienteById(int id) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT id_cliente, "
                + "nombre, "
                + "apellido, "
                + "correo, "
                + "direccion, "
                + "celular, "
                + "cedula, "
                + "id_ciudad "
                + "FROM cliente "
                + "WHERE id_cliente = "+id);
        ResultSet res = pstm.executeQuery();
        return res;
    }
    
    public ResultSet getClienteByNombre(String nombre) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT id_cliente, "
                + "nombre, "
                + "apellido, "
                + "correo, "
                + "direccion, "
                + "celular, "
                + "cedula, "
                + "id_ciudad "
                + "FROM cliente "
                + "WHERE nombre = "+"\""+nombre+"\"");
        ResultSet res = pstm.executeQuery();
        return res;
    }
    public ResultSet getClientes() throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT id_categoria, "
                + "nombre, "
                + "descripcion "
                + "FROM categoria "
                + "ORDER BY nombre ");
        ResultSet res = pstm.executeQuery();
        return res;
    }
    public void insertarArticulo(Categoria c){
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
}
