package reis.polyglot.vertx;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.reactivex.rxjava3.core.Completable;
import io.vertx.rxjava3.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reis.polyglot.vertx.server.HttpServer;
import reis.polyglot.vertx.server.config.ConfigLoader;

public class MainVerticle extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(MainVerticle.class);
    private Injector injector;

    @Override
    public Completable rxStart() {
        logger.info("Starting main verticle...");

        return ConfigLoader.loadConfig(vertx)
            .flatMapCompletable(config -> {
                injector = Guice.createInjector(new MainModule(vertx, config));
                HttpServer server = injector.getInstance(HttpServer.class);
                return server.start();
            })
            .doOnComplete(() -> logger.info("Main verticle successfully started"));
    }

    @Override
    public Completable rxStop() {
        logger.info("Stopping main verticle...");
        HttpServer server = injector.getInstance(HttpServer.class);
        return server.stop().doOnComplete(() -> logger.info("Main verticle successfully stopped"));
    }
}
