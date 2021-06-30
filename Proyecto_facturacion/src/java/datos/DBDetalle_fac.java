package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logica.Detalle_fac;

/**
 *
 * @author Camilo Garcia
 */
public class DBDetalle_fac {
    DBConexion cn;
    //constructor
    public DBDetalle_fac(){
        cn = new DBConexion();
    }
    public ResultSet getDetalle_facById_factura(int id) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("SELECT id_detalle_fac, "
                + "cantidad, "
                + "total, "
                + "descuento, "
                + "val_descuento, "
                + "id_factura, "
                + "id_articulo "
                + "FROM detalle_fac "
                + "WHERE id_factura = "+id);
        ResultSet res = pstm.executeQuery();
        return res;
    }
    
    public void insertarDetalle_fac(Detalle_fac d){
        try{
            PreparedStatement pstm = cn.getConexion().prepareStatement("insert into detalle_fac (cantidad, "
                + "total, "
                + "descuento, "
                + "val_descuento, "
                + "id_factura, "
                + "id_articulo) "
                + "values(?,?,?,?,?,?)");
            pstm.setInt(1, d.getCantidad());
            pstm.setDouble(2, d.getTotal());
            pstm.setFloat(3, d.getDescuento());
            pstm.setDouble(4, d.getVal_descuento());
            pstm.setInt(5, d.getId_factura());
            pstm.setInt(6, d.getId_articulo());
            
            pstm.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public void eliminarDetalle_fac(int i) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("delete from detalle_fac where id_detalle_fac = "+i);
            pstm.executeUpdate();
    }
    public void modifyDetalle_fac(Detalle_fac d) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("update detalle_fac "
                + "set  cantidad = ? , total = ? , descuento = ? , val_descuento = ? "
                + ", id_factura = ? , id_articulo = ?  where id_detalle_fac = ?");
        pstm.setInt(1, d.getCantidad());
        pstm.setDouble(2, d.getTotal());
        pstm.setFloat(3, d.getDescuento());
        pstm.setDouble(4, d.getVal_descuento());
        pstm.setInt(5, d.getId_factura());
        pstm.setInt(6, d.getId_articulo());
        pstm.setInt(7, d.getId_detalle_fac());
        pstm.executeUpdate();
    }
    public String getMensaje() {
        return cn.getMensaje();
    }
}
