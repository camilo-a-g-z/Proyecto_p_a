package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logica.Factura;

/**
 *
 * @author Camilo Garcia
 */
public class DBFactura {
    DBConexion cn;
    //constructor
    public DBFactura(){
        cn = new DBConexion();
    }
    public ResultSet getFacturaById(int id) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT id_factura, "
                + "fecha_fac, "
                + "val_iva, "
                + "val_sub_total, "
                + "total, "
                + "id_cliente, "
                + "id_metodo_pago "
                + "FROM factura "
                + "WHERE id_factura = "+id);
        ResultSet res = pstm.executeQuery();
        return res;
    }
    
    public ResultSet getFacturaById_usuario(int id) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT id_factura, "
                + "fecha_fac, "
                + "val_iva, "
                + "val_sub_total, "
                + "total, "
                + "id_cliente, "
                + "id_metodo_pago "
                + "FROM factura "
                + "WHERE id_cliente = "+id);
        ResultSet res = pstm.executeQuery();
        return res;
    }
   
    public void insertarFactura(Factura f){
        try{
            PreparedStatement pstm = cn.getConexion().prepareStatement("insert into factura (fecha_fac, "
                    + "val_iva, "
                    + "val_sub_total, "
                    + "total, "
                    + "id_cliente, "
                    + "id_metodo_pago) "
                    + "values(?,?,?,?,?,?)");
            pstm.setString(1, f.getFecha_fac());
            pstm.setString(2, String.valueOf(f.getVal_iva()));
            pstm.setString(3, String.valueOf(f.getVal_sub_total()));
            pstm.setString(4, String.valueOf(f.getTotal()));
            pstm.setString(5, String.valueOf(f.getId_cliente()));
            pstm.setString(6, String.valueOf(f.getId_metodo_pago()));
            
            pstm.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public void eliminarFactura(int i) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("delete from factura where id_factura = "+i);
            pstm.executeUpdate();
    }
    public void modifyFactura(Factura f) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("update factura "
                +"set  fecha_fac = ? , val_iva = ? , val_sub_total = ? , total = ? , id_cliente = ? "
                + ", id_metodo_pago = ? where id_factura = ?");
        pstm.setString(1, f.getFecha_fac());
        pstm.setDouble(2, f.getVal_iva());
        pstm.setDouble(3, f.getVal_sub_total());
        pstm.setDouble(4, f.getTotal());
        pstm.setInt(5, f.getId_cliente());
        pstm.setInt(6, f.getId_metodo_pago());
        pstm.setInt(7, f.getId_factura());
        pstm.executeUpdate();
    }
    public String getMensaje() {
        return cn.getMensaje();
    }
}
