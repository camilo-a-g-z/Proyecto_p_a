package logica.ServerCominication;

import datos.DBArticulo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    public void mensaje(Articulo art, String inst) throws SQLException{
        DBArticulo artDB = new DBArticulo();
        switch(Integer.parseInt(inst)){
            case 1:
                //en caso de editar
                artDB.modifyArticulo(art);
                break;
            case 2:
                //en caso de agregar
                artDB.insertarArticulo(art);
                break;
            case 3:
                //en caso de eliminar
                artDB.eliminarArticulo(art.getId_articulo());
                break;
        }
    }
}
