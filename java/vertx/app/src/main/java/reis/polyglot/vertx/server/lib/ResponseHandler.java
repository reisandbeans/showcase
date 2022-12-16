package reis.polyglot.vertx.server.lib;

import io.vertx.core.json.Json;
import io.vertx.rxjava3.ext.web.RoutingContext;
import reis.polyglot.lib.apiResponses.ApiResponse;

public class ResponseHandler {
    public static void send(RoutingContext context, ApiResponse response) {
        context.response()
            .setStatusCode(response.getStatusCode())
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(response.toJson()));
    }
}
