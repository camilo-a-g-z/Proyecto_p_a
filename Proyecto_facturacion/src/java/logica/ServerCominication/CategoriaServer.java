package logica.ServerCominication;

import datos.DBCategoria;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
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
    public void mensaje(String mens) throws IOException, EncodeException{
        DBCategoria catDB = new DBCategoria();
        Categoria cat = new Categoria();
        try{
            ResultSet res = catDB.getCategoriaByNombre(mens);
            if(!res.next()){
                cat.setNombre("NE");
                conectados.get(i).getBasicRemote().sendObject(cat);
            }else{
                cat.setNombre(res.getString("nombre"));
                cat.setDescripcion(res.getString("descripcion"));
                cat.setId_categoria(Integer.parseInt(res.getString("id_categoria")));
                conectados.get(i).getBasicRemote().sendObject(cat);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @OnError
    public void onError(Throwable t) {
    }
}
