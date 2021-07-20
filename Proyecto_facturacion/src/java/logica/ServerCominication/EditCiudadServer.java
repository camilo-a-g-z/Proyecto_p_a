package logica.ServerCominication;

import datos.DBCiudad;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import logica.Ciudad;
import logica.EncoderDecoder.DecoderCiudad;
import logica.EncoderDecoder.EncoderCiudad;

/**
 *
 * @author Camilo Garcia
 */
@ServerEndpoint(value="/modifyCiudad", encoders= {EncoderCiudad.class}, decoders = {DecoderCiudad.class})
public class EditCiudadServer {
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
    public void mensaje(Ciudad ciu) {
        DBCiudad ciuDB = new DBCiudad();
        try{
            switch(Integer.parseInt(ciu.getMensaje())){
                case 1:
                    ciuDB.modifyCiudad(ciu);
                    break;
                case 2:
                    ciuDB.insertarCiudad(ciu);
                    System.out.println(ciuDB.get_last_id());
                    conectados.get(i).getBasicRemote().sendText(ciuDB.get_last_id());
                    break;
                case 3:
                    ciuDB.eliminarCiudad(ciu.getId_ciudad());
                    break;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
