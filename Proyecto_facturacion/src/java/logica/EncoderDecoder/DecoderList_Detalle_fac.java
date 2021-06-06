package logica.EncoderDecoder;

import java.io.IOException;
import java.io.Reader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import logica.List_Detalle_fac;

/**
 *
 * @author Camilo Garcia
 */
public class DecoderList_Detalle_fac implements Decoder.TextStream<List_Detalle_fac>{

    @Override
    public List_Detalle_fac decode(Reader reader) throws DecodeException, IOException {
        List_Detalle_fac list = new List_Detalle_fac();
        try(JsonReader jsonReader = Json.createReader(reader)){
            JsonObject json = jsonReader.readObject();
            //list.setDetalle_fac(json.getJsonArray("list"));
        }
        return list;
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
    
}
