/*
 * Written by Mario Casas
 */
package ia_mariocasasdonjuan;

import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import ia_mariocasasdonjuan.resources.ApiHandler;

public class App {
    public static void main(String[] args) throws IOException {
        // Iniciar servidor HTTP básico
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api", new ApiHandler()); // Endpoint para la API AQUI ESTA LA DIRECCION DE LA API
        server.setExecutor(null); // Usar el executor por defecto
        server.start();
        System.out.println("Servidor corriendo en http://localhost:8080/api");
    }
}
