package reis.polyglot.vertx.server.application;

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

        Router apiRouter = Router.router(vertx);
        apiRouter.route().handler(BodyHandler.create());
        apiRouter.route().failureHandler(errorHandler::handleError);

        this.apiRoutes.mount(apiRouter);

        StaticHandler staticHandler = StaticHandler.create(Application.assetsPath);
        Router mainRouter = Router.router(vertx);
        mainRouter.route("/api/v1/*").subRouter(apiRouter);
        mainRouter.route("/*").handler(staticHandler);
        mainRouter.get().handler(routingContext -> routingContext.response().sendFile("assets/index.html"));

        return mainRouter;
    }
}
