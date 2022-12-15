package reis.polyglot.vertx.server;

import com.google.inject.Inject;
import io.vertx.rxjava3.ext.web.Router;
import reis.polyglot.lib.apiResponses.ErrorResponse;
import reis.polyglot.vertx.server.resources.user.UserRouter;

public class ApiRoutes {
    private final UserRouter userRouter;

    @Inject
    public ApiRoutes(UserRouter userRouter) {
        this.userRouter = userRouter;
    }

    public void mount(Router router) {
        router.route("/users/*").subRouter(this.userRouter.getHandler());

        router.route("/*").handler(routingContext -> ResponseHandler.send(routingContext, ErrorResponse.notFound()));
    }
}
