package logica.ServerCominication;

import datos.DBMetodo_pago;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import logica.EncoderDecoder.DecoderCliente;
import logica.EncoderDecoder.EncoderCliente;
import logica.Metodo_pago;

/**
 *
 * @author Camilo Garcia
 */
@ServerEndpoint(value="/cliente", encoders = {EncoderCliente.class}, decoders = {DecoderCliente.class})
public class ClienteServer {
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
        DBMetodo_pago metDB = new DBMetodo_pago();
        Metodo_pago met = new Metodo_pago();
        try{
            ResultSet res = metDB.getCiudadByTipo(mens);
            if(!res.next()){
                met.setTipo("NE");
                conectados.get(i).getBasicRemote().sendObject(met);
            }else{
                met.setTipo(res.getString("tipo"));
                met.setId_metodo_pago(Integer.parseInt(res.getString("id_metodo_pago")));
                conectados.get(i).getBasicRemote().sendObject(met);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
}
