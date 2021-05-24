package EncoderDecoder;

import java.io.IOException;
import java.io.Writer;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import logica.Metodo_pago;

/**
 *
 * @author Camilo Garcia
 */
public class EncoderMetodo_pago implements Encoder.TextStream<Metodo_pago>{

    @Override
    public void encode(Metodo_pago object, Writer writer) throws EncodeException, IOException {
        JsonObject json = Json.createObjectBuilder()
                .add("id_metodo_pago", object.getId_metodo_pago())
                .add("tipo", object.getTipo())
                .build();
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
    
}
