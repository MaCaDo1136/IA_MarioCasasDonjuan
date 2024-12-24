package ia_mariocasasdonjuan.resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class ApiHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Permitir solicitudes desde cualquier origen
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");

        // Manejar preflight request (OPTIONS)
        if ("OPTIONS".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(200, -1);
            return;
        }

        // Leer el método de la solicitud
        String method = exchange.getRequestMethod();

        if ("POST".equals(method) && exchange.getRequestURI().getPath().equals("/api/medicine")) {
            handlePostRequest(exchange);
        } else {
            // Si el método no es POST o la ruta es diferente, enviar un error 404
            exchange.sendResponseHeaders(404, -1);
        }
    }

    private void handlePostRequest(HttpExchange exchange) throws IOException {
        // Leer los datos enviados en la solicitud (el cuerpo de la solicitud)
        InputStream requestBody = exchange.getRequestBody();
        String requestData = new String(requestBody.readAllBytes(), StandardCharsets.UTF_8);

        // Aquí puedes procesar los datos recibidos (por ejemplo, convertir el JSON en un objeto)
        System.out.println("Datos recibidos: " + requestData);

        // Responder con un JSON (puedes personalizar el objeto de respuesta según tus necesidades)
        String response = "{\"message\": \"Datos recibidos correctamente\", \"status\": \"success\"}";

        // Establecer las cabeceras de la respuesta
        exchange.getResponseHeaders().set("Content-Type", "application/json");

        // Establecer el código de estado (200 OK)
        exchange.sendResponseHeaders(200, response.getBytes().length);

        // Enviar la respuesta al cliente
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
