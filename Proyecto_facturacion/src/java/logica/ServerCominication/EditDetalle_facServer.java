package logica.ServerCominication;

import datos.DBDetalle_fac;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import logica.Detalle_fac;
import logica.EncoderDecoder.DecoderDetalle_fac;
import logica.EncoderDecoder.EncoderDetalle_fac;

/**
 *
 * @author Camilo Garcia
 */
//@ServerEndpoint(value="/modifyDetalle_fac", encoders= {EncoderDetalle_fac.class}, decoders={DecoderDetalle_fac.class})
public class EditDetalle_facServer {
    /*private static final List<Session> conectados = new ArrayList<>();
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
    public void mensaje(Detalle_fac det, String inst) throws SQLException{
        DBDetalle_fac detDB = new DBDetalle_fac();
        switch(Integer.parseInt(inst)){
            case 1:
                detDB.modifyDetalle_fac(det);
                break;
            case 2:
                detDB.insertarDetalle_fac(det);
                break;
            case 3:
                detDB.eliminarDetalle_fac(det.getId_detalle_fac());
                break;
        }
    }*/
}
