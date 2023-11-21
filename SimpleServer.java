import java.io.IOException;
import java.io.OutputStream;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.net.InetSocketAddress;

public class SimpleServer {
    public static void main(String[] args) throws Exception {
        // Create a simple HTTP server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8082), 0);
        
        // Create a basic handler for incoming requests
        server.createContext("/", new MyHandler());
        
        // Start the server
        server.start();
        
        System.out.println("Server is running on port 8082");
    }
    
    // Handler class to handle incoming requests
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Hello! This is a simple website.";

            // Set the response headers
            exchange.getResponseHeaders().set("Content-Type", "text/plain");
            exchange.sendResponseHeaders(200, response.length());

            // Get the output stream and write the response
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
