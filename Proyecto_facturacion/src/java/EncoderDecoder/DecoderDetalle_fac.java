package EncoderDecoder;

import java.io.IOException;
import java.io.Reader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import logica.Detalle_fac;

/**
 *
 * @author Camilo Garcia
 */
public class DecoderDetalle_fac implements Decoder.TextStream<Detalle_fac>{

    @Override
    public Detalle_fac decode(Reader reader) throws DecodeException, IOException {
        Detalle_fac detalle_fac = new Detalle_fac();
        try(JsonReader jsonReader = Json.createReader(reader)){
            JsonObject json = jsonReader.readObject();
            detalle_fac.setId_detalle_fac(Integer.parseInt(json.getString("id_detalle_fac")));
            detalle_fac.setCantidad(Integer.parseInt(json.getString("cantidad")));
            detalle_fac.setTotal(Double.parseDouble(json.getString("total")));
            detalle_fac.setDescuento(Float.parseFloat(json.getString("descuento")));
            detalle_fac.setVal_descuento(Double.parseDouble(json.getString("val_descuento")));
            detalle_fac.setId_factura(Integer.parseInt(json.getString("id_factura")));
            detalle_fac.setId_articulo(Integer.parseInt(json.getString("id_articulo")));
        }
        return detalle_fac;
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
    
}
