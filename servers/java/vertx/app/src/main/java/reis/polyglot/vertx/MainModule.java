package reis.polyglot.vertx;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import io.vertx.rxjava3.core.Vertx;
import reis.polyglot.vertx.server.HttpServer;
import reis.polyglot.vertx.server.application.ApiRoutes;
import reis.polyglot.vertx.server.application.Application;
import reis.polyglot.vertx.server.config.ServerConfig;
import reis.polyglot.vertx.server.resources.healthcheck.HealthCheckController;
import reis.polyglot.vertx.server.resources.healthcheck.HealthCheckRouter;
import reis.polyglot.vertx.server.resources.user.UserController;
import reis.polyglot.vertx.server.resources.user.UserRouter;

public class MainModule extends AbstractModule {
    private final Vertx vertx;
    private final ServerConfig config;

    public MainModule(Vertx vertx, ServerConfig config) {
        this.config = config;
        this.vertx = vertx;
    }

    @Override
    protected void configure() {
        bind(Vertx.class).toInstance(this.vertx);

        bind(ServerConfig.class).toInstance(this.config);

        bind(HttpServer.class).in(Singleton.class);
        bind(Application.class).in(Singleton.class);
        bind(ApiRoutes.class).in(Singleton.class);
        bind(HealthCheckRouter.class).in(Singleton.class);
        bind(HealthCheckController.class).in(Singleton.class);
        bind(UserController.class).in(Singleton.class);
        bind(UserRouter.class).in(Singleton.class);
    }
}
