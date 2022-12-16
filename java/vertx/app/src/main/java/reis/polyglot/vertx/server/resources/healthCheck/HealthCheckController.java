package reis.polyglot.vertx.server.resources.healthCheck;

import io.vertx.rxjava3.ext.web.RoutingContext;
import reis.polyglot.lib.apiResponses.SuccessResponse;
import reis.polyglot.vertx.server.lib.ResponseHandler;

public class HealthCheckController {
    public void ping(RoutingContext context) {
        ResponseHandler.send(context, SuccessResponse.empty());
    }
}
