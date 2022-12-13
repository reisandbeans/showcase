package reis.polyglot.vertx.server.resources.user;

import io.vertx.rxjava3.core.Vertx;
import io.vertx.rxjava3.ext.web.Router;
import reis.polyglot.vertx.server.ResourceHandler;

public class UserRouter implements ResourceHandler {
    private final Router handler;
    private final UserController controller;

    public UserRouter(Vertx vertx, UserController controller) {
        this.handler = createHandler(vertx);
        this.controller = controller;
    }

    @Override
    public Router getHandler() {
        return handler;
    }

    private Router createHandler(Vertx vertx) {
        Router router = Router.router(vertx);

        router.post("/login").handler(controller::login);

        return router;
    }
}
