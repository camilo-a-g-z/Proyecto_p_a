package logica.ServerCominication;

import datos.DBArticulo;
import datos.DBCiudad;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import logica.Articulo;
import logica.Ciudad;
import logica.EncoderDecoder.DecoderArticulo;
import logica.EncoderDecoder.EncoderArticulo;

/**
 *
 * @author Camilo Garcia
 */
@ServerEndpoint(value="/modifyArticulo", encoders= {EncoderArticulo.class}, decoders ={DecoderArticulo.class})
public class EditArticuloServer {
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
    public void mensaje(Articulo art){
        DBArticulo artDB = new DBArticulo();
        try{
            switch(Integer.parseInt(art.getMensaje())){
                case 1:
                    artDB.modifyArticulo(art);
                    break;
                case 2:
                    artDB.insertarArticulo(art);
                    break;
                case 3:
                    artDB.eliminarArticulo(art.getId_articulo());
                    break;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
