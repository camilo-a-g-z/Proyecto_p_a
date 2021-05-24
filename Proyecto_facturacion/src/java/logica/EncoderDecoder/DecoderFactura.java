package logica.EncoderDecoder;

import java.io.IOException;
import java.io.Reader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import logica.Factura;

/**
 *
 * @author Camilo Garcia
 */
public class DecoderFactura implements Decoder.TextStream<Factura>{

    @Override
    public Factura decode(Reader reader) throws DecodeException, IOException {
        Factura factura = new Factura();
        try(JsonReader jsonReader = Json.createReader(reader)){
            JsonObject json = jsonReader.readObject();
            factura.setId_factura(Integer.parseInt(json.getString("id_factura")));
            factura.setFecha_fac(json.getString("fecha_fac"));
            factura.setVal_iva(Double.parseDouble(json.getString("val_iva")));
            factura.setVal_sub_total(Double.parseDouble(json.getString("val_sub_total")));
            factura.setTotal(Double.parseDouble(json.getString("total")));
            factura.setId_cliente(Integer.parseInt(json.getString("id_cliente")));
            factura.setId_metodo_pago(Integer.parseInt(json.getString("id_metodo_pago")));
        }
        return factura;
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
    
}
