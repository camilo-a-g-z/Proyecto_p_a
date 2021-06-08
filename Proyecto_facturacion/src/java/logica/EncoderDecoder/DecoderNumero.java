package logica.EncoderDecoder;

import java.io.IOException;
import java.io.Reader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author Camilo Garcia
 */
public class DecoderNumero implements Decoder.TextStream<Double>{

    @Override
    public Double decode(Reader reader) throws DecodeException, IOException {
        Double id;
        try(JsonReader jsonReader = Json.createReader(reader)){
            JsonObject json = jsonReader.readObject();
            id = Double.parseDouble(json.getString("id"));
        }
        return id;
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
    
}
