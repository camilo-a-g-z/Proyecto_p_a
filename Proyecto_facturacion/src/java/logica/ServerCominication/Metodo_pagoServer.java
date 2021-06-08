package logica.ServerCominication;

import datos.DBMetodo_pago;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import logica.EncoderDecoder.DecoderMetodo_pago;
import logica.EncoderDecoder.DecoderNumero;
import logica.EncoderDecoder.EncoderMetodo_pago;
import logica.EncoderDecoder.EncoderNumero;
import logica.Metodo_pago;

/**
 *
 * @author Camilo Garcia
 */
@ServerEndpoint (value="/metodo_pago", encoders ={EncoderMetodo_pago.class,EncoderNumero.class}, decoders ={DecoderMetodo_pago.class,DecoderNumero.class})
public class Metodo_pagoServer {
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
    public void mensaje(Metodo_pago meto) throws IOException, EncodeException{
        DBMetodo_pago metDB = new DBMetodo_pago();
        Metodo_pago met = new Metodo_pago();
        ResultSet res;
        try{
            res = metDB.getMetodo_pagoById(meto.getId_metodo_pago());
            while(res.next()){
                met.setId_metodo_pago(Integer.parseInt(res.getString("id_metodo_pago")));
                met.setTipo(res.getString("tipo"));
            }
        }catch(Exception e){
            System.out.println("Error en: "+e.getMessage());
        }finally{
        }
        
        conectados.get(i).getBasicRemote().sendObject(met);
    }
}
