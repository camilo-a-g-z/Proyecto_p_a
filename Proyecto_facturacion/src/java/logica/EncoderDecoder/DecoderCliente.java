package logica.EncoderDecoder;

import java.io.IOException;
import java.io.Reader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import logica.Cliente;

/**
 *
 * @author Camilo Garcia
 */
public class DecoderCliente implements Decoder.TextStream<Cliente>{

    @Override
    public Cliente decode(Reader reader) throws DecodeException, IOException {
        Cliente cliente = new Cliente();
        try(JsonReader jsonReader = Json.createReader(reader)){
            JsonObject json = jsonReader.readObject();
            cliente.setId_cliente(Integer.parseInt(json.getString("id_cliente")));
            cliente.setNombre(json.getString("nombre"));
            cliente.setApellido(json.getString("apellido"));
            cliente.setCorreo(json.getString("correo"));
            cliente.setDireccion(json.getString("direccion"));
            cliente.setCelular(Double.parseDouble(json.getString("celular")));
            cliente.setCedula(json.getString("cedula"));
            cliente.setId_ciudad(Integer.parseInt(json.getString("id_ciudad")));
            cliente.setPassword(json.getString("password"));
            cliente.setMensaje(json.getString("mensaje"));
        }
        return cliente;
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
    
}
