package reis.polyglot.vertx.server;

import com.google.inject.Inject;
import io.reactivex.rxjava3.core.Completable;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.rxjava3.core.Vertx;

public class HttpServer {
    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);

    private io.vertx.rxjava3.core.http.HttpServer server;
    private final Application application;
    private final Vertx vertx;

    @Inject
    public HttpServer(Vertx vertx, Application application) {
        this.vertx = vertx;
        this.application = application;
    }

    public Completable start() {
        HttpServerOptions options = new HttpServerOptions();
        this.server = this.vertx.createHttpServer(options);

        return this.server
            .requestHandler(application.create())
            .rxListen(8080)
            .doOnSuccess((res) -> {
                logger.info("Server listening on port 8080");
            })
            .ignoreElement();
    }

    public void stop() {
        logger.info("Closing Http Server");
        this.server.close();
    }
}
