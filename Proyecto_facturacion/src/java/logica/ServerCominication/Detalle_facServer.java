package logica.ServerCominication;

import datos.DBDetalle_fac;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import logica.Detalle_fac;
import logica.EncoderDecoder.DecoderDetalle_fac;
import logica.EncoderDecoder.EncoderDetalle_fac;

/**
 *
 * @author Camilo Garcia
 */
@ServerEndpoint(value="/detalle_fac", encoders = {EncoderDetalle_fac.class}, decoders={DecoderDetalle_fac.class})
public class Detalle_facServer {
    private static final List<Session> conectados = new ArrayList<>();
    int i;
    
    @OnOpen
    public void inicio(Session session){
        conectados.add(session);
        i = conectados.size()-1;
    }
    
    @OnClose
    public void salir(Session sesion){
        conectados.remove(sesion);
    }
    
    @OnMessage
    public void mensaje(String mens){
        DBDetalle_fac detDB = new DBDetalle_fac();
        Detalle_fac det = new Detalle_fac();
        try{
            ResultSet res = detDB.getDetalle_facById_factura(Integer.parseInt(mens));
            if(!res.next()){
                det.setCantidad(0);
                conectados.get(i).getBasicRemote().sendObject(det);
            }else{
                do{
                    det.setCantidad(Integer.parseInt(res.getString("cantidad")));
                    det.setDescuento(Float.parseFloat(res.getString("descuento")));
                    det.setId_articulo(Integer.parseInt(res.getString("id_articulo")));
                    det.setId_detalle_fac(Integer.parseInt(res.getString("id_detalle_fac")));
                    det.setId_factura(Integer.parseInt(res.getString("id_factura")));
                    det.setTotal(Double.parseDouble(res.getString("total")));
                    det.setVal_descuento(Double.parseDouble(res.getString("val_descuento")));
                    conectados.get(i).getBasicRemote().sendObject(det);
                }while(!res.next());
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
