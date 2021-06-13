package logica.ServerCominication;

import datos.DBMetodo_pago;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import logica.EncoderDecoder.DecoderMetodo_pago;
import logica.EncoderDecoder.DecoderNumero;
import logica.EncoderDecoder.EncoderMetodo_pago;
import logica.EncoderDecoder.EncoderNumero;
import logica.Metodo_pago;

/**
 * @author Camilo Garcia
 */
//nombre de la url a la que se va a hacer la peticion del websocket y los decoder y encoders empleados
@ServerEndpoint (value="/metodo_pago", encoders ={EncoderMetodo_pago.class,EncoderNumero.class}, decoders ={DecoderMetodo_pago.class,DecoderNumero.class})
public class Metodo_pagoServer {
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
    public void mensaje(Metodo_pago meto) throws IOException, EncodeException{
        //se crea conexion a base de datos 
        DBMetodo_pago metDB = new DBMetodo_pago();
        Metodo_pago met = new Metodo_pago();
        ResultSet res;
        try{
            //se carga de la base de datos segun requerimiento de websocket
            res = metDB.getMetodo_pagoById(meto.getId_metodo_pago());
            //se carga resultado
            res.next();
            //se carga a onjeto el resultado obtenido en la base de datos
            met.setId_metodo_pago(Integer.parseInt(res.getString("id_metodo_pago")));
            met.setTipo(res.getString("tipo"));
        }catch(Exception e){
            System.out.println("Error en: "+e.getMessage());
        }finally{
        }
        // se envia a la sesion que solicito la informacion por medio del websocket
        conectados.get(i).getBasicRemote().sendObject(met);
    }
}
