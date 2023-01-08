package reis.polyglot.vertx.lib;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import io.vertx.rxjava3.core.Vertx;
import org.mockito.Mockito;
import reis.polyglot.vertx.server.HttpServer;
import reis.polyglot.vertx.server.application.ApiRoutes;
import reis.polyglot.vertx.server.application.Application;
import reis.polyglot.vertx.server.config.ServerConfig;
import reis.polyglot.vertx.server.resources.healthcheck.HealthCheckController;
import reis.polyglot.vertx.server.resources.healthcheck.HealthCheckRouter;
import reis.polyglot.vertx.server.resources.user.UserController;
import reis.polyglot.vertx.server.resources.user.UserRouter;

public class MockModule extends AbstractModule {
    public MockServerConfig config = new MockServerConfig();
    public HttpServer server = Mockito.mock(HttpServer.class);
    public Application application = Mockito.mock(Application.class);
    public ApiRoutes apiRoutes = Mockito.mock(ApiRoutes.class);
    public HealthCheckRouter healthCheckRouter = Mockito.mock(HealthCheckRouter.class);
    public HealthCheckController healthCheckController = Mockito.mock(HealthCheckController.class);
    public UserController userController = Mockito.mock(UserController.class);
    public UserRouter userRouter = Mockito.mock(UserRouter.class);
    public Vertx vertx = Vertx.vertx();

    @Override
    protected void configure() {
        bind(Vertx.class).toInstance(this.vertx);
        bind(ServerConfig.class).toInstance(this.config);
        bind(HttpServer.class).toInstance(this.server);
        bind(Application.class).toInstance(this.application);
        bind(ApiRoutes.class).toInstance(this.apiRoutes);
        bind(HealthCheckRouter.class).toInstance(this.healthCheckRouter);
        bind(HealthCheckController.class).toInstance(this.healthCheckController);
        bind(UserController.class).toInstance(this.userController);
        bind(UserRouter.class).toInstance(this.userRouter);
    }
}
