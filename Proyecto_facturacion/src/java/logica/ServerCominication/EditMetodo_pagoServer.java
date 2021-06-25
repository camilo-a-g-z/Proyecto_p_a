package logica.ServerCominication;

import datos.DBMetodo_pago;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import logica.EncoderDecoder.DecoderMetodo_pago;
import logica.EncoderDecoder.EncoderMetodo_pago;
import logica.Metodo_pago;

/**
 *
 * @author Camilo Garcia
 */
@ServerEndpoint(value="/modifyMP", encoders= {EncoderMetodo_pago.class}, decoders = {DecoderMetodo_pago.class})
public class EditMetodo_pagoServer {
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
    public void mensaje(Metodo_pago met,String inst){
        DBMetodo_pago metDB = new DBMetodo_pago();
        switch(Integer.parseInt(inst)){
            case 1:
                
                break;
            case 2:
                
                break;
            case 3:
                
                break;
        }
            
    }
}
