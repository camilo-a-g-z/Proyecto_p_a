package logica.ServerCominication;

import datos.DBMetodo_pago;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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
    public void mensaje(String mens){
        //se crea conexion a base de datos 
        DBMetodo_pago metDB = new DBMetodo_pago();
        Metodo_pago met = new Metodo_pago();
        try{
            //se pregunta si el cliente necesita todos o unicamente uno en especifico
            if(mens == "Todo"){
                //se carga de la base de datos segun requerimiento de websocket
                ResultSet res = metDB.getMetodos_pago();
                //se carga resultado
                if(!res.next()){
                    met.setTipo("NE");
                    conectados.get(i).getBasicRemote().sendObject(met);
                }else{
                    do{
                        //se carga a onjeto el resultado obtenido en la base de datos
                        met.setId_metodo_pago(Integer.parseInt(res.getString("id_metodo_pago")));
                        met.setTipo(res.getString("tipo"));
                        // se envia a la sesion que solicito la informacion por medio del websocket
                        conectados.get(i).getBasicRemote().sendObject(met);
                    }while(res.next());
                }
            }else{
                //se carga de la base de datos segun requerimiento de websocket
                ResultSet res = metDB.getMPByNombre(mens);
                //se carga resultado
                if(!res.next()){
                    met.setTipo("NE");
                    conectados.get(i).getBasicRemote().sendObject(met);
                }else{
                    //se carga a onjeto el resultado obtenido en la base de datos
                    met.setId_metodo_pago(Integer.parseInt(res.getString("id_metodo_pago")));
                    met.setTipo(res.getString("tipo"));
                    // se envia a la sesion que solicito la informacion por medio del websocket
                    conectados.get(i).getBasicRemote().sendObject(met);
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
        }
    }
}
