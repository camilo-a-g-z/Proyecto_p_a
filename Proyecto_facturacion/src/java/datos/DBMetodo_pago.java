package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logica.Metodo_pago;

/**
 *
 * @author Camilo Garcia
 */
public class DBMetodo_pago {
    DBConexion cn;
    //constructor
    public DBMetodo_pago(){
        cn = new DBConexion();
    }
    public ResultSet getMetodo_pagoById(int id) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT id_metodo_pago, "
                + "tipo "
                + "FROM metodo_pago "
                + "WHERE id_metodo_pago = "+id);
        ResultSet res = pstm.executeQuery();
        return res;
    }
    
    public ResultSet getCiudadByTipo (String nombre) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT id_metodo_pago, "
                + "tipo "
                + "FROM metodo_pago "
                + "WHERE tipo = "+"\""+nombre+"\"");
        ResultSet res = pstm.executeQuery();
        return res;
    }
    public ResultSet getMetodos_pago() throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT id_metodo_pago, "
                + "tipo "
                + "FROM metodo_pago "
                + "ORDER BY tipo ");
        ResultSet res = pstm.executeQuery();
        return res;
    }
    public void insertarMetodo_pago(Metodo_pago m){
        try{
            PreparedStatement pstm = cn.getConexion().prepareStatement("insert into metodo_pago (tipo) "
                    + "values(?)");
            pstm.setString(1, m.getTipo());
            
            pstm.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public void eliminarMetodo_pago(int i) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("delete from metodo_pago where id_metodo_pago = "+i);
            pstm.executeUpdate();
    }
    public void modifyMetodo_pago(Metodo_pago m) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("update metodo_pago "
                + "set  tipo = ? where id_metodo_pago = ");
        pstm.setString(1, m.getTipo());
        pstm.setInt(2, m.getId_metodo_pago());
        pstm.executeUpdate();
    }
    public String getMensaje() {
        return cn.getMensaje();
    }
}
