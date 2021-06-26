package logica.EncoderDecoder;

import java.io.IOException;
import java.io.Reader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import logica.Metodo_pago;

/**
 *
 * @author Camilo Garcia
 */
public class DecoderMetodo_pago implements Decoder.TextStream<Metodo_pago>{

    @Override
    public Metodo_pago decode(Reader reader) throws DecodeException, IOException {
        Metodo_pago metodo_pago = new Metodo_pago();
        try(JsonReader jsonReader = Json.createReader(reader)){
            JsonObject json = jsonReader.readObject();
            metodo_pago.setId_metodo_pago(Integer.parseInt(json.getString("id_metodo_pago")));
            metodo_pago.setTipo(json.getString("tipo"));
            metodo_pago.setMensaje(json.getString("mensaje"));
        }
        return metodo_pago;
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
    
}
