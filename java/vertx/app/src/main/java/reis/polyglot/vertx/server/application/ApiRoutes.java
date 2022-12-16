package reis.polyglot.vertx.server.application;

import com.google.inject.Inject;
import io.vertx.rxjava3.ext.web.Router;
import reis.polyglot.lib.apiResponses.ErrorResponse;
import reis.polyglot.vertx.server.lib.ResponseHandler;
import reis.polyglot.vertx.server.resources.healthCheck.HealthCheckRouter;
import reis.polyglot.vertx.server.resources.user.UserRouter;

public class ApiRoutes {
    private final HealthCheckRouter healthCheckRouter;
    private final UserRouter userRouter;

    @Inject
    public ApiRoutes(
        HealthCheckRouter healthCheckRouter,
        UserRouter userRouter
    ) {
        this.healthCheckRouter = healthCheckRouter;
        this.userRouter = userRouter;
    }

    public void mount(Router router) {
        router.route("/healthcheck/*").subRouter(this.healthCheckRouter.getHandler());
        router.route("/users/*").subRouter(this.userRouter.getHandler());

        router.route("/*").handler(routingContext -> ResponseHandler.send(routingContext, ErrorResponse.notFound()));
    }
}
