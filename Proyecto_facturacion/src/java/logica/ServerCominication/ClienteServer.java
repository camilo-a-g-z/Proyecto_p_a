package logica.ServerCominication;

import datos.DBCliente;
import datos.DBMetodo_pago;
import java.sql.ResultSet;
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
import logica.Metodo_pago;

/**
 *
 * @author Camilo Garcia
 */
@ServerEndpoint(value="/cliente", encoders = {EncoderCliente.class}, decoders = {DecoderCliente.class})
public class ClienteServer {
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
        DBCliente cliDB = new DBCliente();
        Cliente cli = new Cliente();
        try{
            ResultSet res = cliDB.getClienteByNombre(mens);
            if(!res.next()){
                cli.setNombre("NE");
                conectados.get(i).getBasicRemote().sendObject(cli);
            }else{
                cli.setApellido(res.getString("apellido"));
                cli.setCedula(res.getString("cedula"));
                cli.setCelular(Double.parseDouble(res.getString("celular")));
                cli.setCorreo(res.getString("correo"));
                cli.setDireccion(res.getString("direccion"));
                cli.setId_ciudad(Integer.parseInt(res.getString("id_ciudad")));
                cli.setId_cliente(Integer.parseInt(res.getString("id_cliente")));
                cli.setNombre(res.getString("nombre"));
                cli.setPassword(res.getString("password"));
                conectados.get(i).getBasicRemote().sendObject(cli);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
}
