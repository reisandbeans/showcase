package reis.polyglot.vertx.server.resources.healthCheck;

import com.google.inject.Inject;
import io.vertx.rxjava3.core.Vertx;
import io.vertx.rxjava3.ext.web.Router;
import reis.polyglot.vertx.server.lib.ResourceHandler;

public class HealthCheckRouter implements ResourceHandler {
    private final Router handler;
    private final HealthCheckController controller;

    @Inject
    public HealthCheckRouter(Vertx vertx, HealthCheckController controller) {
        this.controller = controller;
        this.handler = createHandler(vertx);
    }

    @Override
    public Router getHandler() {
        return handler;
    }

    private Router createHandler(Vertx vertx) {
        Router router = Router.router(vertx);
        router.post("/ping").handler(controller::ping);

        return router;
    }
}
