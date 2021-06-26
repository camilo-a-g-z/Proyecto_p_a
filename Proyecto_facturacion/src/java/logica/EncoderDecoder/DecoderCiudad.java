package logica.EncoderDecoder;

import java.io.IOException;
import java.io.Reader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import logica.Ciudad;

/**
 *
 * @author Camilo Garcia
 */
public class DecoderCiudad implements Decoder.TextStream<Ciudad>{

    @Override
    public Ciudad decode(Reader reader) throws DecodeException, IOException {
        Ciudad ciudad = new Ciudad();
        try(JsonReader jsonReader = Json.createReader(reader)){
            JsonObject json = jsonReader.readObject();
            ciudad.setId_ciudad(Integer.parseInt("id_ciudad"));
            ciudad.setNombre(json.getString("nombre"));
            ciudad.setMensaje(json.getString("mensaje"));
        }
        return ciudad;
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
    
}
