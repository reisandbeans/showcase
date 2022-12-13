package reis.polyglot.vertx.server;

import io.vertx.rxjava3.ext.web.Router;
import reis.polyglot.vertx.server.resources.user.UserRouter;

public class ApiRoutes {
    private final UserRouter userRouter;
    public ApiRoutes(UserRouter userRouter) {
        this.userRouter = userRouter;
    }

    public void mount(Router router) {
        router.mountSubRouter("/users/", this.userRouter.getHandler());
    }
}
