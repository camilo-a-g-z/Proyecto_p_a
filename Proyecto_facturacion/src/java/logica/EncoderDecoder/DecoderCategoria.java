package logica.EncoderDecoder;

import java.io.IOException;
import java.io.Reader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import logica.Categoria;

/**
 * @author Camilo Garcia
 */
public class DecoderCategoria implements Decoder.TextStream<Categoria>{

    @Override
    public Categoria decode(Reader reader) throws DecodeException, IOException {
        Categoria categoria = new Categoria();
        try(JsonReader jsonReader = Json.createReader(reader)){
            JsonObject json = jsonReader.readObject();
            categoria.setId_categoria(Integer.parseInt(json.getString("id_categoria")));
            categoria.setNombre(json.getString("nombre"));
            categoria.setDescripcion(json.getString("descripcion"));
            categoria.setMensaje(json.getString("mensaje"));
        }
        return categoria;
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
    
}
