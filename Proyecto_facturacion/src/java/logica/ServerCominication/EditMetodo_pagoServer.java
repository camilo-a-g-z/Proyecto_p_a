package logica.ServerCominication;

import datos.DBMetodo_pago;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import logica.EncoderDecoder.DecoderMetodo_pago;
import logica.EncoderDecoder.EncoderMetodo_pago;
import logica.Metodo_pago;

/**
 *
 * @author Camilo Garcia
 */
@ServerEndpoint(value="/modifyMP", encoders= {EncoderMetodo_pago.class}, decoders = {DecoderMetodo_pago.class})
public class EditMetodo_pagoServer {
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
    public void mensaje(Metodo_pago met,String inst) throws SQLException{
        DBMetodo_pago metDB = new DBMetodo_pago();
        switch(Integer.parseInt(inst)){
            case 1:
                metDB.modifyMetodo_pago(met);
                break;
            case 2:
                metDB.insertarMetodo_pago(met);
                break;
            case 3:
                metDB.eliminarMetodo_pago(met.getId_metodo_pago());
                break;
        }
            
    }
}
