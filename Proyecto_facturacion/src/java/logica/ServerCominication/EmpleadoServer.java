package logica.ServerCominication;

import datos.DBEmpleado;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import logica.Empleado;
import logica.EncoderDecoder.DecoderEmpleado;
import logica.EncoderDecoder.EncoderEmpleado;

/**
 *
 * @author Camilo Garcia
 */
@ServerEndpoint(value="/empleado", encoders = {EncoderEmpleado.class}, decoders = {DecoderEmpleado.class})
public class EmpleadoServer {
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
        DBEmpleado empDB = new DBEmpleado();
        Empleado emp = new Empleado();
        try{
            ResultSet res = empDB.geEmpleadoByNombre(mens);
            if(!res.next()){
                emp.setNombre("NE");
                conectados.get(i).getBasicRemote().sendObject(emp);
            }else{
                emp.setNombre(res.getString("nombre"));
                emp.setCedula(res.getString("cedula"));
                emp.setId_ciudad(Integer.parseInt(res.getString("id_ciudad")));
                emp.setId_empleado(Integer.parseInt(res.getString("id_empleado")));
                emp.setPassword(res.getString("password"));
                conectados.get(i).getBasicRemote().sendObject(emp);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
