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
import logica.Articulo;
import logica.EncoderDecoder.DecoderArticulo;
import logica.EncoderDecoder.EncoderArticulo;

/**
 *
 * @author Camilo Garcia
 */

@ServerEndpoint(value="/articulo", encoders = {EncoderArticulo.class}, decoders = {DecoderArticulo.class})
public class ArticuloServer {
    private static final List<Session> conectados = new ArrayList<>();
    
    @OnOpen
    public void inicio(Session session){
        conectados.add(session);
    }
    
    @OnClose
    public void salir(Session sesion){
        conectados.add(sesion);
    }
    
    @OnMessage
    public void mensaje(Articulo articulo, Session sesion) throws IOException, EncodeException{
        sesion.getBasicRemote().sendObject(articulo);
    }
}
