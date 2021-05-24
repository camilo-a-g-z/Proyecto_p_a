package EncoderDecoder;

import java.io.IOException;
import java.io.Writer;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import logica.Articulo;

/**
 *
 * @author Camilo Garcia
 */
public class EncoderArticulo implements Encoder.TextStream<Articulo>{

    @Override
    public void encode(Articulo object, Writer writer) throws EncodeException, IOException {
        JsonObject json = Json.createObjectBuilder()
                .add("id_articulo", object.getId_articulo())
                .add("nombre", object.getNombre())
                .add("cant_stock", object.getCant_stock())
                .add("descripcion", object.getDescripcion())
                .add("id_categoria", object.getId_categoria())
                .build();
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
    
}
