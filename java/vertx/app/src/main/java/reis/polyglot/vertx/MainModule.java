package reis.polyglot.vertx;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import io.vertx.rxjava3.core.Vertx;
import reis.polyglot.vertx.server.ApiRoutes;
import reis.polyglot.vertx.server.Application;
import reis.polyglot.vertx.server.HttpServer;
import reis.polyglot.vertx.server.resources.user.UserController;
import reis.polyglot.vertx.server.resources.user.UserRouter;

public class MainModule extends AbstractModule {
    private final Vertx vertx;

    public MainModule(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    protected void configure() {
        bind(Vertx.class).toInstance(this.vertx);

        bind(HttpServer.class).in(Singleton.class);
        bind(Application.class).in(Singleton.class);
        bind(ApiRoutes.class).in(Singleton.class);
        bind(UserController.class).in(Singleton.class);
        bind(UserRouter.class).in(Singleton.class);
    }
}
