package logica.EncoderDecoder;

import java.io.IOException;
import java.io.Writer;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import logica.Cliente;

/**
 *
 * @author Camilo Garcia
 */
public class EncoderCliente implements Encoder.TextStream<Cliente>{

    @Override
    public void encode(Cliente object, Writer writer) throws EncodeException, IOException {
        JsonObject json = Json.createObjectBuilder()
                .add("id_cliente", object.getId_cliente())
                .add("nombre", object.getNombre())
                .add("apellido", object.getApellido())
                .add("correo", object.getCorreo())
                .add("direccion", object.getDireccion())
                .add("celular", object.getCelular())
                .add("cedula", object.getCedula())
                .add("id_ciudad", object.getId_ciudad())
                .add("password", object.getPassword())
                .add("mensaje", object.getMensaje())
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
