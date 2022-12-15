package reis.polyglot.vertx.server.resources.user;

import com.google.inject.Inject;
import io.vertx.rxjava3.ext.web.RoutingContext;
import reis.polyglot.lib.apiResponses.SuccessResponse;
import reis.polyglot.vertx.server.ResponseHandler;

public class UserController {
    @Inject
    public UserController() {}

    public void login(RoutingContext context) {
        ResponseHandler.send(context, SuccessResponse.empty());
    }
}
