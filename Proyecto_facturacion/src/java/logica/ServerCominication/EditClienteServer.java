package logica.ServerCominication;

import datos.DBCliente;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import logica.Cliente;
import logica.EncoderDecoder.DecoderCliente;
import logica.EncoderDecoder.EncoderCliente;

/**
 *
 * @author Camilo Garcia
 */
@ServerEndpoint(value="/modifyCliente", encoders= {EncoderCliente.class}, decoders= {DecoderCliente.class})
public class EditClienteServer {
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
    public void mensaje(Cliente cli, String inst) throws SQLException{
        DBCliente cliDB = new DBCliente();
        switch(Integer.parseInt(inst)){
            case 1:
                cliDB.modifyCliente(cli);
                break;
            case 2:
                cliDB.insertarCliente(cli);
                break;
            case 3:
                cliDB.eliminarCliente(cli.getId_cliente());
                break;
        }
    }
    
}
