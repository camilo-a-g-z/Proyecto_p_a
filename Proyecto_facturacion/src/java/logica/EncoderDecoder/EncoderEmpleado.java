package logica.EncoderDecoder;

import java.io.IOException;
import java.io.Writer;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import logica.Empleado;

/**
 *
 * @author Camilo Garcia
 */
public class EncoderEmpleado implements Encoder.TextStream<Empleado>{

    @Override
    public void encode(Empleado object, Writer writer) throws EncodeException, IOException {
        JsonObject json = Json.createObjectBuilder()
                .add("id_empleado", object.getId_empleado())
                .add("nombre", object.getNombre())
                .add("cedula", object.getCedula())
                .add("id_ciudad", object.getId_ciudad())
                .add("password", object.getPassword())
                .build();
        try (JsonWriter jsonWriter = Json.createWriter(writer)){
            jsonWriter.writeObject(json);
        }
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
    
}
