package reis.polyglot.vertx.server;

import io.vertx.rxjava3.core.Vertx;
import io.vertx.rxjava3.ext.web.Router;
import io.vertx.rxjava3.ext.web.handler.BodyHandler;
import io.vertx.rxjava3.ext.web.handler.StaticHandler;

import javax.inject.Inject;

public class Application {
    private static final String assetsPath = "assets";

    private final Vertx vertx;
    private final ApiRoutes apiRoutes;

    @Inject
    public Application(Vertx vertx, ApiRoutes apiRoutes) {
        this.vertx = vertx;
        this.apiRoutes = apiRoutes;
    }

    public Router create() {
        ApiErrorHandler errorHandler = new ApiErrorHandler();
        StaticHandler staticHandler = StaticHandler.create(Application.assetsPath);

        Router mainRouter = Router.router(vertx);
        Router apiRouter = Router.router(vertx);
        apiRouter.route().handler(BodyHandler.create());
        apiRouter.route().failureHandler(errorHandler::handleError);

        this.apiRoutes.mount(apiRouter);

        mainRouter.route("/api/*").subRouter(apiRouter);
        mainRouter.route("/*").handler(staticHandler);
        mainRouter.get().handler(routingContext -> routingContext.response().sendFile("assets/index.html"));

        return mainRouter;
    }
}
