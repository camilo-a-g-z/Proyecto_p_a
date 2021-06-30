package logica.ServerCominication;

import datos.DBFactura;
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
@ServerEndpoint(value="/modifyFactura", encoders= {EncoderFactura.class}, decoders={DecoderFactura.class})
public class EditFacturaServer {
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
    public void mensaje(Factura fac){
        DBFactura facDB = new DBFactura();
        try{
            switch(Integer.parseInt(fac.getMensaje())){
                case 1:
                    facDB.modifyFactura(fac);
                    break;
                case 2:
                    facDB.insertarFactura(fac);
                    break;
                case 3:
                    facDB.eliminarFactura(fac.getId_factura());
                    break;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
