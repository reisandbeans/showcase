package reis.polyglot.vertx.server;

import io.vertx.core.http.HttpServerOptions;
import io.vertx.rxjava3.core.Vertx;

public class HttpServer {
    private io.vertx.rxjava3.core.http.HttpServer server;
    private final Vertx vertx;
    public HttpServer(Vertx vertx) {
        this.vertx = vertx;
    }

    public void startServer() {
        HttpServerOptions options = new HttpServerOptions();
        this.server = this.vertx.createHttpServer();
    }
}
