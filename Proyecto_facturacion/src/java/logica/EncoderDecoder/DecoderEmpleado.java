package logica.EncoderDecoder;

import java.io.IOException;
import java.io.Reader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import logica.Empleado;

/**
 *
 * @author Camilo Garcia
 */
public class DecoderEmpleado implements Decoder.TextStream<Empleado>{

    @Override
    public Empleado decode(Reader reader) throws DecodeException, IOException {
        Empleado empleado = new Empleado();
        try(JsonReader jsonReader = Json.createReader(reader)){
            JsonObject json = jsonReader.readObject();
            empleado.setId_empleado(Integer.parseInt(json.getString("id_empleado")));
            empleado.setNombre(json.getString("nombre"));
            empleado.setCedula(json.getString("cedula"));
            empleado.setId_ciudad(Integer.parseInt(json.getString("id_ciudad")));
            empleado.setPassword(json.getString("password"));
            empleado.setMensaje(json.getString("mensaje"));
        }
        return empleado;
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
    
}
