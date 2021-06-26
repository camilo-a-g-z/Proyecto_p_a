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
            pstm.setString(1, String.valueOf(d.getCantidad()));
            pstm.setString(2, String.valueOf(d.getTotal()));
            pstm.setString(3, String.valueOf(d.getDescuento()));
            pstm.setString(4, String.valueOf(d.getVal_descuento()));
            pstm.setString(5, String.valueOf(d.getId_factura()));
            pstm.setString(6, String.valueOf(d.getId_articulo()));
            
            pstm.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public void eliminarDetalle_fac(int i) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement("delete from detalle_fac where id_detalle_fac="+i);
            pstm.executeUpdate();
    }
    public void modifyDetalle_fac(Detalle_fac d) throws SQLException{
        PreparedStatement pstm = cn.getConexion().prepareStatement(d.getId_articulo()+
                " where id_detalle_fac="+d.getId_detalle_fac()+"update detalle_fac"
                + "set  cantidad="+d.getCantidad()+", total="+d.getTotal()+
                ", descuento="+d.getDescuento()+", val_descuento="+d.getVal_descuento()+
                ", id_factura="+d.getId_factura()+", id_articulo=");
            pstm.executeUpdate();
    }
    public String getMensaje() {
        return cn.getMensaje();
    }
}
