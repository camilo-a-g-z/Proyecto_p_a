package logica.ServerCominication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import logica.Categoria;
import logica.EncoderDecoder.DecoderCategoria;
import logica.EncoderDecoder.EncoderCategoria;

/**
 *
 * @author Camilo Garcia
 */
@ServerEndpoint (value="/categoria", encoders ={EncoderCategoria.class}, decoders = {DecoderCategoria.class})
public class CategoriaServer {
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
    public void mensaje(Categoria categoria) throws IOException, EncodeException{
        conectados.get(i).getBasicRemote().sendObject(categoria);
    }
}
