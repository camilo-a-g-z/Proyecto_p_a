package EncoderDecoder;

import java.io.IOException;
import java.io.Writer;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import logica.Ciudad;

/**
 *
 * @author Camilo Garcia
 */
public class EncoderCiudad implements Encoder.TextStream<Ciudad>{

    @Override
    public void encode(Ciudad object, Writer writer) throws EncodeException, IOException {
        JsonObject json = Json.createObjectBuilder()
                .add("id_ciudad", object.getId_ciudad())
                .add("nombre", object.getId_ciudad())
                .build();
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
    
}
