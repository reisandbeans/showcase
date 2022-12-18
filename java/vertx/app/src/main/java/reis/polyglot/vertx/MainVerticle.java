package reis.polyglot.vertx;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.reactivex.rxjava3.core.Completable;
import io.vertx.rxjava3.core.AbstractVerticle;
import reis.polyglot.vertx.server.HttpServer;
import reis.polyglot.vertx.server.config.ConfigLoader;

public class MainVerticle extends AbstractVerticle {
    @Override
    public Completable rxStart() {
        return ConfigLoader.loadConfig(vertx)
            .flatMapCompletable(config -> {
                Injector injector = Guice.createInjector(new MainModule(vertx, config));
                HttpServer server = injector.getInstance(HttpServer.class);
                return server.start();
            });
    }
}
