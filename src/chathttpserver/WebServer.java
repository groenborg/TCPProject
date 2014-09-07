package chathttpserver;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;

/**
 *
 * @author Grønborg
 */
public class WebServer {

    private HttpServer server;
    private static WebServer instance = new WebServer();

    private WebServer() {
    }

    public static WebServer getInstance() {
        if (instance == null) {
            instance = new WebServer();
        }
        return instance;
    }

    public void startServer() {
        try {
            server = HttpServer.create(new InetSocketAddress("10.0.1.11", 8028), 0);
            server.createContext("/", new DefaultHandler());
            System.out.println("Starting webserver");
            server.setExecutor(null);
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void closeHttpServer() {
        this.server.stop(5);
        System.out.println("webserver closed");
    }

    static class DefaultHandler implements com.sun.net.httpserver.HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {
            System.out.println("Http request reciewed");
            String response = "if you see this.. something went horribly wrong";

            Headers header = he.getResponseHeaders();
            header.add("content-type", "text/html");
            he.sendResponseHeaders(200, response.length());

            try (OutputStreamWriter out = new OutputStreamWriter(he.getResponseBody())) {
                out.write(response);
            }

        }
    }

}
