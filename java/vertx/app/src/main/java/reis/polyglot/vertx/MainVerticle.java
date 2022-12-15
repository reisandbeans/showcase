package reis.polyglot.vertx;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.reactivex.rxjava3.core.Completable;
import io.vertx.rxjava3.core.AbstractVerticle;
import reis.polyglot.vertx.server.HttpServer;

public class MainVerticle extends AbstractVerticle {
    @Override
    public Completable rxStart() {
        Injector injector = Guice.createInjector(new MainModule(vertx));
        HttpServer server = injector.getInstance(HttpServer.class);
        return server.start();
    }
}
