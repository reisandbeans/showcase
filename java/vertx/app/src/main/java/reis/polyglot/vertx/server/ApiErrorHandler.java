package reis.polyglot.vertx.server;

import io.vertx.rxjava3.ext.web.RoutingContext;
import reis.polyglot.lib.apiResponses.ErrorResponse;
import reis.polyglot.lib.exceptions.ApplicationException;

public class ApiErrorHandler {
    void handleError(RoutingContext routingContext) {
        Throwable error = routingContext.failure();
        ApplicationException exception = ApplicationException.fromThrowable(error);
        ErrorResponse response = ErrorResponse.fromException(exception);
        ResponseHandler.send(routingContext, response);
    }
}
