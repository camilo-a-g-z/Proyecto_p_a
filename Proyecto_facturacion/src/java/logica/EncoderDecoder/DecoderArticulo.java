package logica.EncoderDecoder;

import java.io.IOException;
import java.io.Reader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import logica.Articulo;

/**
 *
 * @author Camilo Garcia
 */
public class DecoderArticulo implements Decoder.TextStream<Articulo>{

    @Override
    public Articulo decode(Reader reader) throws DecodeException, IOException {
        Articulo articulo = new Articulo();
        try(JsonReader jsonReader = Json.createReader(reader)){
            JsonObject json = jsonReader.readObject();
            articulo.setId_articulo(Integer.parseInt(json.getString("id_articulo")));
            articulo.setNombre(json.getString("nombre"));
            articulo.setCant_stock(Double.parseDouble(json.getString("cant_stock")));
            articulo.setDescripcion(json.getString("descripcion"));
            articulo.setId_categoria(Integer.parseInt(json.getString("id_categoria")));
        }
        return articulo;
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
    
}
