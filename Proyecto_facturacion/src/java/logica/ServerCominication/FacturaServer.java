package logica.ServerCominication;

import datos.DBFactura;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import logica.EncoderDecoder.DecoderFactura;
import logica.EncoderDecoder.EncoderFactura;
import logica.Factura;

/**
 *
 * @author Camilo Garcia
 */
@ServerEndpoint(value="/factura", encoders = {EncoderFactura.class}, decoders = {DecoderFactura.class})
public class FacturaServer {
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
        DBFactura facDB = new DBFactura();
        Factura fac = new Factura();
        try{
            ResultSet res = facDB.getFacturaById(Integer.parseInt(mens));
            if(!res.next()){
                fac.setFecha_fac("NE");
                conectados.get(i).getBasicRemote().sendObject(fac);
            }else{
                fac.setFecha_fac(res.getString("fecha_fac"));
                fac.setId_cliente(Integer.parseInt(res.getString("id_cliente")));
                fac.setId_factura(Integer.parseInt(res.getString("id_factura")));
                fac.setId_metodo_pago(Integer.parseInt(res.getString("id_metodo_pago")));
                fac.setTotal(Double.parseDouble(res.getString("total")));
                fac.setVal_iva(Double.parseDouble(res.getString("val_iva")));
                fac.setVal_sub_total(Double.parseDouble(res.getString("val_sub_total")));
                conectados.get(i).getBasicRemote().sendObject(fac);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
