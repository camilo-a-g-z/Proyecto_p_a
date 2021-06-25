package logica.ServerCominication;

import datos.DBCategoria;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
@ServerEndpoint(value="/modifyCategoria", encoders = {EncoderCategoria.class}, decoders = {DecoderCategoria.class})
public class EditCategoriaServer {
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
    public void mensaje(Categoria cat, String inst) throws SQLException{
        DBCategoria catDB = new DBCategoria();
        switch(Integer.parseInt(inst)){
            case 1:
                catDB.modifyCategoria(cat);
                break;
            case 2:
                catDB.insertarCategoria(cat);
                break;
            case 3:
                catDB.eliminarCategoria(cat.getId_categoria());
                break;
        }
    }
}
