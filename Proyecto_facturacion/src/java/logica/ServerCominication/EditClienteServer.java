package logica.ServerCominication;

import java.util.ArrayList;
import java.util.List;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import logica.EncoderDecoder.DecoderCliente;
import logica.EncoderDecoder.EncoderCliente;

/**
 *
 * @author Camilo Garcia
 */
@ServerEndpoint(value="/modifyCliente", encoders= {EncoderCliente.class}, decoders= {DecoderCliente.class})
public class EditClienteServer {
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
    
}
