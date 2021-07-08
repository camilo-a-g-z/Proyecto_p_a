package logica.ServerCominication;

import datos.DBCiudad;
import java.sql.ResultSet;
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
@ServerEndpoint(value="/ciudad", encoders = {EncoderCiudad.class}, decoders = {DecoderCiudad.class})
public class CiudadServer {
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
        DBCiudad ciuDB = new DBCiudad();
        Ciudad ciu = new Ciudad();
        try{
            ResultSet res = ciuDB.getCiudadByNombre(mens);
            if(!res.next()){
                ciu.setNombre("NE");
                conectados.get(i).getBasicRemote().sendObject(ciu);
            }else{
                ciu.setId_ciudad(Integer.parseInt(res.getString("id_ciudad")));
                ciu.setNombre(res.getString("nombre"));
                conectados.get(i).getBasicRemote().sendObject(ciu);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
