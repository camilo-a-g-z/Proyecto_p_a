package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logica.Cliente;

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
        PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT id_cliente, "
                + "nombre, "
                + "apellido, "
                + "correo, "
                + "direccion, "
                + "celular, "
                + "cedula, "
                + "id_ciudad "
                + "FROM cliente "
                + "ORDER BY nombre ");
        ResultSet res = pstm.executeQuery();
        return res;
    }
    public void insertarCliente(Cliente c){
        try{
            PreparedStatement pstm = cn.getConexion().prepareStatement("insert into cliente(nombre, "
                + "apellido, "
                + "correo, "
                + "direccion, "
                + "celular, "
                + "cedula, "
                + "id_ciudad) "
                + "values(?,?,?,?,?,?,?)");
            pstm.setString(1, c.getNombre());
            pstm.setString(2, c.getApellido());
            pstm.setString(3, c.getCorreo());
            pstm.setString(4, c.getDireccion());
            pstm.setString(5, String.valueOf(c.getCelular()));
            pstm.setString(6, c.getCedula());
            pstm.setString(7, String.valueOf(c.getId_ciudad()));
            
            
            pstm.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }
    }
}
