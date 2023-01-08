package reis.polyglot.vertx.server;

import com.google.inject.Inject;
import io.reactivex.rxjava3.core.Completable;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.rxjava3.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reis.polyglot.vertx.server.application.Application;
import reis.polyglot.vertx.server.config.ServerConfig;

public class HttpServer {
    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);

    private io.vertx.rxjava3.core.http.HttpServer server;
    private final Application application;
    private final int port;
    private final Vertx vertx;

    @Inject
    public HttpServer(Vertx vertx, Application application, ServerConfig config) {
        this.vertx = vertx;
        this.port = config.getHttpServerPort();
        this.application = application;
    }

    public Completable start() {
        HttpServerOptions options = new HttpServerOptions();
        this.server = this.vertx.createHttpServer(options);

        return this.server
            .requestHandler(application.create())
            .rxListen(port)
            .doOnSuccess((res) -> {
                logger.info("Server listening on port {}", port);
            })
            .ignoreElement();
    }

    public Completable stop() {
        logger.info("Stopping http server...");
        return this.server.close().doOnComplete(() -> logger.info("Server successfully stopped"));
    }
}
