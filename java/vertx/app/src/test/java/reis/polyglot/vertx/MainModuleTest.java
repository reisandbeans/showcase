package reis.polyglot.vertx;

import static org.junit.jupiter.api.Assertions.*;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.vertx.rxjava3.core.Vertx;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reis.polyglot.vertx.server.HttpServer;
import reis.polyglot.vertx.server.application.ApiRoutes;
import reis.polyglot.vertx.server.application.Application;
import reis.polyglot.vertx.server.config.ServerConfig;
import reis.polyglot.vertx.server.resources.healthcheck.HealthCheckController;
import reis.polyglot.vertx.server.resources.healthcheck.HealthCheckRouter;
import reis.polyglot.vertx.server.resources.user.UserController;
import reis.polyglot.vertx.server.resources.user.UserRouter;

public class MainModuleTest {
    @Test
    @DisplayName("Should create an injector with the expected dependencies")
    public void testMainModule() {
        Vertx vertx = Vertx.vertx();
        ServerConfig config = Mockito.mock(ServerConfig.class);

        Injector injector = Guice.createInjector(new MainModule(vertx, config));
        assertEquals(injector.getInstance(Vertx.class), vertx);
        assertEquals(injector.getInstance(ServerConfig.class), config);
        assertInstanceOf(HttpServer.class, injector.getInstance(HttpServer.class));
        assertInstanceOf(Application.class, injector.getInstance(Application.class));
        assertInstanceOf(ApiRoutes.class, injector.getInstance(ApiRoutes.class));
        assertInstanceOf(HealthCheckRouter.class, injector.getInstance(HealthCheckRouter.class));
        assertInstanceOf(HealthCheckController.class, injector.getInstance(HealthCheckController.class));
        assertInstanceOf(UserController.class, injector.getInstance(UserController.class));
        assertInstanceOf(UserRouter.class, injector.getInstance(UserRouter.class));
    }
}
