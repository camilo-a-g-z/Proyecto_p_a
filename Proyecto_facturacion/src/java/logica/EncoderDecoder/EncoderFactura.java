package logica.EncoderDecoder;

import java.io.IOException;
import java.io.Writer;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import logica.Factura;

/**
 *
 * @author Camilo Garcia
 */
public class EncoderFactura implements Encoder.TextStream<Factura>{

    @Override
    public void encode(Factura object, Writer writer) throws EncodeException, IOException {
        JsonObject json = Json.createObjectBuilder()
                .add("id_factura", object.getId_factura())
                .add("fecha_fac", object.getFecha_fac())
                .add("val_iva", object.getVal_iva())
                .add("val_sub_total", object.getVal_sub_total())
                .add("total", object.getTotal())
                .add("id_cliente", object.getId_cliente())
                .add("id_metodo_pago", object.getId_metodo_pago())
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
