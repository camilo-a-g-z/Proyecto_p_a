package logica.ServerCominication;

import datos.DBArticulo;
import datos.DBCategoria;
import datos.DBCiudad;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import logica.Articulo;
import logica.Categoria;
import logica.Ciudad;
import logica.EncoderDecoder.DecoderArticulo;
import logica.EncoderDecoder.DecoderCategoria;
import logica.EncoderDecoder.DecoderCiudad;
import logica.EncoderDecoder.EncoderArticulo;
import logica.EncoderDecoder.EncoderCategoria;
import logica.EncoderDecoder.EncoderCiudad;

/**
 *
 * @author Camilo Garcia
 */
@ServerEndpoint(value="/testeo", encoders = {EncoderCategoria.class}, decoders = {DecoderCategoria.class})
public class TesteoServer {
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
    public void mensaje(Categoria cat){
        DBCategoria catDB = new DBCategoria();
        try{
            switch(Integer.parseInt(cat.getMensaje())){
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
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
