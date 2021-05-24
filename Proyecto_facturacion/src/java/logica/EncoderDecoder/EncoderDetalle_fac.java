package logica.EncoderDecoder;

import java.io.IOException;
import java.io.Writer;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import logica.Detalle_fac;

/**
 *
 * @author Camilo Garcia
 */
public class EncoderDetalle_fac implements Encoder.TextStream<Detalle_fac>{

    @Override
    public void encode(Detalle_fac object, Writer writer) throws EncodeException, IOException {
        JsonObject json = Json.createObjectBuilder()
                .add("id_detalle_fac", object.getId_detalle_fac())
                .add("cantidad", object.getCantidad())
                .add("total", object.getTotal())
                .add("descuento", object.getDescuento())
                .add("val_descuento", object.getVal_descuento())
                .add("id_factura", object.getId_factura())
                .add("id_articulo", object.getId_articulo())
                .build();
        try (JsonWriter jsonWriter = Json.createWriter(writer)){
            jsonWriter.writeObject(json);
        }
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
    
}
