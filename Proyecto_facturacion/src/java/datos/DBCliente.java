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
                + "id_ciudad,"
                + "password "
                + "FROM cliente "
                + "WHERE id_cliente = "+id);
        ResultSet res = pstm.executeQuery();
        return res;
    }
    public ResultSet getClienteByCorreo(String correo) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT id_cliente, "
                + "nombre, "
                + "apellido, "
                + "correo, "
                + "direccion, "
                + "celular, "
                + "cedula, "
                + "id_ciudad,"
                + "password "
                + "FROM cliente "
                + "WHERE correo = ?");
        pstm.setString(1, correo);
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
                + "id_ciudad,"
                + "password "
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
                + "id_ciudad,"
                + "password "
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
                + "id_ciudad,"
                + "password) "
                + "values(?,?,?,?,?,?,?,?)");
            pstm.setString(1, c.getNombre());
            pstm.setString(2, c.getApellido());
            pstm.setString(3, c.getCorreo());
            pstm.setString(4, c.getDireccion());
            pstm.setString(5, String.valueOf(c.getCelular()));
            pstm.setString(6, c.getCedula());
            pstm.setString(7, String.valueOf(c.getId_ciudad()));
            pstm.setString(8, c.getPassword());

            pstm.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public void eliminarCliente(int i) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("delete from cliente where id_cliente = "+i);
            pstm.executeUpdate();
    }
    public void modifyCliente(Cliente c) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("update cliente "
                + "set  nombre = ? , apellido = ? ,"
                + "correo = ? , direccion= ? , celular = ? "
                + ", cedula = ? , id_ciudad = ?  where id_cliente = ?");
        pstm.setString(1, c.getNombre());
        pstm.setString(2, c.getApellido());
        pstm.setString(3, c.getCorreo());
        pstm.setString(4, c.getDireccion());
        pstm.setDouble(5, c.getCelular());
        pstm.setString(6, c.getCedula());
        pstm.setInt(7, c.getId_ciudad());
        pstm.setInt(8, c.getId_cliente());
        pstm.executeUpdate();
    }
    public String getMensaje() {
        return cn.getMensaje();
    }
}
