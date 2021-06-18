package logica.ServerCominication;

import datos.DBArticulo;
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
import logica.Articulo;
import logica.EncoderDecoder.DecoderArticulo;
import logica.EncoderDecoder.EncoderArticulo;

/**
 *
 * @author Camilo Garcia
 */

@ServerEndpoint(value="/articulo", encoders = {EncoderArticulo.class}, decoders = {DecoderArticulo.class})
public class ArticuloServer {
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
    public void mensaje(Articulo articulo) throws IOException, EncodeException{
        DBArticulo artDB = new DBArticulo();
        Articulo art = new Articulo();
        try{
            ResultSet res = artDB.getArticuloByNombre(articulo.getNombre());
            if(!res.next()){
                art.setNombre("NE");
                art.setCant_stock(0.0);
                art.setDescripcion("NE");
                art.setId_articulo(0);
                art.setId_categoria(0);
                conectados.get(i).getBasicRemote().sendObject(art);
            }else{
                art.setNombre(res.getString("nombre"));
                art.setCant_stock(Double.parseDouble(res.getString("cant_stock")));
                art.setDescripcion(res.getString("descripccion"));
                art.setId_articulo(Integer.parseInt(res.getString("id_articulo")));
                art.setId_categoria(Integer.parseInt(res.getString("id_categoria")));
                conectados.get(i).getBasicRemote().sendObject(art);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
